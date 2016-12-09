package com.zfwl.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.zfwl.R;

public class MainActivity extends BaseActivity {
    public static void launch(Context context){
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
