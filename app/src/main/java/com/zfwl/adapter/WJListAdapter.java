package com.zfwl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zfwl.R;
import com.zfwl.entity.WJModel;

import java.util.List;

public class WJListAdapter extends BaseAdapter {

	public Context mContext;
	public List<WJModel.ListBean> mList;

	// 构造函数
	public WJListAdapter(Context Context, List<WJModel.ListBean> dataList) {
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
						R.layout.layout_wj_item, arg2, false);
				holder = new ViewHolder();

				holder.txt1 = (TextView) view
						.findViewById(R.id.txt1);
				holder.txt2 = (TextView) view
						.findViewById(R.id.txt2);
				holder.txt3 = (TextView) view
						.findViewById(R.id.txt3);
				view.setTag(holder);
			} else {
				holder = (ViewHolder) view.getTag();
			}
			holder.position = arg0;
			WJModel.ListBean data = mList.get(arg0);
			holder.txt1.setText(data.getTitle());
			holder.txt2.setText("创建时间："+data.getCreateTime());
			holder.txt2.setText("参与人数："+data.getAnswerCount());
		}

		return view;
	}

	class ViewHolder {
		int position;
		TextView txt1,txt2,txt3;
	}
	
}


