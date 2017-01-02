package com.zfwl.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zfwl.R;
import com.zfwl.activity.home.HomeActivity;
import com.zfwl.adapter.MyPublishEmptyCarListAdapter;
import com.zfwl.controls.LoadingDialog;
import com.zfwl.controls.pulltorefresh.PullToRefreshListView;
import com.zfwl.controls.pulltorefresh.PullToRefreshListViewEx;
import com.zfwl.entity.MyPublishEmptyCarListModel;
import com.zfwl.entity.User;
import com.zfwl.event.MyPublishEmptyCarEvent;
import com.zfwl.mvp.logistics.MyPublishEmptyCarMvpView;
import com.zfwl.mvp.logistics.MyPublishEmptyCarPresenter;
import com.zfwl.util.ViewHub;
import com.zfwl.widget.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyPublishEmptyCarActivity extends BaseActivity implements MyPublishEmptyCarMvpView,PullToRefreshListView.OnLoadMoreListener, PullToRefreshListView.OnRefreshListener {

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
    private MyPublishEmptyCarActivity vThis = this;

    private LoadingDialog mloadingDialog;
    private MyPublishEmptyCarListAdapter adapter;
    private List<MyPublishEmptyCarListModel.ListBean> itemList = null;
    private PullToRefreshListView pullRefreshListView;
    private int mPageIndex = 1;
    private int mPageSize = 20;
    private View emptyView;
    private MyPublishEmptyCarPresenter mPresenter;
    private boolean mIsRefresh = false;

    public static void launch(Context context){
        Intent intent = new Intent(context, MyPublishEmptyCarActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_my_publish_empty_car_list);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        mPresenter = new MyPublishEmptyCarPresenter(this);
        mPresenter.attachView(this);

        initView();
        loadData();
    }

    @Subscribe
    public void onEvent(MyPublishEmptyCarEvent event) {
if (event.isReloadCore("reload")) {
    onRefresh();
}
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        mPresenter.detachView();
    }

    /**
     * 初始化视图
     */
    private void initView() {
        titlebarBtnLeft.setVisibility(View.VISIBLE);
        tvTitle.setText("我发布的空车信息");

        emptyView = findViewById(R.id.view_empty);
        mloadingDialog = new LoadingDialog(vThis);

        itemList = new ArrayList<MyPublishEmptyCarListModel.ListBean>();
        mloadingDialog = new LoadingDialog(vThis);
        pullRefreshListView = (PullToRefreshListView) findViewById(R.id.pull_refresh_listview_items);
        pullRefreshListView.setCanLoadMore(true);
        pullRefreshListView.setCanRefresh(true);
        pullRefreshListView.setMoveToFirstItemAfterRefresh(true);
        pullRefreshListView.setOnRefreshListener(this);
        pullRefreshListView.setOnLoadListener(this);
        pullRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MyPublishEmptyCarListModel.ListBean model = adapter.mList.get(i - 1);

                Intent intent = new Intent(vThis, MyPublishEmptyCarDetailActivity.class);
                intent.putExtra("data", (Serializable) model);
                startActivity(intent);
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
    public void onGetListSuccess(MyPublishEmptyCarListModel d) {
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
    public void onDelSuccess(MyPublishEmptyCarListModel.ListBean d) {
    }

    @Override
    public void onDelFail(String errorMsg) {
    }

    /**
     * 获取商品分类数据
     */
    private void loadData() {
        mIsRefresh = true;
        mloadingDialog.start("正在获取数据");

        mPresenter.getList(mPageIndex,mPageSize);
    }

    // 初始化数据
    private void initItemAdapter() {
        if (itemList == null)
            itemList = new ArrayList<MyPublishEmptyCarListModel.ListBean>();

        adapter = new MyPublishEmptyCarListAdapter(vThis, itemList);
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
        mPresenter.getList(mPageIndex,mPageSize);
    }


}
