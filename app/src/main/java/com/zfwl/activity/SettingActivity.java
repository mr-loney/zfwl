package com.zfwl.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.zfwl.R;
import com.zfwl.activity.home.HomeActivity;
import com.zfwl.common.SDCardHelper;
import com.zfwl.controls.LightPopDialog;
import com.zfwl.controls.LoadingDialog;
import com.zfwl.controls.WidgetSettingItem;
import com.zfwl.data.UserInfoManager;
import com.zfwl.util.ViewHub;

import java.io.File;
import java.io.FileInputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity {

    @BindView(R.id.titlebar_btnLeft)
    Button titlebarBtnLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.wsi_cpd_set)
    WidgetSettingItem wsiCpdSet;
    @BindView(R.id.wsi_notify)
    WidgetSettingItem wsiNotify;
    @BindView(R.id.wsi_clear)
    WidgetSettingItem wsiClear;
    @BindView(R.id.wsi_exit)
    WidgetSettingItem wsiExit;

    private Context mContext = this;
    private boolean mUploadingErrorLog;
    private LoadingDialog loadingDialog;

    public static void launch(Context context){
        Intent intent = new Intent(context, SettingActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        initView();
        loadingDialog = new LoadingDialog(this);
    }

    @OnClick(R.id.titlebar_btnLeft)
    public void onTitleLeftClick() {
        finish();
    }

    private void initView() {
        titlebarBtnLeft.setVisibility(View.VISIBLE);
        tvTitle.setText("设置");
    }

    @OnClick(R.id.wsi_cpd_set)
    public void onCptClick() {
        MyCPDActivity.launch(mContext,false);
    }
    @OnClick(R.id.wsi_notify)
    public void onNotifyClick() {
        final String items[] = {"开", "关"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("是否接收订单通知");
        final int checkedItem = 0;
        builder.setSingleChoiceItems(items, checkedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                wsiNotify.setRightText(items[which]);
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if (which>=0&&which<items.length) {
                    wsiNotify.setRightText(items[which]);
                }
            }
        });
        builder.create().show();
    }
    @OnClick(R.id.wsi_clear)
    public void onClearClick() {
        new ClearTask().execute();
    }
    @OnClick(R.id.wsi_exit)
    public void onExitClick() {
        ViewHub.showLightPopDialog(this, getString(R.string.dialog_title),
                getString(R.string.shopset_exit_confirm), "取消", "退出登录", new LightPopDialog.PopDialogListener() {
                    @Override
                    public void onPopDialogButtonClick(int which) {
                        UserInfoManager.INSTANCE.clearOnLogout();
                        HomeActivity.launch(mContext);
                        finish();
                    }
                });
    }

    private class ClearTask extends AsyncTask<Object, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ViewHub.showLongToast(getApplicationContext(), "清除缓存中，请等待...");
        }

        @Override
        protected String doInBackground(Object... params) {

            long gcCountBefore = 0;
            long gcCountEnd = 0;
            try {
                //清除cachedir
                File cacheFile = getApplicationContext().getCacheDir();
                gcCountBefore = getFileSize(cacheFile, 0);
                delFileSize(cacheFile);
                gcCountEnd = getFileSize(cacheFile, 0);
                //清除external cache
                cacheFile = mContext.getExternalCacheDir();
                gcCountBefore += getFileSize(cacheFile, 0);
                delFileSize(cacheFile);
                gcCountEnd += getFileSize(cacheFile, 0);
                //清除sd cache
                File cacheFile5 = new File(SDCardHelper.getSDCardRootDirectory()+"/zfwl");
                gcCountBefore += getFileSize(cacheFile5, 0);
                delFileSize(cacheFile5);delImg2Media(cacheFile5);
                gcCountEnd += getFileSize(cacheFile5, 0);
            }
            catch (Exception ex) {
            }
            //toast
            String showText = Formatter.formatFileSize(mContext, gcCountBefore - gcCountEnd);
            return showText;
        }

        @Override
        protected void onPostExecute(String result) {
            if (loadingDialog.isShowing()) {
                loadingDialog.stop();
            }
            ViewHub.showLongToast(getApplicationContext(), "已释放" + result + "缓存");
        }
    }

    private long getFileSize(File f, long allFileSize) {
        if (f == null) {
            return 0;
        }
        if (f.isDirectory() && f.listFiles() != null) {
            for (File item : f.listFiles()) {
                allFileSize = getFileSize(item, allFileSize);
            }
        } else {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(f);
                allFileSize += fis.available();
            } catch (Exception e) {
            }
        }
        return allFileSize;
    }

    private void delFileSize(File f) {
        if (f == null)
            return;
        if (f.isDirectory() && f.listFiles() != null) {
            for (File item : f.listFiles()) {
                delFileSize(item);
            }
        } else {
            f.delete();
        }
        return;
    }

    private void delImg2Media(File f)
    {
        mContext.getContentResolver().delete(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "_data like '%"+f.getAbsolutePath()+"%'", null);
        Log.e("asd", "asdasd");
    }
}
