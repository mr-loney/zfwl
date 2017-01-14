package com.zfwl.util;

/**
 * Created by ZZB on 2016/12/7.
 */

public class StringUtils {
    /**
     * 1000    -> 1000
     * 10.000  -> 10 (without point in result)
     * 10.0100 -> 10.01
     */
    public static String removeTrailingZero(String s) {
        s = !s.contains(".") ? s : s.replaceAll("0*$", "").replaceAll("\\.$", "");
        return s;
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean notEmpty(String str) {
        return !isEmpty(str);
    }
}
