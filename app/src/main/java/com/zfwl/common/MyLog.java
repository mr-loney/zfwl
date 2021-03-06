package com.zfwl.common;

import android.content.Context;

import com.zfwl.BuildConfig;
import com.zfwl.log.LumberYard;

import timber.log.Timber;
import timber.log.Timber.DebugTree;
import timber.log.Timber.Tree;

/**
 * Created by ZZB on 2016/12/7.
 */

public class MyLog {
    public static void init(Context context) {
        Tree tree;
        if (BuildConfig.DEBUG) {
            tree = new DebugTree();
        } else {
            LumberYard lumberYard = LumberYard.getInstance();
            lumberYard.cleanUp();
            tree = lumberYard.tree();
        }
        Timber.plant(tree);
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
