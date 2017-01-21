package com.zfwl.data.api.pay;

import com.zfwl.entity.WxPayInfo;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * 微信支付相关
 * Created by ZZB on 2017/1/21.
 */
public interface WxPayApi {

    /**
     * appid              String             （必填）           应用ID
     * mch_id           String             （必填）           商户号
     * seckey           String             （必填）           商户号支付加密key，生成签名使用
     * device_info      String             （必填）           设备号 默认传WEB
     * body               String             （必填）           商品描述
     * total_fee          Integer             （必填）          总金额
     * out_trade_no   String             （必填）            正丰订单号
     * trade_type      String             （必填）            交易类型 默认APP
     */
    @GET("app/pay/pay4Weixin.do")
    Call<WxPayInfo> getPayRequestInfo(@QueryMap Map<String, String> params);
}
