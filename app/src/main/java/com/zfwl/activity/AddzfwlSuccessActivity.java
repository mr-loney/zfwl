package com.zfwl.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.zfwl.R;
import com.zfwl.activity.home.HomeActivity;

import butterknife.ButterKnife;

public class AddzfwlSuccessActivity extends BaseActivity {

	private static final String TAG = "AddzfwlSuccessActivity";
	private AddzfwlSuccessActivity vThis = this;

	public static void launch(Context context) {
		Intent intent = new Intent(context, AddzfwlSuccessActivity.class);
		context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_add_zfwl_success);
		ButterKnife.bind(this);

		findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				HomeActivity.launch(AddzfwlSuccessActivity.this);
			}
		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

}
