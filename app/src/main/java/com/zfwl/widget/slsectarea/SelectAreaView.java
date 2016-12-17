package com.zfwl.widget.slsectarea;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.zfwl.R;
import com.zfwl.controls.wheel.widget.OnWheelChangedListener;
import com.zfwl.controls.wheel.widget.WheelView;
import com.zfwl.controls.wheel.widget.adapters.ListWheelAdapter;
import com.zfwl.entity.Address;
import com.zfwl.entity.Area;
import com.zfwl.mvp.selectarea.SelectAreaMvpView;
import com.zfwl.mvp.selectarea.SelectAreaPresenter;
import com.zfwl.util.FP;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ZZB on 2016/12/16.
 */
public class SelectAreaView extends FrameLayout implements SelectAreaMvpView, OnWheelChangedListener {
    private static final String TAG = "SelectAreaView";
    @BindView(R.id.id_province)
    WheelView mViewProvince;
    @BindView(R.id.id_city)
    WheelView mViewCity;
    @BindView(R.id.id_district)
    WheelView mViewDistrict;

    private SelectAreaCallback mCallback;
    private Address mAddress;
    private int mIdWhoSelect;
//    private AreaAdapter mProvinceAdapter, mCityAdapter, mDistrictAdapter;
    private SelectAreaPresenter mSelectAreaPresenter;
    private Area mCurrentProvince, mCurrentCity, mCurrentDistrict;

    public interface SelectAreaCallback {
        void onAreaSelected(int idWhoSelect, Address address);

        void onAreaReset();
    }

    private void init(Context context) {
        inflate(context, R.layout.layout_select_area, this);
        ButterKnife.bind(this, this);
        initWheelView(mViewProvince);
        initWheelView(mViewCity);
        initWheelView(mViewDistrict);
        mSelectAreaPresenter = new SelectAreaPresenter(context);
        mSelectAreaPresenter.attachView(this);
        mSelectAreaPresenter.loadProvincesIfNeeded();
    }

    public void show(int idWhoSelect, Address address) {
        mIdWhoSelect = idWhoSelect;
        setVisibility(VISIBLE);
        mAddress = address;
        //// TODO: 2016/12/17 get list by area
    }

    @Override
    public void onProvincesLoaded(List<Area> provinces) {
        updateProvinces(provinces);
    }

    private String[] toStrArr(List<Area> areas) {
        String[] arr = new String[FP.size(areas)];
        int index = 0;
        for (Area area : areas) {
            arr[index++] = area.getName();
        }
        return arr;
    }

    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        if (wheel == mViewProvince) {
            updateCities(newValue);
        } else if (wheel == mViewCity) {
            updateDistricts(newValue);
        } else if (wheel == mViewDistrict) {
            mCurrentDistrict = mSelectAreaPresenter.getDistrict(mCurrentProvince.getId(), mCurrentCity.getId(), newValue);
        }
    }
    private void updateProvinces(List<Area> provinces){
        updateWheelView(mViewProvince, provinces);
        updateCities(0);
        updateDistricts(0);
    }
    private void updateCities(int provinceId) {
        mCurrentProvince = mSelectAreaPresenter.getProvince(provinceId);
        List<Area> cityList = mSelectAreaPresenter.getCityListByProvince(mCurrentProvince);
        updateWheelView(mViewCity, cityList);
        updateDistricts(0);
    }

    private void updateDistricts(int cityId) {
        mCurrentCity = mSelectAreaPresenter.getCity(mCurrentProvince.getId(), cityId);
        List<Area> districtList = mSelectAreaPresenter.getDistrictListByProvince(mCurrentCity);
        updateWheelView(mViewDistrict, districtList);
    }



    @OnClick(R.id.tv_reset_address)
    public void onResetClick() {
        setVisibility(GONE);
        mCallback.onAreaReset();
    }

    @OnClick(R.id.tv_select_address)
    public void onSelectClick() {
        setVisibility(GONE);
        mCallback.onAreaSelected(mIdWhoSelect, mAddress);
    }

    @OnClick(R.id.root_view)
    public void onRootViewClick() {
        setVisibility(GONE);
    }

    public void setCallback(SelectAreaCallback callback) {
        mCallback = callback;
    }


    private void updateWheelView(WheelView view, List<Area> areas) {
        AreaAdapter adapter = new AreaAdapter(view.getContext(), areas);
//        adapter.setItems(areas);
        view.setViewAdapter(adapter);
        view.setCurrentItem(0);
    }

    private void initWheelView(WheelView view) {
//        adapter = new AreaAdapter(view.getContext());
        view.addChangingListener(this);
    }

    public SelectAreaView(Context context) {
        super(context);
        init(context);
    }

    public SelectAreaView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    public SelectAreaView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mSelectAreaPresenter.detachView();
    }

    private static class AreaAdapter extends ListWheelAdapter<Area> {
        protected AreaAdapter(Context context, List<Area> items) {
            super(context, items);
        }

        @Override
        public String itemToText(Area item) {
            return item.getName();
        }
    }
}
