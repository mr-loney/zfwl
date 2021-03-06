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
import com.zfwl.widget.ToastUtils;

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

    private Context mContext;
    private SelectAreaCallback mCallback;
    private Address mAddress;
    private int mIdWhoSelect;
    //    private AreaAdapter mProvinceAdapter, mCityAdapter, mDistrictAdapter;
    private SelectAreaPresenter mSelectAreaPresenter;
    private Area mCurrentProvince, mCurrentCity, mCurrentDistrict;

    public interface SelectAreaCallback {
        void onAddressSelected(int idWhoSelect, Address address);

        void onAreaReset();
    }

    private void init(Context context) {
        mContext = context;
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
        if (address != null) {
        }
    }

    @Override
    public void onProvincesLoaded(List<Area> provinces) {
        updateWheelView(mViewProvince, provinces);
        if (mAddress!=null) {
            mCurrentProvince = mAddress.getProvince();
        } else {
            mCurrentProvince = mSelectAreaPresenter.getProvinceWithIndex(0);
        }
        mSelectAreaPresenter.loadNextCity(mCurrentProvince.getId());
    }

    @Override
    public void onCityLoaded(List<Area> citys) {
        updateWheelView(mViewCity, citys);
        if (mAddress!=null) {
            mCurrentCity = mAddress.getCity();
        } else {
            mCurrentCity = mSelectAreaPresenter.getCitysWithIndex(0);
        }
        mSelectAreaPresenter.loadNextDistrict(mCurrentCity.getId());
    }

    @Override
    public void onDistrictLoaded(List<Area> districts) {
        updateWheelView(mViewDistrict, districts);
        if (mAddress!=null) {
            mCurrentDistrict = mAddress.getDistrict();
        } else {
            mCurrentDistrict = mSelectAreaPresenter.getDistrictWithIndex(0);
        }
    }

    @Override
    public void onFailed(String msg) {

        ToastUtils.show(mContext, msg);
    }

    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        if (wheel == mViewProvince) {
            mCurrentProvince = mSelectAreaPresenter.getProvinceWithIndex(newValue);
            mSelectAreaPresenter.loadNextCity(mCurrentProvince.getId());
        } else if (wheel == mViewCity) {
            mCurrentCity = mSelectAreaPresenter.getCitysWithIndex(newValue);
            mSelectAreaPresenter.loadNextDistrict(mCurrentCity.getId());
        } else if (wheel == mViewDistrict) {
            mCurrentDistrict = mSelectAreaPresenter.getDistrictWithIndex(newValue);
        }
    }

    @OnClick(R.id.tv_reset_address)
    public void onResetClick() {
        setVisibility(GONE);
        mCallback.onAreaReset();
    }

    @OnClick(R.id.tv_select_address)
    public void onSelectClick() {
        setVisibility(GONE);
        mAddress = new Address(mCurrentProvince, mCurrentCity, mCurrentDistrict);
        mCallback.onAddressSelected(mIdWhoSelect, mAddress);
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
