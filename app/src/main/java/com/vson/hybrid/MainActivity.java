package com.vson.hybrid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.vson.common.autoservice.IWebViewService;

import java.util.ServiceLoader;

/**
 * @author vson
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void gotoHybridWebView(View view) {
        IWebViewService webViewService =  ServiceLoader.load(IWebViewService.class).iterator().next();
        if (webViewService!=null){
            webViewService.startWebViewActivity(this,"http://www.baidu.com","百度",true);
        }
    }
}