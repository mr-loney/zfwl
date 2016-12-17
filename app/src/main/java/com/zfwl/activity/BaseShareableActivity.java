package com.zfwl.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.bilibili.socialize.share.core.BiliShare;
import com.bilibili.socialize.share.core.error.BiliShareStatusCode;
import com.zfwl.R;
import com.zfwl.share.ShareHelper;

/**
 * Created by ZZB on 2016/12/17.
 */
public abstract class BaseShareableActivity extends BaseActivity implements ShareHelper.Callback {
    protected ShareHelper mShare;

    public void startShare(@Nullable View anchor) {
        startShare(anchor, false);
    }

    public void startShare(@Nullable View anchor, boolean isWindowFullScreen) {
        if (mShare == null) {
            mShare = ShareHelper.instance(this, this);
        }
        if (anchor == null) {
            mShare.showShareDialog();
        } else {
            if (isWindowFullScreen)
                mShare.showShareFullScreenWindow(anchor);
            else
                mShare.showShareWarpWindow(anchor);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        BiliShare.onActivityResult(this, requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        if (mShare != null) {
            mShare.reset(); // reset held instance
            mShare = null;
        }
        super.onDestroy();
    }

    @Override
    public void onShareStart(ShareHelper helper) {

    }

    @Override
    public void onShareComplete(ShareHelper helper, int code) {
        if (code == BiliShareStatusCode.ST_CODE_SUCCESSED)
            Toast.makeText(this, R.string.bili_share_sdk_share_success, Toast.LENGTH_SHORT).show();
        else if (code == BiliShareStatusCode.ST_CODE_ERROR)
            Toast.makeText(this, R.string.bili_share_sdk_share_failed, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDismiss(ShareHelper helper) {
    }

}