package com.vson.hybrid.process.settings;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.vson.hybrid.BuildConfig;

/**
 * @author vson
 */
public class WebViewDefaultSettings {
    private WebSettings mWebSettings;

    public static WebViewDefaultSettings getInstance() {
        return new WebViewDefaultSettings();
    }

    private WebViewDefaultSettings() {
    }

    private static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null) {
            boolean a = networkInfo.isConnected();
            return a;
        } else {
            return false;
        }
    }

    public void setSettings(WebView webView) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            WebView.enableSlowWholeDocumentDraw();
        }
        mWebSettings = webView.getSettings();
        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setSupportZoom(true);
        mWebSettings.setBuiltInZoomControls(false);
        if (isNetworkConnected(webView.getContext())) {
            mWebSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        } else {
            mWebSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mWebSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        // 硬件加速兼容性问题有点多
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

        mWebSettings.setTextZoom(100);
        mWebSettings.setDatabaseEnabled(true);
        mWebSettings.setAppCacheEnabled(true);
        mWebSettings.setLoadsImagesAutomatically(true);
        mWebSettings.setSupportMultipleWindows(false);
        //是否阻塞加载网络图片  协议http or https
        mWebSettings.setBlockNetworkImage(false);
        //允许加载本地文件html  file协议
        mWebSettings.setAllowFileAccess(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            //通过 file url 加载的 Javascript 读取其他的本地文件 .建议关闭
            mWebSettings.setAllowFileAccessFromFileURLs(false);
            //允许通过 file url 加载的 Javascript 可以访问其他的源，包括其他的文件和 http，https 等其他的源
            mWebSettings.setAllowUniversalAccessFromFileURLs(false);
        }
        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        } else {
            mWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        }
        mWebSettings.setSavePassword(false);
        mWebSettings.setSaveFormData(false);
        mWebSettings.setLoadWithOverviewMode(true);
        mWebSettings.setUseWideViewPort(true);
        mWebSettings.setDomStorageEnabled(true);
        mWebSettings.setNeedInitialFocus(true);
        //设置编码格式
        mWebSettings.setDefaultTextEncodingName("utf-8");
        mWebSettings.setDefaultFontSize(16);
        //设置 WebView 支持的最小字体大小，默认为 8
        mWebSettings.setMinimumFontSize(10);
        mWebSettings.setGeolocationEnabled(true);
        mWebSettings.setUseWideViewPort(true);

        String appCacheDir = webView.getContext().getDir("cache", Context.MODE_PRIVATE).getPath();
        Log.i("WebSetting", "WebView cache dir: " + appCacheDir);
        mWebSettings.setDatabasePath(appCacheDir);
        mWebSettings.setAppCachePath(appCacheDir);
        mWebSettings.setAppCacheMaxSize(1024 * 1024 * 80);

        // 用户可以自己设置useragent
        // mWebSettings.setUserAgentString("webprogress/build you agent info");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(BuildConfig.DEBUG);
        }
    }

}
