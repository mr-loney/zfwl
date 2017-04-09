package com.zfwl.data.api;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by ZZB on 2017/4/9.
 */
public interface SettingsApi {

    @FormUrlEncoded
    @POST("app/sysConfig/updateConfig.do")
    Observable<String> alwaysRunPush(@Field("username") String username,
                                     @Field("value") int value,
                                     @Field("type") int type);
}
