package com.zfwl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.zfwl.R;
import com.zfwl.entity.AllzfwlModel;

import java.util.List;

public class AllzfwlAdapter extends BaseAdapter {

	public Context mContext;
	public List<AllzfwlModel> mList;

	// 构造函数
	public AllzfwlAdapter(Context Context, List<AllzfwlModel> dataList) {
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
						R.layout.layout_all_zfwl_list_item, arg2, false);
				holder = new ViewHolder();

				holder.txt1 = (TextView) view
						.findViewById(R.id.txt1);
				holder.txt2 = (TextView) view
						.findViewById(R.id.txt2);
				holder.txt3 = (TextView) view
						.findViewById(R.id.txt3);
				holder.txt4 = (TextView) view
						.findViewById(R.id.txt4);
				holder.txtTime = (TextView) view
						.findViewById(R.id.txt_time);
				holder.btn = (Button) view
						.findViewById(R.id.btn);
				holder.btn.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {

					}
				});
				view.setTag(holder);
			} else {
				holder = (ViewHolder) view.getTag();
			}
			holder.position = arg0;
			holder.btn.setTag(arg0);

			AllzfwlModel data = mList.get(arg0);
			holder.txtTime.setText(data.getCreateTime());
		}

		return view;
	}

	class ViewHolder {
		int position;
		TextView txt1,txt2,txt3,txt4,txtTime;
		Button btn;
	}
	
}


