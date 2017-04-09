package com.zfwl.push.data;

/**
 * 物流信息车辆已抢完向所有已报价且没有生成订单的司机推送消息
 * "extras" : {
 * "type":1,  //推送信息类型 1 物流信息车辆已抢完向所有已报价且没有生成订单的司机推送消息
 * "logisticsInfoId" : 12,       //物流信息id
 * "memberPriceId" : 12       //报价信息id
 * }
 * Created by ZZB on 2017/4/9.
 */
public class QuotedCarRunOut {

    public long logisticsInfoId;
    public long memberPriceId;
}
