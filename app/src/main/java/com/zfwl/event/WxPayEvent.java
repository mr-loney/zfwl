package com.zfwl.event;

/**
 * Created by ZZB on 2017/1/21.
 */
public class WxPayEvent {
    private int code;

    public WxPayEvent(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
