package com.vson.hybrid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.vson.common.autoservice.IWebViewService;
import com.vson.hybrid.utils.Constants;

import java.util.ServiceLoader;

/**
 * @author vson
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    public void gotoHybridWebView(View view) {
        IWebViewService webViewService = ServiceLoader.load(IWebViewService.class).iterator().next();
        if (webViewService != null) {
            webViewService.startWebViewActivity(this, (Constants.ANDROID_ASSET_URI + "demo.html"), "本地demo页面", true);
        }
    }
}