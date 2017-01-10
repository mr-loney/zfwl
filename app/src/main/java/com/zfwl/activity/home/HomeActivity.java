package com.zfwl.activity.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.zfwl.R;
import com.zfwl.activity.AddzfwlActivity;
import com.zfwl.activity.LoginActivity;
import com.zfwl.adapter.SimpleFragmentPagerAdapter;
import com.zfwl.common.MyLog;
import com.zfwl.data.UserInfoManager;
import com.zfwl.widget.BottomNavBtn;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {
    private Activity mContext = this;
    private static final String TAG = "HomeActivity";
    private WLFragment mWlFragment;
    private MeFragment mMeFragment;
    private int mCurrentPosition = 0;
    @BindView(R.id.viewpager_main)
    ViewPager mViewPager;
    @BindView(R.id.btn_wl)
    BottomNavBtn mBtnWl;
    @BindView(R.id.btn_fc)
    View mBtnFc;
    @BindView(R.id.btn_me)
    BottomNavBtn mBtnMe;

    public static void launch(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initFragments();
        initViews();
    }

    @OnClick(R.id.btn_wl)
    public void onBtnWlClick() {
        mViewPager.setCurrentItem(0);
    }

    @OnClick(R.id.btn_fc)
    public void onBtnFcClick() {
        if (UserInfoManager.INSTANCE.hasLogin()) {
            AddzfwlActivity.launch(this);
        } else {
            LoginActivity.launch(this, false);
        }
    }

    @OnClick(R.id.btn_me)
    public void onBtnMeClick() {
        if (UserInfoManager.INSTANCE.hasLogin()) {
            mViewPager.setCurrentItem(1);
        } else {
            LoginActivity.launch(this, false);
        }
    }

    private void initViews() {
        mBtnWl.init("物流", R.drawable.ic_main_wl_e, R.drawable.ic_main_wl_d);
        mBtnMe.init("我的", R.drawable.ic_main_me_e, R.drawable.ic_main_me_d);
        mBtnWl.enable();
        mBtnMe.disable();
    }

    private void initFragments() {
        SimpleFragmentPagerAdapter adapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager());
        final List<Fragment> fragments = new ArrayList<>();

        mWlFragment = WLFragment.newInstance();
        mMeFragment = MeFragment.newInstance();
        fragments.add(mWlFragment);
        fragments.add(mMeFragment);
        adapter.setItems(fragments);
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                MyLog.i(TAG, "onPageSelected, " + position);
                mCurrentPosition = position;
                switch (position) {
                    case 0:
                        mBtnWl.enable();
                        mBtnMe.disable();
                        break;
                    case 1:
                        mBtnMe.enable();
                        mBtnWl.disable();
                        break;
                }
            }
        });
    }


    private long exitTime = 0;

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - exitTime > 2000) {
            Toast.makeText(mContext, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
            finish();
            System.exit(0);
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }
}
