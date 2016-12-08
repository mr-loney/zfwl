package com.zfwl.model;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class UserRegAddressModel implements Serializable {

    private static final long serialVersionUID = -2492773240079820333L;

	@Expose
	private String fromProvince = "";
	@Expose
	private String fromCity = "";
	@Expose
	private String fromDistrict = "";
	@Expose
	private String toProvince = "";
	@Expose
	private String toCity = "";
	@Expose
	private String toDistrict = "";

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

	public String getFromDistrict() {
		return fromDistrict;
	}

	public void setFromDistrict(String fromDistrict) {
		this.fromDistrict = fromDistrict;
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

	public String getToDistrict() {
		return toDistrict;
	}

	public void setToDistrict(String toDistrict) {
		this.toDistrict = toDistrict;
	}
}
