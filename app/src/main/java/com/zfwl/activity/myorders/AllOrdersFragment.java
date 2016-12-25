package com.zfwl.activity.myorders;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zfwl.R;
import com.zfwl.activity.BaseFragment;
import com.zfwl.adapter.OrdersAdapter;
import com.zfwl.adapter.OrdersAdapter.Callback;
import com.zfwl.entity.Order;
import com.zfwl.widget.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout.BGARefreshLayoutDelegate;
import cn.bingoogolapple.refreshlayout.BGARefreshViewHolder;

/**
 * Created by ZZB on 2016/12/20.
 */
public class AllOrdersFragment extends BaseFragment implements Callback, BGARefreshLayoutDelegate {
    @BindView(R.id.rv_orders)
    RecyclerView mRvOrders;
    @BindView(R.id.refresh_layout)
    BGARefreshLayout mRefreshLayout;
    private OrdersAdapter mOrdersAdapter;
    private Context mContext;

    public AllOrdersFragment() {
    }

    public static AllOrdersFragment newInstance() {
        return new AllOrdersFragment();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_all_orders, container, false);
        ButterKnife.bind(this, contentView);
        initViews();
        loadData();
        return contentView;
    }

    private void initViews() {
        initRv();
        initRefreshLayout();
    }

    private void initRefreshLayout() {
        mRefreshLayout.setDelegate(this);
        BGARefreshViewHolder refreshViewHolder = new BGANormalRefreshViewHolder(getContext(), true);
        mRefreshLayout.setRefreshViewHolder(refreshViewHolder);
    }

    private void initRv() {
        mOrdersAdapter = new OrdersAdapter();
        mOrdersAdapter.setCallback(this);
        mRvOrders.setAdapter(mOrdersAdapter);
        mRvOrders.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
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

    private void loadData() {
        List<Order> orders = new ArrayList<>();
        orders.add(getOrder("yt1", "from1", "to1", 1));
        orders.add(getOrder("yt2", "from2", "to2", 2));
        orders.add(getOrder("yt3", "from3", "to3", 3));
        orders.add(getOrder("yt4", "from4", "to4", 4));
        orders.add(getOrder("yt5", "from5", "to5", 5));
        mOrdersAdapter.setItems(orders);

    }

    private Order getOrder(String name, String from, String to, int type) {
        Order order = new Order();
        order.setGoodsName(name);
        order.setType(type);
        order.setFrom(from);
        order.setTo(to);
        return order;
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {

    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        return false;
    }
}
