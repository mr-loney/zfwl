package com.zfwl.presenter.login;

import com.zfwl.data.api.LoginApi;
import com.zfwl.entity.User;

import retrofit2.http.Field;
import retrofit2.mock.BehaviorDelegate;
import rx.Observable;

/**
 * Created by ZZB on 2016/12/7.
 */
public class MockLoginApi implements LoginApi {
    private final BehaviorDelegate<LoginApi> delegate;

    public MockLoginApi(BehaviorDelegate<LoginApi> delegate) {
        this.delegate = delegate;
    }

    @Override
    public Observable<User> login(@Field("phone") String phone, @Field("password") String password) {
        return delegate.returningResponse("").login(phone, password);
    }
}
