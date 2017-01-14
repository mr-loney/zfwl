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
import com.zfwl.widget.ToastUtils;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by ZZB on 2017/1/15.
 */
public class OrderPushHandler {
    private static final String TAG = "OrderPushHandler";

    public static void onReceiveOrderPush(long orderId, int orderType) {
        MyLog.i(TAG, "onReceiveOrderPush");
        EventBus.getDefault().post(new OrderPushEvent(orderType));
    }

    public static void onClickOrderPush(Context context, long orderId, int orderType) {
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
}
