package com.zfwl.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.zfwl.R;
import com.zfwl.adapter.AddLogisticsAdapter;
import com.zfwl.controls.LineTextView;
import com.zfwl.controls.LoadingDialog;
import com.zfwl.entity.Address;
import com.zfwl.entity.AllzfwlModel;
import com.zfwl.entity.CPDModel;
import com.zfwl.mvp.logistics.AddLogisticsMvpView;
import com.zfwl.mvp.logistics.AddLogisticsPresenter;
import com.zfwl.util.DisplayUtil;
import com.zfwl.util.FP;
import com.zfwl.widget.SelectCPDListView;
import com.zfwl.widget.ToastUtils;
import com.zfwl.widget.slsectarea.SelectAreaListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.titlebar.BGATitleBar;

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
	@BindView(R.id.title_bar)
	BGATitleBar mTitleBar;

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

		data.setFromProvinceId("130000");
			data.setFromCityId("130200");
			data.setFromCountyId("130208");
		data.setFromAddressName("河北省唐山市丰润区 ");
		txtFrom.setText(data.getFromAddressName());
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
		TextView rightTv = mTitleBar.getRightCtv();
		rightTv.setTextSize(DisplayUtil.spToPx(6));
		rightTv.setTextColor(0xff666666);
		rightTv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mSelectCPDView.show();
			}
		});

		mTitleBar.getLeftCtv().setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

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
		detailTxt1.setEditing(false);
		detailTxt1.mtv2.setGravity(Gravity.LEFT|Gravity.CENTER_VERTICAL);
		detailTxt1.mtv2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Calendar c= Calendar.getInstance();
				Dialog dateDialog=new DatePickerDialog(vThis, new DatePickerDialog.OnDateSetListener() {
					@Override
					public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
						detailTxt1.met.setText(year+"-"+(month+1)+"-"+dayOfMonth+" ");
						detailTxt1.mtv2.setText(year+"-"+(month+1)+"-"+dayOfMonth+" ");
						Calendar time=Calendar.getInstance();
						Dialog timeDialog=new TimePickerDialog(vThis, new TimePickerDialog.OnTimeSetListener() {
							@Override
							public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
								detailTxt1.met.setText(detailTxt1.met.getText().toString()+" "+hourOfDay+":"+minute);
								detailTxt1.mtv2.setText(detailTxt1.mtv2.getText().toString()+" "+hourOfDay+":"+minute);
							}
						}, time.get(Calendar.HOUR_OF_DAY), time.get(Calendar.MINUTE), true);
						timeDialog.setTitle("请选择时间");
						timeDialog.show();
					}
				}, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
				dateDialog.setTitle("请选择日期");
				dateDialog.show();
			}
		});
		detailTxt2.setEditing(true);
		detailTxt2.met.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
		detailTxt3.setEditing(true);
		detailTxt3.met.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
		detailTxt4.setEditing(true);
		detailTxt4.met.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
	}

	private static final int TIME_PICKER_INTERVAL=5;
	private boolean mIgnoreEvent=false;
	private TimePicker.OnTimeChangedListener mTimePickerListener=new TimePicker.OnTimeChangedListener(){
		public void onTimeChanged(TimePicker timePicker, int hourOfDay, int minute){
			if (mIgnoreEvent)
				return;
			if (minute%TIME_PICKER_INTERVAL!=0){
				int minuteFloor=minute-(minute%TIME_PICKER_INTERVAL);
				minute=minuteFloor + (minute==minuteFloor+1 ? TIME_PICKER_INTERVAL : 0);
				if (minute==60)
					minute=0;
				mIgnoreEvent=true;
				timePicker.setCurrentMinute(minute);
				mIgnoreEvent=false;
			}

		}
	};

	@OnClick(R.id.txt_from)
	public void onFromClick() {
		mSelectAreaView.show(ID_WHO_SELECT_FROM, data.fromaddress);
	}

	@OnClick(R.id.submit)
	public void onSubmitClick() {
		if(FP.empty(detailTxt1.getDetail()) || FP.empty(detailTxt2.getDetail()) || FP.empty(detailTxt3.getDetail()) || FP.empty(detailTxt4.getDetail())){
			ToastUtils.show(this, "请输入完整信息");
			return;
		}
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
				if (address.getCity()!=null) {
					data.setFromCityId(address.getCity().getId());
				}
				if (address.getDistrict()!=null) {
					data.setFromCountyId(address.getDistrict().getId());
				}
				data.setFromAddressName(address.getProvince().getName()+" "+
						(address.getCity()==null?"":address.getCity().getName())+" "+
						(address.getDistrict()==null?"":address.getDistrict().getName()));
				txtFrom.setText(data.getFromAddressName());
				break;
			case ID_WHO_SELECT_TO:
				AllzfwlModel.EmptyCarAddressListBean item = ((AllzfwlModel.EmptyCarAddressListBean)adapter.getItem(select_address_index));
				item.toaddress = address;
				item.setToProvinceId(address.getProvince().getId());
				item.setToProvinceName(address.getProvince().getName());
				if (address.getCity()!=null) {
					item.setToCityId(address.getCity().getId());
					item.setToCityName(address.getCity().getName());
				}
				if (address.getDistrict()!=null) {
					item.setToCountyId(address.getDistrict().getId());
					item.setToCountyName(address.getDistrict().getName());
				}
				item.setToAddressName(address.getProvince().getName()+" "+
						(address.getCity()==null?"":address.getCity().getName())+" "+
						(address.getDistrict()==null?"":address.getDistrict().getName()));
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
