package com.zfwl.activity.home;


import android.app.Activity;
import android.os.Bundle;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MeFragment extends Fragment {


    @BindView(R.id.iv_logobg)
    ImageView ivLogobg;
    @BindView(R.id.iv_userhead)
    com.zfwl.controls.CircleImageView2 ivUserhead;
    @BindView(R.id.txt_name)
    TextView txtName;
    @BindView(R.id.my_dcwj)
    TextView myDcwj;
    @BindView(R.id.my_setting)
    TextView mySetting;
    @BindView(R.id.circle_order_text)
    com.zfwl.controls.CircleTextView circleOrderText;
    @BindView(R.id.my_order_1)
    RelativeLayout myOrder1;
    @BindView(R.id.circle_order_text1)
    com.zfwl.controls.CircleTextView circleOrderText1;
    @BindView(R.id.my_order_2)
    RelativeLayout myOrder2;
    @BindView(R.id.circle_order_text2)
    com.zfwl.controls.CircleTextView circleOrderText2;
    @BindView(R.id.my_order_3)
    RelativeLayout myOrder3;
    @BindView(R.id.my_order_4)
    LinearLayout myOrder4;
    @BindView(R.id.item_myorder)
    View itemOrder;
    @BindView(R.id.item_mybj)
    View itemBJ;
    @BindView(R.id.item_mykc)
    View itemKC;

    private Activity mContext;

    public MeFragment() {
    }

    public static MeFragment newInstance() {
        return new MeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, container, false);
        ButterKnife.bind(this, view);
        mContext = this.getActivity();
        initView();
        return view;
    }

    private void initView(){
        initItem(itemOrder, "我的订单", false);
        setItemRightText(itemOrder, "查看全部订单");
        initItem(itemBJ, "我的报价", false);
        initItem(itemKC, "我发布的空车", false);

        txtName.setText(UserInfoManager.INSTANCE.getUserInfo().getNickname());
    }

    @OnClick({R.id.my_dcwj, R.id.my_setting, R.id.my_order_1, R.id.my_order_2, R.id.my_order_3, R.id.my_order_4,
            R.id.item_myorder, R.id.item_mykc, R.id.item_mybj})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.my_dcwj:
                WJActivity.launch(mContext);
                break;
            case R.id.my_setting:
                SettingActivity.launch(mContext);
                break;
            case R.id.my_order_1:
                break;
            case R.id.my_order_2:
                break;
            case R.id.my_order_3:
                break;
            case R.id.my_order_4:
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
