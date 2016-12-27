package com.zfwl.mvp.logistics;

import com.zfwl.data.api.LogisticsApi;
import com.zfwl.data.api.retrofit.ApiModule;
import com.zfwl.mvp.BasePresenter;

/**
 * Created by ZZB on 2016/12/15.
 */
public class LogisticsPresenter extends BasePresenter<LogisticsMvpView> {

    private static final String TAG = "LogisticsPresenter";
    private static final int PAGE_SIZE = 10;
    private LogisticsApi mLogisticsApi;
    private int mPage;

    public LogisticsPresenter() {
        mLogisticsApi = ApiModule.INSTANCE.provideLogisticsApi();
    }

    public void refreshLogisticsList() {
        mPage = 0;
        getMvpView().onRefreshLogisticsListSuccess(null);
    }

    public void loadMoreLogisticsList() {

    }
}
