package com.zfwl.mvp.logistics;

import com.zfwl.mvp.BasePresenter;

public class MyPublishEmptyCarPresenter extends BasePresenter<MyPublishEmptyCarMvpView> {
    public void getList(int mPageIndex) {
        getMvpView().onGetListSuccess(null);
    }

}
