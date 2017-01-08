package com.zfwl.mvp.logistics;

import android.content.Context;

import com.zfwl.controls.LoadingDialog;
import com.zfwl.data.UserInfoManager;
import com.zfwl.data.api.AddLogisticsApi;
import com.zfwl.data.api.MyQuotedApi;
import com.zfwl.data.api.retrofit.ApiModule;
import com.zfwl.entity.AllzfwlModel;
import com.zfwl.entity.MyQuotedModel;
import com.zfwl.mvp.BasePresenter;

import rx.android.schedulers.AndroidSchedulers;

public class MyQuotedPresenter extends BasePresenter<MyQuotedMvpView> {
    private MyQuotedApi api;
    private LoadingDialog loadingDialog;

    public MyQuotedPresenter(Context context) {
        api = ApiModule.INSTANCE.quotedApi();
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
                    getMvpView().onGetListFailed(throwable.getMessage());
                    stoploading();
                });
    }

   public void del(MyQuotedModel.ListBean d) {
        api.del(d.getId())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(str -> {
                    getMvpView().onDel();
                    stoploading();
                }, throwable -> {
                    getMvpView().onDelFail(throwable.getMessage());
                    stoploading();
                });
    }

}
