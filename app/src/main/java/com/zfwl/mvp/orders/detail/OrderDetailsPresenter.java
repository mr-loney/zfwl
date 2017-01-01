package com.zfwl.mvp.orders.detail;

import com.zfwl.Exception.ResponseException;
import com.zfwl.data.api.OrderApi;
import com.zfwl.data.api.retrofit.ApiModule;
import com.zfwl.entity.OrderDetails;
import com.zfwl.mvp.BasePresenter;

import retrofit2.Call;
import retrofit2.callback.CustomCallback;

/**
 * Created by ZZB on 2017/1/1.
 */
public class OrderDetailsPresenter extends BasePresenter<OrderDetailsMvpView> {

    private OrderApi mOrderApi;

    public OrderDetailsPresenter() {
        mOrderApi = ApiModule.INSTANCE.provideOrderApi();
    }

    public void loadOrderDetails(long orderId) {
        getMvpView().showLoadOrderDetailsLoading();
        Call<OrderDetails> call = mOrderApi.getOrderDetails(orderId);
        addCall(call);
        call.enqueue(new CustomCallback<OrderDetails>() {
            @Override
            public void onSuccess(OrderDetails orderDetails) {
                getMvpView().hideLoadOrderDetailsLoading();
                getMvpView().onLoadOrderDetailsSuccess(orderDetails);
            }

            @Override
            public void onFailure(ResponseException exception) {
                getMvpView().hideLoadOrderDetailsLoading();
                getMvpView().onLoadOrderDetailsFailed(exception.getMessage());
            }
        });
    }
}
