package com.zfwl.presenter.login;

import com.zfwl.data.api.LoginApi;
import com.zfwl.entity.User;

import retrofit2.http.Field;
import retrofit2.http.Query;
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
        User user = new User();
        user.setPhone(phone);
        user.setPassword(password);
        return delegate.returningResponse(user).login(phone, password);
    }

    @Override
    public Observable<String> getWechatAccessToken(@Query("appid") String appId, @Query("secret") String secret, @Query("code") String code, @Query("grant_type") String grantType) {
        return null;
    }

    @Override
    public Observable<String> getWechatUser(@Query("access_token") String accessToken, @Query("openid") String openId) {
        return null;
    }
}
