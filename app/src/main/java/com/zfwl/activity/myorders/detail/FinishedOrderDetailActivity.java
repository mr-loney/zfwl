package com.zfwl.activity.myorders.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zfwl.R;
import com.zfwl.entity.LogisticsInfo.ListBean;
import com.zfwl.entity.OrderComment;
import com.zfwl.entity.OrderDetails;
import com.zfwl.entity.OrderDetails.OrderEmptyCar;
import com.zfwl.util.AddressUtils;
import com.zfwl.widget.goodsdetail.KeyValueItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.titlebar.BGATitleBar;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

/**
 * 已完成订单（已评价）详情
 */
public class FinishedOrderDetailActivity extends BaseOrderDetailActivity {

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
    @BindView(R.id.tv_remark)
    TextView mTvRemark;
    @BindView(R.id.item_my_quoted_price)
    KeyValueItem mItemMyQuotedPrice;
    @BindView(R.id.rb_create_time)
    MaterialRatingBar mRbCreateTime;
    @BindView(R.id.rb_out_time)
    MaterialRatingBar mRbOutTime;
    @BindView(R.id.rb_service)
    MaterialRatingBar mRbService;
    @BindView(R.id.rb_sales_attitude)
    MaterialRatingBar mRbSalesAttitude;
    @BindView(R.id.item_order_number)
    KeyValueItem mItemOrderNumber;
    @BindView(R.id.item_order_create_time)
    KeyValueItem mItemOrderCreateTime;

    public static void launch(Context context, long orderId) {
        Intent intent = new Intent(context, FinishedOrderDetailActivity.class);
        intent.putExtra(EXTRA_ORDER_ID, orderId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished_order);
        initViews();
        mOrderDetailsPresenter.attachView(this);
        loadDetails();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mOrderDetailsPresenter.detachView();
    }

    private void initViews() {
        ButterKnife.bind(this);
        initDefaultTitleBar(mTitleBar);
        initRbs();
    }

    private void initRbs() {
        mRbCreateTime.setIsIndicator(true);
        mRbOutTime.setIsIndicator(true);
        mRbSalesAttitude.setIsIndicator(true);
        mRbService.setIsIndicator(true);
    }

    @Override
    protected void populateDetails(OrderDetails orderDetails) {
        OrderEmptyCar carInfo = orderDetails.getMemberEmptyCar();
        ListBean logisticsInfo = orderDetails.getLogisticsInfo();
        mTvFrom.setText(AddressUtils.getFromAddressStr(logisticsInfo.getAddressInfoList()));
        mTvTo.setText(AddressUtils.getToAddressStr(logisticsInfo.getAddressInfoList()));
        //详细信息
        mItemBeginTime.setText("发车时间", carInfo.getGoDate());
        mItemBigCarPassable.setText("大货通行", logisticsInfo.getIsLargeGoDesc());
        mItemGoodsName.setText("物品名称", logisticsInfo.getGoodsName());
        mItemGoodsWeight.setText("货物重量(吨)", logisticsInfo.getWeight() + "");
        mItemGoodsLength.setText("货物长度(米)", logisticsInfo.getLength() + "");
        mItemNeedCarNumber.setText("需要车辆", logisticsInfo.getCarNum() + "");
        mTvRemark.setText(carInfo.getRemark());

        OrderComment comment = orderDetails.getOrderComment();
        mRbCreateTime.setProgress(comment.getDepotTime());
        mRbService.setProgress(comment.getDepotServcie());
        mRbSalesAttitude.setProgress(comment.getServerLove());
        mRbOutTime.setProgress(comment.getDepotOutTime());
    }
}
