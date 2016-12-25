package com.zfwl.mvp.orders;

import com.zfwl.mvp.BasePresenter;

/**
 * Created by ZZB on 2016/12/25.
 */
public class OrdersPresenter extends BasePresenter<OrdersMvpView> {
    private int mOrderType;
    private int mPage;

    public OrdersPresenter(int orderType) {
        mOrderType = orderType;
    }

    public void refreshOrders() {

    }

    public void loadMoreOrders() {
        mPage++;
    }
}
