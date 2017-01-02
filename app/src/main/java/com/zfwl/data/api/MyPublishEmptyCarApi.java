package com.zfwl.data.api;

import com.zfwl.entity.MyPublishEmptyCarListModel;
import com.zfwl.entity.MyQuotedModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface MyPublishEmptyCarApi {

    @GET("app/empty/getEmptyCars.do")
    Observable<MyPublishEmptyCarListModel> getList(@Query("pageNo") int pageNo,
                                                   @Query("pageSize") int pageSize);

    @FormUrlEncoded
    @POST("app/empty/delEmptyCars.do ")
    Observable<MyPublishEmptyCarListModel.ListBean> del(@Field("emptyCarId") int emptyCarId);


}
