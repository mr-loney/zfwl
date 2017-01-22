package com.zfwl.mvp.alipay;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.zfwl.aliapi.PayResult;
import com.zfwl.aliapi.util.OrderInfoUtil2_0;
import com.zfwl.common.Const;
import com.zfwl.common.Const.WeChat;
import com.zfwl.common.MyLog;
import com.zfwl.data.api.pay.WxPayApi;
import com.zfwl.data.api.retrofit.ApiModule;
import com.zfwl.mvp.BasePresenter;
import com.zfwl.util.Md5Utils;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import retrofit2.Call;
import retrofit2.callback.CustomCallback;

public class AliPayPresenter extends BasePresenter<AliPayMvpView> {
    private static final String TAG = "AliPayPresenter";
    private WxPayApi mAliPayApi;
    private Activity vThis;

    public AliPayPresenter() {
        mAliPayApi = ApiModule.INSTANCE.provideWxPayApi();
    }

    /**
     * 传入的价格是以分为单位
     */
    public void aliPay(Activity context, String orderNumber, String goodsDesc, double totalFee) {
        vThis = context;
        getMvpView().showGetWxPayInfoLoading_Ali();
        if (TextUtils.isEmpty(Const.AliPay.APPID) || (TextUtils.isEmpty(Const.AliPay.RSA2_PRIVATE) && TextUtils.isEmpty(Const.AliPay.RSA_PRIVATE))) {
            new AlertDialog.Builder(context).setTitle("警告").setMessage("需要配置APPID | RSA_PRIVATE")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialoginterface, int i) {

                        }
                    }).show();
            getMvpView().hideGetWxPayInfoLoading_Ali();
            return;
        }

        /**
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * orderInfo的获取必须来自服务端；
         */
        boolean rsa2 = (Const.AliPay.RSA2_PRIVATE.length() > 0);
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(Const.AliPay.APPID, rsa2,totalFee,orderNumber,goodsDesc);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

        String privateKey = rsa2 ? Const.AliPay.RSA2_PRIVATE : Const.AliPay.RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        final String orderInfo = orderParam + "&" + sign;

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(context);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = 1;
                msg.obj = result;
                mHandler.sendMessage(msg);

            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();

        getMvpView().hideGetWxPayInfoLoading_Ali();
        getMvpView().showCallingPayLoading_Ali();
    }
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        getMvpView().onGetWxPayInfoFailed_Ali("支付成功");
                    } else {
                        getMvpView().onGetWxPayInfoFailed_Ali("支付失败");
                    }
                    break;
                }
                default:
                    break;
            }
        };
    };

}
