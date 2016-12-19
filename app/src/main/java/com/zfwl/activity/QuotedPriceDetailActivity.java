package com.zfwl.activity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zfwl.R;
import com.zfwl.util.DisplayUtil;
import com.zfwl.widget.goodsdetail.KeyValueItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.titlebar.BGATitleBar;

/**
 * 报价列表
 */
public class QuotedPriceDetailActivity extends BaseActivity {

    @BindView(R.id.title_bar)
    BGATitleBar mTitleBar;
    @BindView(R.id.item_providable_car_number)
    KeyValueItem mItemProvidableCarNumber;
    @BindView(R.id.item_car_carry_weight)
    KeyValueItem mItemCarCarryWeight;
    @BindView(R.id.item_pay_way)
    KeyValueItem mItemPayWay;
    @BindView(R.id.item_unit_price)
    KeyValueItem mItemUnitPrice;
    @BindView(R.id.item_total_price)
    KeyValueItem mItemTotalPrice;
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
    @BindView(R.id.tv_remark)
    TextView mTvRemark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quoted_price_detail);
        ButterKnife.bind(this);
        initTitleBar();
    }

    private void initTitleBar() {
        TextView rightTv = mTitleBar.getRightCtv();
        rightTv.setTextSize(DisplayUtil.spToPx(17));
        rightTv.setTextColor(0xff666666);
    }
}
