package com.zfwl.data.api;

import com.zfwl.entity.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by ZZB on 2016/12/7.
 */

public interface SignUpApi {
    @FormUrlEncoded
    @POST("app/sendMsg.do")
    Call veriftCode(@Field("phone") String phone,
                              @Field("content") String content);

    @FormUrlEncoded
    @POST("app/member/reg.do")
    Call<User> register(@Field("phone") String phone,
                         @Field("password") String password);
    @FormUrlEncoded
    @POST("app/member/complete.do")
    Call<User> registerAddInfo(@Field("userid") String userid,
                              @Field("phone") String phone,
                              @Field("realname") String realname,
                              @Field("memberType") String memberType);
}
