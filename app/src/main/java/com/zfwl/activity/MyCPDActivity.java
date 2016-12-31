package com.zfwl.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.zfwl.R;
import com.zfwl.activity.home.HomeActivity;
import com.zfwl.adapter.CPDAdatper;
import com.zfwl.controls.LineTextView;
import com.zfwl.controls.LoadingDialog;
import com.zfwl.entity.Address;
import com.zfwl.entity.CPDModel;
import com.zfwl.mvp.cpd.CPDMvpView;
import com.zfwl.mvp.cpd.CPDPresenter;
import com.zfwl.util.ViewHub;
import com.zfwl.widget.slsectarea.SelectAreaListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyCPDActivity extends BaseActivity implements SelectAreaListView.SelectAreaCallback,CPDMvpView {

    private static final int ID_WHO_SELECT_FROM = 1;
    private static final int ID_WHO_SELECT_TO = 2;

    @BindView(R.id.titlebar_btnLeft)
    Button titlebarBtnLeft;
    @BindView(R.id.btn_add_new)
    Button btnAddNew;
    @BindView(R.id.listview_step3)
    ListView mListAddress;
    @BindView(R.id.view_select_area)
    SelectAreaListView mSelectAreaView;

    private CPDAdatper adapter;
    private int select_address_index;

    private MyCPDActivity vThis = this;
    private LoadingDialog loadingDialog;
    private CPDPresenter mPresenter;

    public static void launch(Context context) {
        Intent intent = new Intent(context, MyCPDActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_my_cpd);
        ButterKnife.bind(this);
        mPresenter = new CPDPresenter(this);
        mPresenter.attachView(this);

        initView();
        mPresenter.getList();
    }

    /**
     * 初始化视图
     */
    private void initView() {
        loadingDialog = new LoadingDialog(vThis);

        titlebarBtnLeft.setVisibility(View.VISIBLE);

        TextView tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setText("管理常跑地");

        Button sigupBtn = (Button) findViewById(R.id.titlebar_btnRight);
        sigupBtn.setText("完成");
        sigupBtn.setVisibility(View.VISIBLE);

        mSelectAreaView.setCallback(this);
    }

    @OnClick(R.id.btn_add_new)
    public void onAddNewClick() {
        CPDModel m = new CPDModel();
        adapter.mList.add(m);
        adapter.notifyDataSetChanged();
    }
    @OnClick(R.id.titlebar_btnLeft)
    public void onLeftClick() {
        finish();
    }
    @OnClick(R.id.titlebar_btnRight)
    public void onSaveClick() {

    }

    private boolean validateInput() {
//        String firstFrom = adapter.mList.get(0).getFromDistrict();
//        String firstTo = adapter.mList.get(0).getToDistrict();
//        if (firstFrom.length() == 0 || firstTo.length() == 0) {
//            ViewHub.showLongToast(this, "请选择常用地点");
//            return false;
//        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    public void onAddressSelected(int idWhoSelect, Address address) {
        switch (idWhoSelect) {
            case ID_WHO_SELECT_FROM:
                ((CPDModel)adapter.getItem(select_address_index)).fromaddress = address;
                break;
            case ID_WHO_SELECT_TO:
                ((CPDModel)adapter.getItem(select_address_index)).toaddress = address;
                break;
        }
    }
    @Override
    public void onAreaReset() {
    }

    @Override
    public void onListLoaded(List<CPDModel> datas) {

        ArrayList arr = new ArrayList();
        if (datas.size()>0) {
            arr.addAll(datas);
        } else {
            CPDModel m = new CPDModel();
            arr.add(m);
        }
        adapter = new CPDAdatper(this, arr);
        mListAddress.setAdapter(adapter);
        adapter.setListener(new CPDAdatper.OnUserRegAddressAdapterListener() {
            @Override
            public void selectToAddress(int index) {
                select_address_index = index;
                mSelectAreaView.show(ID_WHO_SELECT_TO, ((CPDModel)adapter.getItem(index)).toaddress);
            }

            @Override
            public void selectFromAddress(int index) {
                select_address_index = index;
                mSelectAreaView.show(ID_WHO_SELECT_FROM, ((CPDModel)adapter.getItem(index)).fromaddress);
            }
        });
    }
    @Override
    public void onListLoadedFail(String msg) {
        ViewHub.showLongToast(this, msg);
    }

    @Override
    public void onAdded(CPDModel data) {

    }
    @Override
    public void onAddedFail(String msg) {
        ViewHub.showLongToast(this, msg);
    }

    @Override
    public void onDel() {
        ViewHub.showLongToast(this, "删除成功");
        mPresenter.getList();
    }
    @Override
    public void onDelFail(String msg) {
        ViewHub.showLongToast(this, msg);
    }
}
