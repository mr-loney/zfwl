package com.zfwl.entity;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZZB on 2016/12/16.
 */
public class Area {
    private String id;
    private String parentId;
    private String name;
    private String zipCode;
    private int dataDepth;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public int getDataDepth() {
        return dataDepth;
    }

    public void setDataDepth(int dataDepth) {
        this.dataDepth = dataDepth;
    }
}
