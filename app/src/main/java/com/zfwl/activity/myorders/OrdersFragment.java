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
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.zfwl.R;
import com.zfwl.activity.BaseFragment;
import com.zfwl.activity.PaySuccessActivity;
import com.zfwl.activity.myorders.detail.CarryingOrderDetailActivity;
import com.zfwl.activity.myorders.detail.FinishedOrderDetailActivity;
import com.zfwl.activity.myorders.detail.PaidOrderDetailActivity;
import com.zfwl.activity.myorders.detail.WaitCommentDetailActivity;
import com.zfwl.activity.myorders.detail.WaitConfirmOrderDetailActivity;
import com.zfwl.activity.myorders.detail.WaitPayOrderDetailActivity;
import com.zfwl.adapter.OrdersAdapter;
import com.zfwl.adapter.OrdersAdapter.Callback;
import com.zfwl.common.Const.WeChat;
import com.zfwl.common.MyLog;
import com.zfwl.common.Nav;
import com.zfwl.controls.LoadingDialog;
import com.zfwl.entity.Order;
import com.zfwl.entity.Order.Type;
import com.zfwl.event.ClearOrderReadPointEvent;
import com.zfwl.event.RefreshOrderListEvent;
import com.zfwl.event.WxPayEvent;
import com.zfwl.mvp.orders.OrdersMvpView;
import com.zfwl.mvp.orders.OrdersPresenter;
import com.zfwl.mvp.orders.waitconfirm.WaitConfirmOrderMvpView;
import com.zfwl.mvp.orders.waitconfirm.WaitConfirmOrderPresenter;
import com.zfwl.mvp.wxpay.WxPayMvpView;
import com.zfwl.mvp.wxpay.WxPayPresenter;
import com.zfwl.widget.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ZZB on 2016/12/20.
 */
public class OrdersFragment extends BaseFragment implements Callback, OrdersMvpView, WaitConfirmOrderMvpView, WxPayMvpView {
    private static final String TAG = "OrdersFragment";
    private static final String ARG_ORDER_TYPE = "ARG_ORDER_TYPE";
    @BindView(R.id.rv_orders)
    XRecyclerView mRvOrders;
    private LoadingDialog mLoadingDialog;
    private OrdersAdapter mOrdersAdapter;
    private Context mContext;
    private OrdersPresenter mOrdersPresenter;
    private WaitConfirmOrderPresenter mWaitConfirmOrderPresenter;
    private WxPayPresenter mWxPayPresenter;
    private int mOrderType;
    private boolean mInited;
    private IWXAPI mWxApi;

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
    protected void onVisible() {
        if (mInited) {
            EventBus.getDefault().post(new ClearOrderReadPointEvent(mOrderType));
            mRvOrders.refresh();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        mOrderType = getArguments().getInt(ARG_ORDER_TYPE);
        mWxApi = WXAPIFactory.createWXAPI(mContext, WeChat.APP_ID);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_all_orders, container, false);
        ButterKnife.bind(this, contentView);
        initViews();
        initPresenters();
        mRvOrders.refresh();
        mInited = true;
        return contentView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        mWaitConfirmOrderPresenter.detachView();
        mOrdersPresenter.detachView();
        mWxPayPresenter.detachView();
    }

    private void initPresenters() {
        mOrdersPresenter = new OrdersPresenter();
        mOrdersPresenter.attachView(this);
        mWaitConfirmOrderPresenter = new WaitConfirmOrderPresenter();
        mWaitConfirmOrderPresenter.attachView(this);
        mWxPayPresenter = new WxPayPresenter();
        mWxPayPresenter.attachView(this);
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
        Nav.toDialPhonePage(mContext, order.getRelationPhone());
    }

    @Override
    public void onCommentClick(Order order) {
        WaitCommentDetailActivity.launch(mContext, order.getId());
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
            case Type.WAIT_COMMENT:
                WaitCommentDetailActivity.launch(getActivity(), orderId);
                break;
            case Type.COMMENTED:
                FinishedOrderDetailActivity.launch(getActivity(), orderId);
                break;
            default:
                ToastUtils.show(getActivity(), "未知订单：" + orderId);
                break;
        }
    }

    @Override
    public void onPayOrderClick(Order order) {
        mWxPayPresenter.wechatPay(mWxApi, System.currentTimeMillis() + "", "goodsDesc", 1);
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
        PaySuccessActivity.launch(mContext, 0, "00");
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

    @Subscribe
    public void refreshOrder(RefreshOrderListEvent event) {
        if (mRvOrders != null) {
            mRvOrders.refresh();
        }
    }

    @Subscribe
    public void onWxPayFinished(WxPayEvent event) {
        switch (event.getCode()) {
            case 1:
                ToastUtils.show(mContext, "支付成功");
                break;
            case 2:
                ToastUtils.show(mContext, "取消支付");
                break;
            default:
                ToastUtils.show(mContext, "支付失败, " + event.getCode());
                break;
        }
    }

    @Override
    public void showGetWxPayInfoLoading() {
        mLoadingDialog.start("获取订单中...");
    }

    @Override
    public void hideGetWxPayInfoLoading() {
        mLoadingDialog.stop();
    }

    @Override
    public void showCallingPayLoading() {
        ToastUtils.show(mContext, "正在调起支付...");
    }

    @Override
    public void onGetWxPayInfoFailed(String msg) {
        ToastUtils.show(mContext, "创建订单失败");
    }


}
