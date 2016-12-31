package com.zfwl.entity;

import com.zfwl.entity.AllzfwlModel.EmptyCarAddressListBean;
import com.zfwl.entity.LogisticsInfo.ListBean.AddressInfoListBean;

import java.util.List;

/**
 * Created by ZZB on 2016/12/31.
 */
public class OrderDetails {

    private long id;
    private long memberId;
    private int orderCode;
    private long emptyCarId;
    private long memberPriceId;
    private long logisticsId;
    private int payMethod;
    private int status;
    private long waitConfirmTime;
    private long waitPayTime;
    private long payTime;
    private long transportTime;
    private long endTime;
    private double msgPrice;
    private double unitPrice;
    private int chargeMethod;
    private int orderFrom;
    private LogisticsInfo logisticsInfo;
    private AddressInfoListBean logisticsAddressInfo;
    private OrderEmptyCar memberEmptyCar;
    private List<EmptyCarAddressListBean> emptyCarAddressList;
    private MemberPrice memberPrice;
    private OrderComment orderComment;

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

    public int getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(int orderCode) {
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

    public LogisticsInfo getLogisticsInfo() {
        return logisticsInfo;
    }

    public void setLogisticsInfo(LogisticsInfo logisticsInfo) {
        this.logisticsInfo = logisticsInfo;
    }

    public AddressInfoListBean getLogisticsAddressInfo() {
        return logisticsAddressInfo;
    }

    public void setLogisticsAddressInfo(AddressInfoListBean logisticsAddressInfo) {
        this.logisticsAddressInfo = logisticsAddressInfo;
    }

    public static class MemberPrice {
        private long id;// 1,
        private long memberId;// 1,
        private int carNumber;// 8,
        private int loadNumber;// 13,
        private int priceType;// 1,
        private int price;// 23,
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

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
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

    public static class OrderEmptyCar {
        private long id;
        private long memberId;
        private long fromProvinceId;// 110000,
        private long fromCityId;// 110100,
        private long fromCountyId;// 110101,
        private String fromAddressName;// 北京市-市辖区-东城区 aaaa,
        private long goDate;// 2016-12-29 10;//00;//00.0,
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

        public long getGoDate() {
            return goDate;
        }

        public void setGoDate(long goDate) {
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
