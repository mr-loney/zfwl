package com.zfwl.entity;

import java.io.Serializable;

public class DriverQuotedModel implements Serializable{


    /**
     * id : 1
     * memberId : 1
     * fromProvinceId : 110000
     * fromCityId : 110100
     * fromCountyId : 110101
     * toProvinceId : 130000
     * toCityId : 130300
     * toCountyId : null
     * fromAddressName : 东城区
     * toAddressName : 秦皇岛市
     * cdate : 1479710623000
     * mdate : 1479710623000
     * remark : null
     */

    private int id;
    private int memberId;
    private int fromProvinceId;
    private int fromCityId;
    private int fromCountyId;
    private int toProvinceId;
    private int toCityId;
    private Object toCountyId;
    private String fromAddressName;
    private String toAddressName;
    private long cdate;
    private long mdate;
    private Object remark;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getFromProvinceId() {
        return fromProvinceId;
    }

    public void setFromProvinceId(int fromProvinceId) {
        this.fromProvinceId = fromProvinceId;
    }

    public int getFromCityId() {
        return fromCityId;
    }

    public void setFromCityId(int fromCityId) {
        this.fromCityId = fromCityId;
    }

    public int getFromCountyId() {
        return fromCountyId;
    }

    public void setFromCountyId(int fromCountyId) {
        this.fromCountyId = fromCountyId;
    }

    public int getToProvinceId() {
        return toProvinceId;
    }

    public void setToProvinceId(int toProvinceId) {
        this.toProvinceId = toProvinceId;
    }

    public int getToCityId() {
        return toCityId;
    }

    public void setToCityId(int toCityId) {
        this.toCityId = toCityId;
    }

    public Object getToCountyId() {
        return toCountyId;
    }

    public void setToCountyId(Object toCountyId) {
        this.toCountyId = toCountyId;
    }

    public String getFromAddressName() {
        return fromAddressName;
    }

    public void setFromAddressName(String fromAddressName) {
        this.fromAddressName = fromAddressName;
    }

    public String getToAddressName() {
        return toAddressName;
    }

    public void setToAddressName(String toAddressName) {
        this.toAddressName = toAddressName;
    }

    public long getCdate() {
        return cdate;
    }

    public void setCdate(long cdate) {
        this.cdate = cdate;
    }

    public long getMdate() {
        return mdate;
    }

    public void setMdate(long mdate) {
        this.mdate = mdate;
    }

    public Object getRemark() {
        return remark;
    }

    public void setRemark(Object remark) {
        this.remark = remark;
    }
}
