package com.zfwl.mvp.view;

import com.zfwl.mvp.MvpView;
import com.zfwl.mvp.RequestData;
import com.zfwl.mvp.RequestError;

public interface SignUpView extends MvpView{

    public void showLoading(RequestData requestData);
    public void hideLoading(RequestData requestData);

    public void onGetVerifyCodeSuccess();
    public void onGetVerifyCodeFailed(RequestError requestError);
    public void onSignUpUserSuccess();
    public void onSignUpUserFailed(RequestError requestError);

    public void onRegisterSuccess();
    public void onRegisterFailed(RequestError requestError);
    public void onRegisterAddInfoSuccess();
    public void onRegisterAddInfoFailed(RequestError requestError);


}
