package com.zfwl.activity.myorders.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zfwl.R;
import com.zfwl.entity.LogisticsInfo.ListBean;
import com.zfwl.entity.OrderDetails;
import com.zfwl.entity.OrderDetails.OrderEmptyCar;
import com.zfwl.util.AddressUtils;
import com.zfwl.util.TimeUtils;
import com.zfwl.widget.goodsdetail.KeyValueItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.titlebar.BGATitleBar;

/**
 * 已支付
 */
public class PaidOrderDetailActivity extends BaseOrderDetailActivity {
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
//    @BindView(R.id.item_remark)
//    KeyValueItem mItemRemark;
    @BindView(R.id.btn_contact_sales)
    TextView mBtnContactSales;
    @BindView(R.id.tv_remark)
    TextView mTvRemark;


    public static void launch(Context context, long orderId) {
        Intent intent = new Intent(context, PaidOrderDetailActivity.class);
        intent.putExtra(EXTRA_ORDER_ID, orderId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paid_order_detail);
        ButterKnife.bind(this);
        initViews();
        loadDetails();

    }

    private void initViews() {
        initDefaultTitleBar(mTitleBar);
    }


    @OnClick(R.id.btn_contact_sales)
    public void onContactClick() {

    }

    @Override
    protected void populateDetails(OrderDetails orderDetails) {
        OrderEmptyCar carInfo = orderDetails.getMemberEmptyCar();
        ListBean logisticsInfo = orderDetails.getLogisticsInfo();
        mTvFrom.setText(AddressUtils.getFromAddressStr(orderDetails.getAddressInfoList()));
        mTvTo.setText(AddressUtils.getToAddressStr(orderDetails.getAddressInfoList()));
        //详细信息
        mItemBeginTime.setText("发车时间", TimeUtils.getDefaultTimeStamp(carInfo.getGoDate()));
        mItemBigCarPassable.setText("大货通行", logisticsInfo.getIsLargeGoDesc());
        mItemGoodsName.setText("物品名称", logisticsInfo.getGoodsName());
        mItemGoodsWeight.setText("货物重量(吨)", logisticsInfo.getWeight() + "");
        mItemGoodsLength.setText("货物长度(米)", logisticsInfo.getLength() + "");
        mItemNeedCarNumber.setText("需要车辆", logisticsInfo.getCarNum() + "");
        mTvRemark.setText(carInfo.getRemark());

    }
}
