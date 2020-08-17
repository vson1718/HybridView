package com.vson.hybrid;

import android.content.Context;
import android.content.Intent;

import com.vson.hybrid.utils.Constants;
import com.google.auto.service.AutoService;
import com.vson.common.autoservice.IWebViewService;


/**
 * @author vson
 * 项目描述:
 */
@AutoService({IWebViewService.class})
public class WebViewServiceImpl implements IWebViewService {

    @Override
    public void startWebViewActivity(Context context, String url, String title, boolean isShowActionBar) {
        if (context != null) {
            Intent intent = new Intent(context, WebViewActivity.class);
            intent.putExtra(Constants.TITLE, title);
            intent.putExtra(Constants.URL, url);
            intent.putExtra(Constants.IS_SHOW_ACTION_BAR, isShowActionBar);
            context.startActivity(intent);
        }
    }
}
