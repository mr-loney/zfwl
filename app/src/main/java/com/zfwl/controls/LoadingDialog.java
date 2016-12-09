package com.zfwl.controls;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

import com.zfwl.R;

public class LoadingDialog extends Dialog {

    private Context mContext;
    private ImageView imageView;
    private CircleLoadingDrawable mCircleLoadingDrawable;

    public LoadingDialog(Context context) {
        super(context, R.style.LoadingDialog);
        initDialog(context);
    }

    public LoadingDialog(Context context, int theme) {
        super(context, R.style.LoadingDialog);
        initDialog(context);
    }

    public void initDialog(Context context) {
        mContext = context;
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        setContentView(R.layout.loadingdialog);
        getWindow().getAttributes().gravity = Gravity.CENTER;

    }

    public void onWindowFocusChanged(boolean hasFocus) {
//        Animation animation = AnimationUtils.loadAnimation(mContext,
//                R.anim.loading);
//        animation.setInterpolator(new LinearInterpolator());// 匀速旋转
        imageView = (ImageView) findViewById(R.id.loadingdialog_loadingicon);
//        imageView.setAnimation(animation);
//        animation.start();
        mCircleLoadingDrawable = new CircleLoadingDrawable(mContext.getApplicationContext());
        imageView.setImageDrawable(mCircleLoadingDrawable);
        mCircleLoadingDrawable.start();
    }

    /**
     * 设置提示信息
     *
     * @param message 提示信息
     */
    public void setMessage(String message) {
        TextView tvMsg = (TextView) findViewById(R.id.loadingdialog_message);
        if (tvMsg != null) {
            tvMsg.setText(message);
        }
    }

    /**
     * 弹出等待提示框
     */
    public void start() {
        start("");
    }

    /**
     * 弹出等待提示框
     *
     * @param message 提示信息
     */
    public void start(String message) {
        try {
            setMessage(message);
            show();
        } catch (Exception e) {
            //activity != null && !activity.isFinshing
            e.printStackTrace();
        }
    }

    /**
     * 关闭等待提示框
     */
    public void stop() {
        if (imageView != null && imageView.getAnimation() != null) {
            imageView.clearAnimation();
        }
        if (mCircleLoadingDrawable != null) {
            mCircleLoadingDrawable.stop();
        }
        try {
            dismiss();
        } catch (Exception e) {
            //activity != null && !activity.isFinshing
            e.printStackTrace();
        }

    }

}
