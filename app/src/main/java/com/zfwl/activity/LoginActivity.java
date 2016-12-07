package com.zfwl.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;

import com.zfwl.R;
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
    @BindView(R.id.et_psw)
    EditText mEtPsw;
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    private LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        mLoginPresenter = new LoginPresenter();
        mLoginPresenter.attachView(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLoginPresenter.detachView();
    }

    @OnClick(R.id.btn_login)
    public void onLoginClick() {
        mLoginPresenter.login(mEtPhone.getText().toString(), mEtPsw.getText().toString());
    }

    @Override
    public void showLoginLoading() {
        //login...
    }

    @Override
    public void onLoginSuccess(User user) {
        //go to other activity
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
