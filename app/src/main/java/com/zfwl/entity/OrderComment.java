package com.zfwl.entity;

import java.io.Serializable;

/**
 * Created by ZZB on 2016/12/31.
 */
public class OrderComment implements Serializable {
    private long id;// 1,
    private long memberId;// 1,
    private long orderId;// 1,
    private int depotTime;// 1,
    private int depotOutTime;// 2,
    private int depotServcie;// 3,
    private int serverLove;// 4,
    private long cdate;// 1483028681000,
    private String remark;// ""

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

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public int getDepotTime() {
        return depotTime;
    }

    public void setDepotTime(int depotTime) {
        this.depotTime = depotTime;
    }

    public int getDepotOutTime() {
        return depotOutTime;
    }

    public void setDepotOutTime(int depotOutTime) {
        this.depotOutTime = depotOutTime;
    }

    public int getDepotServcie() {
        return depotServcie;
    }

    public void setDepotServcie(int depotServcie) {
        this.depotServcie = depotServcie;
    }

    public int getServerLove() {
        return serverLove;
    }

    public void setServerLove(int serverLove) {
        this.serverLove = serverLove;
    }

    public long getCdate() {
        return cdate;
    }

    public void setCdate(long cdate) {
        this.cdate = cdate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
