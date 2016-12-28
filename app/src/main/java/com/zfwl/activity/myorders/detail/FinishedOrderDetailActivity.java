package com.zfwl.activity.myorders.detail;

import android.os.Bundle;

import com.zfwl.R;
import com.zfwl.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.titlebar.BGATitleBar;

/**
 * 已完成订单详情
 */
public class FinishedOrderDetailActivity extends BaseActivity {

    @BindView(R.id.title_bar)
    BGATitleBar mTitleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished_order);
        ButterKnife.bind(this);
        initDefaultTitleBar(mTitleBar);
    }
}
