package com.zfwl.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.zfwl.R;
import com.zfwl.activity.home.HomeActivity;

import butterknife.ButterKnife;

public class DriverQuotedSuccessActivity extends BaseActivity {

	private static final String TAG = "DriverQuotedSuccessActivity";
	private DriverQuotedSuccessActivity vThis = this;

	public static void launch(Context context) {
		Intent intent = new Intent(context, DriverQuotedSuccessActivity.class);
		context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_driver_quoted_success);
		ButterKnife.bind(this);

		findViewById(R.id.btn).setOnClickListener(v -> HomeActivity.launch(DriverQuotedSuccessActivity.this));
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

}
