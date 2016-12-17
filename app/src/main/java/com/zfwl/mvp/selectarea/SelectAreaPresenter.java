package com.zfwl.mvp.selectarea;

import android.content.Context;

import com.zfwl.entity.Area;
import com.zfwl.mvp.BasePresenter;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by ZZB on 2016/12/17.
 */
public class SelectAreaPresenter extends BasePresenter<SelectAreaMvpView> {
    private AreaModel mAreaModel;
    private boolean mIsDataLoaded = false;

    public SelectAreaPresenter(Context context) {
        mAreaModel = new AreaModel(context);
    }

    public void loadProvincesIfNeeded() {
//        if (mIsDataLoaded) {
//            return;
//        }
        mAreaModel.asyncLoadData()
//                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(areas -> {
                    mIsDataLoaded = true;
                    getMvpView().onProvincesLoaded(areas);
                });
    }

    public List<Area> getCityListByProvince(Area province) {
        return mAreaModel.getCityListByProvince(province);
    }

    public List<Area> getDistrictListByProvince(Area city) {
        return mAreaModel.getDistrictListByCity(city);
    }

    public Area getProvince(int id) {
        return mAreaModel.getProvinces().get(id);
    }

    public Area getCity(int provinceId, int cityId) {
        return mAreaModel.getProvinces().get(provinceId).getSubAreas().get(cityId);
    }

    public Area getDistrict(int provinceId, int cityId, int districtId) {
        return getCity(provinceId, cityId).getSubAreas().get(districtId);
    }
}
