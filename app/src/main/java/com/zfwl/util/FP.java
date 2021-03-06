package com.zfwl.util;

import java.util.Collection;

/**
 * Created by ZZB on 2016/12/16.
 */
public class FP {
    public static boolean eq(Object a, Object b){
        if (a == null && b == null)
            return true;
        else if (a == null)
            return false;
        else
            return a.equals(b);
    }
    public static boolean empty(String str) {
        return isNull(str) || str.length() == 0;
    }

    public static boolean empty(Collection collection) {
        return isNull(collection) || collection.size() == 0;
    }
    public static boolean notEmpty(String str){
        return !empty(str);
    }
    public static boolean notEmpty(Collection collection) {
        return !empty(collection);
    }

    private static boolean isNull(Object o) {
        return o == null;
    }

    public static int size(Collection collection) {
        return notEmpty(collection) ? collection.size() : 0;
    }
}
