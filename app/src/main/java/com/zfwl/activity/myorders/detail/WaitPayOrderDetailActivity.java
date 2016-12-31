package com.zfwl.activity.myorders.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zfwl.R;
import com.zfwl.entity.LogisticsInfo;
import com.zfwl.entity.OrderDetails;
import com.zfwl.entity.OrderDetails.OrderEmptyCar;
import com.zfwl.util.TimeUtils;
import com.zfwl.widget.goodsdetail.KeyValueItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.titlebar.BGATitleBar;

/**
 * 待支付
 */
public class WaitPayOrderDetailActivity extends BaseOrderDetailActivity {
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
    @BindView(R.id.item_remark)
    KeyValueItem mItemRemark;
    @BindView(R.id.item_my_quoted_price)
    KeyValueItem mItemMyQuotedPrice;
    @BindView(R.id.item_order_number)
    KeyValueItem mItemOrderNumber;
    @BindView(R.id.item_order_create_time)
    KeyValueItem mItemOrderCreateTime;
    @BindView(R.id.btn_pay_order)
    TextView mBtnPayOrder;
    @BindView(R.id.btn_cancel_order)
    TextView mBtnCancelOrder;

    public static void launch(Context context, OrderDetails orderDetails) {
        Intent intent = new Intent(context, WaitPayOrderDetailActivity.class);
        intent.putExtra(EXTRA_ORDER_DETAIL, orderDetails);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_pay_order_detail);
        ButterKnife.bind(this);
        initViews();
        populateViews();
    }

    private void populateViews() {
        mItemOrderNumber.setText("订单编号", mOrderDetails.getOrderCode() + "");
        mItemOrderCreateTime.setText("创建时间", "用哪个字段？");

        LogisticsInfo.ListBean logisticsInfo = mOrderDetails.getLogisticsInfo();
        mItemBigCarPassable.setText("大货通行", logisticsInfo.getIsLargeGoDesc());
        mItemGoodsName.setText("物品名称", logisticsInfo.getGoodsName());
        mItemGoodsWeight.setText("货物重量(吨)", logisticsInfo.getWeight() + "");
        mItemGoodsLength.setText("货物长度(米)", logisticsInfo.getLength() + "");
        mItemNeedCarNumber.setText("需要车辆", logisticsInfo.getCarNum() + "");

        OrderEmptyCar carInfo = mOrderDetails.getMemberEmptyCar();
        mItemBeginTime.setText("发车时间", TimeUtils.getDefaultTimeStamp(carInfo.getGoDate()));
        mItemRemark.setText("备注", carInfo.getRemark());
    }

    private void initViews() {
        initDefaultTitleBar(mTitleBar);
    }


    @OnClick({R.id.btn_cancel_order, R.id.btn_pay_order})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_cancel_order:
                onCancelOrderClick();
                break;
            case R.id.btn_pay_order:
                onPayOrderClick();
                break;
        }
    }

    private void onCancelOrderClick() {

    }

    private void onPayOrderClick() {

    }
}
