package com.zfwl.entity;

import com.google.gson.annotations.SerializedName;

/**
 * {
 * "access_token":"ACCESS_TOKEN",//接口调用凭证
 * "expires_in":7200,//access_token接口调用凭证超时时间，单位（秒）
 * "refresh_token":"REFRESH_TOKEN",//用户刷新access_token
 * "openid":"OPENID",//授权用户唯一标识
 * "scope":"SCOPE",//用户授权的作用域，使用逗号（,）分隔
 * "unionid":"o6_bmasdasdsad6_2sgVt7hMZOPfL"// 当且仅当该移动应用已获得该用户的userinfo授权时，才会出现该字段
 * }
 * Created by ZZB on 2016/12/12.
 */
public class WeChatTokenResult {
    @SerializedName("access_token")
    private String accessToken;
    @SerializedName("expires_in")
    private long expiresIn;
    @SerializedName("refresh_token")
    private String refreshToken;
    @SerializedName("openid")
    private String openId;
    @SerializedName("scope")
    private String scope;
    @SerializedName("unionid")
    private String unionid;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    @Override
    public String toString() {
        return "WeChatTokenResult{" +
                "accessToken='" + accessToken + '\'' +
                ", expiresIn=" + expiresIn +
                ", refreshToken='" + refreshToken + '\'' +
                ", openId='" + openId + '\'' +
                ", scope='" + scope + '\'' +
                ", unionid='" + unionid + '\'' +
                '}';
    }
}
