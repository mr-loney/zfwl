package com.zfwl.mvp.orders.waitpay;

import com.zfwl.mvp.MvpView;

/**
 * Created by ZZB on 2017/1/1.
 */
public interface WaitPayOrderMvpView extends MvpView {

    void showWaitPayOrderLoading();

    void hideWaitPayOrderLoading();

    void onPayOrderSuccess();

    void onPayOrderFailed(String msg);

    void onCancelOrderSuccess();

    void onCancelOrderFailed(String msg);


}
