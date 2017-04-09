package com.zfwl.data.api;

import com.zfwl.entity.DriverQuotedModel;
import com.zfwl.entity.LogisticsInfo;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by ZZB on 2016/12/27.
 */
public interface LogisticsApi {

    /*
    * fromProvince             String        （非必填）            出发地-省ID
fromCity                     String        （非必填）            出发地-市ID
fromCounty               String        （非必填）            出发地-区县ID
toProvince                 String        （非必填）            目的地-省ID
toCity                          String        （非必填）            目的地-市ID
toCounty                    String        （非必填）            目的地-区县ID
sendDate                  String        （暂未定义）        配送日期     (2016-12-23)
pageNo                     Integer      （非必填）            当前页码 ，默认为1
pageSize
*/
    @GET("app/logistics/getLogistics.do")
    Observable<LogisticsInfo> getLogistics(@Query("fromProvince") String fromProvince,
                                           @Query("fromCity") String fromCity,
                                           @Query("fromCounty") String fromCounty,
                                           @Query("toProvince") String toProvince,
                                           @Query("toCity") String toCity,
                                           @Query("toCounty") String toCounty,
                                           @Query("sendDate") String sendDate,
                                           @Query("pageNo") int pageNo,
                                           @Query("pageSize") int pageSize);


    @FormUrlEncoded
    @POST("app/member/savePrice.do ")
    Observable<DriverQuotedModel> add(@Field("logisticsId") String logisticsId,
                                      @Field("carNumber") int carNumber,
                                      @Field("loadNumber") double loadNumber,
                                      @Field("priceType") int priceType,
                                      @Field("price") double price);

    @GET("app/logistics/getLogisticsDtail.do")
    Observable<LogisticsInfo.ListBean> getLogisticsDetail(@Query("logisticsId") long logisticsId);
}
