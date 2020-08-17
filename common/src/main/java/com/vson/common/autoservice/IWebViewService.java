package com.vson.common.autoservice;

import android.content.Context;

/**
 * @author vson
 */
public interface IWebViewService {

    /**
     * 启动WebView Activity
     *
     * @param context
     * @param url
     * @param title
     * @param isShowActionBar
     */
    void startWebViewActivity(Context context, String url, String title, boolean isShowActionBar);


}
