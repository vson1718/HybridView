package com.vson.hybrid.process.webchromeclient;

import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.WebView;

import com.vson.hybrid.WebViewCallBack;


/**
 * @author vson
 */
public class WebChromeClient extends android.webkit.WebChromeClient {
    private WebViewCallBack mWebViewCallBack;
    private static final String TAG = "WebChromeClient";

    public WebChromeClient(WebViewCallBack webViewCallBack) {
        this.mWebViewCallBack = webViewCallBack;
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        if (mWebViewCallBack != null) {
            mWebViewCallBack.updateTitle(title);
        } else {
            Log.e(TAG, "WebViewCallBack is null.");
        }
    }

    /**
     * Report a JavaScript console message to the host application. The ChromeClient
     * should override this to process the log message as they see fit.
     *
     * @param consoleMessage Object containing details of the console message.
     * @return {@code true} if the message is handled by the client.
     */
    @Override
    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        // Call the old version of this function for backwards compatability.
        Log.d(TAG, consoleMessage.message());
        return super.onConsoleMessage(consoleMessage);
    }

}
