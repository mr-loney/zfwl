package com.zfwl.entity.request;

/**
 * Created by ZZB on 2016/12/27.
 */
public class LogisticsRequest {

    private String fromProvince;//  String  （非必填）       出发地-省ID
    private String fromCity;//      String  （非必填）       出发地-市ID
    private String fromCounty;//    String  （非必填）       出发地-区县ID
    private String toProvince;//    String  （非必填）       目的地-省ID
    private String toCity;//        String  （非必填）       目的地-市ID
    private String toCounty;//      String  （非必填）       目的地-区县ID
    private String sendDate;//      String  （暂未定义）      配送日期     (2016-12-23)
    private int pageNo;    //       Integer （非必填）       当前页码 ，默认为1
    private int pageSize;//         Integer （非必填）      每页条数 ，默认为10

    public String getFromProvince() {
        return fromProvince;
    }

    public void setFromProvince(String fromProvince) {
        this.fromProvince = fromProvince;
    }

    public String getFromCity() {
        return fromCity;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    public String getFromCounty() {
        return fromCounty;
    }

    public void setFromCounty(String fromCounty) {
        this.fromCounty = fromCounty;
    }

    public String getToProvince() {
        return toProvince;
    }

    public void setToProvince(String toProvince) {
        this.toProvince = toProvince;
    }

    public String getToCity() {
        return toCity;
    }

    public void setToCity(String toCity) {
        this.toCity = toCity;
    }

    public String getToCounty() {
        return toCounty;
    }

    public void setToCounty(String toCounty) {
        this.toCounty = toCounty;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
