package com.zfwl.mvp.selectarea;

import com.zfwl.entity.Area;
import com.zfwl.mvp.MvpView;

import java.util.List;

/**
 * Created by ZZB on 2016/12/17.
 */
public interface SelectAreaMvpView extends MvpView {

    void onProvincesLoaded(List<Area> provinces);

    void onCityLoaded(List<Area> citys);

    void onDistrictLoaded(List<Area> districts);

    void onFailed(String msg);
}
