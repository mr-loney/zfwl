package com.zfwl.activity.home;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zfwl.R;
import com.zfwl.adapter.LogisticsAdapter;
import com.zfwl.entity.Address;
import com.zfwl.entity.LogisticsInfo;
import com.zfwl.mvp.logistics.LogisticsMvpView;
import com.zfwl.mvp.logistics.LogisticsPresenter;
import com.zfwl.widget.slsectarea.FromAndToView;
import com.zfwl.widget.slsectarea.SelectAreaListView;
import com.zfwl.widget.slsectarea.SelectAreaView;
import com.zfwl.widget.slsectarea.SelectAreaListView.SelectAreaCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class WLFragment extends Fragment implements SelectAreaCallback,
        LogisticsMvpView, FromAndToView.Callback {
    private static final String TAG = "WLFragment";
    private static final int ID_WHO_SELECT_FROM = 1;
    private static final int ID_WHO_SELECT_TO = 2;
    private Activity mContext;
    @BindView(R.id.tv_area_condition)
    View tvAreaCondition;
    @BindView(R.id.tv_area_all)
    TextView tvAreaAll;
    @BindView(R.id.tv_area_district)
    TextView tvAreaDistrict;
    @BindView(R.id.tv_area_city)
    TextView tvAreaCity;
    @BindView(R.id.tv_area_province)
    TextView tvAreaProvince;
    @BindView(R.id.rv_logistics)
    RecyclerView mRvLogistics;
    @BindView(R.id.view_from_n_to)
    FromAndToView mFromAndToView;
    @BindView(R.id.view_select_area)
    SelectAreaListView mSelectAreaView;
    private LogisticsAdapter mRvAdapter;

    private LogisticsPresenter mLogisticsPresenter;

    public WLFragment() {
    }

    public static WLFragment newInstance() {
        WLFragment fragment = new WLFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_wl, container, false);
        ButterKnife.bind(this, contentView);
        mContext = this.getActivity();
        initPresenters();
        initView();
        return contentView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLogisticsPresenter.detachView();
    }

    private void initPresenters() {
        mLogisticsPresenter = new LogisticsPresenter();
        mLogisticsPresenter.attachView(this);
    }

    private void refreshLogistics() {
        mLogisticsPresenter.refreshLogisticsList();
    }

    private void loadMoreLogistics() {
        mLogisticsPresenter.loadMoreLogisticsList();
    }

    @Override
    public void onRefreshLogisticsListSuccess(List<LogisticsInfo> logistics) {
        mRvAdapter.setItems(logistics);
    }

    @Override
    public void onLoadMoreLogisticsListSuccess(List<LogisticsInfo> logistics) {
        mRvAdapter.addItems(logistics);
    }

    @Override
    public void onLoadLogisticsListFailed(String errorMsg) {

    }

    private void resetTvDetailArea(int viewId) {
        tvAreaAll.setBackgroundResource(0);
        tvAreaDistrict.setBackgroundResource(0);
        tvAreaCity.setBackgroundResource(0);
        tvAreaProvince.setBackgroundResource(0);
        switch (viewId) {
            case R.id.tv_area_all:
                tvAreaAll.setBackgroundResource(R.drawable.bg_rect_blue_corner);
                break;
            case R.id.tv_area_district:
                tvAreaDistrict.setBackgroundResource(R.drawable.bg_rect_blue_corner);
                break;
            case R.id.tv_area_city:
                tvAreaCity.setBackgroundResource(R.drawable.bg_rect_blue_corner);
                break;
            case R.id.tv_area_province:
                tvAreaProvince.setBackgroundResource(R.drawable.bg_rect_blue_corner);
                break;
        }
    }

    @OnClick({R.id.tv_area_all, R.id.tv_area_district, R.id.tv_area_city, R.id.tv_area_province})
    public void onAreaConditionBtnClick(View v) {
        resetTvDetailArea(v.getId());
    }


    @Override
    public void onFromAddressClick(Address address) {
        mSelectAreaView.show(ID_WHO_SELECT_FROM, address);
    }

    @Override
    public void onToAddressClick(Address address) {
        mSelectAreaView.show(ID_WHO_SELECT_TO, address);
    }

    @Override
    public void onStartTimeSelected(long timeInMillis) {

    }

    @Override
    public void onAddressSelected(int idWhoSelect, Address address) {
        switch (idWhoSelect) {
            case ID_WHO_SELECT_FROM:
                mFromAndToView.setFromAddress(address);
                break;
            case ID_WHO_SELECT_TO:
                mFromAndToView.setToAddress(address);
                tvAreaCondition.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onAreaReset() {
        mFromAndToView.resetArea();
        tvAreaCondition.setVisibility(View.GONE);
    }

    private void initView() {
        initRv();
        mFromAndToView.setCallback(this);
        mSelectAreaView.setCallback(this);
    }

    private void initRv() {
        mRvAdapter = new LogisticsAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        mRvLogistics.setLayoutManager(layoutManager);
        mRvLogistics.setAdapter(mRvAdapter);
    }

}
