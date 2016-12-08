package com.zfwl.common;

import com.zfwl.BuildConfig;

import timber.log.Timber;

/**
 * Created by ZZB on 2016/12/7.
 */

public class Logger {
    public static void init(){
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
    public static void info(String tag, String msg){
        Timber.tag(tag).i(msg);
    }
    public static void info(String tag, String msg, Object... args){
        Timber.tag(tag).i(msg, args);
    }
}
