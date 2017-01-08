package com.zfwl.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import com.bilibili.socialize.share.core.SocializeMedia;
import com.bilibili.socialize.share.core.shareparam.BaseShareParam;
import com.zfwl.R;
import com.zfwl.entity.LogisticsInfo;
import com.zfwl.entity.MyQuotedModel;
import com.zfwl.share.ShareHelper;
import com.zfwl.util.Utils;
import com.zfwl.widget.goodsdetail.KeyValueItem;

import java.io.Serializable;
import java.text.ParseException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.titlebar.BGATitleBar;
import cn.bingoogolapple.titlebar.BGATitleBar.SimpleDelegate;

/**
 * 货源详情
 * Created by ZZB on 2016/12/17.
 */
public class GoodsDetailActivity extends BaseShareableActivity {

    @BindView(R.id.title_bar)
    BGATitleBar mTitleBar;
    @BindView(R.id.tv_from)
    TextView mTvFrom;
    @BindView(R.id.tv_to)
    TextView mTvTo;
    @BindView(R.id.item_start_time)
    KeyValueItem mItemStartTime;
    @BindView(R.id.item_big_car_passable)
    KeyValueItem mItemBigCarPassable;
    @BindView(R.id.item_goods_name)
    KeyValueItem mItemGoodsName;
    @BindView(R.id.item_goods_weight)
    KeyValueItem mItemGoodsWeight;
    @BindView(R.id.item_goods_length)
    KeyValueItem mItemGoodsLength;
    @BindView(R.id.item_car_number)
    KeyValueItem mItemCarNumber;
    @BindView(R.id.tv_remark)
    TextView mTvRemark;

    private LogisticsInfo.ListBean data;

    public static void launch(Context context, LogisticsInfo.ListBean d) {
        Intent intent = new Intent(context, GoodsDetailActivity.class);
        intent.putExtra("data",(Serializable)d);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_activity);
        ButterKnife.bind(this);
        data = (LogisticsInfo.ListBean)getIntent().getSerializableExtra("data");
        initViews();
    }

    @OnClick(R.id.tv_quoted_price)
    public void onSubmitClick() {
        DriverQuotedPriceActivity.launch(this,data.getId()+"");
    }

    private void initTitleBar() {
        mTitleBar.setDelegate(new SimpleDelegate() {
            @Override
            public void onClickLeftCtv() {
                onBackPressed();
            }

            @Override
            public void onClickRightCtv() {
                onShareClick();
            }
        });
    }

    private void onShareClick() {

    }

    private void initViews() {
        initTitleBar();

        String fromStr = "";
        String toStr = "";
        for (LogisticsInfo.ListBean.AddressInfoListBean item : data.getAddressInfoList()) {
            if (item.getFromDetail()!=null && item.getFromDetail().length()>0 && fromStr.indexOf(item.getFromDetail())<0) {
                fromStr+=item.getFromDetail()+"<br/>";
            }
            if (item.getToDetail()!=null && item.getToDetail().length()>0 && fromStr.indexOf(item.getToDetail())<0) {
                toStr+=item.getToDetail()+"<br/>";
            }
        }
        if (fromStr.length()>3) { fromStr = fromStr.substring(0,fromStr.length()-5); }
        if (toStr.length()>3) { toStr = toStr.substring(0,toStr.length()-5); }
        mTvFrom.setText(Html.fromHtml(fromStr));
        mTvTo.setText(Html.fromHtml(toStr));

        mItemStartTime.setKeyText("发车时间");
        try {
            mItemStartTime.setValueText(Utils.longToString(data.getCreateTime(),"yyyy-MM-dd"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
         mItemBigCarPassable.setKeyText("大货通行");
        mItemBigCarPassable.setValueText(data.getIsLargeGo()==0?"允许":"禁止");
        mItemBigCarPassable.setValueTextColor(Color.RED);
         mItemGoodsName.setKeyText("物品名称");
        mItemGoodsName.setValueText(data.getGoodsName());
         mItemGoodsWeight.setKeyText("货物重量（吨）");
        mItemGoodsWeight.setValueText(data.getWeight()+"");
         mItemGoodsLength.setKeyText("货物长度（米）");
        mItemGoodsLength.setValueText(data.getLength()+"");
         mItemCarNumber.setKeyText("需要车辆");
        mItemCarNumber.setValueText(data.getCarNum()+"");
         mTvRemark.setText(data.getRemark());
    }

    @Override
    public BaseShareParam getShareContent(ShareHelper helper, SocializeMedia target) {
        return null;
    }
}
