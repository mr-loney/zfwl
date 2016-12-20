package com.zfwl.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.zfwl.activity.myorders.AllOrdersFragment;

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
        switch (position) {
            case 0:
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
        }
        return AllOrdersFragment.newInstance();
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
