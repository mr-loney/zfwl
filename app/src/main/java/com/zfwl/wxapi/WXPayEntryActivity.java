package com.zfwl.wxapi;

import android.content.Intent;
import android.os.Bundle;

import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.zfwl.activity.BaseActivity;
import com.zfwl.common.Const.WeChat;
import com.zfwl.common.MyLog;
import com.zfwl.event.WxPayEvent;

import org.greenrobot.eventbus.EventBus;

public class WXPayEntryActivity extends BaseActivity implements IWXAPIEventHandler {

    private static final String TAG = "WXPayEntryActivity";

    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, WeChat.APP_ID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
        MyLog.i(TAG, "onReq:" + req.checkArgs());
    }

    /**
     * errCode
     * 0	成功	展示成功页面
     * -1	错误	可能的原因：签名错误、未注册APPID、项目设置APPID不正确、注册的APPID与设置的不匹配、其他异常等。
     * -2	用户取消	无需处理。发生场景：用户不支付了，点击取消，返回APP。
     */
    @Override
    public void onResp(BaseResp resp) {
        MyLog.i(TAG, "onPayFinish, type: %d, errCode: %d ", resp.getType(), resp.errCode);
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            EventBus.getDefault().post(new WxPayEvent(resp.errCode));
            finish();
        }
    }
}
