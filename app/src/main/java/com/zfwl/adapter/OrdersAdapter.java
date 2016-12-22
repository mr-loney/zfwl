package com.zfwl.adapter;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zfwl.adapter.OrdersAdapter.VH;
import com.zfwl.entity.Order;

/**
 * Created by ZZB on 2016/12/21.
 */
public class OrdersAdapter extends BaseRvAdapter<Order, VH> {

    @Override
    public VH onCreateViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public int getAdapterItemCount() {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getType();
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {

    }

    public static class VH extends ViewHolder {
        TextView tvGoodsName;
        TextView tvTo;
        TextView tvFrom;


        public VH(View itemView) {
            super(itemView);
        }
    }

    public static class WaitConfirmVH extends VH {
        View btnCancelOrder;
        View btnConfirmOrder;

        public WaitConfirmVH(View itemView) {
            super(itemView);
        }
    }

    public static class CarryingVH extends VH {
        View btnContactSales;

        public CarryingVH(View itemView) {
            super(itemView);
        }
    }

    public static class WaitPayVH extends VH {
        View btnContactSales;

        public WaitPayVH(View itemView) {
            super(itemView);
        }
    }

    public static class PaidVH extends VH {
        View btnContactSales;

        public PaidVH(View itemView) {
            super(itemView);
        }
    }

    public static class FinishedVH extends VH {
        View btnComment;

        public FinishedVH(View itemView) {
            super(itemView);
        }
    }
}
