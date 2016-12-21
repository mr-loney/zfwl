package com.zfwl.mvp.logistics;

import com.zfwl.mvp.BasePresenter;

public class AddLogisticsPresenter extends BasePresenter<AddLogisticsMvpView> {
    public void addLogistics() {
        getMvpView().onAddLogisticsSuccess();
    }

}
