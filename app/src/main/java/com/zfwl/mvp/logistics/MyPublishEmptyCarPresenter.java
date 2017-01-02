package com.zfwl.mvp.logistics;

import android.content.Context;

import com.zfwl.controls.LoadingDialog;
import com.zfwl.data.api.MyPublishEmptyCarApi;
import com.zfwl.data.api.retrofit.ApiModule;
import com.zfwl.mvp.BasePresenter;

import rx.android.schedulers.AndroidSchedulers;

public class MyPublishEmptyCarPresenter extends BasePresenter<MyPublishEmptyCarMvpView> {
    private MyPublishEmptyCarApi api;
    private LoadingDialog loadingDialog;

    public MyPublishEmptyCarPresenter(Context context) {
        api = ApiModule.INSTANCE.publishEmptyCarApi();
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

    public void getList(int pageIndex,int pageSize) {
        showloading();
        api.getList(pageIndex,pageSize)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(d -> {
                    getMvpView().onGetListSuccess(d);
                    stoploading();
                }, throwable -> {
                    getMvpView().onGetListFailed(throwable.toString());
                    stoploading();
                });
    }

    public void del(int id) {
        showloading();
        api.del(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(d -> {
                    getMvpView().onDelSuccess(d);
                    stoploading();
                }, throwable -> {
                    getMvpView().onDelFail(throwable.toString());
                    stoploading();
                });
    }


}
