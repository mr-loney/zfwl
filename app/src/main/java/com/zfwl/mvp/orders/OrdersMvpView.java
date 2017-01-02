package com.zfwl.mvp.orders;

import com.zfwl.entity.Order;
import com.zfwl.mvp.MvpView;

import java.util.List;

/**
 * Created by ZZB on 2016/12/25.
 */
public interface OrdersMvpView extends MvpView {

    void showOrderEmptyView();

    void showOrderErrorView(String msg);

    void showNoMoreOrdersView();

    void onRefreshOrdersSuccess(List<Order> orders);

    void onLoadMoreOrdersSuccess(List<Order> orders);

    void onGetOrdersFailed(String msg);

    void hideLoading();

}
