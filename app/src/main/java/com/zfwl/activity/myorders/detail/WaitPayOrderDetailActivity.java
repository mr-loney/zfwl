package com.zfwl.activity.myorders.detail;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zfwl.R;
import com.zfwl.activity.BaseActivity;
import com.zfwl.widget.goodsdetail.KeyValueItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.titlebar.BGATitleBar;

/**
 * 待支付
 */
public class WaitPayOrderDetailActivity extends BaseActivity {

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
    @BindView(R.id.btn_contact_sales)
    TextView mBtnContactSales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_pay_order_detail);
        ButterKnife.bind(this);
        initDefaultTitleBar(mTitleBar);
    }
}
