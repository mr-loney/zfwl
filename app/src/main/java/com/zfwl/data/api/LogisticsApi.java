package com.zfwl.data.api;

import com.zfwl.entity.LogisticsInfo;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
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
    @GET("/app/logistics/getLogistics.do")
    Observable<LogisticsInfo> getLogistics(@Field("fromProvince") String fromProvince,
                                           @Field("fromCity") String fromCity,
                                           @Field("fromCounty") String fromCounty,
                                           @Field("toProvince") String toProvince,
                                           @Field("toCity") String toCity,
                                           @Field("toCounty") String toCounty,
                                           @Field("sendDate") String sendDate,
                                           @Field("pageNo") int pageNo,
                                           @Field("pageSize") int pageSize);
}
