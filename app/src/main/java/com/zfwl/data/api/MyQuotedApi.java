package com.zfwl.data.api;

import com.zfwl.entity.AllzfwlModel;
import com.zfwl.entity.MyQuotedModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface MyQuotedApi {

    @GET("app/member/getPrice.do")
    Observable<MyQuotedModel> getList(@Query("pageNo") int pageNo,
            @Query("pageSize") int pageSize);
}
