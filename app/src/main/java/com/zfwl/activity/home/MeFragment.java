package com.zfwl.activity.home;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zfwl.R;
import com.zfwl.activity.MyPublishEmptyCarActivity;
import com.zfwl.activity.MyQuotedListActivity;
import com.zfwl.activity.SettingActivity;
import com.zfwl.activity.WJActivity;
import com.zfwl.activity.myorders.MyOrdersActivity;
import com.zfwl.data.UserInfoManager;
import com.zfwl.common.MyLog;
import com.zfwl.controls.CircleImageView2;
import com.zfwl.controls.CircleTextView;
import com.zfwl.entity.Order.Type;
import com.zfwl.event.ClearOrderReadPointEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MeFragment extends Fragment {
    private static final String TAG = "MeFragment";

    @BindView(R.id.iv_logobg)
    ImageView ivLogobg;
    @BindView(R.id.iv_userhead)
    CircleImageView2 ivUserhead;
    @BindView(R.id.txt_name)
    TextView txtName;
    @BindView(R.id.my_dcwj)
    TextView myDcwj;
    @BindView(R.id.my_setting)
    TextView mySetting;
    @BindView(R.id.circle_order_text)
    CircleTextView circleOrderText;
    @BindView(R.id.btn_order_wait_confirm)
    RelativeLayout myOrder1;
    @BindView(R.id.circle_order_text1)
    CircleTextView circleOrderText1;
    @BindView(R.id.btn_order_wait_pay)
    RelativeLayout myOrder2;
    @BindView(R.id.circle_order_text2)
    CircleTextView circleOrderText2;
    @BindView(R.id.btn_order_paid)
    RelativeLayout myOrder3;
    @BindView(R.id.btn_order_carrying)
    LinearLayout myOrder4;
    @BindView(R.id.item_myorder)
    View itemOrder;
    @BindView(R.id.item_mybj)
    View itemBJ;
    @BindView(R.id.item_mykc)
    View itemKC;
    @BindView(R.id.read_wait_confirm)
    View mReadWaitConfirm;
    @BindView(R.id.read_wait_pay)
    View mReadWaitPay;
    @BindView(R.id.read_wait_paid)
    View mReadWaitPaid;
    @BindView(R.id.read_wait_carrying)
    View mReadWaitCarrying;

    private Activity mContext;

    public MeFragment() {
    }

    public static MeFragment newInstance() {
        return new MeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, container, false);
        ButterKnife.bind(this, view);
        mContext = this.getActivity();
        initView();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void initView() {
        initItem(itemOrder, "我的订单", false);
        setItemRightText(itemOrder, "查看全部订单");
        initItem(itemBJ, "我的报价", false);
        initItem(itemKC, "我发布的空车", false);

        txtName.setText(UserInfoManager.INSTANCE.getUserInfo().getNickname());
    }

    @OnClick({R.id.my_dcwj, R.id.my_setting, R.id.btn_order_wait_confirm, R.id.btn_order_wait_pay, R.id.btn_order_paid, R.id.btn_order_carrying,
            R.id.item_myorder, R.id.item_mykc, R.id.item_mybj})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.my_dcwj:
                WJActivity.launch(mContext);
                break;
            case R.id.my_setting:
                SettingActivity.launch(mContext);
                break;
            case R.id.btn_order_wait_confirm:
                mReadWaitConfirm.setVisibility(View.GONE);
                MyOrdersActivity.launch(mContext, Type.WAIT_CONFIRM);
                break;
            case R.id.btn_order_wait_pay:
                mReadWaitPay.setVisibility(View.GONE);
                MyOrdersActivity.launch(mContext, Type.WAIT_PAY);
                break;
            case R.id.btn_order_paid:
                mReadWaitPaid.setVisibility(View.GONE);
                MyOrdersActivity.launch(mContext, Type.PAID);
                break;
            case R.id.btn_order_carrying:
                mReadWaitCarrying.setVisibility(View.GONE);
                MyOrdersActivity.launch(mContext, Type.CARRYING);
                break;
            case R.id.item_myorder:
                MyOrdersActivity.launch(getActivity());
                break;
            case R.id.item_mykc:
                MyPublishEmptyCarActivity.launch(mContext);
                break;
            case R.id.item_mybj:
                MyQuotedListActivity.launch(mContext);
                break;
        }
    }

    @Subscribe
    public void onClearOrderReadPoint(ClearOrderReadPointEvent event) {
        MyLog.i(TAG, "onClearOrderReadPoint: %d", event.orderType);
        switch (event.orderType) {
            case Type.WAIT_CONFIRM:
                mReadWaitConfirm.setVisibility(View.GONE);
                break;
            case Type.WAIT_PAY:
                mReadWaitPay.setVisibility(View.GONE);
                break;
            case Type.PAID:
                mReadWaitPaid.setVisibility(View.GONE);
                break;
            case Type.CARRYING:
                mReadWaitCarrying.setVisibility(View.GONE);
                break;
        }
    }

    private void initItem(View v, String text, boolean b) {
        TextView tv = (TextView) v.findViewById(R.id.tv_left_text);
        ImageView ivLeftIcon = (ImageView) v.findViewById(R.id.iv_left_icon);
        View bBtmLine = v.findViewById(R.id.view_btm_line);
        if (!b) {
            bBtmLine.setVisibility(View.VISIBLE);
        } else {
            bBtmLine.setVisibility(View.GONE);
        }

        tv.setText(text);
        ivLeftIcon.setVisibility(View.GONE);

    }

    private void setItemRightText(View v, String text) {
        TextView tv = (TextView) v.findViewById(R.id.tv_right_text);
        tv.setText(text);
    }

    private void setItemRightText(int viewId, Spanned spanned) {
        View v = mContext.findViewById(viewId);
        TextView tv = (TextView) v.findViewById(R.id.tv_right_text);
        tv.setText(spanned);
    }
}
