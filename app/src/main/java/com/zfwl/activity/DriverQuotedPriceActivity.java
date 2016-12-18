package com.zfwl.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zfwl.R;

public class DriverQuotedPriceActivity extends AppCompatActivity {

    public static void launch(Context context) {
        Intent intent = new Intent(context, DriverQuotedPriceActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_quoted_price);
    }
}
