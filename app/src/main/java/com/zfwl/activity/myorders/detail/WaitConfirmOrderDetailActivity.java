package com.zfwl.activity.myorders.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zfwl.R;
import com.zfwl.entity.LogisticsInfo.ListBean;
import com.zfwl.entity.OrderDetails;
import com.zfwl.entity.OrderDetails.OrderEmptyCar;
import com.zfwl.mvp.orders.waitconfirm.WaitConfirmOrderMvpView;
import com.zfwl.mvp.orders.waitconfirm.WaitConfirmOrderPresenter;
import com.zfwl.util.AddressUtils;
import com.zfwl.util.StringUtils;
import com.zfwl.util.TimeUtils;
import com.zfwl.widget.ToastUtils;
import com.zfwl.widget.goodsdetail.KeyValueItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.titlebar.BGATitleBar;

/**
 * 待确认
 */
public class WaitConfirmOrderDetailActivity extends BaseOrderDetailActivity implements WaitConfirmOrderMvpView {

    @BindView(R.id.title_bar)
    BGATitleBar mTitleBar;
    @BindView(R.id.tv_from)
    TextView mTvFrom;
    @BindView(R.id.layout_pack_place)
    LinearLayout mLayoutPackPlace;
    @BindView(R.id.tv_to)
    TextView mTvTo;
    @BindView(R.id.layout_to)
    LinearLayout mLayoutTo;
    @BindView(R.id.item_begin_time)
    KeyValueItem mItemBeginTime;
    @BindView(R.id.item_big_car_passable)
    KeyValueItem mItemBigCarPassable;
    @BindView(R.id.item_goods_name)
    KeyValueItem mItemGoodsName;
    @BindView(R.id.item_goods_weight)
    KeyValueItem mItemGoodsWeight;
    @BindView(R.id.item_goods_length)
    KeyValueItem mItemGoodsLength;
    @BindView(R.id.item_need_car_number)
    KeyValueItem mItemNeedCarNumber;
    @BindView(R.id.item_my_quoted_price)
    KeyValueItem mItemMyQuotedPrice;
    @BindView(R.id.item_order_number)
    KeyValueItem mItemOrderNumber;
    @BindView(R.id.item_order_create_time)
    KeyValueItem mItemOrderCreateTime;
    @BindView(R.id.tv_remark)
    TextView mTvRemark;
    private WaitConfirmOrderPresenter mWaitConfirmOrderPresenter;

    public static void launch(Context context, long orderId) {
        Intent intent = new Intent(context, WaitConfirmOrderDetailActivity.class);
        intent.putExtra(EXTRA_ORDER_ID, orderId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_confirm_order_detail);
        ButterKnife.bind(this);
        initViews();
        mWaitConfirmOrderPresenter = new WaitConfirmOrderPresenter();
        mWaitConfirmOrderPresenter.attachView(this);
        loadDetails();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWaitConfirmOrderPresenter.detachView();
    }

    @Override
    protected void populateDetails(OrderDetails orderDetails) {
        OrderEmptyCar carInfo = orderDetails.getMemberEmptyCar();
        ListBean logisticsInfo = orderDetails.getLogisticsInfo();

        //详细信息
        if (logisticsInfo != null) {
            mTvFrom.setText(AddressUtils.getFromAddressStr(logisticsInfo.getAddressInfoList()));
            mTvTo.setText(AddressUtils.getToAddressStr(logisticsInfo.getAddressInfoList()));
            mItemBigCarPassable.setText("大货通行", logisticsInfo.getIsLargeGoDesc());
            mItemGoodsName.setText("物品名称", logisticsInfo.getGoodsName());
            mItemGoodsWeight.setText("货物重量(吨)", StringUtils.removeTrailingZero(logisticsInfo.getWeight() + ""));
            mItemGoodsLength.setText("货物长度(米)", StringUtils.removeTrailingZero(logisticsInfo.getLength() + ""));
            mItemNeedCarNumber.setText("需要车辆", logisticsInfo.getCarNum() + "");
            mItemBeginTime.setText("发车时间", getTimeStr(logisticsInfo.getDepartureTime()));
            mTvRemark.setText(logisticsInfo.getRemark());
        }


        //我的报价
        mItemMyQuotedPrice.setValueTextColor(0xfffa5547);
        mItemMyQuotedPrice.setText("我的报价", getQuotedPrice(orderDetails));

        mItemOrderNumber.setText("订单编号", orderDetails.getOrderCode());
        mItemOrderCreateTime.setText("创建时间", TimeUtils.getDefaultTimeStamp(orderDetails.getWaitConfirmTime()));
    }

    private void initViews() {
        initDefaultTitleBar(mTitleBar);
    }


    @OnClick({R.id.btn_cancel_order, R.id.btn_confirm_order})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_cancel_order:
                onCancelOrderClick();
                break;
            case R.id.btn_confirm_order:
                onConfirmOrderClick();
                break;
        }
    }

    private void onCancelOrderClick() {
        mWaitConfirmOrderPresenter.cancelOrder(mOrderId);
    }

    private void onConfirmOrderClick() {
        mWaitConfirmOrderPresenter.confirmOrder(mOrderId);
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
        ToastUtils.show(this, "确认订单成功");
    }

    @Override
    public void onConfirmOrderFailed(String msg) {
        ToastUtils.show(this, "确认订单失败");
    }

    @Override
    public void onCancelOrderSuccess() {
        ToastUtils.show(this, "取消订单成功");
    }

    @Override
    public void onCancelOrderFailed(String msg) {
        ToastUtils.show(this, "取消订单失败");
    }
}
