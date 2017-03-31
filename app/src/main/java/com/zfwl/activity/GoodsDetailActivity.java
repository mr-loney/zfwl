package com.zfwl.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bilibili.socialize.share.core.SocializeMedia;
import com.bilibili.socialize.share.core.shareparam.BaseShareParam;
import com.zfwl.R;
import com.zfwl.common.Nav;
import com.zfwl.data.UserInfoManager;
import com.zfwl.data.api.retrofit.ApiModule;
import com.zfwl.entity.LogisticsInfo;
import com.zfwl.share.ShareHelper;
import com.zfwl.share.WechatShareManager;
import com.zfwl.util.AddressUtils;
import com.zfwl.util.StringUtils;
import com.zfwl.util.Utils;
import com.zfwl.widget.goodsdetail.KeyValueItem;

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

    @BindView(R.id.share_v)
    View mShareView;
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
    @BindView(R.id.item_has_car_number)
    KeyValueItem mItemHasCarNumber;
    @BindView(R.id.item_contact)
    KeyValueItem mItemContact;
    @BindView(R.id.tv_remark)
    TextView mTvRemark;


    private WechatShareManager mShareManager;
    private LogisticsInfo.ListBean data;

    public static void launch(Context context, LogisticsInfo.ListBean d) {
        if (UserInfoManager.INSTANCE.hasLogin()) {
            Intent intent = new Intent(context, GoodsDetailActivity.class);
            intent.putExtra("data", d);
            context.startActivity(intent);
        } else {
            LoginActivity.launch(context, false);
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_activity);
        ButterKnife.bind(this);
        data = (LogisticsInfo.ListBean) getIntent().getSerializableExtra("data");
        initViews();

        mShareManager = WechatShareManager.getInstance(this);
    }

    @OnClick(R.id.tv_quoted_price)
    public void onSubmitClick() {
        DriverQuotedPriceActivity.launch(this, data.getId() + "");
    }

    @OnClick(R.id.share_wx)
    public void onShareWxClick() {
        share(WechatShareManager.WECHAT_SHARE_TYPE_TALK);
    }

    @OnClick(R.id.share_wx_friend)
    public void onShareWxFriendClick() {
        share(WechatShareManager.WECHAT_SHARE_TYPE_FRENDS);
    }

    private void share(int t) {
        if (!isWebchatAvaliable()) {
            Toast.makeText(this, "请先安装微信", Toast.LENGTH_LONG).show();
            return;
        }
        if (data == null) {
            Toast.makeText(this, "请重新进入详情", Toast.LENGTH_LONG).show();
            return;
        }

        String fromStr = AddressUtils.getFromAddressStr(data.getAddressInfoList());
        String toStr = AddressUtils.getToAddressStr(data.getAddressInfoList());
        String time = data.getCreateTime() + "";
        try {
            time = Utils.longToString(data.getCreateTime(), "yyyy-MM-dd HH:mm");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String title = "需要" + (data.getCarNum()-data.getHasCarNum()) + "辆" + data.getLength() + "米的车 从" + fromStr + "到" + toStr;
        String content = "发车时间" + time;
        String url = ApiModule.BASE_URL + "app/weixin/showDetail.do?logisticsId=" + data.getId();

        WechatShareManager.ShareContentWebpage s =
                (WechatShareManager.ShareContentWebpage) mShareManager.getShareContentWebpag(
                        title,
                        content,
                        url,
                        R.drawable.share_icon);
        mShareManager.shareByWebchat(s, t);
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
        mShareView.setVisibility(View.VISIBLE);
    }

    private void initViews() {
        initTitleBar();

        String fromStr = AddressUtils.getFromAddressStr(data.getAddressInfoList());
        String toStr = AddressUtils.getToAddressStr(data.getAddressInfoList());
        mTvFrom.setText(fromStr);
        mTvTo.setText(toStr);

        mItemStartTime.setKeyText("发车时间");
        try {
            mItemStartTime.setValueText(Utils.longToString(data.getCreateTime(), "yyyy-MM-dd HH:mm"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        mItemBigCarPassable.setKeyText("大货通行");
        mItemBigCarPassable.setValueText(data.getIsLargeGo() == 0 ? "允许" : "禁止");
        mItemBigCarPassable.setValueTextColor(Color.RED);
        mItemGoodsName.setKeyText("物品名称");
        mItemGoodsName.setValueText(data.getGoodsName());
        mItemGoodsWeight.setKeyText("货物重量（吨）");
        mItemGoodsWeight.setValueText(StringUtils.removeTrailingZero(data.getWeight() + ""));
        mItemGoodsLength.setKeyText(StringUtils.removeTrailingZero("货物长度（米）"));
        mItemGoodsLength.setValueText(data.getLength() + "");
        mItemCarNumber.setKeyText("需要车辆");
        mItemCarNumber.setValueText(data.getCarNum() + "");
        mItemHasCarNumber.setKeyText("剩余车辆");
        mItemHasCarNumber.setValueText((data.getCarNum()-data.getHasCarNum()) + "");
        mItemContact.setKeyText("联系方式");
        mItemContact.setValueText(data.getRelationPhone());
        if (data.getRemark() == null || data.getRemark().length() == 0) {
            findViewById(R.id.tv_remark_bg).setVisibility(View.GONE);
        } else {
            mTvRemark.setText(data.getRemark());
        }

        mShareView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                mShareView.setVisibility(View.GONE);
                return false;
            }
        });
    }

    private boolean isWebchatAvaliable() {
        //检测手机上是否安装了微信
        try {
            getPackageManager().getPackageInfo("com.tencent.mm", PackageManager.GET_ACTIVITIES);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    @OnClick(R.id.item_contact)
    void onContactClick() {
        Nav.toDialPhonePage(this, data.getRelationPhone());
    }
    @Override
    public BaseShareParam getShareContent(ShareHelper helper, SocializeMedia target) {

        return null;
    }
}
