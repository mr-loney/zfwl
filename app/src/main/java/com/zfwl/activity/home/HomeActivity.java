package com.zfwl.activity.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.zfwl.R;
import com.zfwl.adapter.SimpleFragmentPagerAdapter;
import com.zfwl.common.MyLog;
import com.zfwl.widget.BottomNavBtn;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";
    private WLFragment mWlFragment;
    private MeFragment mMeFragment;
    private int mCurrentPosition = 0;
    @BindView(R.id.viewpager_main)
    ViewPager mViewPager;
    @BindView(R.id.btn_wl)
    BottomNavBtn mBtnWl;
    @BindView(R.id.btn_fc)
    BottomNavBtn mBtnFc;
    @BindView(R.id.btn_me)
    BottomNavBtn mBtnMe;
    private List<BottomNavBtn> mNavBtns = new ArrayList<>();

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

    }

    @OnClick(R.id.btn_me)
    public void onBtnMeClick() {
        mViewPager.setCurrentItem(1);
    }

    private void initViews() {
        mBtnWl.init("物流", R.drawable.ic_main_wl_e, R.drawable.ic_main_fc_d);
        mBtnFc.init("发车", R.drawable.ic_main_wl_e, R.drawable.ic_main_fc_d);
        mBtnMe.init("我的", R.drawable.ic_main_wl_e, R.drawable.ic_main_me_d);
        mBtnWl.enable();
        mBtnFc.disable();
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
                switch (position){
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
}
