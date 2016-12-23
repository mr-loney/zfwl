package com.zfwl.adapter;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zfwl.R;
import com.zfwl.adapter.OrdersAdapter.VH;
import com.zfwl.entity.Order;
import com.zfwl.entity.Order.Type;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ZZB on 2016/12/21.
 */
public class OrdersAdapter extends BaseRvAdapter<Order, VH> {

    private Callback mCallback;

    public interface Callback {
        void onCancelOrderClick(Order order);

        void onConfirmOrderClick(Order order);

        void onContactSalesClick(Order order);

        void onCommentClick(Order order);
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getType();
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case Type.CARRYING:
                return new CarryingVH(inflate(parent, R.layout.item_order_carrying));
            case Type.FINISHED:
                return new FinishedVH(inflate(parent, R.layout.item_order_finished));
            case Type.PAID:
                return new PaidVH(inflate(parent, R.layout.item_order_paid));
            case Type.WAIT_CONFIRM:
                return new WaitConfirmVH(inflate(parent, R.layout.item_order_wait_confirm));
            case Type.WAIT_PAY:
                return new WaitPayVH(inflate(parent, R.layout.item_order_wait_pay));
        }
        return null;
    }

    private View inflate(ViewGroup parent, int layoutId) {
        return LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        Order order = getItem(position);
        holder.tvGoodsName.setText(order.getGoodsName());
        holder.tvFrom.setText(order.getFrom());
        holder.tvTo.setText(order.getTo());
        holder.setOrder(order);
        holder.setCallback(mCallback);
    }

    public static class VH extends ViewHolder {
        @BindView(R.id.tv_goods_name)
        TextView tvGoodsName;
        @BindView(R.id.tv_to)
        TextView tvTo;
        @BindView(R.id.tv_from)
        TextView tvFrom;
        Order mOrder;
        Callback mCallback;

        public VH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setOrder(Order order) {
            mOrder = order;
        }

        public void setCallback(Callback callback) {
            mCallback = callback;
        }

    }

    public static class WaitConfirmVH extends VH {

        public WaitConfirmVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick({R.id.btn_cancel_order, R.id.btn_confirm_order})
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_cancel_order:
                    mCallback.onCancelOrderClick(mOrder);
                    break;
                case R.id.btn_confirm_order:
                    mCallback.onConfirmOrderClick(mOrder);
                    break;
            }
        }
    }

    public static class CarryingVH extends VH {

        public CarryingVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
        @OnClick(R.id.btn_contact_sales)
        public void onContactSalesClick() {
            mCallback.onContactSalesClick(mOrder);
        }

    }

    public static class WaitPayVH extends VH {

        public WaitPayVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
        @OnClick(R.id.btn_contact_sales)
        public void onContactSalesClick() {
            mCallback.onContactSalesClick(mOrder);
        }

    }

    public static class PaidVH extends VH {

        public PaidVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.btn_contact_sales)
        public void onContactSalesClick() {
            mCallback.onContactSalesClick(mOrder);
        }
    }

    public static class FinishedVH extends VH {
        @BindView(R.id.btn_comment)
        View btnComment;

        public FinishedVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick({R.id.btn_comment})
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_comment:
                    mCallback.onCommentClick(mOrder);
                    break;
            }
        }
    }

    public static class SimpleCallback implements Callback {

        @Override
        public void onCancelOrderClick(Order order) {
        }

        @Override
        public void onConfirmOrderClick(Order order) {
        }

        @Override
        public void onContactSalesClick(Order order) {
        }

        @Override
        public void onCommentClick(Order order) {
        }
    }
}
