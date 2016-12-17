package com.zfwl.controls.wheel.widget.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.zfwl.util.FP;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZZB on 2016/12/17.
 */
public abstract class ListWheelAdapter<T> extends AbstractWheelTextAdapter {
    private List<T> mItems = new ArrayList<>();

    protected ListWheelAdapter(Context context) {
        super(context);
    }

    public ListWheelAdapter(Context context, int itemResource, List<T> items) {
        super(context, itemResource);
        mItems = items;
    }

    public void setItems(List<T> items){
        mItems.clear();
        mItems.addAll(items);
    }
    @Override
    public int getItemsCount() {
        return FP.size(mItems);
    }

    @Override
    protected CharSequence getItemText(int index) {
        return itemToText(mItems.get(index));
    }
    public abstract String itemToText(T item);

    @Override
    public View getItem(int index, View convertView, ViewGroup parent) {
        return super.getItem(index, convertView, parent);
    }
}
