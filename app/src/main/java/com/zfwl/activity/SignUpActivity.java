package com.zfwl.activity;

import android.animation.LayoutTransition;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zfwl.R;
import com.zfwl.activity.home.HomeActivity;
import com.zfwl.adapter.CPDAdatper;
import com.zfwl.common.InputFilterHelper;
import com.zfwl.controls.LoadingDialog;
import com.zfwl.data.UserInfoManager;
import com.zfwl.entity.Address;
import com.zfwl.entity.User;
import com.zfwl.mvp.sigup.SignUpPresenter;
import com.zfwl.mvp.sigup.SignUpView;
import com.zfwl.util.AnimUtils;
import com.zfwl.util.FunctionHelper;
import com.zfwl.util.ViewHub;
import com.zfwl.widget.slsectarea.SelectAreaListView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpActivity extends BaseActivity implements View.OnClickListener, SignUpView {

    @BindView(R.id.layout_step1)
    ViewGroup mViewStep1;
    @BindView(R.id.layout_step2)
    ViewGroup mViewStep2;
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
    RelativeLayout mBtnSj;
    @BindView(R.id.btn_im_sj_img)
    ImageView mBtnSj_img;
    @BindView(R.id.btn_im_sj_txt)
    TextView mBtnSj_txt;
    @BindView(R.id.btn_im_cz)
    RelativeLayout mBtnCZ;
    @BindView(R.id.btn_im_cz_img)
    ImageView mBtnCZ_img;
    @BindView(R.id.btn_im_cz_txt)
    TextView mBtnCZ_txt;
    @BindView(R.id.tv_error)
     TextView mTvStep2Error;
    @BindView(R.id.et_name)
     EditText mEtUserName;

    private WaitTimer mWaitTimer = new WaitTimer();
    private static final int REQUEST_OPEN_CAMERA = 1;
    private int selectSF = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        mPresenter = new SignUpPresenter();
        mPresenter.attachView(this);
        initViews();
    }

    @Override
    public void onBackPressed() {
        if (mViewStep1.getVisibility() == View.VISIBLE) {
            super.onBackPressed();
        } else if (mViewStep2.getVisibility() == View.VISIBLE) {
            HomeActivity.launch(mContext);
            finish();
        }
    }

    private void backToStep1() {
        mViewStep1.setVisibility(View.VISIBLE);
        mViewStep2.setVisibility(View.GONE);
        mEtVerifyCode.setText("");
        mTvStep2Error.setVisibility(View.GONE);
        initToolbar();
    }

    private void initViews() {
        mLoadingDialog = new LoadingDialog(this);
        initLayoutTransition(mViewStep1);;
        initLayoutTransition(mViewStep2);
        mViewStep1.setVisibility(View.VISIBLE);
        mBtnGetVerifyCode.setOnClickListener(this);
        mBtnGotoStep2.setOnClickListener(this);

        mBtnSj.setOnClickListener(this);
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
            mBtnGetVerifyCode.setBackgroundColor(Color.parseColor("#AAAAAA"));
            mBtnGetVerifyCode.setText("重新获取(" + (millisUntilFinished / 1000) + ")");
        }

        @Override
        public void onFinish() {
            mBtnGetVerifyCode.setEnabled(true);
            mBtnGetVerifyCode.setBackgroundColor(Color.parseColor("#54cd93"));
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

        if (mViewStep1.getVisibility() == View.VISIBLE) {
            tvTitle.setText("注册");

            backBtn = (Button) findViewById(R.id.titlebar_btnLeft);
            backBtn.setText("");
            backBtn.setVisibility(View.VISIBLE);
            Drawable img = getResources().getDrawable(R.drawable.back);
            img.setBounds(0, 0, img.getMinimumWidth(), img.getMinimumHeight());
            backBtn.setCompoundDrawables(img, null, null, null);
        }
        if (mViewStep2.getVisibility() == View.VISIBLE) {
            tvTitle.setText("");

            backBtn = (Button) findViewById(R.id.titlebar_btnLeft);
            backBtn.setCompoundDrawables(null, null, null, null);
            backBtn.setText("以后再说");
            backBtn.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_im_sj:
                selectSF = 1;
                mBtnSj.setBackgroundResource(R.drawable.signip_checked);
                mBtnSj_img.setImageResource(R.drawable.signip_check_sj1);
                mBtnSj_txt.setTextColor(getResources().getColor(R.color.white));
                mBtnCZ.setBackgroundResource(R.drawable.transparent);
                mBtnCZ_img.setImageResource(R.drawable.signip_check_cz);
                mBtnCZ_txt.setTextColor(Color.parseColor("#77a1dd"));
                break;
            case R.id.btn_im_cz:
                selectSF = 2;
                mBtnSj.setBackgroundResource(R.drawable.transparent);
                mBtnSj_img.setImageResource(R.drawable.signip_check_sj);
                mBtnSj_txt.setTextColor(Color.parseColor("#77a1dd"));
                mBtnCZ.setBackgroundResource(R.drawable.signip_checked);
                mBtnCZ_img.setImageResource(R.drawable.signip_check_cz1);
                mBtnCZ_txt.setTextColor(getResources().getColor(R.color.white));

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
                }
                break;
            case R.id.et_get_verifycode://重新获取验证码
                String phoneNo = mEtPhoneNum.getText().toString();
                if (isEtEmpty(mEtPhoneNum)) {
                    ViewHub.showLongToast(mContext, "请输入手机号码");
                    return;
                } else if (!FunctionHelper.isPhoneNo(phoneNo)) {
                    ViewHub.showLongToast(mContext, "请输入正确的手机号码");
                    return;
                }
                mPresenter.getVerifyCode(mEtPhoneNum.getText().toString());
                break;
            case R.id.btn_confirm: {
                if (validateStep2Input()) {
                    mPresenter.RegisterAddInfo(UserInfoManager.INSTANCE.getUserInfo().getId()+"",
                            UserInfoManager.INSTANCE.getUserInfo().getPhone(),
                            mEtUserName.getText().toString(),
                            selectSF==1?2:1);
                }
                break;
            }
        }

    }

    private void onGotoStep2(View v) {
        if (validateStep1Input()) {
            mViewStep1.setVisibility(View.GONE);
            mViewStep2.setVisibility(View.VISIBLE);
            initToolbar();
        }
    }

    private boolean validateStep1Input() {
        String phoneNo = mEtPhoneNum.getText().toString();
        String psw = mEtPWD.getText().toString();
        String code = mEtVerifyCode.getText().toString();
        mTvError.setVisibility(View.GONE);
        if (isEtEmpty(mEtPhoneNum)) {
            showError("请输入手机号码");
        } else if (!FunctionHelper.isPhoneNo(phoneNo)) {
            setTilError(mEtPhoneNum, "请输入正确的手机号码");
        } else if (isEtEmpty(mEtVerifyCode)) {
            showError("请输入验证码");
        } else if (code.length() < 4) {
            setTilError(mEtUserName, "验证码格式不正确");
        } else if (isEtEmpty(mEtPWD)) {
            setStep2Error("请输入密码");
        } else if (psw.length() < 6) {
            setTilError(mEtPWD, "密码长度必须大于6");
        } else {
            mTvError.setVisibility(View.GONE);
            return true;
        }
        return false;
    }

    private boolean validateStep2Input() {
        mTvStep2Error.setVisibility(View.GONE);
        if (isEtEmpty(mEtUserName)) {
            setStep2Error("请输入用户名");
        } else {
            mTvStep2Error.setVisibility(View.GONE);
            return true;
        }
        return false;
    }

    private void setStep2Error(String error) {
//        mTvStep2Error.setVisibility(View.VISIBLE);
//        mTvStep2Error.setText(error);
        ViewHub.showLongToast(mContext, error);
    }

    private void setTilError(EditText et, String error) {
//        ViewHub.setEditError(et, error);
//        et.setError(error);
//        AnimUtils.shake(et);
        ViewHub.showLongToast(mContext, error);
    }

    private void showError(String error) {
//        mTvError.setVisibility(View.VISIBLE);
//        mTvError.setText(error);
        ViewHub.showLongToast(mContext, error);
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
//        ViewHub.showOkDialog(mContext, "提示", getString(R.string.forgotpwd_getSmsKey_success), "OK");
    }

    @Override
    public void onGetVerifyCodeFailed(String msg) {
        showError(msg);
    }

    @Override
    public void onRegisterSuccess(User usr) {
        ViewHub.showLongToast(mContext, "注册成功");
        onGotoStep2(null);
    }

    @Override
    public void onRegisterFailed(String msg) {
        showError(msg);
    }


    @Override
    public void onRegisterAddInfoSuccess(User usr) {
        MyCPDActivity.launch(mContext);
    }

    @Override
    public void onRegisterAddInfoFailed(String msg) {
            setStep2Error(msg);
    }

    //    public void onAddressSelected(boolean isFrom, int index) {

}
