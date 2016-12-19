package com.zfwl.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.trello.rxlifecycle.components.support.RxFragmentActivity;

import butterknife.ButterKnife;

/**
 * Created by ZZB on 2016/12/7.
 */
public class BaseActivity extends RxFragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
