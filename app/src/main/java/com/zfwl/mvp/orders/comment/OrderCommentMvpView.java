package com.zfwl.mvp.orders.comment;

import com.zfwl.mvp.MvpView;

/**
 * Created by ZZB on 2017/1/1.
 */
public interface OrderCommentMvpView extends MvpView{
    void showLoading();

    void hideLoading();

    void onCommentSuccess();

    void onCommentFailed(String msg);
}
