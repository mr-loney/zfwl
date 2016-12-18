package com.zfwl.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.bilibili.socialize.share.core.SocializeMedia;
import com.bilibili.socialize.share.core.shareparam.BaseShareParam;
import com.zfwl.R;
import com.zfwl.share.ShareHelper;
import com.zfwl.widget.goodsdetail.GoodsDetailItem;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    GoodsDetailItem mItemStartTime;
    @BindView(R.id.item_big_car_passable)
    GoodsDetailItem mItemBigCarPassable;
    @BindView(R.id.item_goods_name)
    GoodsDetailItem mItemGoodsName;
    @BindView(R.id.item_goods_weight)
    GoodsDetailItem mItemGoodsWeight;
    @BindView(R.id.item_goods_length)
    GoodsDetailItem mItemGoodsLength;
    @BindView(R.id.item_car_number)
    GoodsDetailItem mItemCarNumber;
    @BindView(R.id.tv_remark)
    TextView mTvRemark;
    @BindView(R.id.tv_quoted_price)
    TextView mTvQuotedPrice;

    public static void launch(Context context) {
        Intent intent = new Intent(context, GoodsDetailActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_activity);
        ButterKnife.bind(this);
        initViews();
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
    }

    @Override
    public BaseShareParam getShareContent(ShareHelper helper, SocializeMedia target) {
        return null;
    }
}
