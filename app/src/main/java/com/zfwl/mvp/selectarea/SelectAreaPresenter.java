package com.zfwl.mvp.selectarea;

import android.content.Context;

import com.zfwl.controls.LoadingDialog;
import com.zfwl.data.api.AreaApi;
import com.zfwl.data.api.retrofit.ApiModule;
import com.zfwl.entity.Area;
import com.zfwl.mvp.BasePresenter;
import com.zfwl.util.ViewHub;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by ZZB on 2016/12/17.
 */
public class SelectAreaPresenter extends BasePresenter<SelectAreaMvpView> {
    private AreaApi api;
    private LoadingDialog loadingDialog;
    private List<Area> mProvinces, mCitys, mDistricts;

    public SelectAreaPresenter(Context context) {
        api = ApiModule.INSTANCE.provideAreaApi();
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

    public void loadProvincesIfNeeded() {
        String parentID = "0086";
        api.findDatas(parentID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(areas -> {
                    mProvinces = areas;
            getMvpView().onProvincesLoaded(areas);
        }, throwable -> {
                    getMvpView().onFailed(throwable.getMessage());
        });
    }

    public void loadNextCity(String parentID) {
        showloading();
        api.findDatas(parentID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(areas -> {
                    mCitys = areas;
                    getMvpView().onCityLoaded(areas);
                    stoploading();
                }, throwable -> {
                    getMvpView().onFailed(throwable.getMessage());
                    stoploading();
                });
    }

    public void loadNextDistrict(String parentID) {
        showloading();
        api.findDatas(parentID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(areas -> {
                    mDistricts = areas;
                    getMvpView().onDistrictLoaded(areas);
                    stoploading();
                }, throwable -> {
                    getMvpView().onFailed(throwable.getMessage());
                    stoploading();
                });
    }

    public Area getProvinceWithIndex(int i) {
        return mProvinces.get(i);
    }
    public Area getCitysWithIndex(int i) {
        return mCitys.get(i);
    }
    public Area getDistrictWithIndex(int i) {
        return mDistricts.get(i);
    }

 public Area getProvince(String id) {
        for (Area a : mProvinces) {
            if (a.getId().equals(id)) {
                return a;
            }
        }
        return null;
    }
    public Area getCitys(String id) {
        for (Area a : mCitys) {
            if (a.getId().equals(id)) {
                return a;
            }
        }
        return null;

    }
    public Area getDistrict(String id) {
        for (Area a : mDistricts) {
            if (a.getId().equals(id)) {
                return a;
            }
        }
        return null;

    }
}
