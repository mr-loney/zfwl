package com.zfwl.mvp.orders.waitpay;

import com.zfwl.data.api.OrderApi;
import com.zfwl.data.api.retrofit.ApiModule;
import com.zfwl.entity.Order.Type;
import com.zfwl.mvp.BasePresenter;

import retrofit2.Call;
import retrofit2.callback.CustomCallback;

/**
 * Created by ZZB on 2017/1/1.
 */
public class WaitPayOrderPresenter extends BasePresenter<WaitPayOrderMvpView> {
    private OrderApi mOrderApi;

    public WaitPayOrderPresenter() {
        mOrderApi = ApiModule.INSTANCE.provideOrderApi();
    }

//    public void acceptOrder(long orderId) {
//        getMvpView().showLoading();
//        Call<Object> call = mOrderApi.updateOrderStatus(orderId, Type.WAIT_PAY);
//        addCall(call);
//        call.enqueue(new CustomCallback<Object>() {
//            @Override
//            public void onSuccess(Object o) {
//                getMvpView().hideLoading();
//                getMvpView().onPayOrderSuccess();
//            }
//
//            @Override
//            public void onFailure(int code, String msg) {
//                getMvpView().hideLoading();
//                getMvpView().onPayOrderFailed(msg);
//            }
//        });
//    }

    public void cancelOrder(long orderId) {
        getMvpView().showLoading();
        Call<Object> call = mOrderApi.updateOrderStatus(orderId, Type.CANCEL);
        addCall(call);
        call.enqueue(new CustomCallback<Object>() {
            @Override
            public void onSuccess(Object o) {
                getMvpView().hideLoading();
                getMvpView().onCancelOrderSuccess();
            }

            @Override
            public void onFailure(int code, String msg) {
                getMvpView().hideLoading();
                getMvpView().onCancelOrderFailed(msg);
            }
        });
    }
}
