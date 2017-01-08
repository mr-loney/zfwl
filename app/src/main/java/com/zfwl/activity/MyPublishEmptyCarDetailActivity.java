package com.zfwl.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zfwl.R;
import com.zfwl.controls.LineTextView;
import com.zfwl.controls.LoadingDialog;
import com.zfwl.entity.MyPublishEmptyCarListModel;
import com.zfwl.event.MyPublishEmptyCarEvent;
import com.zfwl.mvp.logistics.MyPublishEmptyCarMvpView;
import com.zfwl.mvp.logistics.MyPublishEmptyCarPresenter;
import com.zfwl.util.ViewHub;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyPublishEmptyCarDetailActivity extends BaseActivity implements MyPublishEmptyCarMvpView {

    @BindView(R.id.titlebar_btnLeft)
    Button titlebarBtnLeft;
    @BindView(R.id.titlebar_btnRight)
    Button titlebarBtnRight;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.txt_from)
    TextView txtFrom;
    @BindView(R.id.txt_to)
    TextView txtTo;
    @BindView(R.id.detail_txt1)
    LineTextView detailTxt1;
    @BindView(R.id.detail_txt2)
    LineTextView detailTxt2;
    @BindView(R.id.detail_txt3)
    LineTextView detailTxt3;
    @BindView(R.id.detail_txt4)
    LineTextView detailTxt4;

    private MyPublishEmptyCarDetailActivity vThis = this;
    private LoadingDialog loadingDialog;
    private MyPublishEmptyCarListModel.ListBean data;

    private MyPublishEmptyCarPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_my_publish_empty_car_detail);
        ButterKnife.bind(this);
        mPresenter = new MyPublishEmptyCarPresenter(this);
        mPresenter.attachView(this);

        Intent intent = getIntent();
        data = (MyPublishEmptyCarListModel.ListBean) getIntent().getSerializableExtra("data");
        initView();
    }

    /**
     * 初始化视图
     */
    private void initView() {
        loadingDialog = new LoadingDialog(vThis);
        // 标题栏
        titlebarBtnLeft.setVisibility(View.VISIBLE);
        tvTitle.setText("空车详情");
        titlebarBtnRight.setVisibility(View.VISIBLE);
        titlebarBtnRight.setText("删除");
        titlebarBtnRight.setTextColor(getResources().getColor(R.color.red));

        loadedData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @OnClick(R.id.titlebar_btnRight)
    public void onTitleRightClick() {
    mPresenter.del(data.getId());
    }

    @OnClick(R.id.titlebar_btnLeft)
    public void onTitleLeftClick() {
        finish();
    }

    private void loadedData() {
        txtFrom.setText(data.getFromAddressName());
        String toStr = "";
        for (MyPublishEmptyCarListModel.ListBean.EmptyCarAddressListBean item : data.getEmptyCarAddressList()) {
            if (item.getToAddressName()!=null && item.getToAddressName().length()>0) {
                toStr+=item.getToAddressName()+"<br/>";
            }
        }
        if (toStr.length()>3) { toStr = toStr.substring(0,toStr.length()-5); }
        txtTo.setText(Html.fromHtml(toStr));

        detailTxt1.setDetail(data.getCdate()+"");
        detailTxt2.setDetail(data.getCarNumber()+"");
        detailTxt3.setDetail(data.getCarLength()+"");
        detailTxt4.setDetail(data.getLoadNumber()+"");
    }

    @Override
    public void onGetListSuccess(MyPublishEmptyCarListModel d) {}

    @Override
    public void onGetListFailed(String errorMsg) {}

    @Override
    public void onDelSuccess(MyPublishEmptyCarListModel.ListBean d) {
        ViewHub.showLongToast(this,"删除成功");
        EventBus.getDefault().post(new MyPublishEmptyCarEvent("reload"));
        finish();
    }

    @Override
    public void onDelFail(String errorMsg) {
        ViewHub.showLongToast(this,errorMsg);
        finish();
    }
}
