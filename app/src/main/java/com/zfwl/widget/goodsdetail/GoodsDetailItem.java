package com.zfwl.widget.goodsdetail;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zfwl.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ZZB on 2016/12/18.
 */
public class GoodsDetailItem extends RelativeLayout {
    @BindView(R.id.tv_key)
    TextView mTvKey;
    @BindView(R.id.tv_value)
    TextView mTvValue;

    public GoodsDetailItem(Context context) {
        super(context);
        init(context);
    }

    public GoodsDetailItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public GoodsDetailItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.layout_goods_detail_item, this);
        ButterKnife.bind(this, this);
    }

    public void setKeyText(String text) {
        mTvKey.setText(text);
    }

    public void setValueText(String text) {
        mTvValue.setText(text);
    }

    public void setValueTextColor(int color) {
        mTvValue.setTextColor(color);
    }
}
