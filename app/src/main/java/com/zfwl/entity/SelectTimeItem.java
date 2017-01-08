package com.zfwl.entity;

/**
 * Created by ZZB on 2017/1/8.
 */
public class SelectTimeItem {

    public String realDate;
    public String displayDate;
    public boolean selected;

    public SelectTimeItem() {
    }

    public SelectTimeItem(String realDate, String displayDate, boolean selected) {
        this.realDate = realDate;
        this.displayDate = displayDate;
        this.selected = selected;
    }
}
