package com.zfwl.mvp.login;

import com.zfwl.data.api.LoginApi;
import com.zfwl.data.api.retrofit.ApiModule;
import com.zfwl.entity.User;
import com.zfwl.mvp.BasePresenter;
import retrofit2.Retrofit;
import rx.functions.Action1;

/**
 * Created by ZZB on 2016/12/7.
 */

public class LoginPresenter extends BasePresenter<LoginMvpView> {
    private Retrofit mRetrofit;
    private LoginApi mLoginApi;

    public LoginPresenter() {
        mRetrofit = ApiModule.INSTANCE.provideRetrofit();
        mLoginApi = mRetrofit.create(LoginApi.class);
    }

    public void login(String phone, String password) {
        mLoginApi.login(phone, password).subscribe(new Action1<User>() {
            @Override
            public void call(User user) {

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

            }
        });
    }
}
