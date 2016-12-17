package com.zfwl.activity;

import android.os.Bundle;
import android.os.PersistableBundle;

import com.zfwl.R;

import butterknife.ButterKnife;

/**
 * 货源详情
 * Created by ZZB on 2016/12/17.
 */
public class GoodsDetailActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_goods_activity);
        ButterKnife.bind(this);
    }
}
