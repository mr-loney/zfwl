package com.zfwl.activity.home;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.zfwl.R;
import com.zfwl.adapter.LogisticsAdapter;
import com.zfwl.entity.Address;
import com.zfwl.entity.LogisticsInfo;
import com.zfwl.mvp.logistics.LogisticsMvpView;
import com.zfwl.mvp.logistics.LogisticsPresenter;
import com.zfwl.widget.slsectarea.FromAndToView;
import com.zfwl.widget.slsectarea.SelectAreaView;
import com.zfwl.widget.slsectarea.SelectAreaView.SelectAreaCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class WLFragment extends Fragment implements View.OnClickListener, SelectAreaCallback,
        LogisticsMvpView, FromAndToView.Callback {
    private static final String TAG = "WLFragment";
    private static final int ID_WHO_SELECT_FROM = 1;
    private static final int ID_WHO_SELECT_TO = 2;
    View mContentView;
    private Activity mContext;

    private View tv_detail_area;
    private TextView tv_detail_area1, tv_detail_area2, tv_detail_area3, tv_detail_area4;
    private int select_tv_detail_area_index = 1;

    @BindView(R.id.rv_logistics)
    UltimateRecyclerView mRvLogistics;
    @BindView(R.id.view_from_n_to)
    FromAndToView mFromAndToView;
    @BindView(R.id.view_select_area)
    SelectAreaView mSelectAreaView;
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
        mContentView = inflater.inflate(R.layout.fragment_wl, container, false);
        ButterKnife.bind(this, mContentView);
        mContext = this.getActivity();
        initPresenters();
        initView();
        return mContentView;
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


    private void resetTvDetailArea() {
        tv_detail_area1.setBackgroundResource(0);
        tv_detail_area2.setBackgroundResource(0);
        tv_detail_area3.setBackgroundResource(0);
        tv_detail_area4.setBackgroundResource(0);

        switch (select_tv_detail_area_index) {
            case 1:
                tv_detail_area1.setBackgroundResource(R.drawable.bg_rect_blue_corner);
                break;
            case 2:
                tv_detail_area2.setBackgroundResource(R.drawable.bg_rect_blue_corner);
                break;
            case 3:
                tv_detail_area3.setBackgroundResource(R.drawable.bg_rect_blue_corner);
                break;
            case 4:
                tv_detail_area4.setBackgroundResource(R.drawable.bg_rect_blue_corner);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_detail_area1: {
                select_tv_detail_area_index = 1;
                resetTvDetailArea();
                break;
            }
            case R.id.tv_detail_area2: {
                select_tv_detail_area_index = 2;
                resetTvDetailArea();
                break;
            }
            case R.id.tv_detail_area3: {
                select_tv_detail_area_index = 3;
                resetTvDetailArea();
                break;
            }
            case R.id.tv_detail_area4: {
                select_tv_detail_area_index = 4;
                resetTvDetailArea();
                break;
            }

        }
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
    public void onAreaSelected(int idWhoSelect, Address address) {
        switch (idWhoSelect) {
            case ID_WHO_SELECT_FROM:
                mFromAndToView.setFromAddress(address);
                break;
            case ID_WHO_SELECT_TO:
                mFromAndToView.setToAddress(address);
                break;
        }
    }

    @Override
    public void onAreaReset() {
        mFromAndToView.resetArea();
    }

    private void initView() {
        initRv();
        mFromAndToView.setCallback(this);
        mSelectAreaView.setCallback(this);
        tv_detail_area = mContentView.findViewById(R.id.tv_detail_area);
        tv_detail_area1 = (TextView) mContentView.findViewById(R.id.tv_detail_area1);
        tv_detail_area1.setOnClickListener(this);
        tv_detail_area2 = (TextView) mContentView.findViewById(R.id.tv_detail_area2);
        tv_detail_area2.setOnClickListener(this);
        tv_detail_area3 = (TextView) mContentView.findViewById(R.id.tv_detail_area3);
        tv_detail_area3.setOnClickListener(this);
        tv_detail_area4 = (TextView) mContentView.findViewById(R.id.tv_detail_area4);
        tv_detail_area4.setOnClickListener(this);


    }

    private void initRv() {
        mRvAdapter = new LogisticsAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        mRvLogistics.setLayoutManager(layoutManager);
        mRvLogistics.setAdapter(mRvAdapter);
        mRvLogistics.setDefaultOnRefreshListener(this::refreshLogistics);
        mRvLogistics.setOnLoadMoreListener((itemsCount, maxLastVisiblePosition) -> loadMoreLogistics());
    }

}
