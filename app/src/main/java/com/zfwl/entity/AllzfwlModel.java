package com.zfwl.entity;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class AllzfwlModel implements Serializable {

    private static final long serialVersionUID = -3718423961923385889L;
    
    @Expose
    private String createTime;

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}
