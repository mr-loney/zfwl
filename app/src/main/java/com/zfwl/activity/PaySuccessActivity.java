package com.zfwl.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.zfwl.R;
import com.zfwl.common.Nav;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ZZB on 2017/1/9.
 */
public class PaySuccessActivity extends AppCompatActivity {
    private static final String EXTRA_PAY_PRICE = "EXTRA_PAY_PRICE";
    private static final String EXTRA_PHONE_NUMBER = "EXTRA_PHONE_NUMBER";
    @BindView(R.id.tv_price)
    TextView mTvPrice;
    private double mPayPrice;
    private String mPhoneNumber;

    public static void launch(Context context, double payPrice, String phoneNumber) {
        Intent intent = new Intent(context, PaySuccessActivity.class);
        intent.putExtra(EXTRA_PAY_PRICE, payPrice);
        intent.putExtra(EXTRA_PHONE_NUMBER, phoneNumber);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_success);
        ButterKnife.bind(this);
        mPayPrice = getIntent().getDoubleExtra(EXTRA_PAY_PRICE, 0);
        mPhoneNumber = getIntent().getStringExtra(EXTRA_PHONE_NUMBER);
        mTvPrice.setText(mPayPrice + "元");
    }

    @OnClick(R.id.btn_contact_sales)
    public void onContactSalesClick() {
        Nav.toDialPhonePage(this, mPhoneNumber);
    }
}
