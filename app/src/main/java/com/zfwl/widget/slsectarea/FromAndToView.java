package com.zfwl.widget.slsectarea;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zfwl.R;
import com.zfwl.common.Const;
import com.zfwl.entity.Area;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ZZB on 2016/12/17.
 */
public class FromAndToView extends LinearLayout {
    private static final String DEFAULT_FROM = "出发地";
    private static final String DEFAULT_TO = "目的地";
    @BindView(R.id.tv_from)
    TextView mTvFrom;
    @BindView(R.id.tv_to)
    TextView mTvTo;
    @BindView(R.id.tv_begin_time)
    TextView mTvStartTime;
    private Callback mCallback;
    private Area mFromArea, mToArea;

    public interface Callback {
        void onFromAreaClick(Area area);

        void onToAreaClick(Area area);

        void onStartTimeSelected(long timeInMillis);
    }

    @OnClick(R.id.tv_from)
    public void onFromAreaClick() {
        mCallback.onFromAreaClick(mFromArea);
    }

    @OnClick(R.id.tv_to)
    public void onToAreaClick() {
        mCallback.onToAreaClick(mToArea);
    }

    @OnClick(R.id.tv_begin_time)
    public void onStartTimeClick() {
//        mCallback.onStartTimeSelected(0);
    }

    public void resetArea() {
        mTvFrom.setText(DEFAULT_FROM);
        mTvTo.setText(DEFAULT_TO);
        mFromArea = Const.INVALID_AREA;
        mToArea = Const.INVALID_AREA;
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    private void init(Context context) {
        inflate(context, R.layout.layout_from_n_to, this);
        ButterKnife.bind(this, this);
        resetArea();
    }

    public void setFromArea(Area area) {
        this.mFromArea = area;
    }

    public void setToArea(Area area) {
        this.mFromArea = area;
    }

    public Area getFromArea() {
        return mFromArea;
    }

    public Area getToArea() {
        return mToArea;
    }

    public FromAndToView(Context context) {
        super(context);
        init(context);
    }

    public FromAndToView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FromAndToView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


}
