package com.zfwl.push;

import android.content.Context;

import java.util.HashSet;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by ZZB on 2017/1/15.
 */
public class PushConfig {

    public static void setTag(Context context, String tag) {
        Set<String> set = new HashSet<>();
        set.add(tag);//名字任意，可多添加几个
        JPushInterface.setTags(context, set, null);//设置标签
    }

    public static void clearTag(Context context) {
        Set<String> set = new HashSet<>();
        JPushInterface.setTags(context, set, null);//设置标签
    }
}
