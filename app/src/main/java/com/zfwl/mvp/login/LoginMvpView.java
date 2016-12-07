package com.zfwl.mvp.login;

import com.zfwl.mvp.MvpView;

/**
 * Created by ZZB on 2016/12/7.
 */

public interface LoginMvpView extends MvpView {

    void showLoginLoading();

    void onLoginSuccess();

    void onLoginFailed();

    void hideLoginLoading();
}
