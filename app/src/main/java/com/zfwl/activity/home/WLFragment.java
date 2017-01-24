package com.zfwl.activity.home;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

import com.zfwl.R;
import com.zfwl.activity.GoodsDetailActivity;
import com.zfwl.adapter.LogisticsAdapter;
import com.zfwl.adapter.SelectTimeAdapter;
import com.zfwl.controls.pulltorefresh.PullToRefreshListView.OnLoadMoreListener;
import com.zfwl.controls.pulltorefresh.PullToRefreshListView.OnRefreshListener;
import com.zfwl.controls.pulltorefresh.PullToRefreshListViewEx;
import com.zfwl.entity.Address;
import com.zfwl.entity.LogisticsInfo.ListBean;
import com.zfwl.event.RefreshWlEvent;
import com.zfwl.mvp.logistics.LogisticsMvpView;
import com.zfwl.mvp.logistics.LogisticsPresenter;
import com.zfwl.widget.ToastUtils;
import com.zfwl.widget.slsectarea.FromAndToView;
import com.zfwl.widget.slsectarea.FromAndToView.Callback;
import com.zfwl.widget.slsectarea.SelectAreaListView;
import com.zfwl.widget.slsectarea.SelectAreaListView.SelectAreaCallback;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class WLFragment extends Fragment implements SelectAreaCallback,
        LogisticsMvpView, Callback, OnLoadMoreListener, OnRefreshListener {
    private static final String TAG = "WLFragment";
    private static final int ID_WHO_SELECT_FROM = 1;
    private static final int ID_WHO_SELECT_TO = 2;
    @BindView(R.id.rv_select_time)
    RecyclerView mRvSelectTime;
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
    //    @BindView(R.id.rv_logistics)
//    RecyclerView mRvLogistics;
    @BindView(R.id.pull_refresh_listview_items)
    PullToRefreshListViewEx pullRefreshListView;
    ;
    @BindView(R.id.view_empty)
    View emptyView;
    @BindView(R.id.view_from_n_to)
    FromAndToView mFromAndToView;
    @BindView(R.id.view_select_area)
    SelectAreaListView mSelectAreaView;
    @BindView(R.id.layout_select_time)
    View mSelectTimeView;
    private LogisticsAdapter adapter;
    private LogisticsPresenter mLogisticsPresenter;

    private int selectindex = 0;
    private Address from;
    private Address to;
    private String sendData;

    public WLFragment() {
    }

    public static WLFragment newInstance() {
        WLFragment fragment = new WLFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
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
        EventBus.getDefault().unregister(this);
        mLogisticsPresenter.detachView();
    }

    private void initPresenters() {
        mLogisticsPresenter = new LogisticsPresenter(mContext);
        mLogisticsPresenter.attachView(this);
    }

    @Override
    public void onRefreshLogisticsListSuccess(List<ListBean> logistics) {

        pullRefreshListView.onRefreshComplete();
        if (adapter == null) {
            adapter = new LogisticsAdapter(mContext);
            pullRefreshListView.setAdapter(adapter);
        }
        adapter.mList = logistics;
        adapter.notifyDataSetChanged();
        showEmptyView(logistics.size() == 0);
    }

    @Override
    public void onLoadMoreLogisticsListSuccess(List<ListBean> logistics) {

        pullRefreshListView.onLoadMoreComplete();
        adapter.mList.addAll(logistics);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadLogisticsListFailed(String errorMsg) {
        ToastUtils.show(mContext, errorMsg);
    }

    private void resetTvDetailArea(int viewId) {
        tvAreaAll.setBackgroundResource(0);
        tvAreaDistrict.setBackgroundResource(0);
        tvAreaCity.setBackgroundResource(0);
        tvAreaProvince.setBackgroundResource(0);
        tvAreaAll.setTextColor(getResources().getColor(R.color.black));
        tvAreaDistrict.setTextColor(getResources().getColor(R.color.black));
        tvAreaCity.setTextColor(getResources().getColor(R.color.black));
        tvAreaProvince.setTextColor(getResources().getColor(R.color.black));
        switch (viewId) {
            case R.id.tv_area_all:
                selectindex = 0;
                tvAreaAll.setBackgroundResource(R.drawable.bg_rect_blue_corner);
                tvAreaAll.setTextColor(getResources().getColor(R.color.white));
                break;
            case R.id.tv_area_district:
                selectindex = 1;
                tvAreaDistrict.setBackgroundResource(R.drawable.bg_rect_blue_corner);
                tvAreaDistrict.setTextColor(getResources().getColor(R.color.white));
                break;
            case R.id.tv_area_city:
                selectindex = 2;
                tvAreaCity.setBackgroundResource(R.drawable.bg_rect_blue_corner);
                tvAreaCity.setTextColor(getResources().getColor(R.color.white));
                break;
            case R.id.tv_area_province:
                selectindex = 3;
                tvAreaProvince.setBackgroundResource(R.drawable.bg_rect_blue_corner);
                tvAreaProvince.setTextColor(getResources().getColor(R.color.white));
                break;
        }

        onRefresh();
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
    public void onCloseView() {
        mSelectAreaView.setVisibility(View.GONE);
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
    public void onAreaClose() {
        mFromAndToView.setNormalStat();
        mFromAndToView.mTvFrom.setTag("0");
        mFromAndToView.mTvTo.setTag("0");
        mFromAndToView.mTvStartTime.setTag("0");
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
        pullRefreshListView.setCanLoadMore(true);
        pullRefreshListView.setCanRefresh(true);
        pullRefreshListView.setMoveToFirstItemAfterRefresh(true);
        pullRefreshListView.setOnRefreshListener(this);
        pullRefreshListView.setOnLoadListener(this);
        pullRefreshListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                GoodsDetailActivity.launch(mContext, adapter.mList.get(i - 1));
            }
        });

        // 刷新数据
        showEmptyView(false);
        emptyView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                pullRefreshListView.pull2RefreshManually();

                if (pullRefreshListView != null) {
                    if (pullRefreshListView.isCanRefresh())
                        pullRefreshListView.onRefreshComplete();

                    if (pullRefreshListView.isCanLoadMore())
                        pullRefreshListView.onLoadMoreComplete();
                }

            }
        });

        onRefresh();
    }

    /**
     * 显示空数据视图
     */
    private void showEmptyView(boolean show) {
        pullRefreshListView.setVisibility(show ? View.GONE : View.VISIBLE);
        emptyView.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onRefresh() {
        showEmptyView(false);
        loadData(true);
    }

    @Override
    public void onLoadMore() {
        loadData(false);
    }

    private void loadData(boolean isRefresh) {
        Address from = mFromAndToView.getFromAddress();
        Address to = mFromAndToView.getToAddress();
        String fromP = "";
        String fromC = "";
        String fromD = "";
        if (from != null) {
            if (from.getProvince() != null) {
                fromP = from.getProvince().getId();
            }
            if (from.getCity() != null) {
                fromC = from.getCity().getId();
            }
            if (from.getDistrict() != null) {
                fromD = from.getDistrict().getId();
            }
        }
        String toP = "";
        String toC = "";
        String toD = "";
        if (to != null) {
            if (to.getProvince() != null) {
                toP = to.getProvince().getId();
            }
            if (to.getCity() != null) {
                toC = to.getCity().getId();
            }
            if (to.getDistrict() != null) {
                toD = to.getDistrict().getId();
            }
        }
        String time = mFromAndToView.mTvStartTime.getText().toString();
        if (time.equals("发车时间") || time.equals("全部时间")) {
            time = "";
        }
        mLogisticsPresenter.getLogisticsList(isRefresh, fromP, fromC, fromD, toP, toC, toD, time);
    }

    private void initRv() {
        SelectTimeAdapter selectTimeAdapter = new SelectTimeAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        mRvSelectTime.setLayoutManager(layoutManager);
        mRvSelectTime.setAdapter(selectTimeAdapter);
        mFromAndToView.setSelectTime(mSelectTimeView, selectTimeAdapter);
    }
    @Subscribe
    public void onReceiveRefresh(RefreshWlEvent event) {
        onRefresh();
    }
}
