package com.zfwl.entity;

import com.zfwl.entity.AllzfwlModel.EmptyCarAddressListBean;
import com.zfwl.entity.LogisticsInfo.ListBean.AddressInfoListBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ZZB on 2016/12/31.
 */
public class OrderDetails implements Serializable{

    private long id;// 订单ID
    private long memberId;// 会员ID
    private String orderCode;//订单编号
    private String relationPhone;
    private long emptyCarId;//空车ID
    private long memberPriceId;//报价ID
    private long logisticsId;//物流ID
    private int payMethod;//  支付方式（0-微信支付,）
    private int status;// 订单状态（0-待确认,1-待支付,2-已支付,3-运输中,4-已结束）
    private long waitConfirmTime;//待确认时间
    private long waitPayTime;// 待支付时间
    private long payTime;//  已支付时间
    private long transportTime;//   运输中时间
    private long endTime;//已结束时间
    private double msgPrice;//   信息费
    private double unitPrice;//  单价
    private int chargeMethod;//   收费方式(0-按车辆收费,1-按吨数收费)
    private int orderFrom;//单据来源(0-空车生成,1-报价生成)
    private LogisticsInfo.ListBean logisticsInfo;// 订单对应的物流信息的对象  （详细属性参考物流文档）
    private List<AddressInfoListBean> addressInfoList;//物流地址信息
    private OrderEmptyCar memberEmptyCar;//  -- 空车信息
    private List<EmptyCarAddressListBean> emptyCarAddressList;//- 空车地址信息
    private MemberPrice memberPrice;// 报价信息
    private OrderComment orderComment;//订单评论信息

    public OrderEmptyCar getMemberEmptyCar() {
        return memberEmptyCar;
    }

    public void setMemberEmptyCar(OrderEmptyCar memberEmptyCar) {
        this.memberEmptyCar = memberEmptyCar;
    }

    public List<EmptyCarAddressListBean> getEmptyCarAddressList() {
        return emptyCarAddressList;
    }

    public void setEmptyCarAddressList(List<EmptyCarAddressListBean> emptyCarAddressList) {
        this.emptyCarAddressList = emptyCarAddressList;
    }

    public String getRelationPhone() {
        return relationPhone;
    }

    public void setRelationPhone(String relationPhone) {
        this.relationPhone = relationPhone;
    }

    public MemberPrice getMemberPrice() {
        return memberPrice;
    }

    public void setMemberPrice(MemberPrice memberPrice) {
        this.memberPrice = memberPrice;
    }

    public OrderComment getOrderComment() {
        return orderComment;
    }

    public void setOrderComment(OrderComment orderComment) {
        this.orderComment = orderComment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public long getEmptyCarId() {
        return emptyCarId;
    }

    public void setEmptyCarId(long emptyCarId) {
        this.emptyCarId = emptyCarId;
    }

    public long getMemberPriceId() {
        return memberPriceId;
    }

    public void setMemberPriceId(long memberPriceId) {
        this.memberPriceId = memberPriceId;
    }

    public long getLogisticsId() {
        return logisticsId;
    }

    public void setLogisticsId(long logisticsId) {
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

    public LogisticsInfo.ListBean getLogisticsInfo() {
        return logisticsInfo;
    }

    public void setLogisticsInfo(LogisticsInfo.ListBean logisticsInfo) {
        this.logisticsInfo = logisticsInfo;
    }

    public List<AddressInfoListBean> getAddressInfoList() {
        return addressInfoList;
    }

    public void setAddressInfoList(List<AddressInfoListBean> addressInfoList) {
        this.addressInfoList = addressInfoList;
    }

    public static class MemberPrice implements Serializable{
        private long id;// 1,
        private long memberId;// 1,
        private int carNumber;// 8,
        private int loadNumber;// 13,
        private int priceType;// 1,
        private double price;// 23,
        private int total;// 299,
        private int status;// 0,

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public long getMemberId() {
            return memberId;
        }

        public void setMemberId(long memberId) {
            this.memberId = memberId;
        }

        public int getCarNumber() {
            return carNumber;
        }

        public void setCarNumber(int carNumber) {
            this.carNumber = carNumber;
        }

        public int getLoadNumber() {
            return loadNumber;
        }

        public void setLoadNumber(int loadNumber) {
            this.loadNumber = loadNumber;
        }

        public int getPriceType() {
            return priceType;
        }

        public void setPriceType(int priceType) {
            this.priceType = priceType;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }

    public static class OrderEmptyCar implements Serializable{
        private long id;
        private long memberId;
        private long fromProvinceId;// 110000,
        private long fromCityId;// 110100,
        private long fromCountyId;// 110101,
        private String fromAddressName;// 北京市-市辖区-东城区 aaaa,
        private String goDate;// 2016-12-29 10;//00;//00.0,
        private int carNumber;// 5,
        private int loadNumber;// 10,
        private int carLength;// 15,
        private long cdate;// 1482908283000,
        private long mdate;// 1482908282000,
        private String remark;// null,
        private int successOfferNum;// null,
        private int offerNum;// null,
        private String address;// null,
        private int succAndTotal;// null,
        private String fromAddress;// null,
        private String toAddress;// null,
        private String fromProvinceName;// null,
        private String fromCityName;// null,
        private String fromCountyName;// null,
        private int breachNum;// null,
        private String account;// null,
        private List<EmptyCarAddressListBean> emptyCarAddressList;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public long getMemberId() {
            return memberId;
        }

        public void setMemberId(long memberId) {
            this.memberId = memberId;
        }

        public long getFromProvinceId() {
            return fromProvinceId;
        }

        public void setFromProvinceId(long fromProvinceId) {
            this.fromProvinceId = fromProvinceId;
        }

        public long getFromCityId() {
            return fromCityId;
        }

        public void setFromCityId(long fromCityId) {
            this.fromCityId = fromCityId;
        }

        public long getFromCountyId() {
            return fromCountyId;
        }

        public void setFromCountyId(long fromCountyId) {
            this.fromCountyId = fromCountyId;
        }

        public String getFromAddressName() {
            return fromAddressName;
        }

        public void setFromAddressName(String fromAddressName) {
            this.fromAddressName = fromAddressName;
        }

        public String getGoDate() {
            return goDate;
        }

        public void setGoDate(String goDate) {
            this.goDate = goDate;
        }

        public int getCarNumber() {
            return carNumber;
        }

        public void setCarNumber(int carNumber) {
            this.carNumber = carNumber;
        }

        public int getLoadNumber() {
            return loadNumber;
        }

        public void setLoadNumber(int loadNumber) {
            this.loadNumber = loadNumber;
        }

        public int getCarLength() {
            return carLength;
        }

        public void setCarLength(int carLength) {
            this.carLength = carLength;
        }

        public long getCdate() {
            return cdate;
        }

        public void setCdate(long cdate) {
            this.cdate = cdate;
        }

        public long getMdate() {
            return mdate;
        }

        public void setMdate(long mdate) {
            this.mdate = mdate;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getSuccessOfferNum() {
            return successOfferNum;
        }

        public void setSuccessOfferNum(int successOfferNum) {
            this.successOfferNum = successOfferNum;
        }

        public int getOfferNum() {
            return offerNum;
        }

        public void setOfferNum(int offerNum) {
            this.offerNum = offerNum;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getSuccAndTotal() {
            return succAndTotal;
        }

        public void setSuccAndTotal(int succAndTotal) {
            this.succAndTotal = succAndTotal;
        }

        public String getFromAddress() {
            return fromAddress;
        }

        public void setFromAddress(String fromAddress) {
            this.fromAddress = fromAddress;
        }

        public String getToAddress() {
            return toAddress;
        }

        public void setToAddress(String toAddress) {
            this.toAddress = toAddress;
        }

        public String getFromProvinceName() {
            return fromProvinceName;
        }

        public void setFromProvinceName(String fromProvinceName) {
            this.fromProvinceName = fromProvinceName;
        }

        public String getFromCityName() {
            return fromCityName;
        }

        public void setFromCityName(String fromCityName) {
            this.fromCityName = fromCityName;
        }

        public String getFromCountyName() {
            return fromCountyName;
        }

        public void setFromCountyName(String fromCountyName) {
            this.fromCountyName = fromCountyName;
        }

        public int getBreachNum() {
            return breachNum;
        }

        public void setBreachNum(int breachNum) {
            this.breachNum = breachNum;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public List<EmptyCarAddressListBean> getEmptyCarAddressList() {
            return emptyCarAddressList;
        }

        public void setEmptyCarAddressList(List<EmptyCarAddressListBean> emptyCarAddressList) {
            this.emptyCarAddressList = emptyCarAddressList;
        }
    }

}
