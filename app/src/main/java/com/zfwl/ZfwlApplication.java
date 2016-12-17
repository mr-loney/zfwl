package com.zfwl;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.zfwl.common.Const.WeChat;
import com.zfwl.common.MyLog;

/**
 * Created by ZZB on 2016/12/8.
 */
public class ZfwlApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MyLog.init();
        initBugly();
        initWeChat();

    }

    private void initBugly() {
        CrashReport.initCrashReport(getApplicationContext(), "6b09ee46cf", BuildConfig.DEBUG);
    }

    private void initWeChat() {
        IWXAPI api = WXAPIFactory.createWXAPI(this, WeChat.APP_ID , false);
        api.registerApp(WeChat.APP_ID);
    }
}
