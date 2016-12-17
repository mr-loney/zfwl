package com.zfwl.entity;

/**
 * Created by ZZB on 2016/12/17.
 */
public class Address {
    private Area province;
    private Area city;
    private Area district;

    public Address(Area province, Area city, Area district) {
        this.province = province;
        this.city = city;
        this.district = district;
    }
}
