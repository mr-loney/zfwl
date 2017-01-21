package com.zfwl.mvp.wxpay;

import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.zfwl.common.Const.WeChat;
import com.zfwl.common.MyLog;
import com.zfwl.data.api.pay.WxPayApi;
import com.zfwl.data.api.retrofit.ApiModule;
import com.zfwl.entity.WxPayInfo;
import com.zfwl.mvp.BasePresenter;
import com.zfwl.util.MD5Utils;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import retrofit2.Call;
import retrofit2.callback.CustomCallback;

/**
 * Created by ZZB on 2017/1/21.
 */
public class WxPayPresenter extends BasePresenter<WxPayMvpView> {
    private static final String TAG = "WxPayPresenter";
    private WxPayApi mWxPayApi;

    public WxPayPresenter() {
        mWxPayApi = ApiModule.INSTANCE.provideWxPayApi();
    }

    /**
     * 传入的价格是以分为单位
     */
    public void wechatPay(IWXAPI wxApi, String orderNumber, String goodsDesc, double totalFeeInFen) {
        getMvpView().showGetWxPayInfoLoading();
        int moneyInYuan = (int) (totalFeeInFen * 100);
        Map<String, String> params = getRequestMap(WeChat.APP_ID, WeChat.PAY_PARTNER_ID, WeChat.PAY_SECRET
                , "WEB", goodsDesc, moneyInYuan, orderNumber, "APP");
        Call<WxPayInfo> call = mWxPayApi.getPayRequestInfo(params);
        call.enqueue(new CustomCallback<WxPayInfo>() {
            @Override
            public void onSuccess(WxPayInfo wxPayInfo) {
                MyLog.i(TAG, "request pay info，%s", wxPayInfo.toString());
                wxPayInfo.setSign(calculateSign(params));
                getMvpView().hideGetWxPayInfoLoading();
                getMvpView().showCallingPayLoading();
                callWxPay(wxApi, wxPayInfo);
            }

            @Override
            public void onFailure(int code, String msg) {
                MyLog.e(TAG, "request pay info failed, code: %d, msg: %s", code, msg);
                getMvpView().hideGetWxPayInfoLoading();
                getMvpView().onGetWxPayInfoFailed(msg);
            }
        });
    }

    private void callWxPay(IWXAPI wxApi, WxPayInfo wxPayInfo) {
        PayReq request = new PayReq();
        request.appId = wxPayInfo.getAppId();
        request.partnerId = wxPayInfo.getPartnerId();
        request.prepayId = wxPayInfo.getPrepayId();
        request.packageValue = "Sign=WXPay";
        request.nonceStr = wxPayInfo.getNonceStr();
        request.timeStamp = wxPayInfo.getTimeStamp() + "";
        request.sign = wxPayInfo.getSign();
        wxApi.sendReq(request);
    }

    private Map<String, String> getRequestMap(String appId, String mchId, String secKey, String deviceInfo,
                                              String goodsDesc, int totalFee, String outTradeNo, String tradeType) {

        Map<String, String> map = new TreeMap<>();
        map.put("appid", appId);
        map.put("mch_id", mchId);
        map.put("seckey", secKey);
        map.put("device_info", deviceInfo);
        map.put("body", goodsDesc);
        map.put("total_fee", totalFee + "");
        map.put("out_trade_no", outTradeNo);
        map.put("trade_type", tradeType);
        return map;
    }

    private String calculateSign(Map<String, String> params) {
        Iterator<Entry<String, String>> it = params.entrySet().iterator();
        StringBuilder paramsStr = new StringBuilder();
        while (it.hasNext()) {
            Entry<String, String> entry = it.next();
            paramsStr.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        paramsStr.append("key").append("=").append(WeChat.PAY_SECRET);
        String sign = MD5Utils.toMD5(paramsStr.toString()).toUpperCase();
        MyLog.i(TAG, "calculateSign, original params: %s, MD5: %s", paramsStr.toString(), sign);
        return sign;
    }


}
