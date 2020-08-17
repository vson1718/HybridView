package com.vson.hybrid;

/**
 * @author vson
 */
public interface WebViewCallBack {
    /**
     * 加载开始
     *
     * @param url
     */
    void pageStarted(String url);

    /**
     * 加载结束
     *
     * @param url
     */
    void pageFinished(String url);

    /**
     * 加载失败
     */
    void onError();

    /**
     * 加载title
     *
     * @param title
     */
    void updateTitle(String title);
}
