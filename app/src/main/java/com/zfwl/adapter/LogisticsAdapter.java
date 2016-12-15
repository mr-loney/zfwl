package com.zfwl.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.zfwl.adapter.LogisticsAdapter.VH;
import com.zfwl.entity.LogisticsInfo;

/**
 * Created by ZZB on 2016/12/15.
 */
public class LogisticsAdapter extends BaseRvAdapter<LogisticsInfo, VH>{

    @Override
    public int getAdapterItemCount() {
        return 0;
    }
    @Override
    public VH onCreateViewHolder(ViewGroup parent) {
        return null;
    }



    @Override
    public void onBindViewHolder(VH holder, int position) {

    }

    public static class VH extends RecyclerView.ViewHolder{

        public VH(View itemView) {
            super(itemView);
        }
    }
}
