package com.zfwl.activity.home;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zfwl.R;
import com.zfwl.adapter.AllzfwlAdapter;
import com.zfwl.adapter.UserRegAddressAdatper;
import com.zfwl.controls.LoadingDialog;
import com.zfwl.controls.pulltorefresh.PullToRefreshListView;
import com.zfwl.controls.wheel.widget.OnWheelChangedListener;
import com.zfwl.controls.wheel.widget.WheelView;
import com.zfwl.controls.wheel.widget.adapters.ArrayWheelAdapter;
import com.zfwl.entity.AllzfwlModel;
import com.zfwl.entity.CityModel;
import com.zfwl.entity.DistrictModel;
import com.zfwl.entity.ProvinceModel;
import com.zfwl.entity.UserRegAddressModel;
import com.zfwl.util.ViewHub;
import com.zfwl.util.XmlParserHandler;

import org.greenrobot.eventbus.EventBus;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import butterknife.ButterKnife;


public class WLFragment extends Fragment implements View.OnClickListener,
        PullToRefreshListView.OnLoadMoreListener,
        PullToRefreshListView.OnRefreshListener,
        OnWheelChangedListener {
    private static final String TAG = "WLFragment";
    View mContentView;
    private Activity mContext;
    private LoadingDialog mloadingDialog;
    private TextView tvEmptyMessage,tvFrom,tvTo,tvBeginTime;
    private AllzfwlAdapter adapter;
    private List<AllzfwlModel> itemList = null;
    private PullToRefreshListView pullRefreshListView;
    private int mPageIndex = 1;
    private int mPageSize = 20;
    private View emptyView;
    private Button btnTitleLeft,btnTitleRight;
    private EventBus mEventBus;
    private boolean isRefresh;

    private View tv_detail_area;
    private TextView tv_detail_area1,tv_detail_area2,tv_detail_area3,tv_detail_area4;
    private int select_tv_detail_area_index = 1;

    private boolean isFrom;
    private UserRegAddressModel addressData = new UserRegAddressModel();
    private View id_select_address;
    private com.zfwl.controls.wheel.widget.WheelView mViewProvince;
    private com.zfwl.controls.wheel.widget.WheelView mViewCity;
    private com.zfwl.controls.wheel.widget.WheelView mViewDistrict;

    private static enum Step {
        LOAD_ALL_LOGISTICS_DATA
    }

    public WLFragment() {
    }

    public static WLFragment newInstance() {
        WLFragment fragment = new WLFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContentView = inflater.inflate(R.layout.fragment_wl, container, false);
        ButterKnife.bind(this, mContentView);
        mContext = this.getActivity();
        initView();
        mloadingDialog = new LoadingDialog(mContext);
        initData();
//        mEventBus.register(mContext);
        return mContentView;
    }

    @Override
    public void onDestroy() {
//        mEventBus.unregister(mContext);
        super.onDestroy();
    }

//    public void onEventMainThread(BusEvent event) {
//        switch (event.id) {
//            case EventBusId.USERINFO_LOADED:
//                break;
//
//        }
//    }

    private void initView() {
        itemList = new ArrayList<AllzfwlModel>();
        emptyView = mContentView.findViewById(R.id.view_empty);
        tvEmptyMessage = (TextView) emptyView
                .findViewById(R.id.layout_empty_tvMessage);
        mloadingDialog = new LoadingDialog(mContext);
        pullRefreshListView = (PullToRefreshListView) mContentView.findViewById(R.id.pull_refresh_listview_items);
        pullRefreshListView.setCanLoadMore(true);
        pullRefreshListView.setCanRefresh(true);
        pullRefreshListView.setMoveToFirstItemAfterRefresh(true);
        pullRefreshListView.setOnRefreshListener(this);
        pullRefreshListView.setOnLoadListener(this);

        // 刷新数据
        showEmptyView(false, "");
        emptyView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                pullRefreshListView.pull2RefreshManually();

                if (pullRefreshListView != null) {
                    if (pullRefreshListView.isCanRefresh())
                        pullRefreshListView.onRefreshComplete();

                    if (pullRefreshListView.isCanLoadMore())
                        pullRefreshListView.onLoadMoreComplete();
                }

            }
        });

        initItemAdapter();

        tv_detail_area = mContentView.findViewById(R.id.tv_detail_area);
        tv_detail_area1 = (TextView)mContentView.findViewById(R.id.tv_detail_area1);
        tv_detail_area1.setOnClickListener(this);
        tv_detail_area2 = (TextView)mContentView.findViewById(R.id.tv_detail_area2);
        tv_detail_area2.setOnClickListener(this);
        tv_detail_area3 = (TextView)mContentView.findViewById(R.id.tv_detail_area3);
        tv_detail_area3.setOnClickListener(this);
        tv_detail_area4 = (TextView)mContentView.findViewById(R.id.tv_detail_area4);
        tv_detail_area4.setOnClickListener(this);

        tvFrom = (TextView)mContentView.findViewById(R.id.tv_from);
        tvFrom.setOnClickListener(this);
        tvTo = (TextView)mContentView.findViewById(R.id.tv_to);
        tvTo.setOnClickListener(this);
        tvBeginTime = (TextView)mContentView.findViewById(R.id.tv_begin_time);
        tvBeginTime.setOnClickListener(this);

        id_select_address = (View) mContentView.findViewById(R.id.id_select_address);
        id_select_address.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                id_select_address.setVisibility(View.GONE);

                if (isFrom) {
                    addressData.setFromProvince(mCurrentProviceName);
                    addressData.setFromCity(mCurrentCityName);
                    addressData.setFromDistrict(mCurrentDistrictName);
                } else {
                    addressData.setToProvince(mCurrentProviceName);
                    addressData.setToCity(mCurrentCityName);
                    addressData.setToDistrict(mCurrentDistrictName);
                }
                reloadSelectAddress();
                return false;
            }
        });
        mViewProvince = (WheelView) mContentView.findViewById(R.id.id_province);
        mViewCity = (WheelView) mContentView.findViewById(R.id.id_city);
        mViewDistrict = (WheelView) mContentView.findViewById(R.id.id_district);
        mViewProvince.addChangingListener(this);
        mContentView.findViewById(R.id.id_reset_wheelView).setOnClickListener(this);
        mContentView.findViewById(R.id.id_ok_wheelView).setOnClickListener(this);
        mViewCity.addChangingListener(this);
        mViewDistrict.addChangingListener(this);

        initSelectProvinceData();
    }

    private void initSelectProvinceData() {
        if (mProvinceDatas == null || mProvinceDatas.length == 0) {
            initProvinceDatas();
            mViewProvince.setViewAdapter(new ArrayWheelAdapter<String>(mContext, mProvinceDatas));
            mViewProvince.setVisibleItems(7);
            mViewCity.setVisibleItems(7);
            mViewDistrict.setVisibleItems(7);
            updateCities();
            updateAreas();
        }
    }

    /**
     * 显示空数据视图
     * */
    private void showEmptyView(boolean show, String msg) {
        pullRefreshListView.setVisibility(show ? View.GONE : View.VISIBLE);
        emptyView.setVisibility(show ? View.VISIBLE : View.GONE);
        if (TextUtils.isEmpty(msg)) {
            tvEmptyMessage.setText("没有物流数据");
        } else {
            tvEmptyMessage.setText(msg);
        }
    }

    // 初始化数据
    private void initItemAdapter() {
        if (itemList == null)
            itemList = new ArrayList<AllzfwlModel>();

        adapter = new AllzfwlAdapter(mContext, itemList);
        pullRefreshListView.setAdapter(adapter);

    }

    @Override
    public void onRefresh() {
        isRefresh = true;
        new Task(Step.LOAD_ALL_LOGISTICS_DATA).execute((Void) null);
        if (itemList.size() == 0) {
            showEmptyView(false, "还没有物流记录");
        } else {

        }
    }
    @Override
    public void onLoadMore() {
        isRefresh = false;
        new Task(Step.LOAD_ALL_LOGISTICS_DATA).execute((Void) null);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void initData()
    {
        onRefresh();
    }

    private class Task extends AsyncTask<Object, Void, Object> {
        private Step mStep;

        public Task(Step step) {
            mStep = step;
        }

        @Override
        protected void onPreExecute() {
            switch (mStep) {
                case LOAD_ALL_LOGISTICS_DATA:
                    mloadingDialog.start("获取物流数据中...");
                    break;
            }
        }

        @Override
        protected Object doInBackground(Object... params) {
            try {
                switch (mStep) {
                    case LOAD_ALL_LOGISTICS_DATA: {

                        try {
//                            List<AllzfwlModel> result = zfwlAPI.getInstance().getAllzfwlLists(1,1,SpManager.getCookie(mContext));
//
//                            if (isRefresh) {
//                                itemList = result;
//                            } else {
//                                itemList.addAll(result);
//                            }

                            return "OK";
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            return ex.getMessage() == null ? "未知异常" : ex.getMessage();
                        }

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return "error:" + e.getMessage();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object result) {
            if (mloadingDialog.isShowing()) {
                mloadingDialog.stop();
            }
            else {
                switch (mStep) {
                    case LOAD_ALL_LOGISTICS_DATA:
                        if (isRefresh) {
                            pullRefreshListView.onRefreshComplete();
                        } else {
                            pullRefreshListView.onLoadMoreComplete();
                        }

                        adapter.mList = itemList;

                        adapter.notifyDataSetChanged();
                        if(itemList.size()>0) {
                            showEmptyView(false,"");
                        } else {
                            showEmptyView(true,"您还没有交易记录");
                        }

                        if (!result.equals("OK")) {
                            ViewHub.showLongToast(mContext,result.toString());
                        }
                        break;
                }
            }
        }
    }

    private void resetTvDetailArea() {
        tv_detail_area1.setBackgroundResource(0);
        tv_detail_area2.setBackgroundResource(0);
        tv_detail_area3.setBackgroundResource(0);
        tv_detail_area4.setBackgroundResource(0);

        switch (select_tv_detail_area_index) {
            case 1:
                tv_detail_area1.setBackgroundResource(R.drawable.bg_rect_blue_corner);
                break;
            case 2:
                tv_detail_area2.setBackgroundResource(R.drawable.bg_rect_blue_corner);
                break;
            case 3:
                tv_detail_area3.setBackgroundResource(R.drawable.bg_rect_blue_corner);
                break;
            case 4:
                tv_detail_area4.setBackgroundResource(R.drawable.bg_rect_blue_corner);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_detail_area1: {
                select_tv_detail_area_index = 1;
                resetTvDetailArea();
                break;
            }
            case R.id.tv_detail_area2: {
                select_tv_detail_area_index = 2;
                resetTvDetailArea();
                break;
            }
            case R.id.tv_detail_area3: {
                select_tv_detail_area_index = 3;
                resetTvDetailArea();
                break;
            }
            case R.id.tv_detail_area4: {
                select_tv_detail_area_index = 4;
                resetTvDetailArea();
                break;
            }
            case R.id.tv_from:
                isFrom = true;
                beginSelectAddress();
                break;
            case R.id.tv_to:
                isFrom = false;
                beginSelectAddress();
                break;
            case R.id.tv_begin_time:
                String nowText = tvBeginTime.getText().toString();

                int checkIndex = 0;
                Calendar ca = Calendar.getInstance();
                ca.setTime(new Date());
                Date lastMonth = ca.getTime();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                ArrayList<String> dateArr = new ArrayList<>();
                for (int i = 0 ; i < 10 ;i ++) {
                    ca.add(Calendar.DAY_OF_YEAR, 1);
                    String t = sdf.format(ca.getTime());
                    if (i==0) {
                        t += " 今天";
                    }
                    if (i==1) {
                        t += " 明天";
                    }
                    if (i==2) {
                        t += " 后天";
                    }
                    if (t.equals(nowText)) {
                        checkIndex = i;
                    }
                    dateArr.add(t);
                }
                final int checkedItem = checkIndex;
                final String[] items = dateArr.toArray(new String[dateArr.size()]);
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("选择发车时间");
                builder.setSingleChoiceItems(items, checkedItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which >= 0 && which < items.length) {
                            tvBeginTime.setText(items[which]);
                        }
                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (which >= 0 && which < items.length) {
                            tvBeginTime.setText(items[which]);
                        }
                    }
                });
                builder.create().show();
                break;
            case R.id.id_reset_wheelView:
                 mCurrentProviceName = null;
                 mCurrentCityName = null;
                 mCurrentDistrictName = null;
                 mCurrentZipCode = null;
                addressData = new UserRegAddressModel();
                tvTo.setText("目的地");
                tvFrom.setText("出发地");
                id_select_address.setVisibility(View.GONE);
                onRefresh();
                reloadSelectAddress();
                break;
            case R.id.id_ok_wheelView:
                id_select_address.setVisibility(View.GONE);
                if (isFrom) {
                    addressData.setFromProvince(mCurrentProviceName);
                    addressData.setFromCity(mCurrentCityName);
                    addressData.setFromDistrict(mCurrentDistrictName);
                } else {
                    addressData.setToProvince(mCurrentProviceName);
                    addressData.setToCity(mCurrentCityName);
                    addressData.setToDistrict(mCurrentDistrictName);
                }
                reloadSelectAddress();
                onRefresh();
                reloadSelectAddress();
                break;
//            case R.id.titlebar_btnRight:
//                Intent intent1 = new Intent(mContext, TradeListActivity.class);
//                startActivity(intent1);
//                break;
            default:
                break;
        }
    }

    private void reloadSelectAddress(){
        String s = "";
        if (addressData.getToProvince().length()>0) {
            s = addressData.getToProvince();
        }
        if (addressData.getToCity().length()>0) {
            s = addressData.getToCity();
        }
        if (addressData.getToDistrict().length()>0) {
            s = addressData.getToDistrict();
        }
        if (s.length()>0) {
            tv_detail_area.setVisibility(View.VISIBLE);
            tvTo.setText(s);
        } else {
            tv_detail_area.setVisibility(View.GONE);
            tvTo.setText("目的地");
        }

        if (addressData.getFromProvince().length()>0) {
            s = addressData.getFromProvince();
        }
        if (addressData.getFromCity().length()>0) {
            s = addressData.getFromCity();
        }
        if (addressData.getFromDistrict().length()>0) {
            s = addressData.getFromDistrict();
        }
        if (s.length()>0) {
            tvFrom.setText(s);
        } else {
            tvFrom.setText("出发地");
        }
    }
//-----------------------------------------------------//

    //选择地址
    private void beginSelectAddress() {
        id_select_address.setVisibility(View.VISIBLE);
        String p = "";
        String c = "";
        String d = "";
        if (isFrom) {
            p = addressData.getFromProvince();
            c = addressData.getFromCity();
            d = addressData.getFromDistrict();
        } else {
            p = addressData.getToProvince();
            c = addressData.getToCity();
            d = addressData.getToDistrict();
        }
        int pIndex = 0;
        int cIndex = 0;
        int dIndex = 0;
        if (p.length()>0) {
            for (int i = 0; i < mProvinceDatas.length; i++) {
                if (mProvinceDatas[i].contains(p)) {
                    pIndex = i;
                }
            }

            String[] cs = mCitisDatasMap.get(p);
            for (int i = 0; i < cs.length; i++) {
                if (cs[i].toString().contains(c)) {
                    cIndex = i;
                }
            }

            String[] ds = mDistrictDatasMap.get(c);
            for (int i = 0; i < ds.length; i++) {
                if (ds[i].toString().contains(d)) {
                    dIndex = i;
                }
            }
        }

        mViewProvince.setCurrentItem(pIndex);
        mViewCity.setCurrentItem(cIndex);
        mViewDistrict.setCurrentItem(dIndex);
    }

    protected String[] mProvinceDatas;
    protected Map<String, String[]> mCitisDatasMap = new HashMap<String, String[]>();
    protected Map<String, String[]> mDistrictDatasMap = new HashMap<String, String[]>();
    protected Map<String, String> mZipcodeDatasMap = new HashMap<String, String>();
    protected String mCurrentProviceName;
    protected String mCurrentCityName;
    protected String mCurrentDistrictName ="";
    protected String mCurrentZipCode ="";
    protected void initProvinceDatas() {
        List<ProvinceModel> provinceList = null;
        AssetManager asset = mContext.getAssets();
        try {
            InputStream input = asset.open("province_data.xml");
            // ����һ������xml�Ĺ�������
            SAXParserFactory spf = SAXParserFactory.newInstance();
            // ����xml
            SAXParser parser = spf.newSAXParser();
            XmlParserHandler handler = new XmlParserHandler();
            parser.parse(input, handler);
            input.close();
            // ��ȡ�������������
            provinceList = handler.getDataList();
            //*/ ��ʼ��Ĭ��ѡ�е�ʡ���С���
            if (provinceList != null && !provinceList.isEmpty()) {
                mCurrentProviceName = provinceList.get(0).getName();
                List<CityModel> cityList = provinceList.get(0).getCityList();
                if (cityList != null && !cityList.isEmpty()) {
                    mCurrentCityName = cityList.get(0).getName();
                    List<DistrictModel> districtList = cityList.get(0).getDistrictList();
                    mCurrentDistrictName = districtList.get(0).getName();
                    mCurrentZipCode = districtList.get(0).getZipcode();
                }
            }
            //*/
            mProvinceDatas = new String[provinceList.size()];
            for (int i = 0; i < provinceList.size(); i++) {
                // ��������ʡ�����
                mProvinceDatas[i] = provinceList.get(i).getName();
                List<CityModel> cityList = provinceList.get(i).getCityList();
                String[] cityNames = new String[cityList.size()];
                for (int j = 0; j < cityList.size(); j++) {
                    // ����ʡ����������е����
                    cityNames[j] = cityList.get(j).getName();
                    List<DistrictModel> districtList = cityList.get(j).getDistrictList();
                    String[] distrinctNameArray = new String[districtList.size()];
                    DistrictModel[] distrinctArray = new DistrictModel[districtList.size()];
                    for (int k = 0; k < districtList.size(); k++) {
                        // ����������������/�ص����
                        DistrictModel districtModel = new DistrictModel(districtList.get(k).getName(), districtList.get(k).getZipcode());
                        // ��/�ض��ڵ��ʱ࣬���浽mZipcodeDatasMap
                        mZipcodeDatasMap.put(districtList.get(k).getName(), districtList.get(k).getZipcode());
                        distrinctArray[k] = districtModel;
                        distrinctNameArray[k] = districtModel.getName();
                    }
                    // ��-��/�ص���ݣ����浽mDistrictDatasMap
                    mDistrictDatasMap.put(cityNames[j], distrinctNameArray);
                }
                // ʡ-�е���ݣ����浽mCitisDatasMap
                mCitisDatasMap.put(provinceList.get(i).getName(), cityNames);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {

        }
    }
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        // TODO Auto-generated method stub
        if (wheel == mViewProvince) {
            updateCities();
        } else if (wheel == mViewCity) {
            updateAreas();
        } else if (wheel == mViewDistrict) {
            mCurrentDistrictName = mDistrictDatasMap.get(mCurrentCityName)[newValue];
            mCurrentZipCode = mZipcodeDatasMap.get(mCurrentDistrictName);
        }
    }
    private void updateAreas() {
        int pCurrent = mViewCity.getCurrentItem();
        mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[pCurrent];
        String[] areas = mDistrictDatasMap.get(mCurrentCityName);

        if (areas == null) {
            areas = new String[] { "" };
        }
        mViewDistrict.setViewAdapter(new ArrayWheelAdapter<String>(mContext, areas));
        mViewDistrict.setCurrentItem(0);
    }
    private void updateCities() {
        int pCurrent = mViewProvince.getCurrentItem();
        mCurrentProviceName = mProvinceDatas[pCurrent];
        String[] cities = mCitisDatasMap.get(mCurrentProviceName);
        if (cities == null) {
            cities = new String[] { "" };
        }
        mViewCity.setViewAdapter(new ArrayWheelAdapter<String>(mContext, cities));
        mViewCity.setCurrentItem(0);
        updateAreas();
    }


}