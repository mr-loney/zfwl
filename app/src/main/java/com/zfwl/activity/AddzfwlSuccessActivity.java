package com.zfwl.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.zfwl.R;
import com.zfwl.adapter.AddLogisticsAdapter;
import com.zfwl.controls.LineTextView;
import com.zfwl.controls.LoadingDialog;
import com.zfwl.entity.Address;
import com.zfwl.entity.AllzfwlModel;
import com.zfwl.mvp.logistics.AddLogisticsMvpView;
import com.zfwl.mvp.logistics.AddLogisticsPresenter;
import com.zfwl.widget.ToastUtils;
import com.zfwl.widget.slsectarea.SelectAreaListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddzfwlSuccessActivity extends BaseActivity {

	@BindView(R.id.titlebar_btnLeft)
	Button titlebarBtnLeft;

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