package com.zfwl.widget.pay;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;

import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.zfwl.R;
import com.zfwl.common.Const.WeChat;
import com.zfwl.controls.LoadingDialog;
import com.zfwl.event.WxPayEvent;
import com.zfwl.mvp.wxpay.WxPayMvpView;
import com.zfwl.mvp.wxpay.WxPayPresenter;
import com.zfwl.widget.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ZZB on 2017/1/22.
 */
public class PayPopupWindow extends PopupWindow implements OnDismissListener, WxPayMvpView {


    @BindView(R.id.iv_check_wx)
    ImageView mIvCheckWx;
    @BindView(R.id.iv_check_zfb)
    ImageView mIvCheckZfb;
    @BindView(R.id.iv_check_face)
    ImageView mIvCheckFace;

    private LoadingDialog mLoadingDialog;
    private Context mContext;
    private IWXAPI mWxApi;
    private WxPayPresenter mWxPayPresenter;

    public PayPopupWindow(Context context) {
        super(context);
        initViews(context);
        EventBus.getDefault().register(this);
        mContext = context;
        mWxApi = WXAPIFactory.createWXAPI(context, WeChat.APP_ID);
        mWxPayPresenter = new WxPayPresenter();
        mWxPayPresenter.attachView(this);
        setOnDismissListener(this);
    }

    private void initViews(Context context) {
        View contentView = LayoutInflater.from(context).inflate(R.layout.layout_pay_popup, null);
        setContentView(contentView);
        ButterKnife.bind(this, contentView);
        this.setWidth(LayoutParams.MATCH_PARENT);
        setHeight(LayoutParams.MATCH_PARENT);
        mLoadingDialog = new LoadingDialog(context);
    }

    public void show(View anchorView) {
        showAtLocation(anchorView, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL,
                0, 0);
    }

    @OnClick({R.id.layout_wx, R.id.layout_zfb, R.id.layout_face})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_wx:
                onWxPayClick();
                break;
            case R.id.layout_zfb:
                onZfbPayClick();
                break;
            case R.id.layout_face:
                onFacePayClick();
                break;
        }
    }

    private void onWxPayClick() {
        mWxPayPresenter.wechatPay(mWxApi, System.currentTimeMillis() + "", "goodsDesc", 0.01);
    }

    private void onZfbPayClick() {

    }

    private void onFacePayClick() {

    }

    @Override
    public void onDismiss() {
        mWxPayPresenter.detachView();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void showGetWxPayInfoLoading() {
        mLoadingDialog.start("获取订单中...");
    }

    @Override
    public void hideGetWxPayInfoLoading() {
        mLoadingDialog.stop();
    }

    @Override
    public void showCallingPayLoading() {
        ToastUtils.show(mContext, "正在调起支付...");
    }

    @Override
    public void onGetWxPayInfoFailed(String msg) {
        ToastUtils.show(mContext, "创建订单失败");
    }

    @Subscribe
    public void onWxPayFinished(WxPayEvent event) {
        switch (event.getCode()) {
            case 0:
                ToastUtils.show(mContext, "支付成功");
                break;
            case -2:
                ToastUtils.show(mContext, "取消支付");
                break;
            default:
                ToastUtils.show(mContext, "支付失败, " + event.getCode());
                break;
        }
    }
}
