package com.zfwl.mvp.alipay;

import com.zfwl.mvp.MvpView;

public interface AliPayMvpView extends MvpView {

    void showGetWxPayInfoLoading_Ali();

    void hideGetWxPayInfoLoading_Ali();

    void showCallingPayLoading_Ali();

    void onGetWxPayInfoFailed_Ali(String msg);
}
