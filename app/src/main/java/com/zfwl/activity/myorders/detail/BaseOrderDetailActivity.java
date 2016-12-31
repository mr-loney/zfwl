package com.zfwl.activity.myorders.detail;

import android.os.Bundle;
import android.os.PersistableBundle;

import com.zfwl.activity.BaseActivity;
import com.zfwl.entity.OrderDetails;

/**
 * Created by ZZB on 2016/12/31.
 */
public class BaseOrderDetailActivity extends BaseActivity {
    protected static final String EXTRA_ORDER_DETAIL = "EXTRA_ORDER_DETAIL";
    protected OrderDetails mOrderDetails;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        initExtras();
    }

    protected void initExtras() {
        mOrderDetails = (OrderDetails) getIntent().getExtras().getSerializable(EXTRA_ORDER_DETAIL);
    }
}
