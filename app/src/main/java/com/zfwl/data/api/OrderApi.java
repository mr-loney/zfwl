package com.zfwl.data.api;

import com.zfwl.entity.Order;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ZZB on 2016/12/25.
 */
public interface OrderApi {
    @GET("https://github.com/zzb1")
    Call<List<Order>> getOrders();
}
