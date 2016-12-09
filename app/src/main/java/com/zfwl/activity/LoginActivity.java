package com.zfwl.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.widget.EditText;
import android.widget.ImageView;

import com.zfwl.R;
import com.zfwl.controls.AutoCompleteTextViewEx;
import com.zfwl.entity.User;
import com.zfwl.mvp.login.LoginMvpView;
import com.zfwl.mvp.login.LoginPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ZZB on 2016/12/7.
 */
public class LoginActivity extends BaseActivity implements LoginMvpView {
    @BindView(R.id.login_edtPassword)
    EditText mEtPsw;
    @BindView(R.id.login_edtAccount)
    AutoCompleteTextViewEx mEtPhone;
    @BindView(R.id.img_see_pwd)
    ImageView mImSeePWD;
    private LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mLoginPresenter = new LoginPresenter();
        mLoginPresenter.attachView(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLoginPresenter.detachView();
    }

    @OnClick(R.id.login_btnLogin)
    public void onLoginClick() {
        mLoginPresenter.login(mEtPhone.getText().toString(), mEtPsw.getText().toString());
    }
    @OnClick(R.id.login_btnForgotPwd)
    public void onForgotPWDClick() {
//        Intent findPwdIntent = new Intent(mContext, ForgotPwdActivity.class);
//        startActivity(findPwdIntent);
    }
    @OnClick(R.id.login_btnWXLogin)
    public void onWxLoginClick() {
    }
    @OnClick(R.id.img_see_pwd)
    public void onSeePWDClick() {
        int length = mEtPsw.getText().length();
        if (length > 0) {
            if (mEtPsw.getInputType() == InputType.TYPE_TEXT_VARIATION_PASSWORD) {
                mEtPsw.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                mEtPsw.invalidate();
                mEtPsw.setSelection(length);
                mImSeePWD.setImageResource(R.drawable.see_pwd);
            } else {
                mEtPsw.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                mEtPsw.invalidate();
                mEtPsw.setSelection(mEtPsw.getText().length());
                mImSeePWD.setImageResource(R.drawable.see_pwd1);
            }
        }
    }

    @Override
    public void showLoginLoading() {
        //login...
    }

    @Override
    public void onLoginSuccess(User user) {
        //go to other activity
        MainActivity.launch(this);
    }

    @Override
    public void onLoginFailed(String msg) {
        //show error
    }

    @Override
    public void hideLoginLoading() {
        //hide loading
    }
}
