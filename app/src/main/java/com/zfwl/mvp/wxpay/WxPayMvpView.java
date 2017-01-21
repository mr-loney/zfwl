package com.zfwl.mvp.wxpay;

import com.zfwl.mvp.MvpView;

/**
 * Created by ZZB on 2017/1/21.
 */
public interface WxPayMvpView extends MvpView {

    void showGetWxPayInfoLoading();

    void hideGetWxPayInfoLoading();

    void showCallingPayLoading();

    void onGetWxPayInfoFailed(String msg);
}
