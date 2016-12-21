package com.zfwl.entity;

/**
 * Created by ZZB on 2016/12/21.
 */
public class Order {
    public interface Type {
        int ALL = 0;
        int WAIT_CONFIRM = 1;
        int WAIT_PAY = 2;
        int PAID = 3;
        int CARRYING = 4;
        int FINISHED = 5;
    }

    private int type;
    private String goodsName;
    private String from;
    private String to;
}
