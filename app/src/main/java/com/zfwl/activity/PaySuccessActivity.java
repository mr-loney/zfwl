package com.zfwl.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zfwl.R;
import com.zfwl.entity.Order;

/**
 * Created by ZZB on 2017/1/9.
 */
public class PaySuccessActivity extends AppCompatActivity {

    public static void launch(Order order) {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_success);
    }
}
