package com.vson.hybrid.process;


import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.vson.hybrid.WebViewCallBack;
import com.vson.hybrid.bean.JsParam;
import com.vson.hybrid.process.settings.WebViewDefaultSettings;
import com.vson.hybrid.process.webchromeclient.WebChromeClient;
import com.vson.hybrid.process.webviewclient.WebViewClient;

/**
 * @author vson
 */
public class HybridWebView extends WebView {

    public static final String TAG = "HybridWebView";


    public HybridWebView(@NonNull Context context) {
        super(context);
        init();
    }

    public HybridWebView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HybridWebView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    public void init() {
//        WebViewProcessCommandDispatcher.getInstance().initAidlConnection();
        WebViewDefaultSettings.getInstance().setSettings(this);
        addJavascriptInterface(this, "hybrid");
    }

    public void registerWebViewCallBack(WebViewCallBack webViewCallBack) {
        setWebViewClient(new WebViewClient(webViewCallBack));
        setWebChromeClient(new WebChromeClient(webViewCallBack));
    }

    @JavascriptInterface
    public void takeNativeAction(final String jsParam) {
        Log.i(TAG, jsParam);
        if (!TextUtils.isEmpty(jsParam)) {
            final JsParam jsParamObject = new Gson().fromJson(jsParam, JsParam.class);
            if (jsParamObject != null) {
//                WebViewProcessCommandDispatcher.getInstance().executeCommand(jsParamObject.name, new Gson().toJson(jsParamObject.param), this);
            }
        }
    }

    public void handleCallback(final String callBackName, final String response) {
        if (!TextUtils.isEmpty(callBackName) && !TextUtils.isEmpty(response)) {
            post(() -> {
                String jsCode = "javascript:hybrid.callback('" + callBackName + "'," + response + ")";
                evaluateJavascript(jsCode, null);
            });
        }
    }
}
