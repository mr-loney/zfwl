package com.zfwl.adapter;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;

import com.marshalchen.ultimaterecyclerview.UltimateViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZZB on 2016/12/15.
 */
public abstract class BaseRvAdapter<T, VH extends ViewHolder> extends UltimateViewAdapter<VH> {

    private List<T> mList = new ArrayList<>();

    public void setItems(List<T> items) {
        mList.clear();
        if (items != null) {
            mList.addAll(items);
        }
        notifyDataSetChanged();
    }
    public void addItems(List<T> items){
        if(items != null){
            mList.addAll(items);
            notifyDataSetChanged();
        }
    }
    public void addItem(T item) {
        mList.add(item);
        notifyDataSetChanged();
    }

    public List<T> getItems() {
        return new ArrayList<>(mList);
    }
    public T getItem(int position){
        return mList.get(position);
    }
    public void clear() {
        mList.clear();
        notifyDataSetChanged();
    }

    @Override
    public VH newFooterHolder(View view) {
        return null;
    }

    @Override
    public VH newHeaderHolder(View view) {
        return null;
    }

    @Override
    public ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        return null;
    }

    @Override
    public void onBindHeaderViewHolder(ViewHolder holder, int position) {

    }


    @Override
    public long generateHeaderId(int position) {
        return 0;
    }
}
