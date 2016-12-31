package com.zfwl.data.api;

import com.zfwl.entity.OrderListResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ZZB on 2016/12/25.
 */
public interface OrderApi {
    @GET("/app/order/getOrders.do")
    Call<OrderListResult> getOrders(@Query("memberId") long memberId, @Query("status") int status, @Query("pageNo") int pageNo, @Query("pageSize") int pageSize);


}
