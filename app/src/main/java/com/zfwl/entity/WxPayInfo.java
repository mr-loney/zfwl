package com.zfwl.entity;

import com.google.gson.annotations.SerializedName;

/**
 * request.appId = "wxd930ea5d5a258f4f";
 * request.partnerId = "1900000109";
 * request.prepayId= "1101000000140415649af9fc314aa427",;
 * request.packageValue = "Sign=WXPay";
 * request.nonceStr= "1101000000140429eb40476f8896f4c9";
 * request.timeStamp= "1398746574";
 * request.sign= "7FFECB600D7157C5AA49810D2D8F28BC2811827B";
 * Created by ZZB on 2017/1/21.
 */
public class WxPayInfo {

    @SerializedName("appid")
    private String appId;
    @SerializedName("mch_id")
    private String partnerId;
    @SerializedName("prepay_id")
    private String prepayId;
    private String packageValue;
    @SerializedName("nonce_str")
    private String nonceStr;
    @SerializedName("timestamp")
    private long timeStamp;
    private String sign;
    @SerializedName("return_code")
    private String returnCode;//SUCCESS表示请求成功
    @SerializedName("err_code")
    private String errorCode;
    @SerializedName("err_code_des")
    private String errorDesc;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getPackageValue() {
        return packageValue;
    }

    public void setPackageValue(String packageValue) {
        this.packageValue = packageValue;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    @Override
    public String toString() {
        return "WxPayInfo{" +
                "appId='" + appId + '\'' +
                ", partnerId='" + partnerId + '\'' +
                ", prepayId='" + prepayId + '\'' +
                ", packageValue='" + packageValue + '\'' +
                ", nonceStr='" + nonceStr + '\'' +
                ", timeStamp=" + timeStamp +
                ", sign='" + sign + '\'' +
                '}';
    }
}
