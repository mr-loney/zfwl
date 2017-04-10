package com.zfwl.entity;

import java.io.Serializable;

public class CPDModel{
	public Address fromaddress = new Address();
	public Address toaddress = new Address();
	/**
	 * id : 4
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
	private String fromProvinceId;
	private String fromCityId;
	private String fromCountyId;
	private String toProvinceId;
	private String toCityId;
	private String toCountyId;
	private String fromAddressName;
	private String toAddressName;
	private long cdate;
	private long mdate;
	private Object remark;
	private String fromProvinceName;
	private String fromCityName;
	private String fromCountyName;
	private String toProvinceName;
	private String toCityName;
	private String toCountyName;

	public String getFromProvinceName() {
		return fromProvinceName;
	}

	public void setFromProvinceName(String fromProvinceName) {
		this.fromProvinceName = fromProvinceName;
	}

	public String getFromCityName() {
		return fromCityName;
	}

	public void setFromCityName(String fromCityName) {
		this.fromCityName = fromCityName;
	}

	public String getFromCountyName() {
		return fromCountyName;
	}

	public void setFromCountyName(String fromCountyName) {
		this.fromCountyName = fromCountyName;
	}

	public String getToProvinceName() {
		return toProvinceName;
	}

	public void setToProvinceName(String toProvinceName) {
		this.toProvinceName = toProvinceName;
	}

	public String getToCityName() {
		return toCityName;
	}

	public void setToCityName(String toCityName) {
		this.toCityName = toCityName;
	}

	public String getToCountyName() {
		return toCountyName;
	}

	public void setToCountyName(String toCountyName) {
		this.toCountyName = toCountyName;
	}

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

	public String getFromProvinceId() {
		return fromProvinceId;
	}

	public void setFromProvinceId(String fromProvinceId) {
		this.fromProvinceId = fromProvinceId;
	}

	public String getFromCityId() {
		return fromCityId;
	}

	public void setFromCityId(String fromCityId) {
		this.fromCityId = fromCityId;
	}

	public String getFromCountyId() {
		return fromCountyId;
	}

	public void setFromCountyId(String fromCountyId) {
		this.fromCountyId = fromCountyId;
	}

	public String getToProvinceId() {
		return toProvinceId;
	}

	public void setToProvinceId(String toProvinceId) {
		this.toProvinceId = toProvinceId;
	}

	public String getToCityId() {
		return toCityId;
	}

	public void setToCityId(String toCityId) {
		this.toCityId = toCityId;
	}

	public String getToCountyId() {
		return toCountyId;
	}

	public void setToCountyId(String toCountyId) {
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
