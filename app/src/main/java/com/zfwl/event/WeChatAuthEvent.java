package com.zfwl.event;

/**
 * Created by ZZB on 2016/12/10.
 */
public class WeChatAuthEvent {
    private String code;

    public WeChatAuthEvent(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
