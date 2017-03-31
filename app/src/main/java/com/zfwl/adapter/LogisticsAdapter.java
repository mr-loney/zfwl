package com.zfwl.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zfwl.R;
import com.zfwl.activity.GoodsDetailActivity;
import com.zfwl.entity.LogisticsInfo;
import com.zfwl.entity.MyPublishEmptyCarListModel;
import com.zfwl.util.Utils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ZZB on 2016/12/15.
 */
public class LogisticsAdapter  extends BaseAdapter {


    public Context mContext;
    public List<LogisticsInfo.ListBean> mList = new ArrayList<>();

    // 构造函数
    public LogisticsAdapter(Context Context) {
        mContext = Context;

    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int arg0) {
        return mList.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int arg0, View arg1, ViewGroup arg2) {

        final LogisticsAdapter.ViewHolder holder;
        View view = arg1;
        if (mList.size() > 0) {
            if (view == null) {
                view = LayoutInflater.from(mContext).inflate(
                        R.layout.item_wl, arg2, false);
                holder = new LogisticsAdapter.ViewHolder();

                holder.tvFrom = (TextView) view
                        .findViewById(R.id.tv_from);
                holder.tvTo = (TextView) view
                        .findViewById(R.id.tv_to);
                holder.tvSendTime = (TextView) view
                        .findViewById(R.id.tv_send_date);
                holder.tvDesc = (TextView) view
                        .findViewById(R.id.tv_desc);
                holder.tvPublishTime = (TextView) view
                        .findViewById(R.id.tv_publish_time);
                holder.btnRob = (TextView) view
                        .findViewById(R.id.btn_rob);
                view.setTag(holder);
            } else {
                holder = (LogisticsAdapter.ViewHolder) view.getTag();
            }
            holder.position = arg0;
            final  LogisticsInfo.ListBean data = mList.get(arg0);
            holder.tvFrom.setText(data.getAddressInfoList().get(0).getFromProvinceName()+""+
                    data.getAddressInfoList().get(0).getFromCityName()+""+
                    data.getAddressInfoList().get(0).getFromCountyName());
            holder.tvTo.setText(data.getAddressInfoList().get(0).getToProvinceName()+""+
                    data.getAddressInfoList().get(0).getToCityName()+""+
                    data.getAddressInfoList().get(0).getToCountyName());
            try {
                holder.tvSendTime.setText(Utils.longToStringFriendly(data.getDepartureTime()));
                holder.tvPublishTime.setText(Utils.longToStringFriendly1(data.getCreateTime()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            holder.tvDesc.setText(data.getGoodsName()+" "+data.getLength()+"米  要"+(data.getCarNum()-data.getHasCarNum())+"辆车");
            holder.btnRob.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GoodsDetailActivity.launch(mContext,data);
                }
            });
        }

        return view;
    }

    class ViewHolder {
        int position;
        TextView tvFrom;
        TextView tvTo;
        TextView tvSendTime;
        TextView tvDesc;
        TextView tvPublishTime;
        View btnRob;
    }

}
