package com.zfwl.activity;

import com.trello.rxlifecycle.components.support.RxFragment;

/**
 * Created by ZZB on 2016/12/20.
 */
public class BaseFragment extends RxFragment {
    @Override
    public void setMenuVisibility(final boolean visible) {
        super.setMenuVisibility(visible);
        if (visible) {
            onVisible();
        } else {
            onInVisible();
        }
    }

    protected void onVisible() {

    }

    protected void onInVisible() {

    }
}

