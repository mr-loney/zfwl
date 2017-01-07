package com.zfwl.mvp.logistics;

import android.content.Context;

import com.zfwl.controls.LoadingDialog;
import com.zfwl.data.UserInfoManager;
import com.zfwl.data.api.AddLogisticsApi;
import com.zfwl.data.api.retrofit.ApiModule;
import com.zfwl.entity.AllzfwlModel;
import com.zfwl.mvp.BasePresenter;

import rx.android.schedulers.AndroidSchedulers;

public class AddLogisticsPresenter extends BasePresenter<AddLogisticsMvpView> {
    private AddLogisticsApi api;
    private LoadingDialog loadingDialog;

    public AddLogisticsPresenter(Context context) {
        api = ApiModule.INSTANCE.addLogisticsApi();
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

    public void addLogistics(AllzfwlModel data) {
        showloading();

        String toStr = "{";
        int index=1;
        for (AllzfwlModel.EmptyCarAddressListBean to : data.getEmptyCarAddressList()) {
            toStr += "'emptyCarAddress_"+index+"':"+
                    "{\"provinceId\":\""+to.getToProvinceId()+"\","+
                    "\"cityId\":\""+to.getToCityId()+"\","+
                    "\"countyId\":\""+to.getToCountyId()+"\","+
                    "\"addressDetail\":\""+to.getToProvinceName()+" "+to.getToCityName()+" "+to.getToCountyName()+"\"}";
        }
        toStr+="}";

        api.add(UserInfoManager.INSTANCE.getUserInfo().getId()+"",
                data.getFromProvinceId(),
                data.getFromCityId(),
                data.getFromCountyId(),
                data.getFromAddressName(),
                toStr,
                data.getCarNumber(),
                data.getLoadNumber(),
                data.getCarLength(),
                data.getGoDate())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(d -> {
                    getMvpView().onAddLogisticsSuccess(d);
                    stoploading();
                }, throwable -> {
                    getMvpView().onAddLogisticsFailed(throwable.getMessage());
                    stoploading();
                });
    }

}
