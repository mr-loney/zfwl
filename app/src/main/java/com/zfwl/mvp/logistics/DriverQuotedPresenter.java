package com.zfwl.mvp.logistics;

import android.content.Context;

import com.zfwl.controls.LoadingDialog;
import com.zfwl.data.UserInfoManager;
import com.zfwl.data.api.LogisticsApi;
import com.zfwl.data.api.retrofit.ApiModule;
import com.zfwl.entity.CPDModel;
import com.zfwl.entity.request.LogisticsRequest;
import com.zfwl.mvp.BasePresenter;
import com.zfwl.util.FP;

import java.util.HashMap;
import java.util.Map;

import retrofit2.http.Field;
import rx.android.schedulers.AndroidSchedulers;

public class DriverQuotedPresenter extends BasePresenter<DriverQuotedMvpView> {

    private static final String TAG = "DriverQuotedPresenter";
    private LogisticsApi api;
    private LoadingDialog loadingDialog;

    public DriverQuotedPresenter(Context context) {
        api = ApiModule.INSTANCE.provideLogisticsApi();
        loadingDialog = new LoadingDialog(context);
    }

    private void showloading() {
        if (!loadingDialog.isShowing()) {
            loadingDialog.show();
        }
    }

    private void stoploading() {
        if (loadingDialog.isShowing()) {
            loadingDialog.stop();
        }
    }

    public void add(String logisticsId,
                    int carNumber,
                    double loadNumber,
                    int priceType,
                    double price) {
        showloading();
        api.add(logisticsId,carNumber,loadNumber,priceType,price)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(d -> {
                    getMvpView().onAddSuccess(d);
                    stoploading();
                }, throwable -> {
                    getMvpView().onAddFailed(throwable.toString());
                    stoploading();
                });
    }

}
