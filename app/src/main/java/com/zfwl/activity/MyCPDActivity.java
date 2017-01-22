package com.zfwl.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;

import com.zfwl.R;
import com.zfwl.activity.home.HomeActivity;
import com.zfwl.adapter.CPDAdatper;
import com.zfwl.controls.LoadingDialog;
import com.zfwl.entity.Address;
import com.zfwl.entity.CPDModel;
import com.zfwl.mvp.cpd.CPDMvpView;
import com.zfwl.mvp.cpd.CPDPresenter;
import com.zfwl.util.DisplayUtil;
import com.zfwl.util.ViewHub;
import com.zfwl.widget.ToastUtils;
import com.zfwl.widget.slsectarea.SelectAreaListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.titlebar.BGATitleBar;

public class MyCPDActivity extends BaseActivity implements SelectAreaListView.SelectAreaCallback,CPDMvpView {

    private static final int ID_WHO_SELECT_FROM = 1;
    private static final int ID_WHO_SELECT_TO = 2;

    @BindView(R.id.title_bar)
    BGATitleBar mTitleBar;
    @BindView(R.id.btn_add_new)
    TextView btnAddNew;
    @BindView(R.id.listview_step3)
    ListView mListAddress;
    @BindView(R.id.view_select_area)
    SelectAreaListView mSelectAreaView;

    private CPDAdatper adapter;
    private int select_address_index;

    private MyCPDActivity vThis = this;
    private LoadingDialog loadingDialog;
    private CPDPresenter mPresenter;
    private boolean isFromSignup;

    public static void launch(Context context,boolean _isFromSignup) {
        Intent intent = new Intent(context, MyCPDActivity.class);
        intent.putExtra("isFromSignup",_isFromSignup);
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
        isFromSignup = getIntent().getBooleanExtra("isFromSignup",false);

        initView();
        mPresenter.getList();
    }

    /**
     * 初始化视图
     */
    private void initView() {
        loadingDialog = new LoadingDialog(vThis);

        // 标题栏
        TextView rightTv = mTitleBar.getRightCtv();
//        rightTv.setTextSize(DisplayUtil.spToPx(8));
        rightTv.setTextColor(0xff666666);
        rightTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CPDModel m = ((CPDModel)adapter.getItem(select_address_index));
                mPresenter.add(m);
            }
        });

        mTitleBar.getLeftCtv().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mSelectAreaView.setCallback(this);
    }

    @OnClick(R.id.btn_add_new)
    public void onAddNewClick() {
        CPDModel m = new CPDModel();
        adapter.mList.add(m);
        adapter.notifyDataSetChanged();
        select_address_index = adapter.mList.size()-1;
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
        if(address == null || address.getProvince() == null || address.getCity() == null || address.getDistrict() == null){
            ToastUtils.show(this, "请把信息填完整");
            return;
        }
        CPDModel m = (CPDModel)adapter.getItem(select_address_index);
        switch (idWhoSelect) {
            case ID_WHO_SELECT_FROM:
                m.fromaddress = address;
                m.setFromProvinceId(address.getProvince().getId());
                m.setFromCityId(address.getCity().getId());
                m.setFromCountyId(address.getDistrict().getId());
                m.setFromAddressName(address.getProvince().getName()+" "+
                        address.getCity().getName()+" "+
                        address.getDistrict().getName()+" ");
                break;
            case ID_WHO_SELECT_TO:
                m.toaddress = address;
                m.setToProvinceId(address.getProvince().getId());
                m.setToCityId(address.getCity().getId());
                m.setToCountyId(address.getDistrict().getId());
                m.setToAddressName(address.getProvince().getName()+" "+
                        address.getCity().getName()+" "+
                        address.getDistrict().getName()+" ");
                break;
        }
        adapter.notifyDataSetChanged();
    }
    @Override
    public void onAreaClose(){}
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

            @Override
            public void del(int index) {
                select_address_index = index;
                mPresenter.del(((CPDModel)adapter.getItem(index)));
            }
        });
    }
    @Override
    public void onListLoadedFail(String msg) {
        ViewHub.showLongToast(this, msg);
    }

    @Override
    public void onAdded(CPDModel data) {
        mPresenter.getList();
        if (isFromSignup) {
            HomeActivity.launch(this);
        } else {
            finish();
        }
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
