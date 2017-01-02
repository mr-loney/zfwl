package com.zfwl.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zfwl.R;
import com.zfwl.entity.MyQuotedModel;

import java.util.List;

public class MyQuotedListAdapter extends BaseAdapter {

	public Context mContext;
	public List<MyQuotedModel.ListBean> mList;

	// 构造函数
	public MyQuotedListAdapter(Context Context, List<MyQuotedModel.ListBean> dataList) {
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
						R.layout.layout_my_quoted_list_item, arg2, false);
				holder = new ViewHolder();

				holder.txt1 = (TextView) view
						.findViewById(R.id.txt1);
				holder.txt2 = (TextView) view
						.findViewById(R.id.txt2);
				view.setTag(holder);
			} else {
				holder = (ViewHolder) view.getTag();
			}
			holder.position = arg0;
			MyQuotedModel.ListBean data = mList.get(arg0);

			String fromStr = "";
			String toStr = "";
			for (MyQuotedModel.ListBean.AddressInfoListBean item : data.getAddressInfoList()) {
				if (item.getFromDetail()!=null && item.getFromDetail().length()>0 && fromStr.indexOf(item.getFromDetail())<0) {
					fromStr+=item.getFromDetail()+"<br/>";
				}
				if (item.getToDetail()!=null && item.getToDetail().length()>0 && fromStr.indexOf(item.getToDetail())<0) {
					toStr+=item.getToDetail()+"<br/>";
				}
			}
			holder.from.setText(Html.fromHtml(fromStr));
			holder.to.setText(Html.fromHtml(toStr));
			holder.txt1.setText(data.getCdate()+" 装");
			holder.txt1.setText("重量 "+data.getLoadNumber()+"吨  要"+data.getCarNumber()+"辆车");
			holder.txt1.setText("总计："+data.getTotal()+"元");
		}

		return view;
	}

	class ViewHolder {
		int position;
		TextView txt1,txt2,txt3,from,to;
	}
	
}


