package com.zfwl.common;

import com.zfwl.BuildConfig;

import timber.log.Timber;

/**
 * Created by ZZB on 2016/12/7.
 */

public class MyLog {
    public static void init() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    public static void i(String tag, String msg, Object... args) {
        Timber.tag(tag).i(msg, args);
    }

    public static void d(String tag, String msg, Object... args) {
        Timber.tag(tag).d(msg, args);
    }

    public static void w(String tag, String msg, Object... args) {
        Timber.tag(tag).w(msg, args);
    }

    public static void v(String tag, String msg, Object... args) {
        Timber.tag(tag).v(msg, args);
    }

    public static void e(String tag, Throwable throwable, String msg, Object... args) {
        Timber.tag(tag).e(throwable, msg, args);
    }

    public static void e(String tag, String msg, Object... args) {
        Timber.tag(tag).e(msg, args);
    }

    public static void e(String tag, Throwable throwable) {
        Timber.tag(tag).e(throwable);
    }
}
