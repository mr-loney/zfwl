package com.zfwl.mvp.orders.detail;

import com.zfwl.entity.OrderDetails;
import com.zfwl.mvp.MvpView;

/**
 * Created by ZZB on 2017/1/1.
 */
public interface OrderDetailsMvpView extends MvpView {
    void showLoadOrderDetailsLoading();

    void hideLoadOrderDetailsLoading();

    void onLoadOrderDetailsSuccess(OrderDetails orderDetails);

    void onLoadOrderDetailsFailed(String msg);
}
