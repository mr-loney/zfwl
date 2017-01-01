package com.zfwl.data.api;

import com.zfwl.entity.OrderDetails;
import com.zfwl.entity.OrderListResult;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by ZZB on 2016/12/25.
 */
public interface OrderApi {
    @GET("app/order/getOrders.do")
    Call<OrderListResult> getOrders(@Query("status") int status, @Query("pageNo") int pageNo, @Query("pageSize") int pageSize);

    @GET("app/member/getOrdersDetail.do")
    Call<OrderDetails> getOrderDetails(@Query("id") long orderId);

    @FormUrlEncoded
    @POST("app/order/saveOrdersStatus.do")
    Call<Object> updateOrderStatus(@Field("id") long orderId, @Field("status") int status);

    /**
     * @param orderId
     * @param depotTime    仓库时间                   ---评分最高5分
     * @param depotOutTime 仓库出库时间            ---评分最高5分
     * @param depotService 服务水平                   ---评分最高5分
     * @param serverLove   服务人员态度           ---评分最高5分
     * @param remark       备注建议
     * @return
     */
    @FormUrlEncoded
    @POST("app/order/saveOrderComment.do")
    Call<Object> commentOrder(@Field("id") long orderId, @Field("depotTime") int depotTime, @Field("depotOutTime") int depotOutTime,
                              @Field("depotService") int depotService, @Field("serverLove") int serverLove, @Field("remark") String remark);
}
