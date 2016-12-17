package com.zfwl.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZZB on 2016/12/16.
 */
public class Area {
    private int id;
    private int parentId;
    private String name;
    private String zipCode;
    List<Area> mSubAreas = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
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

    public List<Area> getSubAreas() {
        return mSubAreas;
    }

    public void setSubAreas(List<Area> subAreas) {
        mSubAreas = subAreas;
    }

    @Override
    public String toString() {
        return "Area{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", mSubAreas=" + mSubAreas +
                '}';
    }
}
