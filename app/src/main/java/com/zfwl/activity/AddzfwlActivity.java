package com.zfwl.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.zfwl.R;
import com.zfwl.adapter.AddLogisticsAdapter;
import com.zfwl.adapter.CPDAdatper;
import com.zfwl.adapter.QuickCPDAdatper;
import com.zfwl.controls.LineTextView;
import com.zfwl.controls.LoadingDialog;
import com.zfwl.entity.Address;
import com.zfwl.entity.AllzfwlModel;
import com.zfwl.entity.CPDModel;
import com.zfwl.mvp.cpd.CPDMvpView;
import com.zfwl.mvp.cpd.CPDPresenter;
import com.zfwl.mvp.logistics.AddLogisticsMvpView;
import com.zfwl.mvp.logistics.AddLogisticsPresenter;
import com.zfwl.util.ViewHub;
import com.zfwl.widget.SelectCPDListView;
import com.zfwl.widget.ToastUtils;
import com.zfwl.widget.slsectarea.SelectAreaListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddzfwlActivity extends BaseActivity implements SelectAreaListView.SelectAreaCallback,
		SelectCPDListView.SelectCallback,AddLogisticsMvpView {

	private static final int ID_WHO_SELECT_FROM = 1;
	private static final int ID_WHO_SELECT_TO = 2;

	private static final String TAG = "LoginActivity";
	private AddzfwlActivity vThis = this;
	private LoadingDialog loadingDialog;

	@BindView(R.id.txt_from)
	TextView txtFrom;
	@BindView(R.id.listview_step3)
	 ListView listView;
	@BindView(R.id.titlebar_btnRight)
	 Button rBtn;
	@BindView(R.id.titlebar_btnLeft)
	 Button lBtn;
	@BindView(R.id.tv_title)
	 TextView tvTitle;
	@BindView(R.id.view_select_area)
	SelectAreaListView mSelectAreaView;
	@BindView(R.id.view_select_cpd)
	SelectCPDListView mSelectCPDView;

	@BindView(R.id.detail_txt1)
	LineTextView detailTxt1;
	@BindView(R.id.detail_txt2)
	LineTextView detailTxt2;
	@BindView(R.id.detail_txt3)
	LineTextView detailTxt3;
	@BindView(R.id.detail_txt4)
	LineTextView detailTxt4;

	private AllzfwlModel data = new AllzfwlModel();
	private int select_address_index;
	private AddLogisticsAdapter adapter;
	private AddLogisticsPresenter mLogisticsPresenter;


	public static void launch(Context context) {
		Intent intent = new Intent(context, AddzfwlActivity.class);
		context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_add_zfwl);
		ButterKnife.bind(this);
		initPresenters();
		loadingDialog = new LoadingDialog(this);

		initView();
	}

	private void initPresenters() {
		mLogisticsPresenter = new AddLogisticsPresenter(this);
		mLogisticsPresenter.attachView(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mLogisticsPresenter.detachView();
	}

	@Override
	public void onAddLogisticsSuccess(AllzfwlModel d) {
		loadingDialog.stop();
		AddzfwlSuccessActivity.launch(vThis);
		finish();
	}

	@Override
	public void onAddLogisticsFailed(String errorMsg) {
		loadingDialog.stop();
		ToastUtils.show(this, errorMsg);
	}

	/**
	 * 初始化视图
	 * */
	private void initView() {
		loadingDialog = new LoadingDialog(vThis);
		// 标题栏
		tvTitle.setText("发布信息");

		lBtn.setVisibility(View.VISIBLE);

		rBtn.setVisibility(View.VISIBLE);
		rBtn.setText("常跑路线");

		List<AllzfwlModel.EmptyCarAddressListBean> list = new ArrayList<>();
		AllzfwlModel.EmptyCarAddressListBean addModel = new AllzfwlModel().new EmptyCarAddressListBean();
		addModel.setToCityId("");
		addModel.setToCityName("");
		addModel.setToProvinceId("");
		addModel.setToProvinceName("");
		addModel.setToCountyId("");
		addModel.setToCountyName("");
		list.add(addModel);
		adapter = new AddLogisticsAdapter(vThis,list);
		listView.setAdapter(adapter);
		adapter.setListener(new AddLogisticsAdapter.OnAdapterListener() {
			@Override
			public void selectToAddress(int index) {
				select_address_index = index;
				mSelectAreaView.show(ID_WHO_SELECT_TO, ((AllzfwlModel.EmptyCarAddressListBean)adapter.getItem(index)).toaddress);
			}
		});

		mSelectAreaView.setCallback(this);
		mSelectCPDView.setCallback(this);
		detailTxt1.setEditing(true);
		detailTxt1.met.setInputType(EditorInfo.TYPE_CLASS_DATETIME);
		detailTxt2.setEditing(true);
		detailTxt2.met.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
		detailTxt3.setEditing(true);
		detailTxt3.met.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
		detailTxt4.setEditing(true);
		detailTxt4.met.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
	}

	@OnClick(R.id.txt_from)
	public void onFromClick() {
		mSelectAreaView.show(ID_WHO_SELECT_FROM, data.fromaddress);
	}
	@OnClick(R.id.titlebar_btnRight)
	public void onTitleRightClick() {
		mSelectCPDView.show();
	}
	@OnClick(R.id.titlebar_btnLeft)
	public void onTitleLeftClick() {
		finish();
	}

	@OnClick(R.id.submit)
	public void onSubmitClick() {
		if (!loadingDialog.isShowing()) {
			loadingDialog.show();
		}
		data.setEmptyCarAddressList(adapter.mList);
		data.setGoDate(detailTxt1.getDetail());
		data.setCarNumber(Integer.parseInt(detailTxt2.getDetail()));
		data.setCarLength(Double.parseDouble(detailTxt3.getDetail()));
		data.setLoadNumber(Double.parseDouble(detailTxt4.getDetail()));
		mLogisticsPresenter.addLogistics(data);
	}

	@Override
	public void onAddressSelected(int idWhoSelect, Address address) {
		switch (idWhoSelect) {
			case ID_WHO_SELECT_FROM:
				data.setFromProvinceId(address.getProvince().getId());
				data.setFromCityId(address.getCity().getId());
				data.setFromCountyId(address.getDistrict().getId());
				data.setFromAddressName(address.getProvince().getName()+" "+address.getCity().getName()+" "+ address.getDistrict().getName());
				txtFrom.setText(data.getFromAddressName());
				break;
			case ID_WHO_SELECT_TO:
				AllzfwlModel.EmptyCarAddressListBean item = ((AllzfwlModel.EmptyCarAddressListBean)adapter.getItem(select_address_index));
				item.toaddress = address;
				item.setToProvinceId(address.getProvince().getId());
				item.setToProvinceName(address.getProvince().getName());
				item.setToCityId(address.getCity().getId());
				item.setToCityName(address.getCity().getName());
				item.setToCountyId(address.getDistrict().getId());
				item.setToCountyName(address.getDistrict().getName());
				item.setToAddressName(address.getProvince().getName()+" "+address.getCity().getName()+" "+ address.getDistrict().getName());
				adapter.notifyDataSetChanged();
				break;
		}
	}
	@Override
	public void onAreaClose(){}
	@Override
	public void onAreaReset() {
	}

	@Override
	public void onSelectedCPD(CPDModel model){
		data.setFromProvinceId(model.getFromProvinceId());
		data.setFromCityId(model.getFromCityId());
		data.setFromCountyId(model.getFromCountyId());
		data.setFromAddressName(model.getFromAddressName());
		txtFrom.setText(data.getFromAddressName());

		AllzfwlModel.EmptyCarAddressListBean lastItem = adapter.mList.get(adapter.mList.size()-1);
		if (lastItem.getToProvinceId().length()<=0) {
			AllzfwlModel.EmptyCarAddressListBean addModel = new AllzfwlModel().new EmptyCarAddressListBean();
			adapter.mList.add(addModel);
		}
		lastItem.setToCityId(model.getToCityId());
		lastItem.setToCityName("");
		lastItem.setToProvinceId(model.getToProvinceId());
		lastItem.setToProvinceName("");
		lastItem.setToCountyId(model.getToCountyId());
		lastItem.setToCountyName("");
		lastItem.setToAddressName(model.getToAddressName());
		adapter.notifyDataSetChanged();

		mSelectCPDView.setVisibility(View.GONE);
	}

}
