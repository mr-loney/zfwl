package com.zfwl.widget.slsectarea;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.zfwl.R;
import com.zfwl.adapter.SelectAreaAdapter;
import com.zfwl.controls.wheel.widget.OnWheelChangedListener;
import com.zfwl.controls.wheel.widget.WheelView;
import com.zfwl.controls.wheel.widget.adapters.ListWheelAdapter;
import com.zfwl.entity.Address;
import com.zfwl.entity.Area;
import com.zfwl.mvp.selectarea.SelectAreaMvpView;
import com.zfwl.mvp.selectarea.SelectAreaPresenter;
import com.zfwl.widget.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectAreaListView extends FrameLayout implements SelectAreaMvpView {
    private static final String TAG = "SelectAreaListView";
    @BindView(R.id.id_province)
    ListView mViewProvince;
    @BindView(R.id.id_city)
    ListView mViewCity;
    @BindView(R.id.id_district)
    ListView mViewDistrict;

    private Context mContext;
    private SelectAreaCallback mCallback;
    private Address mAddress;
    private int mIdWhoSelect;
    //    private AreaAdapter mProvinceAdapter, mCityAdapter, mDistrictAdapter;
    private SelectAreaPresenter mSelectAreaPresenter;
    private Area mCurrentProvince, mCurrentCity, mCurrentDistrict;

    SelectAreaAdapter provinceAdapter;
    SelectAreaAdapter cityAdapter;
    SelectAreaAdapter districtAdapter;

    public interface SelectAreaCallback {
        void onAddressSelected(int idWhoSelect, Address address);

        void onAreaReset();

        void onAreaClose();
    }

    private void init(Context context) {
        mContext = context;
        inflate(context, R.layout.layout_select_list_area, this);
        ButterKnife.bind(this, this);
        mSelectAreaPresenter = new SelectAreaPresenter(context);
        mSelectAreaPresenter.attachView(this);
        mSelectAreaPresenter.loadProvincesIfNeeded();

        provinceAdapter = new SelectAreaAdapter(mContext,new ArrayList<>());
        provinceAdapter.setListener(new SelectAreaAdapter.OnAdapterListener() {
            @Override
            public void selected(Area area) {
                mCurrentProvince = area;
                mSelectAreaPresenter.loadNextCity(mCurrentProvince.getId());
                mCurrentCity = null;
                mCurrentDistrict = null;
                mAddress.setCity(null);
                mAddress.setDistrict(null);
                districtAdapter.setDatas(new ArrayList<>());
                districtAdapter.setSelectArea(null);
                districtAdapter.notifyDataSetChanged();
            }
        });
        cityAdapter = new SelectAreaAdapter(mContext,new ArrayList<>());
        cityAdapter.setListener(new SelectAreaAdapter.OnAdapterListener() {
            @Override
            public void selected(Area area) {
                mCurrentCity = area;
                mSelectAreaPresenter.loadNextDistrict(mCurrentCity.getId());
                mCurrentDistrict = null;
                mAddress.setDistrict(null);
            }
        });
        districtAdapter = new SelectAreaAdapter(mContext,new ArrayList<>());
        districtAdapter.setListener(new SelectAreaAdapter.OnAdapterListener() {
            @Override
            public void selected(Area area) {
                mCurrentDistrict = area;
                onSelectClick();
            }
        });
        mViewProvince.setAdapter(provinceAdapter);
        mViewCity.setAdapter(cityAdapter);
        mViewDistrict.setAdapter(districtAdapter);
    }

    public void show(int idWhoSelect, Address address) {
        mIdWhoSelect = idWhoSelect;
        setVisibility(VISIBLE);
        mAddress = address;
        if (address != null) {
            onProvincesLoaded(null);
        }
    }

    @Override
    public void onProvincesLoaded(List<Area> provinces) {
        if (mAddress!=null && mAddress.getProvince()!=null) {
            mCurrentProvince = mAddress.getProvince();
        mSelectAreaPresenter.loadNextCity(mCurrentProvince.getId());
        } else {
//            mCurrentProvince = mSelectAreaPresenter.getProvinceWithIndex(0);
            mCurrentCity = null;
            cityAdapter.setDatas(new ArrayList<>());
            cityAdapter.setSelectArea(null);
            cityAdapter.notifyDataSetChanged();
            mCurrentDistrict = null;
            districtAdapter.setDatas(new ArrayList<>());
            districtAdapter.setSelectArea(null);
            districtAdapter.notifyDataSetChanged();
        }
        if (provinces!=null) {
            provinceAdapter.setDatas(provinces);
        }
        if (mCurrentProvince!=null) {
            provinceAdapter.setSelectArea(mCurrentProvince.getId());
        }
        provinceAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCityLoaded(List<Area> citys) {
        if (mAddress!=null && mAddress.getCity()!=null) {
            mCurrentCity = mAddress.getCity();
        mSelectAreaPresenter.loadNextDistrict(mCurrentCity.getId());
        } else {
//            mCurrentCity = mSelectAreaPresenter.getCitysWithIndex(0);
        }
        cityAdapter.setDatas(citys);
        if (mCurrentCity!=null) {
            cityAdapter.setSelectArea(mCurrentCity.getId());
        }
        cityAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDistrictLoaded(List<Area> districts) {
        if (mAddress!=null && mAddress.getDistrict()!=null) {
            mCurrentDistrict = mAddress.getDistrict();
        } else {
//            mCurrentDistrict = mSelectAreaPresenter.getDistrictWithIndex(0);
        }
        districtAdapter.setDatas(districts);
        if (mCurrentDistrict!=null) {
            districtAdapter.setSelectArea(mCurrentDistrict.getId());
        }
        districtAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailed(String msg) {
        ToastUtils.show(mContext, msg);
    }

    @OnClick(R.id.tv_reset_address)
    public void onResetClick() {
        setVisibility(GONE);
        mCallback.onAreaClose();
        mCallback.onAreaReset();
    }

    @OnClick(R.id.tv_select_address)
    public void onSelectClick() {
        setVisibility(GONE);
        mCallback.onAreaClose();
        mAddress = new Address(mCurrentProvince, mCurrentCity, mCurrentDistrict);
        mCallback.onAddressSelected(mIdWhoSelect, mAddress);
    }

    @OnClick(R.id.root_view)
    public void onRootViewClick() {
        setVisibility(GONE);
        mCallback.onAreaClose();
    }

    public void setCallback(SelectAreaCallback callback) {
        mCallback = callback;
    }


    public SelectAreaListView(Context context) {
        super(context);
        init(context);
    }

    public SelectAreaListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    public SelectAreaListView(Context context, AttributeSet attrs, int defStyleAttr) {
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
