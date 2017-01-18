package com.zfwl.mvp.login;

import android.content.Context;

import com.zfwl.common.Const.WeChat;
import com.zfwl.common.MyLog;
import com.zfwl.data.UserInfoManager;
import com.zfwl.data.api.LoginApi;
import com.zfwl.data.api.retrofit.ApiModule;
import com.zfwl.data.sp.GlobalPref;
import com.zfwl.data.sp.WeChatPref;
import com.zfwl.entity.User;
import com.zfwl.entity.WeChatTokenResult;
import com.zfwl.entity.WechatUser;
import com.zfwl.mvp.BasePresenter;
import com.zfwl.push.PushConfig;
import com.zfwl.util.GsonUtils;
import com.zfwl.util.StringUtils;
import com.zzb.easysp.generated.EasySPWeChatPref;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by ZZB on 2016/12/7.
 */

public class LoginPresenter extends BasePresenter<LoginMvpView> {
    private static final String TAG = "LoginPresenter";
    private LoginApi mLoginApi;
    private WeChatPref mWeChatPref;
    private Context mContext;

    public LoginPresenter(Context context) {
        mContext = context.getApplicationContext();
        mLoginApi = ApiModule.INSTANCE.provideLoginApi();
        mWeChatPref = EasySPWeChatPref.create(mContext);
    }

    public LoginPresenter(LoginApi mLoginApi) {
        this.mLoginApi = mLoginApi;
    }

    public void autoLogin() {
        MyLog.i(TAG, "autoLogin");
        long lastMemberId = GlobalPref.get(mContext).getLastLoginMemberId();
        if (lastMemberId > 0) {
            MyLog.i(TAG, "autoLogin success");
            UserInfoManager.INSTANCE.init(lastMemberId);
            onLoginSuccess(UserInfoManager.INSTANCE.getUserInfo());
        } else {
            MyLog.i(TAG, "autoLogin Failed");
            getMvpView().autoLoginFailed();
        }
    }

    public void wxOpenIdLogin(String wxOpenId) {
        mLoginApi.login(wxOpenId)
                .doOnNext(this::saveUserInfo)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onLoginSuccess, throwable -> {
                    getMvpView().onLoginFailed(throwable.getMessage());
                });
    }

    public void phoneLogin(String phone, String password) {
        if (!isPhoneAndPswValid(phone, password)) {
            getMvpView().onLoginFailed("invalid phone or psw");
            return;
        }
        mLoginApi.login(phone, password)
                .doOnNext(this::saveUserInfo)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onLoginSuccess, throwable -> {
                    getMvpView().onLoginFailed(throwable.getMessage());
                });
    }


    public void wechatLogin(String code) {
        MyLog.i(TAG, "wechatLogin, code:" + code);

        getWechatToken(code)
                .map(WeChatTokenResult::getOpenId)
                .flatMap(this::checkIsWxAccountExist)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::wxOpenIdLogin, throwable -> {
                    if (throwable.getMessage().contains("账号不存在")) {
                        String openId = throwable.getMessage().split(",")[1];
                        getMvpView().goToBindWx(openId);
                    } else {
                        getMvpView().onLoginFailed(throwable.getMessage());
                    }
                });
//                .flatMap(this::getWechatUser)
//                .subscribe(wechatUser -> {
//                    MyLog.i(TAG, "wechat login success, user: %s", wechatUser.toString());
//                }, throwable -> {
//                    MyLog.e(TAG, throwable, "wechat login failed");
//                });
    }

    private Observable<String> checkIsWxAccountExist(String openId) {
        return mLoginApi.checkIsWxAccountExist(openId).flatMap(s -> {
            if (s != null) {
                if (s.contains("该帐号已存在")) {
                    return Observable.just(openId);
                }
            }
            return Observable.error(new RuntimeException("账号不存在," + openId));
        });
    }

    //  https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
    private Observable<WeChatTokenResult> getWechatToken(String code) {
        MyLog.i(TAG, "getWechatToken, code：" + code);
        return mLoginApi.getWechatAccessToken(WeChat.APP_ID, WeChat.APP_SECRET, code, "authorization_code")
                .map(this::parseWechatTokenResult);
    }

    private Observable<WechatUser> getWechatUser(WeChatTokenResult tokenResult) {
        return mLoginApi.getWechatUser(tokenResult.getAccessToken(), tokenResult.getOpenId())
                .map(this::parseWechatUser);
    }

    private WeChatTokenResult parseWechatTokenResult(String json) {
        if (json.contains("access_token")) {
            return GsonUtils.jsonToObject(json, WeChatTokenResult.class);
        } else {
            throw new RuntimeException("get wechat token failed: " + json);
        }
    }

    private WechatUser parseWechatUser(String json) {
        MyLog.i(TAG, "parseWechatUser:" + json);
        return GsonUtils.jsonToObject(json, WechatUser.class);
    }

    /**
     * @param code
     * @return
     */
    private Observable<String> getAccessToken(String code) {
        isTokenValid();
        isRefreshTokenValid();
        return null;
    }

    private void isRefreshTokenValid() {

    }

    private boolean isTokenValid() {
        long expiresAt = mWeChatPref.getExpiresAt();
        return expiresAt < (System.currentTimeMillis() - TimeUnit.MINUTES.toMillis(1));
    }

    private boolean isPhoneAndPswValid(String phone, String psw) {
        return StringUtils.notEmpty(phone) && StringUtils.notEmpty(psw);
    }

    private void saveUserInfo(User user) {
        UserInfoManager.INSTANCE.saveUserInfo(user);
    }

    private void onLoginSuccess(User user) {
        getMvpView().onLoginSuccess(user);
        PushConfig.setTag(mContext, user.getAccount());
    }
}
