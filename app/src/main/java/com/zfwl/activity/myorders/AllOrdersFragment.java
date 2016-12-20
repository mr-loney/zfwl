package com.zfwl.activity.myorders;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zfwl.R;
import com.zfwl.activity.BaseFragment;

/**
 * Created by ZZB on 2016/12/20.
 */
public class AllOrdersFragment extends BaseFragment {
    public AllOrdersFragment() {
    }

    public static AllOrdersFragment newInstance() {
        return new AllOrdersFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_all_orders, container, false);
        return contentView;
    }
}
