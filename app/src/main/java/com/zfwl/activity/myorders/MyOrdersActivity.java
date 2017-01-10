package com.zfwl.activity.myorders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.astuetz.PagerSlidingTabStrip;
import com.zfwl.R;
import com.zfwl.activity.BaseActivity;
import com.zfwl.adapter.MyOrdersPagerAdapter;
import com.zfwl.entity.Order.Type;
import com.zfwl.util.DisplayUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.titlebar.BGATitleBar;

/**
 * 我的订单
 */
public class MyOrdersActivity extends BaseActivity {
    private static final String EXTRA_ORDER_TYPE = "EXTRA_ORDER_TYPE";
    @BindView(R.id.tabs)
    PagerSlidingTabStrip mTabs;
    @BindView(R.id.pager)
    ViewPager mViewPager;
    @BindView(R.id.title_bar)
    BGATitleBar mTitleBar;
    private int mRedirectOrderType;
    private static final Map<Integer, Integer> ORDER_TYPE_PAGE_INDEX_MAP = new HashMap<>();
    static{
        ORDER_TYPE_PAGE_INDEX_MAP.put(Type.ALL, 0);
        ORDER_TYPE_PAGE_INDEX_MAP.put(Type.WAIT_CONFIRM, 1);
        ORDER_TYPE_PAGE_INDEX_MAP.put(Type.WAIT_PAY, 2);
        ORDER_TYPE_PAGE_INDEX_MAP.put(Type.PAID, 3);
        ORDER_TYPE_PAGE_INDEX_MAP.put(Type.CARRYING, 4);
    }
    public static void launch(Context context) {
        launch(context, Type.ALL);
    }

    public static void launch(Context context, int orderType) {
        Intent intent = new Intent(context, MyOrdersActivity.class);
        intent.putExtra(EXTRA_ORDER_TYPE, orderType);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);
        ButterKnife.bind(this);
        mRedirectOrderType = getIntent().getIntExtra(EXTRA_ORDER_TYPE, Type.ALL);
        initViews();
    }

    private void initViews() {
        initDefaultTitleBar(mTitleBar);
        initTabs();
    }

    private void initTabs() {
        MyOrdersPagerAdapter adapter = new MyOrdersPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        mTabs.setViewPager(mViewPager);
        mTabs.setTextColor(getResources().getColorStateList(R.color.orders_tab_text_color));
        mViewPager.setOffscreenPageLimit(1);
        mTabs.setIndicatorColor(0xff1373fa);
        mTabs.setIndicatorHeight(DisplayUtil.dpToPx(3));
        mTabs.setDividerColorResource(R.color.transparent);
        mTabs.setUnderlineHeight(DisplayUtil.dpToPx(1));
        mTabs.setUnderlineColorResource(R.color.divider);
        mViewPager.setCurrentItem(ORDER_TYPE_PAGE_INDEX_MAP.get(mRedirectOrderType));
    }
}
