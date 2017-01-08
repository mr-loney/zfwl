package com.zfwl.activity.myorders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.zfwl.R;
import com.zfwl.activity.BaseActivity;
import com.zfwl.controls.LoadingDialog;
import com.zfwl.mvp.orders.comment.OrderCommentMvpView;
import com.zfwl.mvp.orders.comment.OrderCommentPresenter;
import com.zfwl.widget.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.titlebar.BGATitleBar;
import cn.bingoogolapple.titlebar.BGATitleBar.SimpleDelegate;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;
import me.zhanghai.android.materialratingbar.MaterialRatingBar.OnRatingChangeListener;

public class OrderCommentActivity extends BaseActivity implements OnRatingChangeListener, OrderCommentMvpView {
    private static final String TAG = "OrderCommentActivity";
    private static final String EXTRA_ORDER_ID = "EXTRA_ORDER_ID";
    @BindView(R.id.title_bar)
    BGATitleBar mTitleBar;
    @BindView(R.id.rb_create_time)
    MaterialRatingBar mRbCreateTime;
    @BindView(R.id.rb_out_time)
    MaterialRatingBar mRbOutTime;
    @BindView(R.id.rb_service)
    MaterialRatingBar mRbService;
    @BindView(R.id.rb_sales_attitude)
    MaterialRatingBar mRbSalesAttitude;
    @BindView(R.id.et_remark)
    EditText mEtRemark;
    private LoadingDialog mLoadingDialog;
    private OrderCommentPresenter mOrderCommentPresenter;
    private int mRateDepotTime, mRateOutTime, mRateService, mRateAttitude;
    private long mOrderId;

    public static void launch(Context context, long orderId) {
        Intent intent = new Intent(context, OrderCommentActivity.class);
        intent.putExtra(EXTRA_ORDER_ID, orderId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_comment);
        ButterKnife.bind(this);
        initViews();
        mOrderId = getIntent().getLongExtra(EXTRA_ORDER_ID, 0);
        mOrderCommentPresenter = new OrderCommentPresenter();
        mOrderCommentPresenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mOrderCommentPresenter.detachView();
    }

    private void initViews() {
        ButterKnife.bind(this);
        initTitleBar();
        initRatingBars();
        mLoadingDialog = new LoadingDialog(this);
    }

    private void initTitleBar() {
        mTitleBar.getRightCtv().setTextColor(0xff1373fa);
        mTitleBar.setDelegate(new SimpleDelegate() {
            @Override
            public void onClickLeftCtv() {
                onBackPressed();
            }

            @Override
            public void onClickRightCtv() {
                submitComment();
            }
        });
    }

    private void submitComment() {
        if (isAllRated()) {
            mOrderCommentPresenter.submitComment(mOrderId, mRateDepotTime, mRateOutTime, mRateService, mRateAttitude, getRemark());
        } else {
            ToastUtils.show(this, "请全部评分再提交哦");
        }
    }

    private String getRemark() {
        return mEtRemark.getText() == null ? "" : mEtRemark.getText().toString();
    }
    private void initRatingBars() {
        mRbCreateTime.setOnRatingChangeListener(this);
        mRbOutTime.setOnRatingChangeListener(this);
        mRbSalesAttitude.setOnRatingChangeListener(this);
        mRbService.setOnRatingChangeListener(this);
    }

    @Override
    public void onRatingChanged(MaterialRatingBar ratingBar, float rating) {
        switch (ratingBar.getId()) {
            case R.id.rb_create_time:
                mRateDepotTime = (int) rating;
                break;
            case R.id.rb_out_time:
                mRateOutTime = (int) rating;
                break;
            case R.id.rb_sales_attitude:
                mRateAttitude = (int) rating;
                break;
            case R.id.rb_service:
                mRateService = (int) rating;
                break;
        }
    }

    @Override
    public void showLoading() {
        mLoadingDialog.show();
    }

    @Override
    public void hideLoading() {
        mLoadingDialog.hide();
    }

    @Override
    public void onCommentSuccess() {
        ToastUtils.show(this, "评价成功");
    }

    @Override
    public void onCommentFailed(String msg) {
        ToastUtils.show(this, "评价失败:" + msg);
    }

    public boolean isAllRated() {
        return mRateAttitude * mRateOutTime * mRateService * mRateDepotTime > 0;
    }
}
