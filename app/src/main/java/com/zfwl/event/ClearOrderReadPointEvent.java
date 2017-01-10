package com.zfwl.event;

/**
 * Created by ZZB on 2017/1/9.
 */
public class ClearOrderReadPointEvent {

    public int orderType;

    public ClearOrderReadPointEvent(int orderType) {
        this.orderType = orderType;
    }
}
