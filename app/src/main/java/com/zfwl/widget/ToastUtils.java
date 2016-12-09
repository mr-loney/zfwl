package com.zfwl.widget;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by ZZB on 2016/12/9.
 */
public class ToastUtils {

    public static void show(Context context, String msg){
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
    public static void show(Context context, int stringRes){
        show(context, context.getString(stringRes));
    }
}
