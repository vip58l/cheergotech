package com.cheergotech.UI.activity.Web;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.cheergotech.Base.BaseActivity;
import com.cheergotech.R;
import com.cheergotech.ulist.Constants;
import com.cheergotech.ulist.Config;
import com.cheergotech.widget.Backtitle;

import butterknife.BindView;

/**
 * 浏览器
 */
public class DyWebActivity extends BaseActivity implements View.OnTouchListener {
    private String TAG = DyWebActivity.class.getSimpleName();
    @BindView(R.id.dy_webview)
    WebView webView;
    @BindView(R.id.backtitle)
    Backtitle backtitle;
    @BindView(R.id.progressbar)
    ProgressBar progressBar;
    @BindView(R.id.lin1)
    LinearLayout lin1;
    @BindView(R.id.conversation)
    ConstraintLayout conversation;
    String videoUrl;
    String JSON, mtitle;

    public static void starAction(Context context, String videoUrl) {
        Intent intent = new Intent(context, DyWebActivity.class);
        intent.putExtra(Constants.VIDEOURL, videoUrl);
        context.startActivity(intent);
    }

    public static void starAction(Context context, String videoUrl, String title) {
        Intent intent = new Intent(context, DyWebActivity.class);
        intent.putExtra(Constants.VIDEOURL, videoUrl);
        intent.putExtra(Constants.TITLE, title);
        context.startActivity(intent);
    }

    @Override
    protected int getview() {
        return R.layout.activity_dy;
    }

    @Override
    public void iniview() {
        videoUrl = getIntent().getStringExtra(Constants.VIDEOURL);
        JSON = getIntent().getStringExtra(Constants.JSON);
        mtitle = getIntent().getStringExtra(Constants.TITLE);
        conversation.setOnTouchListener(this);
        iniurl();
    }

    private void iniurl() {
        lin1.setVisibility(View.VISIBLE);
        webView.loadUrl(videoUrl);
        webView.setWebViewClient(new webviewClient());
        webView.setWebChromeClient(new myWebChromeClient());
        webView.setDownloadListener(downloadListener);
        WebSettings(webView);
    }

    @Override
    public void initData() {


    }

    @Override
    public void OnClick(View v) {

    }

    @Override
    public void OnEorr() {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        iniurl();
        return false;
    }

    private class webviewClient extends WebViewClient {

        @Override
        public void onFormResubmission(WebView view, Message dontResend, Message resend) {
            resend.sendToTarget();
            Log.d(TAG, "onFormResubmission: ");
        }

        public void onPageFinished(WebView view, String url) {
            if (!view.getSettings().getLoadsImagesAutomatically()) {
                //设置网页加载完成再显示图片
                view.getSettings().setLoadsImagesAutomatically(true);
                webView.getSettings().setBlockNetworkImage(false);
                if (TextUtils.isEmpty(mtitle)) {
                    backtitle.setconter(view.getTitle());
                } else {
                    backtitle.setconter(mtitle);
                    //执行JS代码
                    //view.loadUrl("javascript:function setTop(){document.querySelector('div.appbanner').style.display=\"none\";}setTop();");

                    //加载js隐藏多余协议标题
                    view.loadUrl("javascript:setTimeout(function(){document.getElementsByTagName('h2')[0].style.display=\"none\";});");

                }
            }
            super.onPageFinished(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            if (TextUtils.isEmpty(mtitle)) {
                backtitle.setconter(view.getTitle());
            } else {
                backtitle.setconter(mtitle);
            }

        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            lin1.setVisibility(View.GONE);
            super.onReceivedError(view, errorCode, description, failingUrl);
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            lin1.setVisibility(View.GONE);
            super.onReceivedError(view, request, error);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            //拦截超链接 & 自定义跳转 true 为拦截点击事件 false 就是正常的处理事件
            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
                return shouldOverrideUrlLoadingByApp(view, request.toString());
            } else {
                return shouldOverrideUrlLoadingByApp(view, request.getUrl().toString());
            }
        }

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.d(TAG, "覆盖Url加载:2 ");
            view.loadUrl(url);
            return true;
        }
    }

    private class myWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            if (newProgress == 100) {
                progressBar.setVisibility(View.GONE);//加载完网页进度条消失
                if (TextUtils.isEmpty(mtitle)) {
                    backtitle.setconter(view.getTitle());
                } else {
                    backtitle.setconter(mtitle);
                }
            } else {
                progressBar.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                progressBar.setProgress(newProgress);//设置进度值
            }
        }


    }

    /**
     * 根据url的scheme处理跳转第三方app的业务
     */
    public boolean shouldOverrideUrlLoadingByApp(WebView view, String url) {
        if (url.startsWith("http") || url.startsWith("https") || url.startsWith("ftp")) {
            //不处理http, https, ftp的请求
            return false;
        }
        try {
            Intent intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
            intent.setComponent(null);
            if (isInstall(intent)) {
                startActivity(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //判断app是否已安装
    public boolean isInstall(Intent intent) {
        return getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY).size() > 0;
    }

    @Override
    protected void onDestroy() {

        if (webView != null) {
            webView.destroy();
            webView.removeAllViews();
        }
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView.canGoBack()) {
                webView.goBack();
                return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 调用系统浏览器下载文件
     */
    private DownloadListener downloadListener = new DownloadListener() {
        @Override
        public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }
    };

    /**
     * 将cookie同步到WebView
     *
     * @param url
     * @param cookies
     */
    public void syncCookie(String url, String cookies) {
        Log.d(TAG, "syncCookie: " + cookies);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            CookieSyncManager.createInstance(this);
        }
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        //cookieManager.removeAllCookie(); //删除所有cookie 重新设置
        String[] split = cookies.split(";");
        for (String cookie : split) {
            Log.d(TAG, "syncCookie_for: " + cookie.trim());
            cookieManager.setCookie(url, cookie.trim());
        }
        CookieSyncManager.getInstance().sync();
    }

    /**
     * 浏览器设置
     *
     * @param webView
     */
    public void WebSettings(WebView webView) {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccess(true);              //是否允许访问文件
        settings.setDefaultTextEncodingName("UTF-8");
        settings.setSupportZoom(true);                  //支持缩放
        settings.setAppCacheEnabled(true);              //开启H5(APPCache)缓存功能
        settings.setDomStorageEnabled(true);            //开启 DOM storage 功能
        settings.setLoadWithOverviewMode(true);         //调整到适合webview大小
        settings.setUseWideViewPort(true);              //支持可任意比例缩放
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);//设置 缓存模式
        settings.setJavaScriptCanOpenWindowsAutomatically(true); //设置脚本是否允许自动打开弹窗
        settings.setDisplayZoomControls(false);         //是否展现在屏幕缩放控件上
        settings.setBuiltInZoomControls(false);         //启用WebView内置缩放功能
        settings.setDatabaseEnabled(true);              // 应用可以有数据库
        settings.setSaveFormData(true);                 // 保存表单数据
        settings.setGeolocationEnabled(true);           // 启用地理定位
        settings.setDefaultZoom(WebSettings.ZoomDensity.FAR);// 屏幕自适应网页,如果没有这个，在低分辨率的手机上显示可能会异常
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportMultipleWindows(true);               //app内的webview突然出现点击网页内的url无法跳转的bug true
        settings.setMediaPlaybackRequiresUserGesture(false);    //播放音频，多媒体需要用户手动
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH); //提高网页加载速度，暂时阻塞图片加载，然后网页加载好了，在进行加载图片

        //设置WebView JavaScript接口可以供页面JS调用 JSInvoker.xxx() 在js网页中调用
        webView.addJavascriptInterface(new JsInterface(context), "JSInvoker");
        webView.setHorizontalScrollBarEnabled(false);
        //加快HTML网页加载完成速度
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
            settings.setLoadsImagesAutomatically(false);
            settings.setBlockNetworkImage(true);
        }
        if (!Config.isNetworkAvailable()) {
            settings.setCacheMode(WebSettings.LOAD_CACHE_ONLY);     //不使用网络，只加载缓存
        }
    }

}
