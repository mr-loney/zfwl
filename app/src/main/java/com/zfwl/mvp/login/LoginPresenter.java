package com.zfwl.mvp.login;

import com.zfwl.data.api.LoginApi;
import com.zfwl.data.api.retrofit.ApiModule;
import com.zfwl.entity.User;
import com.zfwl.mvp.BasePresenter;

import rx.functions.Action1;

/**
 * Created by ZZB on 2016/12/7.
 */

public class LoginPresenter extends BasePresenter<LoginMvpView> {
    private LoginApi mLoginApi;

    public LoginPresenter() {
        mLoginApi = ApiModule.INSTANCE.provideLoginApi();
    }

    public LoginPresenter(LoginApi mLoginApi) {
        this.mLoginApi = mLoginApi;
    }

    public void login(String phone, String password) {
        if (!isPhoneAndPswValid(phone, password)) {
            getMvpView().onLoginFailed("invalid phone or psw");
            return;
        }
        mLoginApi.login(phone, password).subscribe(new Action1<User>() {
            @Override
            public void call(User user) {
                getMvpView().onLoginSuccess(user);
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                getMvpView().onLoginFailed(throwable.toString());
            }
        });
    }

    private boolean isPhoneAndPswValid(String phone, String psw) {
        return true;
    }
}
