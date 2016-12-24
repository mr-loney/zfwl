package com.zfwl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zfwl.R;
import com.zfwl.entity.MyPublishEmptyCarListModel;

import java.util.List;

public class MyPublishEmptyCarListAdapter extends BaseAdapter {

	public Context mContext;
	public List<MyPublishEmptyCarListModel> mList;

	// 构造函数
	public MyPublishEmptyCarListAdapter(Context Context, List<MyPublishEmptyCarListModel> dataList) {
		mContext = Context;

		mList = dataList;

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

		final ViewHolder holder;
		View view = arg1;
		if (mList.size() > 0) {
			if (view == null) {
				view = LayoutInflater.from(mContext).inflate(
						R.layout.layout_my_publish_empty_car_list_item, arg2, false);
				holder = new ViewHolder();

				holder.txt_from = (TextView) view
						.findViewById(R.id.txt_from);
				holder.txt_to = (TextView) view
						.findViewById(R.id.txt_to);
				holder.txt_detail = (TextView) view
						.findViewById(R.id.txt_detail);
				holder.txt_detail1 = (TextView) view
						.findViewById(R.id.txt_detail1);
				view.setTag(holder);
			} else {
				holder = (ViewHolder) view.getTag();
			}
			holder.position = arg0;
			MyPublishEmptyCarListModel data = mList.get(arg0);
			holder.txt_from.setText(data.getFrom());
			holder.txt_to.setText(data.getTo());
			holder.txt_detail.setText("有辆"+data.getCarCount()+"车 "+data.getLength()+"米 "+data.getWeight()+"吨");
			holder.txt_detail1.setText("预计发车 "+data.getCreateTime());
		}

		return view;
	}

	class ViewHolder {
		int position;
		TextView txt_from,txt_to,txt_detail,txt_detail1;
	}
	
}


