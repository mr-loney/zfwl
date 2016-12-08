package com.zfwl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zfwl.R;
import com.zfwl.model.UserRegAddressModel;

import java.util.List;

public class UserRegAddressAdatper extends BaseAdapter {

    public UserRegAddressAdatper vThis = this;
    public Context mContext;
    public List<UserRegAddressModel> mList;

    public UserRegAddressAdatper(Context Context, List<UserRegAddressModel> List){
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
        final UserRegAddressModel model = mList.get(position);
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.layout_user_reg_address, parent, false);
            holder = new ViewHolder();
            holder.txt1 = (TextView) view.findViewById(R.id.user_reg_address_txt);
            holder.del = (TextView) view.findViewById(R.id.user_reg_address_del);
            holder.del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mList.remove((int)view.getTag());
                    vThis.notifyDataSetChanged();
                }
            });
            holder.from = (TextView) view.findViewById(R.id.user_reg_address_from);
            holder.from.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.selectFromAddress(Integer.parseInt(view.getTag().toString()));
                }
            });
            holder.to = (TextView) view.findViewById(R.id.user_reg_address_to);
            holder.to.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.selectToAddress(Integer.parseInt(view.getTag().toString()));
                }
            });
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.from.setTag(position);
        holder.to.setTag(position);
        holder.del.setTag(position);
        holder.del.setVisibility(position == 0 ? View.GONE : View.VISIBLE);
        holder.txt1.setText("常用"+(position+1));
        if (model != null) {
            holder.from.setText(model.getFromProvince()+" "+model.getFromCity()+" "+model.getFromDistrict());
            holder.to.setText(model.getToProvince()+" "+model.getToCity()+" "+model.getToDistrict());
        }

        return view;
    }

    public class ViewHolder {
        TextView txt1,from,to,del;
    }

    private OnUserRegAddressAdapterListener mListener;
    public static interface OnUserRegAddressAdapterListener {
        public void selectToAddress(int index);
        public void selectFromAddress(int index);
    }
    public void setListener(OnUserRegAddressAdapterListener listener) {
        mListener = listener;
    }

}
