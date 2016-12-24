package com.zfwl.entity;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class MyPublishEmptyCarListModel implements Serializable {

    private static final long serialVersionUID = -3718423961923385889L;

	@Expose
	private String from;
	@Expose
	private String to;
	@Expose
	private String createTime;
	@Expose
	private String carCount;
	@Expose
	private String length;
	@Expose
	private String weight;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCarCount() {
		return carCount;
	}

	public void setCarCount(String carCount) {
		this.carCount = carCount;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}
}
