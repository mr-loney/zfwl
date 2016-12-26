package com.zfwl.mvp.orders;

import com.zfwl.common.MyLog;
import com.zfwl.data.api.OrderApi;
import com.zfwl.data.api.retrofit.ApiModule;
import com.zfwl.entity.Order;
import com.zfwl.mvp.BasePresenter;
import com.zfwl.util.FP;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        mOrderApi.getOrders().enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                List<Order> orders = response.body();
                onRefreshOrdersSuccess(orders);
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                onRefreshOrdersFailed(t);
            }
        });
    }

    //加载更多订单
    public void loadMoreOrders() {
        mPage++;
        mOrderApi.getOrders().enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                onLoadMoreOrdersSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                onLoadMoreOrdersFailed(t);
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
