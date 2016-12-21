package com.zfwl.entity;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.util.List;

public class AllzfwlModel implements Serializable {

    private static final long serialVersionUID = -3718423961923385889L;

	@Expose
	private String fromProvince = "";
	@Expose
	private String fromCity = "";
	@Expose
	private String fromDistrict = "";
	@Expose
	private String beginTime = "";
	@Expose
	private int carCount = 0;
	@Expose
	private double carLength = 0;
	@Expose
	private double carWeight = 0;

    @Expose
    private List<AllzfwlToModel> to;

	public List<AllzfwlToModel> getTo() {
		return to;
	}

	public void setTo(List<AllzfwlToModel> to) {
		this.to = to;
	}

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

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public int getCarCount() {
		return carCount;
	}

	public void setCarCount(int carCount) {
		this.carCount = carCount;
	}

	public double getCarLength() {
		return carLength;
	}

	public void setCarLength(double carLength) {
		this.carLength = carLength;
	}

	public double getCarWeight() {
		return carWeight;
	}

	public void setCarWeight(double carWeight) {
		this.carWeight = carWeight;
	}

	public class AllzfwlToModel {
		@Expose
		private String toProvince = "";
		@Expose
		private String toCity = "";
		@Expose
		private String toDistrict = "";

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

		public String getStr() {
			if (toDistrict != "") {
				return toDistrict;
			}
			if (toCity != "") {
				return toCity;
			}
			if (toProvince != "") {
				return toProvince;
			}
			return "";
		}
	}
}
