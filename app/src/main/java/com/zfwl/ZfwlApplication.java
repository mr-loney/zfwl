package com.zfwl;

import android.app.Application;

import com.zfwl.common.MyLog;

/**
 * Created by ZZB on 2016/12/8.
 */
public class ZfwlApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MyLog.init();
        initWeChat();
    }

    private void initWeChat() {
//        IWXAPI api = WXAPIFactory.createWXAPI(this, Const.WeChatLogin.APP_ID , false);
    }
}
