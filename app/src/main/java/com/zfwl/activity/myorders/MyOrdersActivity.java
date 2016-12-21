package com.zfwl.activity.myorders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.astuetz.PagerSlidingTabStrip;
import com.zfwl.R;
import com.zfwl.activity.BaseActivity;
import com.zfwl.adapter.MyOrdersPagerAdapter;
import com.zfwl.util.DisplayUtil;

import butterknife.BindView;

/**
 * 我的订单
 */
public class MyOrdersActivity extends BaseActivity {
    @BindView(R.id.tabs)
    PagerSlidingTabStrip mTabs;
    @BindView(R.id.pager)
    ViewPager mViewPager;

    public static void launch(Context context) {
        Intent intent = new Intent(context, MyOrdersActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);
        initTabs();
    }

    private void initTabs() {
        MyOrdersPagerAdapter adapter = new MyOrdersPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        mTabs.setViewPager(mViewPager);
        mTabs.setTextColor(getResources().getColorStateList(R.color.orders_tab_text_color));
        mTabs.setIndicatorColor(0xff1373fa);
        mTabs.setIndicatorHeight(DisplayUtil.dpToPx(3));
        mTabs.setDividerColorResource(R.color.transparent);
        mTabs.setUnderlineHeight(DisplayUtil.dpToPx(1));
        mTabs.setUnderlineColorResource(R.color.divider);

    }
}
