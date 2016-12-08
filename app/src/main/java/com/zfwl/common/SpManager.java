package com.zfwl.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import com.zfwl.R;

import java.util.List;

public class SpManager {
    private static final String FIRST_USE_TIME = "FIRST_USE_TIME";
    private static final String IS_FIRST_USE_APP = "IS_FIRST_USE_APP";
    private static final String IS_FIRST_IN_MAIN = "IS_FIRST_IN_MAIN";
    private static final String LOGIN_ACCOUNTS = "LOGIN_ACCOUNTS";
    private static final String LOGIN_ACCOUNT = "LOGIN_ACCOUNT";
    private static final String COOKIE = "COOKIE";
    private static final String SHOWCASE_USERIDS = "SHOWCASE_USERIDS";

    public static void setLoginAccount(Context context, String loginAccount) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putString(LOGIN_ACCOUNT, loginAccount).commit();

        addLoginAccounts(context, loginAccount);
    }
    public static String getLoginAccount(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getString(LOGIN_ACCOUNT, "");
    }
    public static void addLoginAccounts(Context context, String loginAccount) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        String account = getLoginAccounts(context);
        if (!StringUtils.contains(account, loginAccount, ",")) {// 不存在账号，添加
            sp.edit().putString(LOGIN_ACCOUNTS, account + loginAccount + ",").commit();
        }
    }
    public static String getLoginAccounts(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getString(LOGIN_ACCOUNTS, "");
    }
    public static String deleteLoginAccounts(Context context, String text) {
        String newChar = SpManager.getLoginAccounts(context.getApplicationContext()).replace(text + ",", "");
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putString(LOGIN_ACCOUNTS, newChar).commit();
        return newChar;
    }
    public static void setShowcaseUserIds(Context context, String value) {
        setString(context, SHOWCASE_USERIDS, value);
    }
    public static String getShowcaseUserIds(Context context) {
        return getString(context, SHOWCASE_USERIDS);
    }
    public static void setCookie(Context context, String cookie) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putString(COOKIE, cookie).commit();
    }
    public static String getCookie(Context context) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getString(COOKIE, "");
    }

    public static void setIsFirstInMain(Context context, boolean isFirst) {
        setBoolean(context, IS_FIRST_IN_MAIN, isFirst);
    }
    public static boolean isFirstInMain(Context context) {
        return getBoolean(context, IS_FIRST_IN_MAIN, true);
    }
    public static void setIsFirstUseApp(Context context, boolean isFirstUse) {
        setBoolean(context, IS_FIRST_USE_APP, isFirstUse);
    }
    public static boolean isFirstUseApp(Context context) {
        return getBoolean(context, IS_FIRST_USE_APP, true);
    }
    public static void setFirstUseTime(Context context, String time) {
        setString(context, FIRST_USE_TIME, time);
    }
    public static String getFirstUseTime(Context context) {
        return getString(context, FIRST_USE_TIME, "");
    }

    public static String getString(Context context, String key) {
        return getString(context, key, "");
    }

    public static String getString(Context context, String key, String defValue) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getString(key, defValue);
    }

    public static void setString(Context context, String key, String value) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putString(key, value).commit();
    }

    public static boolean getBoolean(Context context, String key, boolean defValue) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getBoolean(key, defValue);
    }

    public static void setBoolean(Context context, String key, boolean value) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putBoolean(key, value).commit();
    }

    public static int getInt(Context context, String key, int defValue) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getInt(key, defValue);
    }

    public static void setInt(Context context, String key, int value) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putInt(key, value).commit();
    }

    private static long getLong(Context context, String key, long defValue) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        return sp.getLong(key, defValue);
    }

    private static void setLong(Context context, String key, long value) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        sp.edit().putLong(key, value).commit();
    }

    private static void remove(Context context, String... keys) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
        Editor edit = sp.edit();
        for (String key : keys) {
            edit.remove(key);
        }
        edit.commit();
    }

}
