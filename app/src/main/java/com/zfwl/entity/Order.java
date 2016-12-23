package com.zfwl.entity;

/**
 * Created by ZZB on 2016/12/21.
 */
public class Order {
    public interface Type {
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

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

    @Override
    public String toString() {
        return "Order{" +
                "type=" + type +
                ", goodsName='" + goodsName + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                '}';
    }
}
