package com.zfwl.data.sp;

import com.zzb.easysp.EasySP;

/**
 * Created by ZZB on 2016/12/10.
 */
@EasySP
public class WeChatPref {
    private String refreshToken;
    private String accessToken;
    private String openid;
    private long expiresAt;

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }


    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public long getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(long expiresAt) {
        this.expiresAt = expiresAt;
    }
}
