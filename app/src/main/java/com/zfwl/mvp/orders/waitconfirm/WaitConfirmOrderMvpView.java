package com.zfwl.mvp.orders.waitconfirm;

import com.zfwl.mvp.MvpView;

/**
 * Created by ZZB on 2017/1/1.
 */
public interface WaitConfirmOrderMvpView extends MvpView {

    void showWaitConfirmLoading();

    void hideWaitConfirmLoading();

    void onConfirmOrderSuccess();

    void onConfirmOrderFailed(String msg);

    void onCancelOrderSuccess();

    void onCancelOrderFailed(String msg);
}
