package com.zfwl.activity.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zfwl.R;

import butterknife.ButterKnife;


public class WLFragment extends Fragment {
    private static final String TAG = "WLFragment";
    View mContentView;

    public WLFragment() {
    }

    public static WLFragment newInstance() {
        WLFragment fragment = new WLFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContentView = inflater.inflate(R.layout.fragment_wl, container, false);
        ButterKnife.bind(this, mContentView);
        initViews();
        return mContentView;
    }

    private void initViews() {
    }




}
