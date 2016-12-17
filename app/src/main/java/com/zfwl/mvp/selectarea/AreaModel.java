package com.zfwl.mvp.selectarea;

import android.content.Context;
import android.content.res.AssetManager;

import com.zfwl.common.MyLog;
import com.zfwl.entity.Area;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by ZZB on 2016/12/16.
 */
public class AreaModel {
    private static final String TAG = "AreaModel";
    private Context mContext;
    List<Area> mProvinces = new ArrayList<>();

    public AreaModel(Context context) {
        mContext = context.getApplicationContext();
        asyncLoadData();
    }
    private void asyncLoadData(){
        Observable.just("").observeOn(Schedulers.io()).subscribe(s -> {
            initData();
        });
    }
    private void initData() {
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
            asset.close();
        }
        long duration = System.currentTimeMillis() - start;
        MyLog.i(TAG, "initData, parse xml takes: %d ms", duration);
    }

    public List<Area> getCityList(Area province) {
        return mProvinces.get(province.getId()).getSubAreas();
    }

    public List<Area> getDistrictList(Area city) {
        try {
            Area province = mProvinces.get(city.getParentId());
            List<Area> cityList = getCityList(province);
            return cityList.get(city.getId()).getSubAreas();// or directly return?
        } catch (Exception e) {
            MyLog.e(TAG, e, "getDistricts failed");
            return new ArrayList<>();
        }
    }
}
