package com.zfwl.util;


import com.google.gson.Gson;
import com.zfwl.common.MyLog;

/**
 * Created by ZZB on 2016/12/12.
 */
public class GsonUtils {
    private static final String TAG = "GsonUtils";
    private static final Gson sGson = new Gson();

    public static String objectToJson(Object obj) {
        String json = "";
        try{
            json = sGson.toJson(obj);
        }catch (Throwable e){
            MyLog.e(TAG, e, "objectToJson failed");
        }
        return json;
    }
    public static <T> T jsonToObject(String json, Class<T> clazz) {
        T t = null;
        try {
            t = sGson.fromJson(json, clazz);
        } catch (Throwable e) {
            MyLog.e(TAG, e, "jsonToObject failed");
        }
        return t;
    }
}
