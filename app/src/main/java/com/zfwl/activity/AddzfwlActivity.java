package com.zfwl.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.zfwl.R;
import com.zfwl.adapter.AddLogisticsAdapter;
import com.zfwl.controls.LoadingDialog;
import com.zfwl.mvp.logistics.AddLogisticsMvpView;
import com.zfwl.mvp.logistics.AddLogisticsPresenter;
import com.zfwl.mvp.logistics.LogisticsPresenter;
import com.zfwl.widget.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddzfwlActivity extends BaseActivity implements AddLogisticsMvpView {

	private static final String TAG = "LoginActivity";
	private AddzfwlActivity vThis = this;
	private LoadingDialog loadingDialog;

	@BindView(R.id.txt_from)
	private TextView txtFrom;
	@BindView(R.id.listview_step3)
	private ListView listView;
	@BindView(R.id.titlebar_btnRight)
	private Button rBtn;
	@BindView(R.id.titlebar_btnLeft)
	private Button lBtn;
	@BindView(R.id.tv_title)
	private Button tvTitle;

	private AddLogisticsAdapter adapter;

	private AddLogisticsPresenter mLogisticsPresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_add_zfwl);
		ButterKnife.bind(this);
		initPresenters();
		loadingDialog = new LoadingDialog(this);

		initView();
	}

	private void initPresenters() {
		mLogisticsPresenter = new AddLogisticsPresenter();
		mLogisticsPresenter.attachView(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mLogisticsPresenter.detachView();
	}

	@Override
	public void onAddLogisticsSuccess() {
		loadingDialog.stop();
		ToastUtils.show(this, "添加成功");
		finish();
	}

	@Override
	public void onAddLogisticsFailed(String errorMsg) {
		loadingDialog.stop();
		ToastUtils.show(this, errorMsg);
	}

	/**
	 * 初始化视图
	 * */
	private void initView() {
		loadingDialog = new LoadingDialog(vThis);
		// 标题栏
		tvTitle.setText("发布信息");

		lBtn.setVisibility(View.VISIBLE);

		rBtn.setVisibility(View.VISIBLE);
		rBtn.setText("常跑地");
	}

	@OnClick(R.id.titlebar_btnRight)
	public void onTitleRightClick() {

	}
	@OnClick(R.id.titlebar_btnLeft)
	public void onTitleLeftClick() {
		finish();
	}

	@OnClick(R.id.submit)
	public void onSubmitClick() {
		if (!loadingDialog.isShowing()) {
			loadingDialog.show();
		}
		mLogisticsPresenter.addLogistics();
	}

}
