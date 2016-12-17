package com.zfwl.widget.slsectarea;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zfwl.R;
import com.zfwl.common.Const;
import com.zfwl.entity.Address;

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
    private Address mFromAddress, mToAddress;

    public interface Callback {
        void onFromAddressClick(Address address);

        void onToAddressClick(Address address);

        void onStartTimeSelected(long timeInMillis);
    }

    @OnClick(R.id.tv_from)
    public void onFromAreaClick() {
        mCallback.onFromAddressClick(mFromAddress);
    }

    @OnClick(R.id.tv_to)
    public void onToAreaClick() {
        mCallback.onToAddressClick(mToAddress);
    }

    @OnClick(R.id.tv_begin_time)
    public void onStartTimeClick() {
//        mCallback.onStartTimeSelected(0);
    }

    public void resetArea() {
        mTvFrom.setText(DEFAULT_FROM);
        mTvTo.setText(DEFAULT_TO);
        mFromAddress = Const.INVALID_ADDRESS;
        mToAddress = Const.INVALID_ADDRESS;
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    private void init(Context context) {
        inflate(context, R.layout.layout_from_n_to, this);
        ButterKnife.bind(this, this);
        resetArea();
    }

    public Address getFromAddress() {
        return mFromAddress;
    }

    public void setFromAddress(Address fromAddress) {
        mFromAddress = fromAddress;
        mTvFrom.setText(fromAddress.getDistrict().getName());
    }

    public Address getToAddress() {
        return mToAddress;
    }

    public void setToAddress(Address toAddress) {
        mToAddress = toAddress;
        mTvTo.setText(toAddress.getDistrict().getName());
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
