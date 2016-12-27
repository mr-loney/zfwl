package com.zfwl.data.api;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by ZZB on 2016/12/27.
 */
public interface LogisticsApi {

    @GET("/app/logistics/getLogistics.do")
    Call<List> getLogistics(@QueryMap Map<String, String> params);
}
