package com.zfwl.mvp.orders;

import com.zfwl.common.MyLog;
import com.zfwl.data.UserInfoManager;
import com.zfwl.data.api.OrderApi;
import com.zfwl.data.api.retrofit.ApiModule;
import com.zfwl.entity.Order;
import com.zfwl.entity.OrderListResult;
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
    private int mPage;
    private long mMemberId;


    public OrdersPresenter() {
        mOrderApi = ApiModule.INSTANCE.provideOrderApi();
        mMemberId = UserInfoManager.INSTANCE.getMemberId();
    }

    //刷新订单
    public void refreshOrders(int status) {
        MyLog.i(TAG, "refreshOrders, status: %d", status);
        mPage = 0;
        Call<OrderListResult> call = mOrderApi.getOrders(status, mPage, PAGE_SIZE);
        addCall(call);
        call.enqueue(new CustomCallback<OrderListResult>() {
            @Override
            public void onSuccess(OrderListResult response) {
                onRefreshOrdersSuccess(response.list);
            }

            @Override
            public void onFailure(int code, String msg) {
                onRefreshOrdersFailed(code, msg);
            }
        });
    }

    //加载更多订单
    public void loadMoreOrders(int status) {
        mPage++;
        Call<OrderListResult> call = mOrderApi.getOrders(status, mPage, PAGE_SIZE);
        addCall(call);
        call.enqueue(new CustomCallback<OrderListResult>() {
            @Override
            public void onSuccess(OrderListResult response) {
                onLoadMoreOrdersSuccess(response.list);
            }

            @Override
            public void onFailure(int code, String msg) {
                onLoadMoreOrdersFailed(code, msg);
            }
        });
    }

    private void onRefreshOrdersFailed(int code, String msg) {
        MyLog.e(TAG, msg);
        getMvpView().showOrderErrorView(msg);
    }


    private void onLoadMoreOrdersSuccess(List<Order> orders) {
        if (FP.size(orders) < PAGE_SIZE) {
            getMvpView().showNoMoreOrdersView();
        } else {
            getMvpView().onLoadMoreOrdersSuccess(orders);
        }
    }

    private void onLoadMoreOrdersFailed(int code, String msg) {
        getMvpView().onGetOrdersFailed(msg);
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
