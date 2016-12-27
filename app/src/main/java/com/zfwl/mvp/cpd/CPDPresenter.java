package com.zfwl.mvp.cpd;

import android.content.Context;

import com.zfwl.controls.LoadingDialog;
import com.zfwl.data.UserInfoManager;
import com.zfwl.data.api.AreaApi;
import com.zfwl.data.api.CPDApi;
import com.zfwl.data.api.retrofit.ApiModule;
import com.zfwl.entity.Area;
import com.zfwl.entity.CPDModel;
import com.zfwl.mvp.BasePresenter;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;

public class CPDPresenter extends BasePresenter<CPDMvpView> {
    private CPDApi api;
    private LoadingDialog loadingDialog;

    public CPDPresenter(Context context) {
        api = ApiModule.INSTANCE.cpdApi();
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

    public void add(CPDModel data) {
        showloading();
        api.add(UserInfoManager.INSTANCE.getUserInfo().getId()+"",
                data.getFromProvinceId(),
                data.getFromCityId(),
                data.getFromCountyId(),
                data.getToProvinceId(),
                data.getToCityId(),
                data.getToCountyId())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(d -> {
                    getMvpView().onAdded(d);
                    stoploading();
                }, throwable -> {
                    getMvpView().onAddedFail(throwable.toString());
                    stoploading();
                });
    }

    public void del(CPDModel data) {
        showloading();
        api.del(UserInfoManager.INSTANCE.getUserInfo().getId()+"",
                data.getId()+"")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(str -> {
                    getMvpView().onDel();
                    stoploading();
                }, throwable -> {
                    getMvpView().onDelFail(throwable.toString());
                    stoploading();
                });
    }
}
