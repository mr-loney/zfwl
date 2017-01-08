package com.zfwl.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.zfwl.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DriverQuotedSuccessActivity extends BaseActivity {

	@BindView(R.id.titlebar_btnLeft)
	Button titlebarBtnLeft;

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

		titlebarBtnLeft.setVisibility(View.VISIBLE);

		((TextView)findViewById(R.id.tv_title)).setText("");
		findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	@OnClick(R.id.titlebar_btnLeft)
	public void onTitleLeftClick() {
		finish();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

}
