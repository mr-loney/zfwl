package com.zfwl.widget.slsectarea;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zfwl.R;
import com.zfwl.common.Const;
import com.zfwl.entity.Address;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
    public TextView mTvFrom;
    @BindView(R.id.tv_to)
    public TextView mTvTo;
    @BindView(R.id.tv_begin_time)
    public TextView mTvStartTime;
    @BindView(R.id.tv_from_img)
    public ImageView mTvFromImg;
    @BindView(R.id.tv_to_img)
    public ImageView mTvToImg;
    @BindView(R.id.tv_begin_time_img)
    public ImageView mTvStartTimeImg;
    private Callback mCallback;
    private Address mFromAddress, mToAddress;

    public interface Callback {
        void onFromAddressClick(Address address);

        void onToAddressClick(Address address);

        void onCloseView();

        void onStartTimeSelected(long timeInMillis);
    }

    @OnClick(R.id.tv_from)
    public void onFromAreaClick() {
        setNormalStat();
        mTvTo.setTag("0");
        mTvStartTime.setTag("0");
        if (mTvFrom.getTag()=="0") {
            mTvFrom.setTextColor(Color.parseColor("#2060fe"));
            mTvFromImg.setImageResource(R.drawable.up);
            mTvFrom.setTag("1");

            mCallback.onFromAddressClick(mFromAddress);
        } else {
            mTvFrom.setTag("0");

            mCallback.onCloseView();
        }
    }

    @OnClick(R.id.tv_to)
    public void onToAreaClick() {
        setNormalStat();
        mTvFrom.setTag("0");
        mTvStartTime.setTag("0");
        if (mTvTo.getTag()=="0") {
            mTvTo.setTextColor(Color.parseColor("#2060fe"));
            mTvToImg.setImageResource(R.drawable.up);
            mTvTo.setTag("1");

            mCallback.onToAddressClick(mToAddress);
        } else {
            mTvTo.setTag("0");

            mCallback.onCloseView();
        }
    }

    @OnClick(R.id.tv_begin_time)
    public void onStartTimeClick() {
        setNormalStat();
        mTvFrom.setTag("0");
        mTvTo.setTag("0");
        if (mTvStartTime.getTag()=="0") {
            mTvStartTime.setTextColor(Color.parseColor("#2060fe"));
            mTvStartTimeImg.setImageResource(R.drawable.up);
            mTvStartTime.setTag("1");
        } else {
            mTvStartTime.setTag("0");
        }
        mCallback.onCloseView();
//        mCallback.onStartTimeSelected(0);
        String nowText = mTvStartTime.getText().toString();
        int checkIndex = 0;
        Calendar ca = Calendar.getInstance();
        ca.setTime(new Date());
        Date lastMonth = ca.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<String> dateArr = new ArrayList<>();
        dateArr.add("全部时间");
        for (int i = 1; i < 11; i++) {
            ca.add(Calendar.DAY_OF_YEAR, 1);
            String t = sdf.format(ca.getTime());
            if (i == 0) {
                t += " 今天";
            }
            if (i == 1) {
                t += " 明天";
            }
            if (i == 2) {
                t += " 后天";
            }
            if (t.equals(nowText)) {
                checkIndex = i;
            }
            dateArr.add(t);
        }
        final int checkedItem = checkIndex;
        final String[] items = dateArr.toArray(new String[dateArr.size()]);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("选择发车时间");
        builder.setSingleChoiceItems(items, checkedItem, (dialog, which) -> {
            if (which >= 0 && which < items.length) {
                String t = items[which].replace(" 今天","").replace(" 明天","").replace(" 后天","");
                mTvStartTime.setText(t);
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("确定", (dialog, which) -> {
            dialog.dismiss();
            if (which >= 0 && which < items.length) {
                String t = items[which].replace(" 今天","").replace(" 明天","").replace(" 后天","");
                mTvStartTime.setText(t);
            }
            setNormalStat();
        });
        builder.create().show();
    }

    public void setNormalStat(){
        mTvFrom.setTextColor(Color.parseColor("#000000"));
        mTvFromImg.setImageResource(R.drawable.down);
        mTvTo.setTextColor(Color.parseColor("#000000"));
        mTvToImg.setImageResource(R.drawable.down);
        mTvStartTime.setTextColor(Color.parseColor("#000000"));
        mTvStartTimeImg.setImageResource(R.drawable.down);
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
        mTvFrom.setTag("0");
        mTvTo.setTag("0");
        mTvStartTime.setTag("0");
    }

    public Address getFromAddress() {
        return mFromAddress;
    }

    public void setFromAddress(Address fromAddress) {
        mFromAddress = fromAddress;
        String txt = "";
        if (fromAddress.getProvince()!=null) {
            txt = fromAddress.getProvince().getName();
        }
        if (fromAddress.getCity()!=null) {
            txt = fromAddress.getCity().getName();
        }
        if (fromAddress.getDistrict()!=null) {
            txt = fromAddress.getDistrict().getName();
        }
        mTvFrom.setText(txt);
    }

    public Address getToAddress() {
        return mToAddress;
    }

    public void setToAddress(Address toAddress) {
        mToAddress = toAddress;
        String txt = "";
        if (mToAddress.getProvince()!=null) {
            txt = mToAddress.getProvince().getName();
        }
        if (mToAddress.getCity()!=null) {
            txt = mToAddress.getCity().getName();
        }
        if (mToAddress.getDistrict()!=null) {
            txt = mToAddress.getDistrict().getName();
        }
        mTvTo.setText(txt);
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
