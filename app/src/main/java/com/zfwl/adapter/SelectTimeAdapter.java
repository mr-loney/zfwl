package com.zfwl.adapter;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zfwl.R;
import com.zfwl.adapter.SelectTimeAdapter.VH;
import com.zfwl.entity.SelectTimeItem;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ZZB on 2017/1/8.
 */
public class SelectTimeAdapter extends BaseRvAdapter<SelectTimeItem, VH> {
    private Callback mCallback;
    public interface Callback {
        void onTimeSelected(SelectTimeItem item);
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_select_time, parent, false);
        VH vh = new VH(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        SelectTimeItem item = getItem(position);
        holder.init(item);
        holder.itemView.setOnClickListener(view -> mCallback.onTimeSelected(item));
    }

    static class VH extends ViewHolder {
        @BindView(R.id.tv_date)
        TextView tvDate;

        public VH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void init(SelectTimeItem item) {
            if (item.selected) {
                itemView.setBackgroundColor(0xFFcccccc);
                tvDate.setTextColor(0xff1760c8);
            } else {
                itemView.setBackgroundColor(0xFFFFFFFF);
                tvDate.setTextColor(0xff333333);
            }
            tvDate.setText(item.displayDate);

        }
    }
}
