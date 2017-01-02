package com.zfwl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zfwl.R;
import com.zfwl.entity.CPDModel;

import java.util.List;

public class QuickCPDAdatper extends BaseAdapter {

    public QuickCPDAdatper vThis = this;
    public Context mContext;
    public List<CPDModel> mList;

    public QuickCPDAdatper(Context Context, List<CPDModel> List){
        mContext=Context;
        mList=List;
    }

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
        final ViewHolder holder;
        final CPDModel model = mList.get(position);
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.layout_quick_cpd_item, parent, false);
            holder = new ViewHolder();
            holder.txt = (TextView) view.findViewById(R.id.txt);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.txt.setText(model.getFromAddressName() + "--"+model.getToAddressName());

        return view;
    }

    public class ViewHolder {
        TextView txt;
    }

}
