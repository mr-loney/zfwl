package com.zfwl.activity.myorders.detail;

import android.os.Bundle;

import com.zfwl.R;
import com.zfwl.entity.OrderDetails;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.titlebar.BGATitleBar;

/**
 * 运输中
 */
public class CarryingOrderDetailActivity extends BaseOrderDetailActivity {

    @BindView(R.id.title_bar)
    BGATitleBar mTitleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrying_order_detail);
        ButterKnife.bind(this);
        initDefaultTitleBar(mTitleBar);
    }

    @Override
    protected void populateDetails(OrderDetails orderDetails) {

    }
}
