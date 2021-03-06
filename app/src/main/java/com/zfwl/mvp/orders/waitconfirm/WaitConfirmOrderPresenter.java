package com.zfwl.mvp.orders.waitconfirm;

import com.zfwl.data.api.OrderApi;
import com.zfwl.data.api.retrofit.ApiModule;
import com.zfwl.entity.Order.Type;
import com.zfwl.mvp.BasePresenter;

import retrofit2.Call;
import retrofit2.callback.CustomCallback;

/**
 * Created by ZZB on 2017/1/1.
 */
public class WaitConfirmOrderPresenter extends BasePresenter<WaitConfirmOrderMvpView> {
    private OrderApi mOrderApi;

    public WaitConfirmOrderPresenter() {
        mOrderApi = ApiModule.INSTANCE.provideOrderApi();
    }

    public void confirmOrder(long orderId) {
        getMvpView().showWaitConfirmLoading();
        Call<Object> call = mOrderApi.updateOrderStatus(orderId, Type.WAIT_PAY);
        addCall(call);
        call.enqueue(new CustomCallback<Object>() {
            @Override
            public void onSuccess(Object o) {
                getMvpView().hideWaitConfirmLoading();
                getMvpView().onConfirmOrderSuccess();
            }

            @Override
            public void onFailure(int code, String msg) {
                getMvpView().hideWaitConfirmLoading();
                getMvpView().onConfirmOrderFailed(msg);
            }
        });
    }

    public void cancelOrder(long orderId) {
        getMvpView().showWaitConfirmLoading();
        Call<Object> call = mOrderApi.updateOrderStatus(orderId, Type.NOT_ACCEPTED_ORDER);
        addCall(call);
        call.enqueue(new CustomCallback<Object>() {
            @Override
            public void onSuccess(Object o) {
                getMvpView().hideWaitConfirmLoading();
                getMvpView().onCancelOrderSuccess();
            }

            @Override
            public void onFailure(int code, String msg) {
                getMvpView().hideWaitConfirmLoading();
                getMvpView().onCancelOrderFailed(msg);
            }
        });
    }
}
