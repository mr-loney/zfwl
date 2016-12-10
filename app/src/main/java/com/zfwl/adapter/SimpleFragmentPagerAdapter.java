package com.zfwl.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by ZZB on 2016/12/10.
 */
public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments;
    private List<String> mTitles;
    private FragmentManager mFragmentManager;

    public SimpleFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        this.mFragmentManager = fm;
    }

    public void setItems(List<Fragment> fragments) {
        mFragments = fragments;
    }

    public void setItems(List<Fragment> fragments, List<String> titles) {
        mFragments = fragments;
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int i) {
        if (mFragments != null && i >= 0 && i < mFragments.size()) {
            return mFragments.get(i);
        }
        return null;
    }

    @Override
    public int getCount() {
        return mFragments == null ? 0 : mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (mTitles != null && position >= 0 && position < mTitles.size()) {
            return mTitles.get(position);
        }
        return null;
    }
}
