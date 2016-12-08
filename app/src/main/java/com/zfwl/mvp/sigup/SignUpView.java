package com.zfwl.mvp.signup;

import com.zfwl.entity.User;
import com.zfwl.mvp.MvpView;

public interface SignUpView extends MvpView{

    public void showLoading();
    public void hideLoading();

    public void onGetVerifyCodeSuccess();
    public void onGetVerifyCodeFailed(String msg);
    public void onRegisterSuccess(User user);
    public void onRegisterFailed(String msg);
    public void onRegisterAddInfoSuccess(User user);
    public void onRegisterAddInfoFailed(String msg);


}
