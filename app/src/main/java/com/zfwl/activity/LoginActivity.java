package com.zfwl.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.widget.EditText;
import android.widget.ImageView;

import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.zfwl.R;
import com.zfwl.common.Const;
import com.zfwl.controls.AutoCompleteTextViewEx;
import com.zfwl.controls.LoadingDialog;
import com.zfwl.entity.User;
import com.zfwl.event.WeChatAuthEvent;
import com.zfwl.mvp.login.LoginMvpView;
import com.zfwl.mvp.login.LoginPresenter;

import org.greenrobot.eventbus.Subscribe;

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
    private LoadingDialog mLoadingDialog;
    private IWXAPI mWxApi;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mLoadingDialog = new LoadingDialog(this);
        mLoginPresenter = new LoginPresenter();
        mLoginPresenter.attachView(this);
        mWxApi = WXAPIFactory.createWXAPI(this, Const.WeChatLogin.APP_ID , false);
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
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "none";
        mWxApi.sendReq(req);
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
        mLoadingDialog.start("登录中...");
    }

    @Override
    public void onLoginSuccess(User user) {
        mLoadingDialog.stop();
        MainActivity.launch(this);

    }

    @Override
    public void onLoginFailed(String msg) {
        mLoadingDialog.stop();
    }

    @Override
    public void hideLoginLoading() {
        //hide loading
    }
    @Subscribe
    public void onWeChatAuthSuccess(WeChatAuthEvent event){
        //event.getCode() then get token
    }
}
