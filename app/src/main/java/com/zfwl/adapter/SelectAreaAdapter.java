package com.zfwl.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zfwl.R;
import com.zfwl.entity.AllzfwlModel;
import com.zfwl.entity.Area;

import java.util.List;

public class SelectAreaAdapter extends BaseAdapter {

    public SelectAreaAdapter vThis = this;
    public Context mContext;
    public List<Area> mList;
    private String selectAreaID;

    public SelectAreaAdapter(Context Context, List<Area> List){
        mContext=Context;
        mList=List;
    }

    public void setDatas(List<Area> List) {mList=List;}

    public void setSelectArea(String areaid) {selectAreaID=areaid;}

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final SelectAreaAdapter.ViewHolder holder;
        final Area model = mList.get(position);
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_select_area, parent, false);
            holder = new SelectAreaAdapter.ViewHolder();
            holder.name = (TextView) view.findViewById(R.id.name);
            holder.name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Area area = mList.get(Integer.parseInt(view.getTag().toString()));
                    mListener.selected(area);
                    selectAreaID = area.getId();
                    vThis.notifyDataSetChanged();
                }
            });
            view.setTag(holder);
        } else {
            holder = (SelectAreaAdapter.ViewHolder) view.getTag();
        }

        holder.name.setTag(position);
        holder.name.setText(model.getName());
        if (selectAreaID.equals(model.getId())) {
            holder.name.setBackgroundColor(Color.parseColor("#eeeeee"));
        } else {
            holder.name.setBackgroundColor(Color.parseColor("#ffffff"));
        }

        return view;
    }

    public class ViewHolder {
        TextView name;
    }

    private SelectAreaAdapter.OnAdapterListener mListener;
    public static interface OnAdapterListener {
        public void selected(Area area);
    }
    public void setListener(SelectAreaAdapter.OnAdapterListener listener) {
        mListener = listener;
    }

}
