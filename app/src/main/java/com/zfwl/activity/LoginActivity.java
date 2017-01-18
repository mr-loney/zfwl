package com.zfwl.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.zfwl.R;
import com.zfwl.activity.home.HomeActivity;
import com.zfwl.common.Const.WeChat;
import com.zfwl.common.MyLog;
import com.zfwl.controls.AutoCompleteTextViewEx;
import com.zfwl.controls.LoadingDialog;
import com.zfwl.entity.User;
import com.zfwl.event.WeChatAuthEvent;
import com.zfwl.mvp.login.LoginMvpView;
import com.zfwl.mvp.login.LoginPresenter;
import com.zfwl.widget.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ZZB on 2016/12/7.
 */
public class LoginActivity extends BaseActivity implements LoginMvpView {
    private static final String TAG = "LoginActivity";
    private static final String EXTRA_NEED_AUTO_LOGIN = "EXTRA_NEED_AUTO_LOGIN";
    @BindView(R.id.login_edtPassword)
    EditText mEtPsw;
    @BindView(R.id.login_edtAccount)
    AutoCompleteTextViewEx mEtPhone;
    @BindView(R.id.img_see_pwd)
    ImageView mImSeePWD;
    @BindView(R.id.login_btnLogin)
    Button btnLogin;
    private LoginPresenter mLoginPresenter;
    private LoadingDialog mLoadingDialog;
    private IWXAPI mWxApi;

    public static void launch(Context context) {
        launch(context, true);
    }

    public static void launch(Context context, boolean needAutoLogin) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.putExtra(EXTRA_NEED_AUTO_LOGIN, needAutoLogin);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        mLoginPresenter = new LoginPresenter(this);
        mLoginPresenter.attachView(this);
        mWxApi = WXAPIFactory.createWXAPI(this, WeChat.APP_ID, false);

        initView();

        tryAutoLogin();
    }

    private void tryAutoLogin() {
        boolean needAutoLogin = getIntent().getBooleanExtra(EXTRA_NEED_AUTO_LOGIN, true);
        if (needAutoLogin) {
            mLoginPresenter.autoLogin();
        }
    }

    private void initView() {
        mEtPsw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (mEtPsw.getText().toString().length()>0 &&
                        mEtPhone.getText().toString().length()>0) {
                    btnLogin.setEnabled(true);
                    btnLogin.setBackgroundResource(R.drawable.btn_zfwl_blue_bg1);
                } else {
                    btnLogin.setEnabled(false);
                    btnLogin.setBackgroundResource(R.drawable.btn_zfwl_gray_bg);
                }
            }
        });

//        mEtPhone.setText("18500226297");
//        mEtPsw.setText("111111");
        mLoadingDialog = new LoadingDialog(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLoginPresenter.detachView();
        EventBus.getDefault().unregister(this);
    }

    @OnClick(R.id.close_btn)
    public void onCloseClick() {
        finish();
    }

    @OnClick(R.id.reg_btn)
    public void onTitleRightClick() {
        Intent intent1 = new Intent(this, SignUpActivity.class);
        startActivity(intent1);
    }

    @OnClick(R.id.login_btnLogin)
    public void onLoginClick() {
        showLoginLoading();
//        HomeActivity.launch(this);
        mLoginPresenter.phoneLogin(mEtPhone.getText().toString(), mEtPsw.getText().toString());
    }

    @OnClick(R.id.login_btnForgotPwd)
    public void onForgotPWDClick() {
//        Intent findPwdIntent = new Intent(mContext, ForgotPwdActivity.class);
//        startActivity(findPwdIntent);
    }

    @OnClick(R.id.login_btnWXLogin)
    public void onWxLoginClick() {
        final SendAuth.Req req = new SendAuth.Req();
        //弹出授权页面，可通过openid拿到昵称、性别、所在地。并且，即使在未关注的情况下，只要用户授权，也能获取其信息）
        req.scope = "snsapi_userinfo";
        req.state = "none";
        mWxApi.sendReq(req);

//        mLoginPresenter.wechatLogin("code");
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
        HomeActivity.launch(this);

    }

    @Override
    public void onLoginFailed(String msg) {
        mLoadingDialog.stop();
        ToastUtils.show(this, msg);
    }

    @Override
    public void hideLoginLoading() {
        //hide loading
    }

    @Override
    public void autoLoginFailed() {

        HomeActivity.launch(this);
    }

    @Override
    public void goToBindWx(String openId) {
        SignUpActivity.launch(this, openId);
    }

    @Subscribe
    public void onWeChatAuthSuccess(WeChatAuthEvent event) {
        MyLog.i(TAG, "wechat auth success, code is %s", event.getCode() + ":" + this);
        //event.getCode() then get token
        mLoginPresenter.wechatLogin(event.getCode());
    }


}
