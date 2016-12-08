package com.zfwl.common;

import android.util.Log;

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

    }

}
