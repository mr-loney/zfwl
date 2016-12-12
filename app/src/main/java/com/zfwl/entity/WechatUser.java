package com.zfwl.entity;

import com.google.gson.annotations.SerializedName;

/**
 * "openid":"OPENID",普通用户的标识，对当前开发者帐号唯一
 "nickname":"NICKNAME",
 "sex":1,普通用户性别，1为男性，2为女性
 "province":"PROVINCE",
 "city":"CITY",
 "country":"COUNTRY",
 //用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空
 "headimgurl": "http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0",
 "privilege":[//用户特权信息，json数组，如微信沃卡用户为（chinaunicom）
 "PRIVILEGE1",
 "PRIVILEGE2"
 ],
 "unionid": " o6_bmasdasdsad6_2sgVt7hMZOPfL"//用户统一标识。针对一个微信开放平台帐号下的应用，同一用户的unionid是唯一的。

 * Created by ZZB on 2016/12/12.
 */
public class WechatUser {
    @SerializedName("openid")
    private String openId;
    private String nickname;
    private int sex;//1为男性，2为女性
    private String province;
    private String city;
    private String country;
    @SerializedName("headimgurl")
    private String avatar;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "WechatUser{" +
                "openId='" + openId + '\'' +
                ", nickname='" + nickname + '\'' +
                ", sex=" + sex +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
