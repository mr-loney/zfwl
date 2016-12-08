package com.zfwl;

import android.app.Application;

import com.zfwl.common.Logger;

/**
 * Created by ZZB on 2016/12/8.
 */
public class ZfwlApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.init();
        initWeChat();
    }

    private void initWeChat() {
//        IWXAPI api = WXAPIFactory.createWXAPI(this, Const.WeChatLogin.APP_ID , false);
    }
}
