package com.zfwl.activity.myorders.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zfwl.R;
import com.zfwl.activity.BaseActivity;
import com.zfwl.entity.OrderDetails;
import com.zfwl.widget.goodsdetail.KeyValueItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.titlebar.BGATitleBar;

/**
 * 已支付
 */
public class PaidOrderDetailActivity extends BaseActivity {
    private static final String EXTRA_ORDER_DETAIL = "EXTRA_ORDER_DETAIL";
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
    @BindView(R.id.btn_contact_sales)
    TextView mBtnContactSales;

    public static void launch(Context context, OrderDetails orderDetails) {
        Intent intent = new Intent(context, PaidOrderDetailActivity.class);
        intent.putExtra(EXTRA_ORDER_DETAIL, orderDetails);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paid_order_detail);
        ButterKnife.bind(this);
        initDefaultTitleBar(mTitleBar);
    }

    @OnClick(R.id.btn_contact_sales)
    public void onContactClick() {

    }
}
