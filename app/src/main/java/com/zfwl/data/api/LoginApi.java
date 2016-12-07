package com.zfwl.data.api;

import com.zfwl.entity.User;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by ZZB on 2016/12/7.
 */

public interface LoginApi {
    @POST("app/member/login.do")
    @FormUrlEncoded
    Observable<User> login(@Field("phone") String phone, @Field("password") String password);
}
