package com.zfwl.wxapi;

import android.os.Bundle;

import com.bilibili.socialize.share.core.ui.BaseWXEntryActivity;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.zfwl.R;
import com.zfwl.common.Const.WeChat;
import com.zfwl.common.MyLog;
import com.zfwl.event.WeChatAuthEvent;
import com.zfwl.widget.ToastUtils;

import org.greenrobot.eventbus.EventBus;

public class WXEntryActivity extends BaseWXEntryActivity implements IWXAPIEventHandler {
    private static final String TAG = "WXEntryActivity";
    private static final int RESP_CODE_LOGIN = 1;
    private static final int RESP_CODE_SHARE = 2;
//    private IWXAPI mWxApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxentry);
//        mWxApi = WXAPIFactory.createWXAPI(this, WeChat.APP_ID, false);
//        mWxApi.handleIntent(getIntent(), this);
    }

//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//        setIntent(intent);
////        mWxApi.handleIntent(intent, this);
//    }

//    @Override
//    public void onReq(BaseReq baseReq) {
//
//    }

    @Override
    public void onResp(BaseResp baseResp) {
        super.onResp(baseResp);
        String result = "";
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                result = "分享成功";
                onAuthSuccess(baseResp);
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                result = "分享取消";
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                result = "分享失败";
                break;
            default:
                result = "unknown:" + baseResp.errCode + ":" + baseResp.errStr;
                break;
        }
        ToastUtils.show(this, result);
        finish();
    }

    @Override
    protected String getAppId() {
        return WeChat.APP_ID;
    }

    private void onAuthSuccess(BaseResp baseResp) {
        switch (baseResp.getType()) {

            case RESP_CODE_LOGIN:
                MyLog.i(TAG, "onAuthSuccess, login");
                String code = ((SendAuth.Resp) baseResp).code;
                EventBus.getDefault().post(new WeChatAuthEvent(code));
                break;
            case RESP_CODE_SHARE:
                MyLog.i(TAG, "onAuthSuccess, share");

                break;
            default:
                MyLog.e(TAG, "onAuthSuccess, unknown type: %d", baseResp.getType());
                break;

        }
    }
    /**
     * 1.get code
     * 2.get token via code
     * https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
     * {
     * "access_token":"ACCESS_TOKEN",
     * "expires_in":7200,//两小时过期
     * "refresh_token":"REFRESH_TOKEN",//30天
     * "openid":"OPENID",
     * "scope":"SCOPE",
     * "unionid":"o6_bmasdasdsad6_2sgVt7hMZOPfL"
     * }
     * 3.https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN
     * token 过期，refresh token可以获取新token
     * token未过期，refresh token刷新token过期时间
     * 4./sns/userinfo	获取用户个人信息
     */
}
