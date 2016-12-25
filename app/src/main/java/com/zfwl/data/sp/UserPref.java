package com.zfwl.data.sp;

import com.zzb.easysp.DefaultValue;
import com.zzb.easysp.EasySP;

/**
 * Created by ZZB on 2016/12/7.
 */
@EasySP
public class UserPref {

    private long userId;
    @DefaultValue("{}")
    private String userJson;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserJson() {
        return userJson;
    }

    public void setUserJson(String userJson) {
        this.userJson = userJson;
    }
}
