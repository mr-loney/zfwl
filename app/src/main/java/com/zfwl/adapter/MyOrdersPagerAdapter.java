package com.zfwl.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zfwl.activity.myorders.OrdersFragment;
import com.zfwl.entity.Order.Type;

/**
 * Created by ZZB on 2016/12/20.
 */
public class MyOrdersPagerAdapter extends FragmentPagerAdapter {
    private final String[] TITLES = {"全部", "待确认", "待付款", "已付款", "运输中", "已完成"};

    public MyOrdersPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        int orderType = -1;
        switch (position) {
            case 0:
                orderType = Type.ALL;
                break;
            case 1:
                orderType = Type.WAIT_CONFIRM;
                break;
            case 2:
                orderType = Type.WAIT_PAY;
                break;
            case 3:
                orderType = Type.PAID;
                break;
            case 4:
                orderType = Type.CARRYING;
                break;
            case 5:
                orderType = Type.WAIT_COMMENT;
                break;
        }
        return OrdersFragment.newInstance(orderType);
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }
}
