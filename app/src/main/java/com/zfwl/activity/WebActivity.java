package com.zfwl.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zfwl.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;

public class WebActivity extends BaseActivity implements OnClickListener {

    private static final String TAG             = "ItemPreviewActivity";
    private Context             mContext        = this;

    private TextView            tvTitle;
    private Button              btnLeft;
    private ImageView           iconLoading;
    private Animation           iconLoadingAnimation;
    private WebView             webView;

    // 直接展示指定URL
    private String              normalUrl;
    private String              name;
    // 上次访问的url
    private String              mLastUrl;
    // 根据用户ID查询到数据后展示URL
    private String              user_id;

    private boolean             isBackUrl;                              // 返回标志，如果为true，则表示这个url是因返回调用的，此时不执行那些非网页加载动作

    private String              wapItemTimeLine = "";
    private TextView            mEmptyView;
    private Button              btnLeft2;

    public static void launch(Context context,String url,String title){
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra("name", title);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_web);

        Intent intent = getIntent();
        if (intent.hasExtra("url") && !TextUtils.isEmpty(intent.getStringExtra("url"))) {
            normalUrl = intent.getStringExtra("url");
        } else {
            normalUrl = "";
        }
        if (intent.hasExtra("name")) {
            name = (String)intent.getStringExtra("name");
        } else {
            name = "";
        }

        initView();
        tvTitle.setText(name);

        loadWebView();

    }

    private void loadWebView() {
        mEmptyView.setVisibility(View.GONE);
        webView.setVisibility(View.VISIBLE);
        // 清空webview中的cookie缓存，保证每次获取token都能正常使用cookie记录登录人状态
        CookieManager cm = CookieManager.getInstance();
        cm.removeSessionCookie();
        cm.removeAllCookie();
        webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);

        isBackUrl = false;
        webView.loadUrl(normalUrl);
    }

    /**
     * 初始化视图
     * */
    @SuppressLint("SetJavaScriptEnabled")
    private void initView() {
        // 标题栏
        tvTitle = (TextView)findViewById(R.id.titlebar_tvTitle);
        btnLeft = (Button)findViewById(R.id.titlebar_btnLeft);
        btnLeft2 = (Button)findViewById(R.id.titlebar_btnLeft2);

        iconLoading = (ImageView)findViewById(R.id.titlebar_icon_loading);
        mEmptyView = (TextView)findViewById(R.id.tv_empty_view);
        mEmptyView.setOnClickListener(this);
        btnLeft.setText("返回");
        btnLeft.setVisibility(View.VISIBLE);

        btnLeft.setOnClickListener(this);
        btnLeft2.setVisibility(View.VISIBLE);
        btnLeft2.setOnClickListener(this);

        // webview
        webView = (WebView)findViewById(R.id.my_webview);
        WebSettings settings = webView.getSettings();
        String user_agent = settings.getUserAgentString();
        settings.setUserAgentString(user_agent + " zfwl/android");
        // webView.getSettings().setBlockNetworkImage(true);
        // webView.addJavascriptInterface(new InJavaScriptLocalObj(), "share");
        // // 支持javascript
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDatabaseEnabled(true);
        webView.getSettings().setRenderPriority(RenderPriority.HIGH);
        webView.getSettings().setDomStorageEnabled(true);// 使用LocalStorage则必须打开
        webView.setWebViewClient(new CustomeWebViewClient());// 自定义WebViewClient
        // webView.setDownloadListener(new MyWebViewDownLoadListener());

        // 转啊转
        iconLoadingAnimation = AnimationUtils.loadAnimation(mContext, R.anim.loading);
        iconLoadingAnimation.setInterpolator(new LinearInterpolator());// 匀速旋转
    }

    /**
     * 自定义WebViewClient
     * */
    private class CustomeWebViewClient extends WebViewClient {

        // 点击网页超链接，在web内跳转，不打开系统自带浏览器
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.i(TAG, "WebView url:" + url);

            if (url.startsWith("http") || url.startsWith("https")) {
                        isBackUrl = false;
                        view.loadUrl(url);
            } else {

                if (isBackUrl) {
                    isBackUrl = true;
                    view.goBack();
                }
            }
            mLastUrl = url;
            return true;
        }

        // 加载网页时，显示加载动画
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            Log.i(TAG, "onPageStarted url:" + url);
            myHandler.sendMessage(myHandler.obtainMessage(LoadingVisible));

        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            Log.d(getClass().getSimpleName(), "onReceivedError");
            // ViewHub.showShortToast(getApplicationContext(), "加载失败....");
            mEmptyView.setVisibility(View.VISIBLE);
            webView.setVisibility(View.GONE);

            // ViewHub.setEmptyView(context, view, emptyText);
            super.onReceivedError(view, errorCode, description, failingUrl);
        }

        // 加载网页完成时，隐藏加载动画
        @Override
        public void onPageFinished(WebView view, String url) {
            Log.d(getClass().getSimpleName(), "onPageFinished url:" + url);
            btnLeft2.setVisibility(webView.canGoBack() ? View.VISIBLE : View.GONE);
            Button rightBtn = (Button)findViewById(R.id.titlebar_btnRight);
            rightBtn.setBackgroundResource(R.drawable.refresh);
            rightBtn.setOnClickListener(WebActivity.this);
            rightBtn.setText("");
            int marginRight = dip2px(mContext.getResources(), 10);
            int width30 = dip2px(mContext.getResources(), 30);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width30, width30);
            lp.setMargins(0, 0, marginRight, 0);
            rightBtn.setLayoutParams(lp);
            rightBtn.setVisibility(View.VISIBLE);

            myHandler.sendMessage(myHandler.obtainMessage(LoadingHidden));
            myHandler.sendMessage(myHandler.obtainMessage(PreviewHidden));
        }
    }

    private static int    PreviewHidden  = 3;
    private static int    LoadingVisible = 4;
    private static int    LoadingHidden  = 5;

    private static String shareJson;
    private Handler       myHandler      = new Handler() {
                                             @Override
                                             public void handleMessage(Message msg) {
                                                 Thread.currentThread().setName("PreviewActivity load webview");
                                                 if (msg.what == LoadingVisible) {
                                                     iconLoading.setAnimation(iconLoadingAnimation);
                                                     iconLoadingAnimation.cancel();
                                                     iconLoading.setVisibility(View.VISIBLE);
                                                 } else if (msg.what == LoadingHidden) {
                                                     iconLoading.setAnimation(null);
                                                     iconLoadingAnimation.start();
                                                     iconLoading.setVisibility(View.GONE);
                                                 }
                                             }

                                         };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.titlebar_btnLeft:// 返回首页
                if (webView.canGoBack()) {
                    webView.goBack();
                } else {
                    finish();
                }
                break;
            case R.id.titlebar_btnLeft2:// 返回首页
                finish();
                break;
            case R.id.tv_empty_view:
            case R.id.titlebar_btnRight:
                loadWebView();
                break;
        }
    }

    private Date lastShareTime;

    long timeline;

    /**
     * 处理“Back”键，按下此键时，让网页返回上一页面，而不是结束整个Activity
     * */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        String url = webView.getUrl();
        Log.i(TAG, "onKeyDown webView.canGoBack():" + webView.canGoBack() + " url:" + url);
        // 05-04 18:11:46.184: I/ItemPreviewActivity(20617): onKeyDown webView.canGoBack():true
        // url:https://m.wangyin.com/wepay/web/pay
        if ((keyCode == KeyEvent.KEYCODE_BACK)
                && (TextUtils.equals(url, "https://m.wangyin.com/wepay/web/pay") || TextUtils
                        .equals(url, "about:blank"))) {
            return super.onKeyDown(keyCode, event);
        }

        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            isBackUrl = true;
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public Intent getFileIntent(File file) {
        // Uri uri = Uri.parse("http://m.ql18.com.cn/hpf10/1.pdf");
        Uri uri = Uri.fromFile(file);
        String type = getMIMEType(file);
        Log.i("tag", "type=" + type);
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(uri, type);
        return intent;
    }

    public void writeToSDCard(String fileName, InputStream input) {

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File directory = Environment.getExternalStorageDirectory();
            File file = new File(directory, fileName);
            try {
                FileOutputStream fos = new FileOutputStream(file);
                byte[] b = new byte[2048];
                int j = 0;
                while ((j = input.read(b)) != -1) {
                    fos.write(b, 0, j);
                }
                fos.flush();
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Log.i("tag", "NO SDCard.");
        }
    }

    private String getMIMEType(File f) {
        String type = "";
        String fName = f.getName();
        /* 取得扩展名 */
        String end = fName.substring(fName.lastIndexOf(".") + 1, fName.length()).toLowerCase();

        /* 依扩展名的类型决定MimeType */
        if (end.equals("pdf")) {
            type = "application/pdf";//
        } else if (end.equals("m4a") || end.equals("mp3") || end.equals("mid") || end.equals("xmf")
                || end.equals("ogg") || end.equals("wav")) {
            type = "audio/*";
        } else if (end.equals("3gp") || end.equals("mp4")) {
            type = "video/*";
        } else if (end.equals("jpg") || end.equals("gif") || end.equals("png") || end.equals("jpeg")
                || end.equals("bmp")) {
            type = "image/*";
        } else if (end.equals("apk")) {
            /* android.permission.INSTALL_PACKAGES */
            type = "application/vnd.android.package-archive";
        }
        // else if(end.equals("pptx")||end.equals("ppt")){
        // type = "application/vnd.ms-powerpoint";
        // }else if(end.equals("docx")||end.equals("doc")){
        // type = "application/vnd.ms-word";
        // }else if(end.equals("xlsx")||end.equals("xls")){
        // type = "application/vnd.ms-excel";
        // }
        else {
            // /*如果无法直接打开，就跳出软件列表给用户选择 */
            type = "*/*";
        }
        return type;
    }
    public int dip2px(Resources res, float dpValue) {
        if (res != null) {
            final float scale = res.getDisplayMetrics().density;
            return (int) (dpValue * scale + 0.5f);
        }
        return (int) dpValue;
    }
}
