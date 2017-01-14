package com.zfwl.event;

/**
 * Created by ZZB on 2017/1/15.
 */
public class OrderPushEvent {
    public int orderType;

    public OrderPushEvent(int orderType) {
        this.orderType = orderType;
    }
}
