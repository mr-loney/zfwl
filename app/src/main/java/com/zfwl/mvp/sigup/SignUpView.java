package com.zfwl.mvp.signup;

import com.zfwl.mvp.MvpView;

public interface SignUpView extends MvpView{

    public void showLoading();
    public void hideLoading();

    public void onGetVerifyCodeSuccess();
    public void onGetVerifyCodeFailed(String msg);
    public void onRegisterSuccess();
    public void onRegisterFailed(String msg);
    public void onRegisterAddInfoSuccess();
    public void onRegisterAddInfoFailed(String msg);


}
