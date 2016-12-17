package com.zfwl.activity;

import android.os.Bundle;
import android.os.PersistableBundle;

import com.bilibili.socialize.share.core.SocializeMedia;
import com.bilibili.socialize.share.core.shareparam.BaseShareParam;
import com.zfwl.R;
import com.zfwl.share.ShareHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.titlebar.BGATitleBar;
import cn.bingoogolapple.titlebar.BGATitleBar.SimpleDelegate;

/**
 * 货源详情
 * Created by ZZB on 2016/12/17.
 */
public class GoodsDetailActivity extends BaseShareableActivity  {

    @BindView(R.id.title_bar)
    BGATitleBar mTitleBar;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_goods_activity);
        ButterKnife.bind(this);
        initViews();
    }

    private void initTitleBar() {
        mTitleBar.setDelegate(new SimpleDelegate() {
            @Override
            public void onClickLeftCtv() {
                onBackPressed();
            }

            @Override
            public void onClickRightCtv() {
                onShareClick();
            }
        });
    }

    private void onShareClick() {

    }

    private void initViews() {
        initTitleBar();
    }

    @Override
    public BaseShareParam getShareContent(ShareHelper helper, SocializeMedia target) {
        return null;
    }
}
