package com.zfwl.data.api;

import com.zfwl.entity.User;
import com.zfwl.entity.WeChatTokenResult;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by ZZB on 2016/12/7.
 */

public interface LoginApi {
    @FormUrlEncoded
    @POST("app/member/login.do")
    Observable<User> login(@Field("phone") String phone, @Field("password") String password);

    @GET("https://api.weixin.qq.com/sns/oauth2/access_token")
    Observable<WeChatTokenResult> getWechatAccessToken(@Query("appid") String appId,
                                                       @Query("secret") String secret,
                                                       @Query("code") String code,
                                                       @Query("grant_type") String grantType);//todo: 直接放url里？authorization_code
}
