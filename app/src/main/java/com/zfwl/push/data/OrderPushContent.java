package com.zfwl.push.data;

import com.zfwl.entity.Order.Type;

/**
 * Created by ZZB on 2017/4/9.
 */
public class OrderPushContent {
    public long orderId;
    public String orderCode;
    public int orderType = Type.UNKNOWN;
}
