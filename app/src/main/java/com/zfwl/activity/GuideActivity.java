package com.zfwl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

import com.baidu.mobstat.StatService;
import com.zfwl.adapter.GuideViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends Activity implements OnClickListener {

	private GuideActivity vThis = this;

	private ViewPager viewPager;
	private List<View> pageViews;
	private Button btnReg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_guide);

		initViewPager();
	}

	/**
	 * 加载分页视图
	 * */
	private void initViewPager() {
		View guideView1 = LayoutInflater.from(vThis).inflate(
				R.layout.layout_guide_item_1, null);
		View guideView2 = LayoutInflater.from(vThis).inflate(
				R.layout.layout_guide_item_2, null);
		View guideViewEnd = LayoutInflater.from(vThis).inflate(
				R.layout.layout_guide_item_end, null);

		btnReg = (Button) guideViewEnd.findViewById(R.id.login_pre_btnReg);
		btnReg.setOnClickListener(this);

		pageViews = new ArrayList<View>();
		pageViews.add(guideView1);
		pageViews.add(guideView2);
		pageViews.add(guideViewEnd);

		viewPager = (ViewPager) findViewById(R.id.guide_viewPager);
		GuideViewPagerAdapter adapter = new GuideViewPagerAdapter(pageViews);
		viewPager.setAdapter(adapter);

		viewPager.setOnPageChangeListener(onPageChangeListener);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.login_pre_btnReg:
				Intent intent1 = new Intent(this, LoginActivity.class);
				startActivity(intent1);
				break ;
		}
	}

	/**
	 * 切换页时，更换页脚小圈圈
	 * */
	private OnPageChangeListener onPageChangeListener = new OnPageChangeListener() {

		@Override
		public void onPageSelected(int arg0) {
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		@Override
		public void onPageScrollStateChanged(int arg0) {

		}
	};

	@Override
	protected void onPause() {
		super.onPause();
		StatService.onPause(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		StatService.onResume(this);
	}
}
