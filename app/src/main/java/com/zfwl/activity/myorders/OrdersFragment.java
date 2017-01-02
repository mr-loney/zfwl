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
import com.zfwl.activity.myorders.detail.CarryingOrderDetailActivity;
import com.zfwl.activity.myorders.detail.FinishedOrderDetailActivity;
import com.zfwl.activity.myorders.detail.PaidOrderDetailActivity;
import com.zfwl.activity.myorders.detail.WaitConfirmOrderDetailActivity;
import com.zfwl.activity.myorders.detail.WaitPayOrderDetailActivity;
import com.zfwl.adapter.OrdersAdapter;
import com.zfwl.adapter.OrdersAdapter.Callback;
import com.zfwl.common.MyLog;
import com.zfwl.controls.LoadingDialog;
import com.zfwl.entity.Order;
import com.zfwl.entity.Order.Type;
import com.zfwl.mvp.orders.OrdersMvpView;
import com.zfwl.mvp.orders.OrdersPresenter;
import com.zfwl.mvp.orders.waitconfirm.WaitConfirmOrderMvpView;
import com.zfwl.mvp.orders.waitconfirm.WaitConfirmOrderPresenter;
import com.zfwl.widget.ToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ZZB on 2016/12/20.
 */
public class OrdersFragment extends BaseFragment implements Callback, OrdersMvpView, WaitConfirmOrderMvpView {
    private static final String TAG = "OrdersFragment";
    private static final String ARG_ORDER_TYPE = "ARG_ORDER_TYPE";
    @BindView(R.id.rv_orders)
    XRecyclerView mRvOrders;
    private LoadingDialog mLoadingDialog;
    private OrdersAdapter mOrdersAdapter;
    private Context mContext;
    private OrdersPresenter mOrdersPresenter;
    private WaitConfirmOrderPresenter mWaitConfirmOrderPresenter;
    private int mOrderType;

    public OrdersFragment() {
    }

    public static OrdersFragment newInstance(int orderType) {
        MyLog.i(TAG, "new orders fragment, type: %b", orderType);
        OrdersFragment fragment = new OrdersFragment();
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
        mWaitConfirmOrderPresenter = new WaitConfirmOrderPresenter();
        mWaitConfirmOrderPresenter.attachView(this);
    }

    private void initViews() {
        initRv();
        mLoadingDialog = new LoadingDialog(mContext);
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
        mWaitConfirmOrderPresenter.cancelOrder(order.getId());
    }

    @Override
    public void onConfirmOrderClick(Order order) {
        mWaitConfirmOrderPresenter.confirmOrder(order.getId());
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
    public void onOrderClick(Order order) {
        int orderId = order.getId();
        switch (order.getStatus()) {
            case Type.WAIT_CONFIRM:
                WaitConfirmOrderDetailActivity.launch(getActivity(), orderId);
                break;
            case Type.PAID:
                PaidOrderDetailActivity.launch(getActivity(), orderId);
                break;
            case Type.WAIT_PAY:
                WaitPayOrderDetailActivity.launch(getActivity(), orderId);
                break;
            case Type.CARRYING:
                CarryingOrderDetailActivity.launch(getActivity(), orderId);
                break;
            case Type.FINISHED:
                FinishedOrderDetailActivity.launch(getActivity(), orderId);
                break;
            default:
                ToastUtils.show(getActivity(), "未知订单：" + orderId);
                break;
        }
    }

    @Override
    public void showOrderEmptyView() {
        // TODO: 2016/12/26
    }

    @Override
    public void showOrderErrorView(String msg) {
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
        ToastUtils.show(mContext, msg);
    }

    @Override
    public void hideLoading() {
        hideRvLoading();
    }

    private void hideRvLoading() {
        mRvOrders.loadMoreComplete();
        mRvOrders.refreshComplete();
    }

    @Override
    public void showWaitConfirmLoading() {
        mLoadingDialog.show();
    }

    @Override
    public void hideWaitConfirmLoading() {
        mLoadingDialog.hide();
    }

    @Override
    public void onConfirmOrderSuccess() {
        mRvOrders.refresh();
        ToastUtils.show(mContext, "确认订单成功");
    }

    @Override
    public void onConfirmOrderFailed(String msg) {
        ToastUtils.show(mContext, "确认订单失败");
    }

    @Override
    public void onCancelOrderSuccess() {
        mRvOrders.refresh();
        ToastUtils.show(mContext, "取消订单成功");
    }

    @Override
    public void onCancelOrderFailed(String msg) {
        ToastUtils.show(mContext, "取消订单失败");
    }
}
