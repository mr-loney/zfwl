package com.zfwl.mvp.logistics;

import com.zfwl.mvp.BasePresenter;

/**
 * Created by ZZB on 2016/12/15.
 */
public class LogisticsPresenter extends BasePresenter<LogisticsMvpView> {
    //String departure, String destination, String date
    public void refreshLogisticsList() {
        getMvpView().onRefreshLogisticsListSuccess(null);
    }

    public void loadMoreLogisticsList() {

    }
}
