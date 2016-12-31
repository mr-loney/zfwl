package com.zfwl.activity.myorders;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jcodecraeer.xrecyclerview.XRecyclerView.LoadingListener;
import com.zfwl.R;
import com.zfwl.activity.BaseFragment;
import com.zfwl.adapter.OrdersAdapter;
import com.zfwl.adapter.OrdersAdapter.Callback;
import com.zfwl.entity.Order;
import com.zfwl.mvp.orders.OrdersMvpView;
import com.zfwl.mvp.orders.OrdersPresenter;
import com.zfwl.widget.ToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ZZB on 2016/12/20.
 */
public class AllOrdersFragment extends BaseFragment implements Callback, OrdersMvpView {
    private static final String ARG_ORDER_TYPE = "ARG_ORDER_TYPE";
    @BindView(R.id.rv_orders)
    XRecyclerView mRvOrders;
    private OrdersAdapter mOrdersAdapter;
    private Context mContext;
    private OrdersPresenter mOrdersPresenter;
    private int mOrderType;
    public AllOrdersFragment() {
    }

    public static AllOrdersFragment newInstance(int orderType) {
        AllOrdersFragment fragment = new AllOrdersFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ORDER_TYPE, orderType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mOrderType = getArguments().getInt(ARG_ORDER_TYPE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_all_orders, container, false);
        ButterKnife.bind(this, contentView);
        initViews();
        initPresenters();
        mRvOrders.refresh();
        return contentView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mOrdersPresenter.detachView();
    }

    private void initPresenters() {
        mOrdersPresenter = new OrdersPresenter();
        mOrdersPresenter.attachView(this);
    }

    private void initViews() {
        initRv();
    }


    private void initRv() {
        mOrdersAdapter = new OrdersAdapter();
        mOrdersAdapter.setCallback(this);
        mRvOrders.setAdapter(mOrdersAdapter);
        mRvOrders.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mRvOrders.setLoadingMoreEnabled(true);
        mRvOrders.setLoadingListener(new LoadingListener() {
            @Override
            public void onRefresh() {
                mOrdersPresenter.refreshOrders(mOrderType);
            }

            @Override
            public void onLoadMore() {
                mOrdersPresenter.loadMoreOrders(mOrderType);
            }
        });
    }

    @Override
    public void onCancelOrderClick(Order order) {
        ToastUtils.show(mContext, "onCancelOrderClick:" + order.toString());
    }

    @Override
    public void onConfirmOrderClick(Order order) {
        ToastUtils.show(mContext, "onConfirmOrderClick:" + order.toString());
    }

    @Override
    public void onContactSalesClick(Order order) {
        ToastUtils.show(mContext, "onContactSalesClick:" + order.toString());
    }

    @Override
    public void onCommentClick(Order order) {
        ToastUtils.show(mContext, "onCommentClick:" + order.toString());
    }

    @Override
    public void showOrderEmptyView() {
        // TODO: 2016/12/26
    }

    @Override
    public void showOrderErrorView(String msg) {
        hideRvLoading();
        ToastUtils.show(mContext, msg);
    }

    @Override
    public void showNoMoreOrdersView() {
        mRvOrders.setNoMore(true);
    }

    @Override
    public void onRefreshOrdersSuccess(List<Order> orders) {
        mOrdersAdapter.setItems(orders);
    }

    @Override
    public void onLoadMoreOrdersSuccess(List<Order> orders) {
        mOrdersAdapter.addItems(orders);
    }

    @Override
    public void onGetOrdersFailed(String msg) {
        hideRvLoading();
        ToastUtils.show(mContext, msg);
    }
    private void hideRvLoading(){
        mRvOrders.loadMoreComplete();
        mRvOrders.refreshComplete();
    }
}
