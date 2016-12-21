package com.zfwl.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zfwl.R;
import com.zfwl.adapter.LogisticsAdapter.VH;
import com.zfwl.entity.LogisticsInfo;
import com.zfwl.util.FP;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ZZB on 2016/12/15.
 */
public class AddLogisticsAdapter extends BaseRvAdapter<LogisticsInfo, VH> {

    @Override
    public int getAdapterItemCount() {
        return FP.size(getItems());
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wl, parent, false);
        return new VH(itemView);
    }


    @Override
    public void onBindViewHolder(VH holder, int position) {
        LogisticsInfo item = getItem(position);
        holder.tvFrom.setText(item.getDeparture());
        holder.tvTo.setText(item.getDescription());
        holder.tvPublishTime.setText(item.getPublishDate());
        holder.tvDesc.setText(item.getDescription());
        holder.tvSendTime.setText(item.getDepartDate());
        holder.btnRob.setOnClickListener(view -> {
            // TODO: 2016/12/17
        });
    }

    public static class VH extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_from)
        TextView tvFrom;
        @BindView(R.id.tv_to)
        TextView tvTo;
        @BindView(R.id.tv_send_date)
        TextView tvSendTime;
        @BindView(R.id.tv_desc)
        TextView tvDesc;
        @BindView(R.id.tv_publish_time)
        TextView tvPublishTime;
        @BindView(R.id.btn_rob)
        View btnRob;

        public VH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
