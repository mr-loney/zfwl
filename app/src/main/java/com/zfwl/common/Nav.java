package com.zfwl.common;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by ZZB on 2017/1/9.
 */
public class Nav {
    //拨打电话页面
    public static void toDialPhonePage(Context context, String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
