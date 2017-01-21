package com.zfwl.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.zfwl.R;
import com.zfwl.activity.MyCPDActivity;
import com.zfwl.adapter.CPDAdatper;
import com.zfwl.adapter.QuickCPDAdatper;
import com.zfwl.controls.wheel.widget.adapters.ListWheelAdapter;
import com.zfwl.entity.Address;
import com.zfwl.entity.Area;
import com.zfwl.entity.CPDModel;
import com.zfwl.mvp.cpd.CPDMvpView;
import com.zfwl.mvp.cpd.CPDPresenter;
import com.zfwl.mvp.selectarea.SelectAreaPresenter;
import com.zfwl.util.ViewHub;
import com.zfwl.widget.slsectarea.SelectAreaView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectCPDListView extends FrameLayout implements CPDMvpView {
    private static final String TAG = "SelectCPDListView";
    @BindView(R.id.cancel)
    TextView cancel;
    @BindView(R.id.other)
    TextView other;
    @BindView(R.id.listview)
    ListView listview;

    private Context mContext;
    private SelectCPDListView.SelectCallback mCallback;
    private QuickCPDAdatper cpdAdatper;
    private CPDPresenter mCPDPresenter;

    public interface SelectCallback {
        void onSelectedCPD(CPDModel model);
    }

    private void init(Context context) {
        mContext = context;
        inflate(context, R.layout.layout_select_cpd, this);
        ButterKnife.bind(this, this);
        mCPDPresenter = new CPDPresenter(context);
        mCPDPresenter.attachView(this);
        mCPDPresenter.getList();
    }


    @Override
    public void onListLoaded(List<CPDModel> datas) {

		cpdAdatper = new QuickCPDAdatper(mContext, datas);
        listview.setAdapter(cpdAdatper);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CPDModel model = cpdAdatper.mList.get(position);
                mCallback.onSelectedCPD(model);
            }
        });
    }

    @Override
    public void onListLoadedFail(String msg) {
        ViewHub.showLongToast(mContext,msg);
    }


    @Override
    public void onAdded(CPDModel data){}
    @Override
    public void onAddedFail(String msg){}
    @Override
    public void onDel(){}
    @Override
    public void onDelFail(String msg){}

    public void show() {
        setVisibility(VISIBLE);
    }

    @OnClick(R.id.cancel)
    public void onCancelClick() {
        setVisibility(GONE);
    }

    @OnClick(R.id.other)
    public void onOtherClick() {
        setVisibility(GONE);
        MyCPDActivity.launch(mContext,false);
    }

    public void setCallback(SelectCallback callback) {
        mCallback = callback;
    }


    public SelectCPDListView(Context context) {
        super(context);
        init(context);
    }

    public SelectCPDListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    public SelectCPDListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @OnClick(R.id.root_view)
    public void onRootViewClick() {
        setVisibility(GONE);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mCPDPresenter.detachView();
    }
}
