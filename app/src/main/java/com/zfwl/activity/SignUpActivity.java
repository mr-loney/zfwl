package com.zfwl.activity;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.zfwl.util.ViewHub;
import com.zfwl.util.FunctionHelper;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.zfwl.util.XmlParserHandler;
import com.zfwl.adapter.UserRegAddressAdatper;
import com.zfwl.controls.wheel.widget.OnWheelChangedListener;
import com.zfwl.controls.wheel.widget.WheelView;
import com.zfwl.controls.wheel.widget.adapters.ArrayWheelAdapter;
import com.zfwl.model.CityModel;
import com.zfwl.model.DistrictModel;
import com.zfwl.model.ProvinceModel;
import com.zfwl.model.UserRegAddressModel;

import com.zfwl.util.AnimUtils;
import com.zfwl.common.InputFilterHelper;
import com.zfwl.R;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import com.zfwl.controls.LoadingDialog;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import com.zfwl.mvp.signup.SignUpView;
import com.zfwl.mvp.signup.SignUpPresenter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends BaseActivity implements View.OnClickListener, SignUpView, OnWheelChangedListener {

    @BindView(R.id.layout_step1)
    ViewGroup mViewStep1;
    @BindView(R.id.layout_step2)
    ViewGroup mViewStep2;
    @BindView(R.id.layout_step3)
    ViewGroup mViewStep3;
    private SignUpPresenter mPresenter;

    private SignUpActivity mContext = this;
    private LoadingDialog mLoadingDialog;

    //step 1
    @BindView(R.id.et_phone_num)
     EditText mEtPhoneNum;
    @BindView(R.id.et_verifycode)
     EditText mEtVerifyCode;
    @BindView(R.id.et_password)
     EditText mEtPWD;
    @BindView(R.id.btn_see_pwd)
     ImageView mImSeePWD;
    @BindView(R.id.btn_next)
     Button mBtnGotoStep2;
    @BindView(R.id.et_get_verifycode)
     Button mBtnGetVerifyCode;
    @BindView(R.id.tv_error1)
     TextView mTvError;

    //step 2
    @BindView(R.id.btn_confirm)
     Button mBtnOK;
    @BindView(R.id.btn_im_sj)
     Button mBtnSj;
    @BindView(R.id.btn_im_cz)
     Button mBtnCZ;
    @BindView(R.id.tv_error)
     TextView mTvStep2Error;
    @BindView(R.id.et_name)
     EditText mEtUserName;

    //step 3
    @BindView(R.id.btn_add_new)
     Button btnAddNew;
    @BindView(R.id.listview_step3)
     ListView mListAddress;
    @BindView(R.id.id_select_address)
     View id_select_address;
    @BindView(R.id.id_province)
     com.zfwl.controls.wheel.widget.WheelView mViewProvince;
    @BindView(R.id.id_city)
     com.zfwl.controls.wheel.widget.WheelView mViewCity;
    @BindView(R.id.id_district)
     com.zfwl.controls.wheel.widget.WheelView mViewDistrict;

    private UserRegAddressAdatper adapter;

    private WaitTimer mWaitTimer = new WaitTimer();
    private static final int REQUEST_OPEN_CAMERA = 1;
    private int selectSF = 1;
    private int select_address_index;
    private boolean isFrom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mPresenter = new SignUpPresenter();
        mPresenter.attachView(this);
        initViews();
    }

    @Override
    public void onBackPressed() {
        if (mViewStep1.getVisibility() == View.VISIBLE) {
            super.onBackPressed();
        } else if (mViewStep2.getVisibility() == View.VISIBLE) {
            //以后再说
//            Intent intent = new Intent(mContext, MainActivity.class);
//            startActivity(intent);
//            finish();
            backToStep1();
        } else if (mViewStep3.getVisibility() == View.VISIBLE) {
            backToStep2();
        }

    }

    private void backToStep1() {
        mViewStep1.setVisibility(View.VISIBLE);
        mViewStep2.setVisibility(View.GONE);
        mViewStep3.setVisibility(View.GONE);
        mEtVerifyCode.setText("");
        mTvStep2Error.setVisibility(View.GONE);
        initToolbar();
    }

    private void backToStep2() {
        mViewStep1.setVisibility(View.GONE);
        mViewStep2.setVisibility(View.VISIBLE);
        mViewStep3.setVisibility(View.GONE);
        mEtVerifyCode.setText("");
        mTvStep2Error.setVisibility(View.GONE);
        initToolbar();
    }

    private void initViews() {
        mLoadingDialog = new LoadingDialog(this);
        initLayoutTransition(mViewStep1);;
        initLayoutTransition(mViewStep2);
        initLayoutTransition(mViewStep3);
        mViewStep1.setVisibility(View.VISIBLE);
        mBtnGetVerifyCode.setOnClickListener(this);
        mBtnGotoStep2.setOnClickListener(this);

        id_select_address.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                id_select_address.setVisibility(View.GONE);

                if (select_address_index >= 0) {
                    if (isFrom) {
                        adapter.mList.get(select_address_index).setFromProvince(mCurrentProviceName);
                        adapter.mList.get(select_address_index).setFromCity(mCurrentCityName);
                        adapter.mList.get(select_address_index).setFromDistrict(mCurrentDistrictName);
                    } else {
                        adapter.mList.get(select_address_index).setToProvince(mCurrentProviceName);
                        adapter.mList.get(select_address_index).setToCity(mCurrentCityName);
                        adapter.mList.get(select_address_index).setToDistrict(mCurrentDistrictName);
                    }
                    adapter.notifyDataSetChanged();
                }
                return false;
            }
        });
        mViewProvince.addChangingListener(this);
        mViewCity.addChangingListener(this);
        mViewDistrict.addChangingListener(this);

        btnAddNew.setOnClickListener(this);
        mBtnSj.setOnClickListener(this);
        mBtnSj.setBackgroundResource(R.drawable.bg_rect_white_stroke_gray_corner);
        mBtnCZ.setOnClickListener(this);
        mBtnOK.setOnClickListener(this);
        mImSeePWD.setOnClickListener(this);

        mEtPWD.setFilters(new InputFilter[]{
                InputFilterHelper.maxLengthFilter(12),
                InputFilterHelper.noWhiteSpaceFilter()});
        mEtUserName.setFilters(new InputFilter[]{
                InputFilterHelper.maxLengthFilter(25),
                InputFilterHelper.noWhiteSpaceFilter()});
        mEtPhoneNum.setFilters(new InputFilter[]{InputFilterHelper.maxLengthFilter(11)});
        mEtVerifyCode.setFilters(new InputFilter[]{InputFilterHelper.maxLengthFilter(6)});

        mEtUserName.addTextChangedListener(watcher);
        mEtPWD.addTextChangedListener(watcher);
        mEtPhoneNum.addTextChangedListener(watcher);
        mEtVerifyCode.addTextChangedListener(watcher);

        initToolbar();
    }

    private TextWatcher watcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            mTvError.setVisibility(View.GONE);
            mTvStep2Error.setVisibility(View.GONE);
        }
    };

    /**
     * 刷新倒计时的线程
     */
    private class WaitTimer extends CountDownTimer {
        public WaitTimer() {
            super(TimeUnit.MINUTES.toMillis(1), TimeUnit.SECONDS.toMillis(1));
        }

        @Override
        public void onTick(long millisUntilFinished) {
            mBtnGetVerifyCode.setEnabled(false);
            mBtnGetVerifyCode.setTextColor(Color.WHITE);
            mBtnGetVerifyCode.setText("重新获取(" + (millisUntilFinished / 1000) + ")");
        }

        @Override
        public void onFinish() {
            mBtnGetVerifyCode.setEnabled(true);
            mBtnGetVerifyCode.setText("重新获取");
        }
    }

    private void initLayoutTransition(ViewGroup viewGroup) {
        int startDelay = getResources().getInteger(android.R.integer.config_shortAnimTime);
        LayoutTransition transition = new LayoutTransition();
//        transition.enableTransitionType(LayoutTransition.CHANGING);
        transition.setStartDelay(LayoutTransition.APPEARING, startDelay);
        transition.setStartDelay(LayoutTransition.CHANGE_APPEARING, startDelay);
        viewGroup.setLayoutTransition(transition);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    private void initToolbar() {
        FunctionHelper.hideSoftInput(mContext);

        TextView tvTitle = (TextView) findViewById(R.id.tv_title);

        Button backBtn = (Button) findViewById(R.id.titlebar_btnLeft);
        backBtn.setOnClickListener(this);

        Button sigupBtn = (Button) findViewById(R.id.titlebar_btnRight);
        sigupBtn.setOnClickListener(this);
        sigupBtn.setText("完成");

        if (mViewStep1.getVisibility() == View.VISIBLE) {
            tvTitle.setText("注册");

            backBtn = (Button) findViewById(R.id.titlebar_btnLeft);
            backBtn.setText("");
            backBtn.setVisibility(View.VISIBLE);
            Drawable img = getResources().getDrawable(R.drawable.back);
            img.setBounds(0, 0, img.getMinimumWidth(), img.getMinimumHeight());
            backBtn.setCompoundDrawables(img, null, null, null);

            sigupBtn.setVisibility(View.GONE);
        }
        if (mViewStep2.getVisibility() == View.VISIBLE) {
            tvTitle.setText("完善信息");

            backBtn = (Button) findViewById(R.id.titlebar_btnLeft);
            backBtn.setCompoundDrawables(null, null, null, null);
            backBtn.setText("以后再说");
            backBtn.setVisibility(View.VISIBLE);

            sigupBtn.setVisibility(View.GONE);
        }
        if (mViewStep3.getVisibility() == View.VISIBLE) {
            tvTitle.setText("管理常跑地");

            backBtn = (Button) findViewById(R.id.titlebar_btnLeft);
            backBtn.setText("");
            backBtn.setVisibility(View.VISIBLE);
            Drawable img = getResources().getDrawable(R.drawable.back);
            img.setBounds(0, 0, img.getMinimumWidth(), img.getMinimumHeight());
            backBtn.setCompoundDrawables(img, null, null, null);

            sigupBtn.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_new:
                UserRegAddressModel m = new UserRegAddressModel();
                adapter.mList.add(m);
                adapter.notifyDataSetChanged();
                break;
            case R.id.btn_im_sj:
                selectSF = 1;
                mBtnSj.setBackgroundResource(R.drawable.bg_rect_white_stroke_blue_corner);
                mBtnCZ.setBackgroundResource(R.drawable.bg_rect_white_stroke_gray_corner);
                break;
            case R.id.btn_im_cz:
                selectSF = 2;
                mBtnSj.setBackgroundResource(R.drawable.bg_rect_white_stroke_gray_corner);
                mBtnCZ.setBackgroundResource(R.drawable.bg_rect_white_stroke_blue_corner);
                break;
            case R.id.titlebar_btnLeft:
                onBackPressed();
                break;
            case R.id.btn_see_pwd:
                int length = mEtPWD.getText().length();
                if (length > 0) {
                    if (mEtPWD.getInputType() == InputType.TYPE_TEXT_VARIATION_PASSWORD) {
                        mEtPWD.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        mEtPWD.invalidate();
                        mEtPWD.setSelection(length);
                        mImSeePWD.setImageResource(R.drawable.see_pwd);
                    } else {
                        mEtPWD.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        mEtPWD.invalidate();
                        mEtPWD.setSelection(mEtPWD.getText().length());
                        mImSeePWD.setImageResource(R.drawable.see_pwd1);
                    }
                }
                break;
            case R.id.btn_next://提交验证码
                if (validateStep1Input()) {
                    mPresenter.Register(mEtPhoneNum.getText().toString(),
                            mEtVerifyCode.getText().toString(),
                            mEtPWD.getText().toString());
//                    onGotoStep2(null);
                }
                break;
            case R.id.et_get_verifycode://重新获取验证码
                String phoneNo = mEtPhoneNum.getText().toString();
                if (isEtEmpty(mEtPhoneNum)) {
                    ViewHub.showLongToast(this, "请输入手机号码");
                    return;
                } else if (!FunctionHelper.isPhoneNo(phoneNo)) {
                    ViewHub.showLongToast(this, "请输入正确的手机号码");
                    return;
                }
                mPresenter.getVerifyCode(mEtPhoneNum.getText().toString());
                break;
            case R.id.btn_confirm: {
                if (validateStep2Input()) {

//                    mPresenter.RegisterAddInfo(SpManager.getUserId(mContext),
//                            SpManager.getUserPhone(mContext),
//                            mEtUserName.getText().toString(),
//                            selectSF==1?2:1);
//                    onGotoStep3(null);
                }
                break;
            }
            case R.id.titlebar_btnRight: {
                if (validateStep3Input()) {
                    mPresenter.Register(
                            mEtPhoneNum.getText().toString(),
                            mEtVerifyCode.getText().toString(),
                            mEtPWD.getText().toString());
                }
            }
        }

    }

    private void onGotoStep2(View v) {
        if (validateStep1Input()) {
            mViewStep1.setVisibility(View.GONE);
            mViewStep2.setVisibility(View.VISIBLE);
            mViewStep3.setVisibility(View.GONE);
            initToolbar();
        }
    }

    private void onGotoStep3(View v) {
        if (validateStep2Input()) {
            mViewStep1.setVisibility(View.GONE);
            mViewStep2.setVisibility(View.GONE);
            mViewStep3.setVisibility(View.VISIBLE);
            initToolbar();
            if (mProvinceDatas == null || mProvinceDatas.length == 0) {
                initProvinceDatas();
                mViewProvince.setViewAdapter(new ArrayWheelAdapter<String>(this, mProvinceDatas));
                mViewProvince.setVisibleItems(7);
                mViewCity.setVisibleItems(7);
                mViewDistrict.setVisibleItems(7);
                updateCities();
                updateAreas();
            }
            if (adapter == null) {
                ArrayList arr = new ArrayList();
                UserRegAddressModel m = new UserRegAddressModel();
                arr.add(m);
                adapter = new UserRegAddressAdatper(this, arr);
                mListAddress.setAdapter(adapter);
                adapter.setListener(new UserRegAddressAdatper.OnUserRegAddressAdapterListener() {
                    @Override
                    public void selectToAddress(int index) {
                        select_address_index = index;
                        isFrom = false;
                        beginSelectAddress();
                    }

                    @Override
                    public void selectFromAddress(int index) {
                        select_address_index = index;
                        isFrom = true;
                        beginSelectAddress();
                    }
                });
//                adapter.notifyDataSetChanged();
            }
        }
    }

    private boolean validateStep1Input() {
        return true;
//        String phoneNo = mEtPhoneNum.getText().toString();
//        String psw = mEtPWD.getText().toString();
//        String code = mEtVerifyCode.getText().toString();
//        mTvError.setVisibility(View.GONE);
//        if (isEtEmpty(mEtPhoneNum)) {
//            showError("请输入手机号码");
//        } else if (!FunctionHelper.isPhoneNo(phoneNo)) {
//            setTilError(mEtPhoneNum, "请输入正确的手机号码");
//        } else if (isEtEmpty(mEtVerifyCode)) {
//            showError("请输入验证码");
//        } else if (code.length() != 6) {
//            setTilError(mEtUserName, "验证码格式不正确");
//        } else if (isEtEmpty(mEtPWD)) {
//            setStep2Error("请输入密码");
//        } else if (psw.length() < 6) {
//            setTilError(mEtPWD, "密码长度必须大于6");
//        } else {
//            mTvError.setVisibility(View.GONE);
//            return true;
//        }
//        return false;
    }

    private boolean validateStep2Input() {
        return true;
//        String phoneNo = mEtPhoneNum.getText().toString();
//        String userName = mEtUserName.getText().toString();
//        mTvStep2Error.setVisibility(View.GONE);
//        if (isEtEmpty(mEtPhoneNum)) {
//            setStep2Error("请输入手机号码");
//        } else if (!FunctionHelper.isPhoneNo(phoneNo)) {
//            setTilError(mEtPhoneNum, "请输入正确的手机号码");
//        } else if (isEtEmpty(mEtUserName)) {
//            setStep2Error("请输入用户名");
//        } else {
//            mTvStep2Error.setVisibility(View.GONE);
//            return true;
//        }
//        return false;
    }

    private boolean validateStep3Input() {
        String firstFrom = adapter.mList.get(0).getFromDistrict();
        String firstTo = adapter.mList.get(0).getToDistrict();
        if (firstFrom.length() == 0 || firstTo.length() == 0) {
            ViewHub.showLongToast(this, "请选择常用地点");
            return false;
        }
        return true;
    }

    private void setStep2Error(String error) {
//        mTvStep2Error.setVisibility(View.VISIBLE);
//        mTvStep2Error.setText(error);
        ViewHub.showLongToast(this, error);
    }

    private void setTilError(EditText et, String error) {
//        ViewHub.setEditError(et, error);
//        et.setError(error);
//        AnimUtils.shake(et);
        ViewHub.showLongToast(this, error);
    }

    private void showError(String error) {
//        mTvError.setVisibility(View.VISIBLE);
//        mTvError.setText(error);
        ViewHub.showLongToast(this, error);
    }

    private boolean isEtEmpty(EditText et) {
        if (TextUtils.isEmpty(et.getText())) {
            AnimUtils.shake(et);
            return true;
        } else {
            return false;
        }
    }


    @Override
    public void showLoading() {
                mLoadingDialog.start("处理中...");
    }

    @Override
    public void hideLoading() {
        mLoadingDialog.stop();
    }

    @Override
    public void onGetVerifyCodeSuccess() {
        mEtVerifyCode.requestFocus();
        mWaitTimer.cancel();
        mWaitTimer.start();
//        ViewHub.showOkDialog(this, "提示", getString(R.string.forgotpwd_getSmsKey_success), "OK");
    }

    @Override
    public void onGetVerifyCodeFailed(String msg) {
        showError(msg);
    }

    @Override
    public void onRegisterSuccess() {
        ViewHub.showLongToast(this, "注册成功");
        onGotoStep2(null);
    }

    @Override
    public void onRegisterFailed(String msg) {
        showError(msg);
    }


    @Override
    public void onRegisterAddInfoSuccess() {
        onGotoStep3(null);
    }

    @Override
    public void onRegisterAddInfoFailed(String msg) {
            setStep2Error(msg);
    }

    //选择地址
    private void beginSelectAddress() {
        id_select_address.setVisibility(View.VISIBLE);
        UserRegAddressModel m = adapter.mList.get(select_address_index);
        String p = "";
        String c = "";
        String d = "";
        if (isFrom) {
            p = m.getFromProvince();
            c = m.getFromCity();
            d = m.getFromDistrict();
        } else {
            p = m.getToProvince();
            c = m.getToCity();
            d = m.getToDistrict();
        }
        int pIndex = 0;
        int cIndex = 0;
        int dIndex = 0;
        if (p.length() > 0) {
            for (int i = 0; i < mProvinceDatas.length; i++) {
                if (mProvinceDatas[i].contains(p)) {
                    pIndex = i;
                }
            }

            String[] cs = mCitisDatasMap.get(p);
            for (int i = 0; i < cs.length; i++) {
                if (cs[i].toString().contains(c)) {
                    cIndex = i;
                }
            }

            String[] ds = mDistrictDatasMap.get(c);
            for (int i = 0; i < ds.length; i++) {
                if (ds[i].toString().contains(d)) {
                    dIndex = i;
                }
            }
        }

        mViewProvince.setCurrentItem(pIndex);
        mViewCity.setCurrentItem(cIndex);
        mViewDistrict.setCurrentItem(dIndex);
    }

    protected String[] mProvinceDatas;
    protected Map<String, String[]> mCitisDatasMap = new HashMap<String, String[]>();
    protected Map<String, String[]> mDistrictDatasMap = new HashMap<String, String[]>();
    protected Map<String, String> mZipcodeDatasMap = new HashMap<String, String>();
    protected String mCurrentProviceName;
    protected String mCurrentCityName;
    protected String mCurrentDistrictName = "";
    protected String mCurrentZipCode = "";

    protected void initProvinceDatas() {
        List<ProvinceModel> provinceList = null;
        AssetManager asset = getAssets();
        try {
            InputStream input = asset.open("province_data.xml");
            // ����һ������xml�Ĺ�������
            SAXParserFactory spf = SAXParserFactory.newInstance();
            // ����xml
            SAXParser parser = spf.newSAXParser();
            XmlParserHandler handler = new XmlParserHandler();
            parser.parse(input, handler);
            input.close();
            // ��ȡ�������������
            provinceList = handler.getDataList();
            //*/ ��ʼ��Ĭ��ѡ�е�ʡ���С���
            if (provinceList != null && !provinceList.isEmpty()) {
                mCurrentProviceName = provinceList.get(0).getName();
                List<CityModel> cityList = provinceList.get(0).getCityList();
                if (cityList != null && !cityList.isEmpty()) {
                    mCurrentCityName = cityList.get(0).getName();
                    List<DistrictModel> districtList = cityList.get(0).getDistrictList();
                    mCurrentDistrictName = districtList.get(0).getName();
                    mCurrentZipCode = districtList.get(0).getZipcode();
                }
            }
            //*/
            mProvinceDatas = new String[provinceList.size()];
            for (int i = 0; i < provinceList.size(); i++) {
                // ��������ʡ�����
                mProvinceDatas[i] = provinceList.get(i).getName();
                List<CityModel> cityList = provinceList.get(i).getCityList();
                String[] cityNames = new String[cityList.size()];
                for (int j = 0; j < cityList.size(); j++) {
                    // ����ʡ����������е����
                    cityNames[j] = cityList.get(j).getName();
                    List<DistrictModel> districtList = cityList.get(j).getDistrictList();
                    String[] distrinctNameArray = new String[districtList.size()];
                    DistrictModel[] distrinctArray = new DistrictModel[districtList.size()];
                    for (int k = 0; k < districtList.size(); k++) {
                        // ����������������/�ص����
                        DistrictModel districtModel = new DistrictModel(districtList.get(k).getName(), districtList.get(k).getZipcode());
                        // ��/�ض��ڵ��ʱ࣬���浽mZipcodeDatasMap
                        mZipcodeDatasMap.put(districtList.get(k).getName(), districtList.get(k).getZipcode());
                        distrinctArray[k] = districtModel;
                        distrinctNameArray[k] = districtModel.getName();
                    }
                    // ��-��/�ص���ݣ����浽mDistrictDatasMap
                    mDistrictDatasMap.put(cityNames[j], distrinctNameArray);
                }
                // ʡ-�е���ݣ����浽mCitisDatasMap
                mCitisDatasMap.put(provinceList.get(i).getName(), cityNames);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {

        }
    }

    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        // TODO Auto-generated method stub
        if (wheel == mViewProvince) {
            updateCities();
        } else if (wheel == mViewCity) {
            updateAreas();
        } else if (wheel == mViewDistrict) {
            mCurrentDistrictName = mDistrictDatasMap.get(mCurrentCityName)[newValue];
            mCurrentZipCode = mZipcodeDatasMap.get(mCurrentDistrictName);
        }
    }

    private void updateAreas() {
        int pCurrent = mViewCity.getCurrentItem();
        mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[pCurrent];
        String[] areas = mDistrictDatasMap.get(mCurrentCityName);

        if (areas == null) {
            areas = new String[]{""};
        }
        mViewDistrict.setViewAdapter(new ArrayWheelAdapter<String>(this, areas));
        mViewDistrict.setCurrentItem(0);
    }

    private void updateCities() {
        int pCurrent = mViewProvince.getCurrentItem();
        mCurrentProviceName = mProvinceDatas[pCurrent];
        String[] cities = mCitisDatasMap.get(mCurrentProviceName);
        if (cities == null) {
            cities = new String[]{""};
        }
        mViewCity.setViewAdapter(new ArrayWheelAdapter<String>(this, cities));
        mViewCity.setCurrentItem(0);
        updateAreas();
    }
}
