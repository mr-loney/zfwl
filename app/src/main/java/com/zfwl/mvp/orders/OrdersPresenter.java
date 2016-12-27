package com.zfwl.mvp.orders;

import com.zfwl.Exception.ResponseException;
import com.zfwl.common.MyLog;
import com.zfwl.data.api.OrderApi;
import com.zfwl.data.api.retrofit.ApiModule;
import com.zfwl.entity.Order;
import com.zfwl.mvp.BasePresenter;
import com.zfwl.util.FP;

import java.util.List;

import retrofit2.Call;
import retrofit2.callback.CustomCallback;

/**
 * Created by ZZB on 2016/12/25.
 */
public class OrdersPresenter extends BasePresenter<OrdersMvpView> {
    private static final String TAG = "OrdersPresenter";
    private static final int PAGE_SIZE = 10;
    private OrderApi mOrderApi;
    private int mOrderType;
    private int mPage;
//    private List<Order> mOrders = new ArrayList<>();


    public OrdersPresenter(int orderType) {
        mOrderType = orderType;
        mOrderApi = ApiModule.INSTANCE.provideOrderApi();
    }

    //刷新订单
    public void refreshOrders() {
        MyLog.i(TAG, "refreshOrders");
        mPage = 0;
        Call<List<Order>> call = mOrderApi.getOrders();
        addCall(call);
        call.enqueue(new CustomCallback<List<Order>>() {
            @Override
            public void onSuccess(List<Order> response) {
                onRefreshOrdersSuccess(response);
            }

            @Override
            public void onFailure(ResponseException exception) {
                onRefreshOrdersFailed(exception);
            }
        });
    }

    //加载更多订单
    public void loadMoreOrders() {
        mPage++;
        Call<List<Order>> call = mOrderApi.getOrders();
        addCall(call);
        call.enqueue(new CustomCallback<List<Order>>() {
            @Override
            public void onSuccess(List<Order> response) {
                onLoadMoreOrdersSuccess(response);
            }

            @Override
            public void onFailure(ResponseException exception) {
                onLoadMoreOrdersFailed(exception);
            }
        });
    }

    private void onRefreshOrdersFailed(Throwable t) {
        MyLog.e(TAG, t, "refreshOrders success");
        String msg = t.getMessage();
        getMvpView().showOrderErrorView(msg);
    }


    private void onLoadMoreOrdersSuccess(List<Order> orders) {
        if (FP.size(orders) < PAGE_SIZE) {
            getMvpView().showNoMoreOrdersView();
        } else {
            getMvpView().onLoadMoreOrdersSuccess(orders);
        }
    }

    private void onLoadMoreOrdersFailed(Throwable t) {
        getMvpView().onGetOrdersFailed(t.getMessage());
    }

    private void onRefreshOrdersSuccess(List<Order> orders) {
        MyLog.i(TAG, "refreshOrders success");
        if (FP.empty(orders)) {
            getMvpView().showOrderEmptyView();
        } else {
            getMvpView().onRefreshOrdersSuccess(orders);
        }
    }
}
