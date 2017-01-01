package com.zfwl.data.api;

import com.zfwl.entity.AllzfwlModel;
import com.zfwl.entity.MyQuotedModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import rx.Observable;

public interface MyQuotedApi {

    @FormUrlEncoded
    @GET("/app/member/getPrice.do ")
    Observable<MyQuotedModel> getList(@Field("pageNo") int pageNo,
            @Field("pageSize") int pageSize);
}
