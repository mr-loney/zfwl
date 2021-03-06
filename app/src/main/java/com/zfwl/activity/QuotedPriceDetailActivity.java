package com.zfwl.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zfwl.R;
import com.zfwl.data.api.MyQuotedApi;
import com.zfwl.data.api.retrofit.ApiModule;
import com.zfwl.entity.MyQuotedModel;
import com.zfwl.entity.MyQuotedModel.ListBean;
import com.zfwl.mvp.logistics.MyQuotedMvpView;
import com.zfwl.mvp.logistics.MyQuotedPresenter;
import com.zfwl.util.StringUtils;
import com.zfwl.util.Utils;
import com.zfwl.util.ViewHub;
import com.zfwl.widget.ToastUtils;
import com.zfwl.widget.goodsdetail.KeyValueItem;

import java.text.ParseException;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.titlebar.BGATitleBar;
import rx.android.schedulers.AndroidSchedulers;

/**
 * 报价列表
 */
public class QuotedPriceDetailActivity extends BaseActivity implements MyQuotedMvpView {

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
    @BindView(R.id.item_has_car_number)
    KeyValueItem mItemHasCarNumber;
    @BindView(R.id.tv_remark)
    TextView mTvRemark;

    private MyQuotedPresenter mPresenter;
    MyQuotedModel.ListBean detailData;

    public static void launch(Context context, MyQuotedModel.ListBean item) {
        Intent intent = new Intent(context, QuotedPriceDetailActivity.class);
        intent.putExtra("data", item);
        context.startActivity(intent);
    }

    public static void launchFromPush(Context context, long priceId) {
        Intent intent = new Intent(context, QuotedPriceDetailActivity.class);
        intent.putExtra("priceId", priceId);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quoted_price_detail);
        ButterKnife.bind(this);
        mPresenter = new MyQuotedPresenter(this);
        mPresenter.attachView(this);
        initTitleBar();
        loadData();
    }

    private void loadData() {
        MyQuotedModel.ListBean data = (MyQuotedModel.ListBean) getIntent().getSerializableExtra("data");
        long id;
        if (data != null) {
            id = data.getId();
//            onDataLoaded(data);
        } else {
            id = getIntent().getLongExtra("priceId", 0);

        }
        MyQuotedApi api = ApiModule.INSTANCE.quotedApi();
        api.getQuotedPriceDetail(id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onDataLoaded, throwable -> {
                    ToastUtils.show(QuotedPriceDetailActivity.this, "加载失败");
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    private void initTitleBar() {
        TextView rightTv = mTitleBar.getRightCtv();
//        rightTv.setTextSize(DisplayUtil.spToPx(6));
        rightTv.setTextColor(0xff666666);
        rightTv.setOnClickListener(v -> mPresenter.del(detailData));

        mTitleBar.getLeftCtv().setOnClickListener(v -> finish());
    }

    private void onDataLoaded(MyQuotedModel.ListBean data) {
        detailData = data;
        if (data != null) {
            mItemProvidableCarNumber.setKeyText("可提供车辆");
            mItemProvidableCarNumber.setValueText(data.getCarNumber() + "");
            mItemCarCarryWeight.setKeyText("装载吨数（吨）");
            mItemCarCarryWeight.setValueText(data.getLoadNumber() + "");
            mItemPayWay.setKeyText("报价方式");
            mItemPayWay.setValueText(data.getPriceType() == 1 ? "按吨数报价" : "按车辆报价");
            mItemUnitPrice.setKeyText("单价");
            mItemUnitPrice.setValueText(data.getPrice() + "元");
            mItemTotalPrice.setKeyText("共计");
            mItemTotalPrice.setValueText(data.getTotal() + "元");
            mItemTotalPrice.setValueTextColor(Color.RED);

            String fromStr = "";
            String toStr = "";
            ListBean logisticInfo = data.getLogisticsInfo();
            for (MyQuotedModel.ListBean.AddressInfoListBean item : logisticInfo.getAddressInfoList()) {
                if (fromStr.indexOf(item.getFromProvinceName() + item.getFromCityName() + item.getFromCountyName() + item.getFromDetail()) < 0) {
                    fromStr += item.getFromProvinceName() + item.getFromCityName() + item.getFromCountyName() + item.getFromDetail() + "<br/>";
                }
                if (item.getToProvinceName() != null) {
                    toStr += item.getToProvinceName();
                }
                if (item.getToCityName() != null) {
                    toStr += item.getToCityName();
                }
                if (item.getToCountyName() != null) {
                    toStr += item.getToCountyName();
                }
                if (item.getToDetail() != null) {
                    toStr += item.getToDetail();
                }
                toStr += "<br/>";
            }
            if (fromStr.length() > 3) {
                fromStr = fromStr.substring(0, fromStr.length() - 5);
            }
            if (toStr.length() > 3) {
                toStr = toStr.substring(0, toStr.length() - 5);
            }
            mTvFrom.setText(Html.fromHtml(fromStr));
            mTvTo.setText(Html.fromHtml(toStr));

            mItemBeginTime.setKeyText("发车时间");
            try {
                mItemBeginTime.setValueText(Utils.longToStringFriendly(data.getCdate()) + "");
            } catch (ParseException e) {
                e.printStackTrace();
            }
            mItemBigCarPassable.setKeyText("大货通行");
            mItemBigCarPassable.setValueText(data.getIsLargeGo() == 0 ? "允许" : "不允许");
            mItemBigCarPassable.setValueTextColor(Color.RED);
            mItemGoodsName.setKeyText("物品名称");
            mItemGoodsName.setValueText(data.getGoodsName() + "");
            mItemGoodsWeight.setKeyText("货物重量（吨）");
            mItemGoodsWeight.setValueText(StringUtils.removeTrailingZero(data.getWeight() + ""));
            mItemGoodsLength.setKeyText("货物长度（米）");
            mItemGoodsLength.setValueText(StringUtils.removeTrailingZero(data.getLength() + ""));
            mItemNeedCarNumber.setKeyText("需要车辆");
            mItemNeedCarNumber.setValueText(logisticInfo.getCarNum() + "");
            mItemHasCarNumber.setKeyText("剩余车辆");
            mItemHasCarNumber.setValueText((logisticInfo.getCarNum() - data.getHasCarNum()) + "");

            if (data.getRemark().length() > 0) {
                mTvRemark.setText(data.getRemark());
            } else {
                findViewById(R.id.tv_remark_bg).setVisibility(View.GONE);
            }
        }
    }


    @Override
    public void onGetListSuccess(MyQuotedModel d) {
    }

    @Override
    public void onGetListFailed(String msg) {
    }

    @Override
    public void onDel() {
        ViewHub.showLongToast(this, "删除成功");
        finish();
    }

    @Override
    public void onDelFail(String msg) {
        ViewHub.showLongToast(this, msg);
    }

}
