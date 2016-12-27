package com.zfwl.Exception;

/**
 * Created by ZZB on 2016/12/27.
 */
public class ResponseException extends RuntimeException {
    private int code;

    public ResponseException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public ResponseException(Throwable cause) {
        super(cause);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
