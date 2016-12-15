package com.zfwl.mvp.logistics;

import com.zfwl.mvp.BasePresenter;

/**
 * Created by ZZB on 2016/12/15.
 */
public class LogisticsPresenter extends BasePresenter<LogisticsMvpView>{

    public void loadLogisticsList(String departure, String destination, String date){
        getMvpView().onLoadLogisticsListSuccess(null);
    }
}
