package com.zfwl.widget;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zfwl.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BottomNavBtn extends LinearLayout {
    private int mEnableImgResId, mDisableImgResId;
    @BindView(R.id.iv_nav_icon)
    ImageView mIvIcon;
    @BindView(R.id.tv_nav_text)
    TextView mTvText;
    private int mEnableColorResId = 0xff157afb;
    private int mDisableColorResId = 0xb9c0c8;

    public BottomNavBtn(Context context) {
        super(context);
        init(null, 0);
    }

    public BottomNavBtn(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public BottomNavBtn(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        inflate(getContext(), R.layout.layout_bottom_nav_btn, this);
        ButterKnife.bind(this, this);
    }

    public void init(String text, @DrawableRes int enableImgResId, @DrawableRes int disableImgResId) {
        mEnableImgResId = enableImgResId;
        mDisableImgResId = disableImgResId;
        mTvText.setText(text);
    }

    public void enable() {
        mIvIcon.setImageResource(mEnableImgResId);
        mTvText.setTextColor(mEnableColorResId);
    }

    public void disable() {
        mIvIcon.setImageResource(mDisableImgResId);
        mTvText.setTextColor(mDisableColorResId);
    }


}
