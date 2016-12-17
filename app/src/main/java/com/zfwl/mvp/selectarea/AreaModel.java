package com.zfwl.mvp.selectarea;

import android.content.Context;
import android.content.res.AssetManager;

import com.zfwl.common.MyLog;
import com.zfwl.entity.Area;
import com.zfwl.util.Utils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by ZZB on 2016/12/16.
 */
public class AreaModel {
    private static final String TAG = "AreaModel";
    private Context mContext;
    List<Area> mProvinces = new ArrayList<>();
    private boolean mIsDataLoaded = false;

    public AreaModel(Context context) {
        mContext = context.getApplicationContext();
    }

    public Observable<List<Area>> asyncLoadData() {

        if (mIsDataLoaded) {
            return Observable.just(mProvinces);
        }
        return Observable.create(new OnSubscribe<List<Area>>() {
            @Override
            public void call(Subscriber<? super List<Area>> subscriber) {
                initData();
                subscriber.onNext(mProvinces);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io());
    }

    private void initData() {
        if(Utils.isMainThread()){
            throw new RuntimeException("load data on main thread");
        }
        long start = System.currentTimeMillis();
        AssetManager asset = mContext.getAssets();
        try {
            InputStream input = asset.open("province_data.xml");
            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser parser = spf.newSAXParser();
            AreaXmlParser xmlParser = new AreaXmlParser();
            parser.parse(input, xmlParser);
            input.close();
            mProvinces = xmlParser.getProvinces();
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
        }
        long duration = System.currentTimeMillis() - start;
        mIsDataLoaded = true;
        MyLog.i(TAG, "initData, parse xml takes: %d ms", duration);
    }

    public List<Area> getProvinces() {
        return mProvinces;
    }

    public List<Area> getCityListByProvince(Area province) {
        return mProvinces.get(province.getId()).getSubAreas();
    }

    public List<Area> getDistrictListByCity(Area city) {
        try {
            Area province = mProvinces.get(city.getParentId());
            List<Area> cityList = getDistrictListByCity(province);
            return cityList.get(city.getId()).getSubAreas();
        } catch (Exception e) {
            MyLog.e(TAG, e, "getDistricts failed");
            return new ArrayList<>();
        }
    }
}
