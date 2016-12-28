package com.zfwl.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zfwl.R;
import com.zfwl.entity.AllzfwlModel;
import com.zfwl.util.FP;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddLogisticsAdapter extends BaseAdapter {

    public AddLogisticsAdapter vThis = this;
    public Context mContext;
    public List<AllzfwlModel.EmptyCarAddressListBean> mList;

    public AddLogisticsAdapter(Context Context, List<AllzfwlModel.EmptyCarAddressListBean> List){
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
        final AddLogisticsAdapter.ViewHolder holder;
        final AllzfwlModel.EmptyCarAddressListBean model = mList.get(position);
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_add_wl, parent, false);
            holder = new AddLogisticsAdapter.ViewHolder();
            holder.to = (TextView) view.findViewById(R.id.to);
            holder.to.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.selectToAddress(Integer.parseInt(view.getTag().toString()));
                }
            });
            holder.btn = (TextView) view.findViewById(R.id.btn);
            holder.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int p = (int)view.getTag();
                    if (p==0) {
                        AllzfwlModel.EmptyCarAddressListBean addModel = new AllzfwlModel().new EmptyCarAddressListBean();
                        addModel.setToCityId("");
                        addModel.setToCityName("");
                        addModel.setToProvinceId("");
                        addModel.setToProvinceName("");
                        addModel.setToCountyId("");
                        addModel.setToCountyName("");
                        mList.add(addModel);
                        vThis.notifyDataSetChanged();
                    } else {
                        mList.remove(p);
                        vThis.notifyDataSetChanged();
                    }
                }
            });
            view.setTag(holder);
        } else {
            holder = (AddLogisticsAdapter.ViewHolder) view.getTag();
        }

        holder.to.setTag(position);
        holder.btn.setTag(position);
        if (model != null) {
            holder.to.setText(model.getToAddressName());
        }

        return view;
    }

    public class ViewHolder {
        TextView to,btn;
    }

    private AddLogisticsAdapter.OnAdapterListener mListener;
    public static interface OnAdapterListener {
        public void selectToAddress(int index);
    }
    public void setListener(AddLogisticsAdapter.OnAdapterListener listener) {
        mListener = listener;
    }

}
