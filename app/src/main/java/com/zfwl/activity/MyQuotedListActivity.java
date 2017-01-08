package com.zfwl.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zfwl.R;
import com.zfwl.adapter.MyQuotedListAdapter;
import com.zfwl.adapter.MyQuotedListAdapter;
import com.zfwl.controls.LoadingDialog;
import com.zfwl.controls.pulltorefresh.PullToRefreshListView;
import com.zfwl.controls.pulltorefresh.PullToRefreshListViewEx;
import com.zfwl.data.UserInfoManager;
import com.zfwl.data.api.retrofit.ApiModule;
import com.zfwl.entity.MyQuotedModel;
import com.zfwl.entity.MyQuotedModel;
import com.zfwl.mvp.logistics.MyQuotedMvpView;
import com.zfwl.mvp.logistics.MyQuotedPresenter;
import com.zfwl.widget.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyQuotedListActivity extends BaseActivity implements MyQuotedMvpView,PullToRefreshListView.OnLoadMoreListener, PullToRefreshListView.OnRefreshListener {

    @BindView(R.id.titlebar_btnLeft)
    Button titlebarBtnLeft;
    @BindView(R.id.titlebar_btnRight)
    Button titlebarBtnRight;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.pull_refresh_listview_items)
    PullToRefreshListViewEx pullRefreshListviewItems;
    @BindView(R.id.view_empty)
    LinearLayout viewEmpty;
    private MyQuotedListActivity vThis = this;

    private LoadingDialog mloadingDialog;
    private MyQuotedListAdapter adapter;
    private List<MyQuotedModel.ListBean> itemList = null;
    private PullToRefreshListView pullRefreshListView;
    private int mPageIndex = 1;
    private int mPageSize = 20;
    private View emptyView;
    private MyQuotedPresenter mPresenter;
    private boolean mIsRefresh = false;

    public static void launch(Context context){
        Intent intent = new Intent(context, MyQuotedListActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_my_quoted_list);
        ButterKnife.bind(this);
        mPresenter = new MyQuotedPresenter(this);
        mPresenter.attachView(this);

        initView();
    }

    /**
     * 初始化视图
     */
    private void initView() {
        titlebarBtnLeft.setVisibility(View.VISIBLE);
        tvTitle.setText("我的报价信息");

        emptyView = findViewById(R.id.view_empty);
        mloadingDialog = new LoadingDialog(vThis);

        itemList = new ArrayList<MyQuotedModel.ListBean>();
        mloadingDialog = new LoadingDialog(vThis);
        pullRefreshListView = (PullToRefreshListView) findViewById(R.id.pull_refresh_listview_items);
        pullRefreshListView.setCanLoadMore(false);
        pullRefreshListView.setCanRefresh(true);
        pullRefreshListView.setMoveToFirstItemAfterRefresh(true);
        pullRefreshListView.setOnRefreshListener(this);
        pullRefreshListView.setOnLoadListener(this);
        pullRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MyQuotedModel.ListBean model = adapter.mList.get(i - 1);

                QuotedPriceDetailActivity.launch(vThis,model);
            }
        });

        // 刷新数据
        showEmptyView(false);
        emptyView.setOnClickListener(new OnClickListener() {

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
        onRefresh();
    }

    @OnClick(R.id.view_empty)
    public void onEmptyClick() {

    }

    @OnClick(R.id.titlebar_btnRight)
    public void onTitleRightClick() {

    }

    @OnClick(R.id.titlebar_btnLeft)
    public void onTitleLeftClick() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    public void onGetListSuccess(MyQuotedModel d) {
        mloadingDialog.stop();

        if (mIsRefresh) {
            itemList = d.getList();
            pullRefreshListView.onRefreshComplete();
        } else {
            itemList.addAll(d.getList());
            pullRefreshListView.onLoadMoreComplete();
        }

        adapter.mList = itemList;

        adapter.notifyDataSetChanged();
        vThis.showEmptyView(!(itemList.size() > 0));

    }

    @Override
    public void onGetListFailed(String msg) {
        mloadingDialog.stop();
        ToastUtils.show(this, msg);
    }

    @Override
    public void onDel() {}

    @Override
    public void onDelFail(String msg) {}

    private void loadData() {
        mIsRefresh = true;
        mloadingDialog.start("正在获取数据");

        mPresenter.getList(mPageIndex,mPageSize);
    }

    // 初始化数据
    private void initItemAdapter() {
        if (itemList == null)
            itemList = new ArrayList<MyQuotedModel.ListBean>();

        adapter = new MyQuotedListAdapter(vThis, itemList);
        pullRefreshListView.setAdapter(adapter);

    }


    @Override
    public void onRefresh() {
        showEmptyView(false);
        mPageIndex = 1;
        mIsRefresh = true;
        mloadingDialog.start("正在获取数据");
        mPresenter.getList(mPageIndex,mPageSize);
    }

    /**
     * 显示空数据视图
     */
    private void showEmptyView(boolean show) {
        pullRefreshListView.setVisibility(show ? View.GONE : View.VISIBLE);
        emptyView.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onLoadMore() {
        mPageIndex++;
        mIsRefresh = false;
        mPresenter.getList(mPageIndex,mPageSize);
    }


}
