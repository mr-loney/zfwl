package com.zfwl.data.api;

import com.zfwl.entity.MyQuotedModel;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface MyQuotedApi {

    @GET("app/member/getPrice.do")
    Observable<MyQuotedModel> getList(@Query("pageNo") int pageNo,
            @Query("pageSize") int pageSize);

    @GET("app/member/delPrice.do ")
    Observable<String> del(@Query("id") int id);

    @GET("app/member/getPriceDetail.do")
    Observable<MyQuotedModel.ListBean> getQuotedPriceDetail(@Query("memberId") long memberId, @Query("priceId") long priceId);
}
