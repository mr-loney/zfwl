package com.zfwl.push;

import android.content.Context;

import com.zfwl.activity.myorders.detail.CarryingOrderDetailActivity;
import com.zfwl.activity.myorders.detail.FinishedOrderDetailActivity;
import com.zfwl.activity.myorders.detail.PaidOrderDetailActivity;
import com.zfwl.activity.myorders.detail.WaitCommentDetailActivity;
import com.zfwl.activity.myorders.detail.WaitConfirmOrderDetailActivity;
import com.zfwl.activity.myorders.detail.WaitPayOrderDetailActivity;
import com.zfwl.common.MyLog;
import com.zfwl.entity.Order.Type;
import com.zfwl.event.OrderPushEvent;
import com.zfwl.util.GsonUtils;
import com.zfwl.widget.ToastUtils;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by ZZB on 2017/1/15.
 */
public class OrderPushHandler {
    private static final String TAG = "OrderPushHandler";

    public static void onReceivePush(String extra) {
        MyLog.i(TAG, "onReceivePush, PushMsg: %s", extra);
        int pushType = getPushType(extra);
        switch (pushType) {
            case 1://订单信息推送
                OrderPushContent content = extraToOrder(extra);
                int orderType = content.orderType;
                EventBus.getDefault().post(new OrderPushEvent(orderType));
                break;
            case 2://物流信息车辆已抢完向所有已报价且没有生成订单的司机推送消息
                break;
            case 3://新增物流信息向app推送消息
                break;
        }
    }

    public static void onClickPush(Context context, String extra) {
        MyLog.i(TAG, "onClickPush, PushMsg: %s", extra);
        int pushType = getPushType(extra);
        switch (pushType) {
            case 1://订单信息推送
                onClickOrderPush(context, extra);
                break;
            case 2://物流信息车辆已抢完向所有已报价且没有生成订单的司机推送消息
                break;
            case 3://新增物流信息向app推送消息
                break;
        }
    }
    private static void onClickOrderPush(Context context, String extra) {
        OrderPushContent content = extraToOrder(extra);
        int orderType = content.orderType;
        long orderId = content.orderId;
        switch (orderType) {
            case Type.WAIT_CONFIRM:
                WaitConfirmOrderDetailActivity.launch(context, orderId, true);
                break;
            case Type.PAID:
                PaidOrderDetailActivity.launch(context, orderId, true);
                break;
            case Type.WAIT_PAY:
                WaitPayOrderDetailActivity.launch(context, orderId, true);
                break;
            case Type.CARRYING:
                CarryingOrderDetailActivity.launch(context, orderId, true);
                break;
            case Type.WAIT_COMMENT:
                WaitCommentDetailActivity.launch(context, orderId, true);
                break;
            case Type.COMMENTED:
                FinishedOrderDetailActivity.launch(context, orderId, true);
                break;
            default:
                ToastUtils.show(context, "未知订单：" + orderId);
                break;
        }
    }

    private static int getPushType(String json) {
        PushData data = GsonUtils.jsonToObject(json, PushData.class);
        return data.type;
    }

    private static OrderPushContent extraToOrder(String extra) {
        OrderPushContent content = GsonUtils.jsonToObject(extra, OrderPushContent.class);
        if (content == null) {
            content = new OrderPushContent();
        }
        return content;
    }

    private static class PushData {
        private int type;
    }

    private static class OrderPushContent {
        private long orderId;
        private String orderCode;
        private int orderType = Type.UNKNOWN;
    }
}
