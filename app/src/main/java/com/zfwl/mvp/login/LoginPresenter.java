package com.zfwl.mvp.login;

import android.content.Context;

import com.zfwl.common.Const.WeChatLogin;
import com.zfwl.common.MyLog;
import com.zfwl.data.api.LoginApi;
import com.zfwl.data.api.retrofit.ApiModule;
import com.zfwl.data.sp.WeChatPref;
import com.zfwl.entity.WeChatTokenResult;
import com.zfwl.mvp.BasePresenter;
import com.zfwl.util.StringUtils;
import com.zzb.easysp.generated.EasySPWeChatPref;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by ZZB on 2016/12/7.
 */

public class LoginPresenter extends BasePresenter<LoginMvpView> {
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

    public void phoneLogin(String phone, String password) {
        if (!isPhoneAndPswValid(phone, password)) {
            getMvpView().onLoginFailed("invalid phone or psw");
            return;
        }
        mLoginApi.login(phone, password).subscribe(user -> {
            getMvpView().onLoginSuccess(user);
        }, throwable -> {
            getMvpView().onLoginFailed(throwable.toString());
        });
    }

    public void wechatLogin(String code) {
//        https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
        mLoginApi.getWechatAccessToken(WeChatLogin.APP_ID, WeChatLogin.APP_SECRET, code, "authorization_code")
                .subscribe(new Action1<WeChatTokenResult>() {
                    @Override
                    public void call(WeChatTokenResult weChatTokenResult) {
                        MyLog.i(TAG, "wechat:" + weChatTokenResult.toString());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        MyLog.e(TAG, "wechat error:" + throwable.toString());
                    }
                });
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
}
