package com.zfwl.activity.myorders.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.zfwl.activity.BaseActivity;
import com.zfwl.controls.LoadingDialog;
import com.zfwl.entity.OrderDetails;
import com.zfwl.mvp.orders.detail.OrderDetailsMvpView;
import com.zfwl.mvp.orders.detail.OrderDetailsPresenter;
import com.zfwl.util.TimeUtils;
import com.zfwl.widget.ToastUtils;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by ZZB on 2016/12/31.
 */
public abstract class BaseOrderDetailActivity extends BaseActivity implements OrderDetailsMvpView {
    protected static final String EXTRA_ORDER_ID = "EXTRA_ORDER_ID";
    protected OrderDetailsPresenter mOrderDetailsPresenter = new OrderDetailsPresenter();
    protected LoadingDialog mLoadingDialog;
    protected long mOrderId;
    protected OrderDetails mOrderDetails;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        initExtras();
        mOrderDetailsPresenter.attachView(this);
        mLoadingDialog = new LoadingDialog(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        mOrderDetailsPresenter.detachView();
    }

    protected void initExtras() {
        mOrderId = getIntent().getExtras().getLong(EXTRA_ORDER_ID);
    }

    protected void loadDetails() {
        mOrderDetailsPresenter.loadOrderDetails(mOrderId);
    }

    @Override
    public void showLoadOrderDetailsLoading() {
        mLoadingDialog.show();
    }

    @Override
    public void hideLoadOrderDetailsLoading() {
        mLoadingDialog.hide();
    }

    @Override
    public void onLoadOrderDetailsSuccess(OrderDetails orderDetails) {
        mOrderDetails = orderDetails;
        populateDetails(orderDetails);
    }

    @Override
    public void onLoadOrderDetailsFailed(String msg) {
        ToastUtils.show(this, msg);
    }

    protected abstract void populateDetails(OrderDetails orderDetails);

    public String getQuotedPrice(OrderDetails details) {
        if (details == null || details.getMemberPrice() == null) {
            return "￥0";
        } else {
            return "￥" + details.getMemberPrice().getTotal();
        }
    }
    public String getTimeStr(long millis){
        return TimeUtils.toDefaultDateFormat(millis);
    }
}
