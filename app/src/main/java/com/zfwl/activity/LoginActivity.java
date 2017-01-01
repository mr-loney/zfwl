package com.zfwl.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.zfwl.R;
import com.zfwl.activity.home.HomeActivity;
import com.zfwl.activity.myorders.OrderCommentActivity;
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
    @BindView(R.id.login_edtPassword)
    EditText mEtPsw;
    @BindView(R.id.login_edtAccount)
    AutoCompleteTextViewEx mEtPhone;
    @BindView(R.id.img_see_pwd)
    ImageView mImSeePWD;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.titlebar_btnRight)
    Button sigupBtn;
    private LoginPresenter mLoginPresenter;
    private LoadingDialog mLoadingDialog;
    private IWXAPI mWxApi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        mLoadingDialog = new LoadingDialog(this);
        mLoginPresenter = new LoginPresenter(this);
        mLoginPresenter.attachView(this);
        mWxApi = WXAPIFactory.createWXAPI(this, WeChat.APP_ID, false);

        initView();
    }

    private void initView() {
        tvTitle.setText("登录");
        sigupBtn.setText("注册");
        sigupBtn.setVisibility(View.VISIBLE);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLoginPresenter.detachView();
        EventBus.getDefault().unregister(this);
    }

    @OnClick(R.id.titlebar_btnRight)
    public void onTitleRightClick() {
        Intent intent1 = new Intent(this, SignUpActivity.class);
        startActivity(intent1);
    }

    @OnClick(R.id.login_btnLogin)
    public void onLoginClick() {

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
//        final SendAuth.Req req = new SendAuth.Req();
//        //弹出授权页面，可通过openid拿到昵称、性别、所在地。并且，即使在未关注的情况下，只要用户授权，也能获取其信息）
//        req.scope = "snsapi_userinfo";
//        req.state = "none";
//        mWxApi.sendReq(req);

//        mLoginPresenter.wechatLogin("code");
        OrderCommentActivity.launch(this, 0);
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

    @Subscribe
    public void onWeChatAuthSuccess(WeChatAuthEvent event) {
        MyLog.i(TAG, "wechat auth success, code is %s", event.getCode());
        //event.getCode() then get token
        mLoginPresenter.wechatLogin(event.getCode());
    }
}
