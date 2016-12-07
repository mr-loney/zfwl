package com.zfwl.mvp;

import com.zfwl.common.Logger;

/**
 * Created by ZZB on 2016/10/13.
 */

public class BasePresenter<V extends MvpView> implements Presenter<V> {

    public static final String TAG = "BasePresenter";

    private V mMvpView;
    private boolean mIsAttachViewMethodCalled = false;

    public BasePresenter() {
    }

    @Override
    public void attachView(V mvpView) {
        mIsAttachViewMethodCalled = true;
        mMvpView = mvpView;
    }

    @Override
    public void detachView() {
        mMvpView = null;
    }

    @Override
    public boolean isViewAttached() {
        return mMvpView != null;
    }

    @Override
    public V getMvpView() {
        if (!mIsAttachViewMethodCalled) {
            throw new RuntimeException("please call attachView first");
        }
        return mMvpView;
    }
}
