package com.zfwl.mvp;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by ZZB on 2016/10/13.
 */

public class BasePresenter<V extends MvpView> implements Presenter<V> {

    private static final String TAG = "BasePresenter";

    private V mMvpView;
    private boolean mIsAttachViewMethodCalled = false;
    private CompositeSubscription mCompositeSubscription = new CompositeSubscription();
    public BasePresenter() {
    }

    @Override
    public void attachView(V mvpView) {
        mIsAttachViewMethodCalled = true;
        mMvpView = mvpView;
    }

    @Override
    public void detachView() {
        mCompositeSubscription.unsubscribe();
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

    protected void addSubscription(Subscription subscription){
        mCompositeSubscription.add(subscription);
    }
}
