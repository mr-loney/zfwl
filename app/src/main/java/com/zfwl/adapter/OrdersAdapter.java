package com.zfwl.adapter;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;

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
    public int getAdapterItemCount() {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {

    }

    public static class VH extends ViewHolder {

        public VH(View itemView) {
            super(itemView);
        }
    }
}
