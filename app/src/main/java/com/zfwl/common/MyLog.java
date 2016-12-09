package com.zfwl.common;

import com.zfwl.BuildConfig;

import timber.log.Timber;

/**
 * Created by ZZB on 2016/12/7.
 */

public class MyLog {
    public static void init(){
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
    public static void i(String tag, String msg, Object... args){
        Timber.tag(tag).i(msg, args);
    }
    public static void e(String tag, Throwable throwable, String msg, Object... args){
        Timber.tag(tag).e(throwable, msg, args);
    }
}
