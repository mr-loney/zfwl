package com.zfwl.mvp.logistics;

import android.content.Context;

import com.zfwl.controls.LoadingDialog;
import com.zfwl.data.UserInfoManager;
import com.zfwl.data.api.LogisticsApi;
import com.zfwl.data.api.retrofit.ApiModule;
import com.zfwl.entity.LogisticsInfo;
import com.zfwl.mvp.BasePresenter;

import retrofit2.http.Field;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by ZZB on 2016/12/15.
 */
public class LogisticsPresenter extends BasePresenter<LogisticsMvpView> {

    private static final String TAG = "LogisticsPresenter";
    private static final int PAGE_SIZE = 10;
    private LogisticsApi api;
    private int mPage;
    private LoadingDialog loadingDialog;

    public LogisticsPresenter(Context context) {
        api = ApiModule.INSTANCE.provideLogisticsApi();
        loadingDialog = new LoadingDialog(context);
    }

    private void showloading(){
        if (!loadingDialog.isShowing()) {
            loadingDialog.show();
        }
    }
    private void stoploading(){
        if (loadingDialog.isShowing()) {
            loadingDialog.stop();
        }
    }

    public void getLogisticsList(boolean isrefresh,String fromProvince,
                                      String fromCity,
                                      String fromCounty,
                                      String toProvince,
                                      String toCity,
                                      String toCounty,
                                      String sendDate) {
        if (isrefresh) {
            mPage = 0;
        } else {
            mPage++;
        }
        showloading();
        final boolean isRe = isrefresh;
        api.getLogistics(fromProvince,fromCity,fromCounty,toProvince,toCity,toCounty,sendDate,mPage,20)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(info -> {
                    if (isRe) {
                        getMvpView().onRefreshLogisticsListSuccess(info.getList());
                    } else {
                        getMvpView().onLoadMoreLogisticsListSuccess(info.getList());
                    }
                    stoploading();
                }, throwable -> {
                    getMvpView().onLoadLogisticsListFailed(throwable.toString());
                    stoploading();
                });
    }

}
