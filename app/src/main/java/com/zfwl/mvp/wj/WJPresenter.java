package com.zfwl.mvp.wj;

import android.content.Context;

import com.zfwl.controls.LoadingDialog;
import com.zfwl.data.UserInfoManager;
import com.zfwl.data.api.CPDApi;
import com.zfwl.data.api.WJApi;
import com.zfwl.data.api.retrofit.ApiModule;
import com.zfwl.entity.CPDModel;
import com.zfwl.mvp.BasePresenter;

import rx.android.schedulers.AndroidSchedulers;

public class WJPresenter extends BasePresenter<WJMvpView> {
    private WJApi api;
    private LoadingDialog loadingDialog;

    public WJPresenter(Context context) {
        api = ApiModule.INSTANCE.wjApi();
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

    public void getList() {
        showloading();
        api.getList(UserInfoManager.INSTANCE.getUserInfo().getId()+"")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(datas -> {
                    getMvpView().onListLoaded(datas);
                    stoploading();
        }, throwable -> {
                    getMvpView().onListLoadedFail(throwable.toString());
                    stoploading();
        });
    }
}
