package com.zfwl.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.trello.rxlifecycle.components.support.RxFragmentActivity;

import butterknife.ButterKnife;
import cn.bingoogolapple.titlebar.BGATitleBar;
import cn.bingoogolapple.titlebar.BGATitleBar.SimpleDelegate;

/**
 * Created by ZZB on 2016/12/7.
 */
public class BaseActivity extends RxFragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    protected void initDefaultTitleBar(BGATitleBar titleBar) {
        titleBar.setDelegate(new SimpleDelegate() {
            @Override
            public void onClickLeftCtv() {
                onBackPressed();
            }
        });
    }
}
