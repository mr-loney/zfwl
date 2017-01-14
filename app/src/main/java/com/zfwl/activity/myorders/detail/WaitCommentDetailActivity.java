package com.zfwl.activity.myorders.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.zfwl.R;
import com.zfwl.activity.myorders.OrderCommentActivity;
import com.zfwl.entity.LogisticsInfo.ListBean;
import com.zfwl.entity.OrderDetails;
import com.zfwl.event.CloseWaitCommentEvent;
import com.zfwl.util.AddressUtils;
import com.zfwl.util.StringUtils;
import com.zfwl.widget.goodsdetail.KeyValueItem;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.titlebar.BGATitleBar;

public class WaitCommentDetailActivity extends BaseOrderDetailActivity {

    @BindView(R.id.title_bar)
    BGATitleBar mTitleBar;
    @BindView(R.id.tv_from)
    TextView mTvFrom;
    @BindView(R.id.tv_to)
    TextView mTvTo;
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

    public static void launch(Context context, long orderId) {
        Intent intent = new Intent(context, WaitCommentDetailActivity.class);
        intent.putExtra(EXTRA_ORDER_ID, orderId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait_comment_detail);
        ButterKnife.bind(this);
        initDefaultTitleBar(mTitleBar);
        loadDetails();
    }

    @Override
    protected void populateDetails(OrderDetails orderDetails) {
        ListBean logisticsInfo = orderDetails.getLogisticsInfo();
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
    }

    @OnClick(R.id.btn_comment)
    public void onCommentClick() {
        OrderCommentActivity.launch(this, mOrderId);
    }
    @Subscribe
    public void closeWaitComment(CloseWaitCommentEvent event) {
        finish();
    }
}