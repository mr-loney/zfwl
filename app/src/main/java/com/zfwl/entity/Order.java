package com.zfwl.entity;

import com.zfwl.entity.LogisticsInfo.ListBean;
import com.zfwl.entity.LogisticsInfo.ListBean.AddressInfoListBean;

import java.util.List;

/**
 * Created by ZZB on 2016/12/21.
 */
public class Order {
    public interface Type {
        int ALL = 9;
        int WAIT_CONFIRM = 0;
        int WAIT_PAY = 1;
        int PAID = 2;
        int CARRYING = 3;
        int FINISHED = 4;
    }
    //0-按车辆收费,1-按吨数收费
    public interface ChargeMethod{
        int CHARGE_BY_CAR = 0;
        int CHARGE_BY_WEIGHT = 1;
    }
    private int id;
    private int memberId;
    private int emptyCardId;
    private int memberPriceId;
    private int logisticsId;
    private int payMethod;//支付方式（0-微信支付,）
    private int status;//              订单状态（0-待确认,1-待支付,2-已支付,3-运输中,4-已结束）
    private long waitConfirmTime;//待确认时间
    private long waitPayTime;//待支付时间
    private long payTime;//已支付时间
    private long transportTime;//运输中时间
    private long endTime;//已结束时间
    private double msgPrice;//信息费
    private double unitPrice;//单价
    private int chargeMethod;//0-按车辆收费,1-按吨数收费
    private int orderFrom;//单据来源(0-空车生成,1-报价生成)
    private ListBean logisticsInfo;//订单对应的物流信息的对象
    private List<AddressInfoListBean> logisticsAddressInfo;// 订单对应物流信息地址的对象

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getEmptyCardId() {
        return emptyCardId;
    }

    public void setEmptyCardId(int emptyCardId) {
        this.emptyCardId = emptyCardId;
    }

    public int getMemberPriceId() {
        return memberPriceId;
    }

    public void setMemberPriceId(int memberPriceId) {
        this.memberPriceId = memberPriceId;
    }

    public int getLogisticsId() {
        return logisticsId;
    }

    public void setLogisticsId(int logisticsId) {
        this.logisticsId = logisticsId;
    }

    public int getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(int payMethod) {
        this.payMethod = payMethod;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getWaitConfirmTime() {
        return waitConfirmTime;
    }

    public void setWaitConfirmTime(long waitConfirmTime) {
        this.waitConfirmTime = waitConfirmTime;
    }

    public long getWaitPayTime() {
        return waitPayTime;
    }

    public void setWaitPayTime(long waitPayTime) {
        this.waitPayTime = waitPayTime;
    }

    public long getPayTime() {
        return payTime;
    }

    public void setPayTime(long payTime) {
        this.payTime = payTime;
    }

    public long getTransportTime() {
        return transportTime;
    }

    public void setTransportTime(long transportTime) {
        this.transportTime = transportTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public double getMsgPrice() {
        return msgPrice;
    }

    public void setMsgPrice(double msgPrice) {
        this.msgPrice = msgPrice;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getChargeMethod() {
        return chargeMethod;
    }

    public void setChargeMethod(int chargeMethod) {
        this.chargeMethod = chargeMethod;
    }

    public int getOrderFrom() {
        return orderFrom;
    }

    public void setOrderFrom(int orderFrom) {
        this.orderFrom = orderFrom;
    }


    public ListBean getLogisticsInfo() {
        return logisticsInfo;
    }

    public void setLogisticsInfo(ListBean logisticsInfo) {
        this.logisticsInfo = logisticsInfo;
    }

    public List<AddressInfoListBean> getLogisticsAddressInfo() {
        return logisticsAddressInfo;
    }

    public void setLogisticsAddressInfo(List<AddressInfoListBean> logisticsAddressInfo) {
        this.logisticsAddressInfo = logisticsAddressInfo;
    }
}
