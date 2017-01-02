package com.zfwl.event;

/**
 * Created by ZZB on 2016/12/17.
 */
public class MyPublishEmptyCarEvent {
    private String code;

    public MyPublishEmptyCarEvent(String code) {
        this.code = code;
    }

    public boolean isReloadCore(String c) {
        return code.equals(c);
    }
}
