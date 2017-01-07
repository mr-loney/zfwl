package com.zfwl.data.sp;

import android.content.Context;

import com.zzb.easysp.EasySP;
import com.zzb.easysp.generated.EasySPGlobalPref;

/**
 * 全局SP
 * Created by ZZB on 2017/1/7.
 */
@EasySP
public class GlobalPref {
    private static GlobalPref INSTANCE;

    public static GlobalPref get(Context context) {
        if (INSTANCE == null) {
            INSTANCE = EasySPGlobalPref.create(context);
        }
        return INSTANCE;
    }

    protected GlobalPref() {

    }

    private long lastLoginMemberId;

    public long getLastLoginMemberId() {
        return lastLoginMemberId;
    }

    public void setLastLoginMemberId(long lastLoginMemberId) {
        this.lastLoginMemberId = lastLoginMemberId;
    }
}
