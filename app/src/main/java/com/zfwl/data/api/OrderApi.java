package com.zfwl.data.api;

import com.zfwl.entity.Order;

import java.util.List;

import retrofit2.Call;

/**
 * Created by ZZB on 2016/12/25.
 */
public interface OrderApi {

    Call<List<Order>> getOrders();
}
