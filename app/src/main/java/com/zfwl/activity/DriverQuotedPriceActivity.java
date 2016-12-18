package com.zfwl.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.zfwl.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.titlebar.BGATitleBar;

public class DriverQuotedPriceActivity extends AppCompatActivity {

    @BindView(R.id.title_bar)
    BGATitleBar mTitleBar;
    @BindView(R.id.tv_price_by_weight)
    TextView mTvPriceByWeight;
    @BindView(R.id.tv_price_by_car)
    TextView mTvPriceByCar;
    @BindView(R.id.tv_price_total)
    TextView mTvPriceTotal;
    @BindView(R.id.tv_car_num)
    TextView mTvCarNum;

    public static void launch(Context context) {
        Intent intent = new Intent(context, DriverQuotedPriceActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_quoted_price);
        ButterKnife.bind(this);
    }
}
