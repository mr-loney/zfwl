package com.zfwl.entity;

import java.io.Serializable;
import java.util.List;

public class AllzfwlModel implements Serializable {

    private static final long serialVersionUID = -3718423961923385889L;


	public Address fromaddress = new Address();
	/**
	 * id : 4
	 * memberId : 1
	 * fromProvinceId : 110000
	 * fromCityId : 110100
	 * fromCountyId : 110101
	 * fromAddressName : 北京市-市辖区-东城区 aaaa
	 * goDate : 2016-12-29 10:00:00.0
	 * carNumber : 5
	 * loadNumber : 10
	 * carLength : 15
	 * cdate : 1482908283000
	 * mdate : 1482908282000
	 * remark : null
	 * emptyCarAddressList : [{"id":2,"emptyCarId":4,"toProvinceId":120000,"toCityId":120100,"toCountyId":0,"toAddressName":"滨海","toProvinceName":"天津市","toCityName":"市辖区","toCountyName":null},{"id":1,"emptyCarId":4,"toProvinceId":110000,"toCityId":110100,"toCountyId":110112,"toAddressName":"大运河","toProvinceName":"北京市","toCityName":"市辖区","toCountyName":"通州区"}]
	 */

	private int id;
	private int memberId;
	private String fromProvinceId;
	private String fromCityId;
	private String fromCountyId;
	private String fromAddressName;
	private String goDate;
	private int carNumber;
	private double loadNumber;
	private double carLength;
	private long cdate;
	private long mdate;
	private Object remark;
	private List<EmptyCarAddressListBean> emptyCarAddressList;

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

	public String getFromAddressName() {
		return fromAddressName;
	}

	public void setFromAddressName(String fromAddressName) {
		this.fromAddressName = fromAddressName;
	}

	public String getGoDate() {
		return goDate;
	}

	public void setGoDate(String goDate) {
		this.goDate = goDate;
	}

	public int getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(int carNumber) {
		this.carNumber = carNumber;
	}

	public double getLoadNumber() {
		return loadNumber;
	}

	public void setLoadNumber(double loadNumber) {
		this.loadNumber = loadNumber;
	}

	public double getCarLength() {
		return carLength;
	}

	public void setCarLength(double carLength) {
		this.carLength = carLength;
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

	public List<EmptyCarAddressListBean> getEmptyCarAddressList() {
		return emptyCarAddressList;
	}

	public void setEmptyCarAddressList(List<EmptyCarAddressListBean> emptyCarAddressList) {
		this.emptyCarAddressList = emptyCarAddressList;
	}

	public class EmptyCarAddressListBean {

		public Address toaddress = new Address();
		/**
		 * id : 2
		 * emptyCarId : 4
		 * toProvinceId : 120000
		 * toCityId : 120100
		 * toCountyId : 0
		 * toAddressName : 滨海
		 * toProvinceName : 天津市
		 * toCityName : 市辖区
		 * toCountyName : null
		 */

		private int id;
		private int emptyCarId;
		private String toProvinceId;
		private String toCityId;
		private String toCountyId;
		private String toAddressName;
		private String toProvinceName;
		private String toCityName;
		private Object toCountyName;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getEmptyCarId() {
			return emptyCarId;
		}

		public void setEmptyCarId(int emptyCarId) {
			this.emptyCarId = emptyCarId;
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

		public String getToAddressName() {
			return toAddressName;
		}

		public void setToAddressName(String toAddressName) {
			this.toAddressName = toAddressName;
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

		public Object getToCountyName() {
			return toCountyName;
		}

		public void setToCountyName(Object toCountyName) {
			this.toCountyName = toCountyName;
		}
	}
}
