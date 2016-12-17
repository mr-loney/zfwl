package com.zfwl.util;

import android.os.Looper;

/**
 * Created by ZZB on 2016/12/17.
 */
public class Utils {

    public static boolean isMainThread(){
        return Looper.getMainLooper() == Looper.myLooper();
    }
}
