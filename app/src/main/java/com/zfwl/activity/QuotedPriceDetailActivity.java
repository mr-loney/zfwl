package com.zfwl.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zfwl.R;
import com.zfwl.entity.MyQuotedModel;
import com.zfwl.util.DisplayUtil;
import com.zfwl.widget.goodsdetail.KeyValueItem;

import java.io.Serializable;

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

    private MyQuotedModel.ListBean data;

    public static void launch(Context context, MyQuotedModel.ListBean item){
        Intent intent = new Intent(context, MyQuotedListActivity.class);
        intent.putExtra("data",(Serializable)item);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quoted_price_detail);
        ButterKnife.bind(this);
        data = (MyQuotedModel.ListBean)getIntent().getSerializableExtra("data");
        initTitleBar();
        loadData();
    }

    private void initTitleBar() {
        TextView rightTv = mTitleBar.getRightCtv();
        rightTv.setTextSize(DisplayUtil.spToPx(17));
        rightTv.setTextColor(0xff666666);
    }

    private void loadData(){
        if (data!=null) {
            mItemProvidableCarNumber.setKeyText("可提供车辆");
            mItemProvidableCarNumber.setValueText(data.getCarNumber()+"");
            mItemCarCarryWeight.setKeyText("装载吨数（吨）");
            mItemCarCarryWeight.setValueText(data.getLoadNumber()+"");
            mItemPayWay.setKeyText("报价方式");
            mItemPayWay.setValueText(data.getPriceType()==1?"按吨数报价":"按车辆报价");
            mItemUnitPrice.setKeyText("单价");
            mItemUnitPrice.setValueText(data.getPrice()+"元");
            mItemTotalPrice.setKeyText("共计");
            mItemTotalPrice.setValueText(data.getTotal()+"元");

            mTvFrom.setText("");
            mTvTo.setText("");

            mItemBeginTime.setKeyText("发车时间");
            mItemBeginTime.setValueText(data.getCarNumber()+"");
            mItemBeginTime.setKeyText("大货通行");
            mItemBeginTime.setValueText(data.getCarNumber()+"");
            mItemBeginTime.setKeyText("物品名称");
            mItemBeginTime.setValueText(data.getCarNumber()+"");
            mItemBeginTime.setKeyText("货物重量（吨）");
            mItemBeginTime.setValueText(data.getCarNumber()+"");
            mItemBeginTime.setKeyText("货物长度（米）");
            mItemBeginTime.setValueText(data.getCarNumber()+"");
            mItemBeginTime.setKeyText("需要车辆");
            mItemBeginTime.setValueText(data.getCarNumber()+"");

            mTvRemark.setText(data.getRemark());
        }
    }
}
