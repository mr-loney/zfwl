package com.zfwl.mvp.orders.comment;

import com.zfwl.Exception.ResponseException;
import com.zfwl.data.api.OrderApi;
import com.zfwl.data.api.retrofit.ApiModule;
import com.zfwl.mvp.BasePresenter;

import retrofit2.Call;
import retrofit2.callback.CustomCallback;

/**
 * Created by ZZB on 2017/1/1.
 */
public class OrderCommentPresenter extends BasePresenter<OrderCommentMvpView> {
    private OrderApi mOrderApi;

    public OrderCommentPresenter() {
        mOrderApi = ApiModule.INSTANCE.provideOrderApi();
    }

    public void submitComment(long orderId, int depotTime, int depotOutTime,
                              int depotService, int serverLove, String remark) {
        Call<Object> call = mOrderApi.commentOrder(orderId, depotTime, depotOutTime, depotService, serverLove, remark);
        addCall(call);
        call.enqueue(new CustomCallback<Object>() {
            @Override
            public void onSuccess(Object o) {
                getMvpView().hideLoading();
                getMvpView().onCommentSuccess();
            }

            @Override
            public void onFailure(int code, String msg) {
                getMvpView().hideLoading();
                getMvpView().onCommentFailed(msg);
            }
        });
    }
}
