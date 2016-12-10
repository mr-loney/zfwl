package com.zfwl.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.zfwl.common.MyLog;

/**
 * Created by ZZB on 2016/12/10.
 */
public class SwipeControllableViewPager extends ViewPager {
    private static final String TAG = "SwipeControllableViewPager";
    private boolean swipeEnabled;

    public SwipeControllableViewPager(Context context) {
        this(context, null);
    }

    public SwipeControllableViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        swipeEnabled = true;
    }

    public void setSwipeEnabled(boolean enabled) {
        swipeEnabled = enabled;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        try {
            return swipeEnabled && super.onInterceptTouchEvent(event);
        } catch (Exception e) {
            MyLog.e(TAG, e, "onInterceptTouchEvent error");
            return false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return swipeEnabled && super.onTouchEvent(event);
    }
}
