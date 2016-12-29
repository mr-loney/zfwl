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
import com.zfwl.adapter.WJListAdapter;
import com.zfwl.controls.LoadingDialog;
import com.zfwl.controls.pulltorefresh.PullToRefreshListView;
import com.zfwl.controls.pulltorefresh.PullToRefreshListViewEx;
import com.zfwl.entity.WJModel;
import com.zfwl.mvp.wj.WJMvpView;
import com.zfwl.mvp.wj.WJPresenter;
import com.zfwl.widget.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WJActivity extends BaseActivity implements WJMvpView,PullToRefreshListView.OnLoadMoreListener, PullToRefreshListView.OnRefreshListener {

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
    private WJActivity vThis = this;

    private LoadingDialog mloadingDialog;
    private WJListAdapter adapter;
    private List<WJModel> itemList = null;
    private PullToRefreshListView pullRefreshListView;
    private int mPageIndex = 1;
    private int mPageSize = 20;
    private View emptyView;
    private WJPresenter mPresenter;
    private boolean mIsRefresh = false;

    public static void launch(Context context){
        Intent intent = new Intent(context, WJActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_wj_list);
        ButterKnife.bind(this);
        mPresenter = new WJPresenter(this);
        mPresenter.attachView(this);

        initView();
    }

    /**
     * 初始化视图
     */
    private void initView() {
        titlebarBtnLeft.setVisibility(View.VISIBLE);
        tvTitle.setText("问卷调查");

        emptyView = findViewById(R.id.view_empty);
        mloadingDialog = new LoadingDialog(vThis);

        itemList = new ArrayList<WJModel>();
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
                WJModel model = adapter.mList.get(i - 1);

//                Intent intent = new Intent(vThis, WJDetailActivity.class);
//                intent.putExtra("data", model);
//                startActivity(intent);
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
    }

    @Override
    public void onListLoaded(List<WJModel> datas) {
        mloadingDialog.stop();

        if (mIsRefresh) {
            itemList = datas;
            pullRefreshListView.onRefreshComplete();
        } else {
            itemList.addAll(datas);
            pullRefreshListView.onLoadMoreComplete();
        }

        adapter.mList = itemList;

        adapter.notifyDataSetChanged();
        vThis.showEmptyView(!(itemList.size() > 0));

    }

    @Override
    public void onListLoadedFail(String msg) {
        mloadingDialog.stop();
        ToastUtils.show(this, msg);
    }

    private void loadData() {
        mIsRefresh = true;
        mloadingDialog.start("正在获取数据");

        mPresenter.getList();
    }

    // 初始化数据
    private void initItemAdapter() {
        if (itemList == null)
            itemList = new ArrayList<WJModel>();

        adapter = new WJListAdapter(vThis, itemList);
        pullRefreshListView.setAdapter(adapter);

    }


    @Override
    public void onRefresh() {
        showEmptyView(false);
        mPageIndex = 1;
        mIsRefresh = true;
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
        mPresenter.getList();
    }


}
