package com.zfwl.entity;

/**
 * Created by ZZB on 2016/12/17.
 */
public class Address {
    private Area province;
    private Area city;
    private Area district;

    public Address() {
    }

    public Address(Area province, Area city, Area district) {
        this.province = province;
        this.city = city;
        this.district = district;
    }

    public Area getProvince() {
        return province;
    }

    public void setProvince(Area province) {
        this.province = province;
    }

    public Area getCity() {
        return city;
    }

    public void setCity(Area city) {
        this.city = city;
    }

    public Area getDistrict() {
        return district;
    }

    public void setDistrict(Area district) {
        this.district = district;
    }
}
