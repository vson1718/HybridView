package com.vson.hybrid.process;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import com.vson.framwork.BaseApplication;
import com.vson.hybrid.ICallbackFromMainprocessToWebViewProcessInterface;
import com.vson.hybrid.IWebViewProcessToMainProcessInterface;
import com.vson.hybrid.main.MainProcessCommandService;

/**
 * @author vson
 * 分发指令
 */
public class WebViewCommandDispatcher implements ServiceConnection {

    private static volatile WebViewCommandDispatcher sInstance;
    private IWebViewProcessToMainProcessInterface iWebViewProcessToMainProcessInterface;

    public static WebViewCommandDispatcher getInstance() {
        if (sInstance == null) {
            synchronized (WebViewCommandDispatcher.class) {
                if (sInstance == null) {
                    sInstance = new WebViewCommandDispatcher();
                }
            }
        }
        return sInstance;
    }


    public void initAidlConnection() {
        Intent intent = new Intent(BaseApplication.sApplication, MainProcessCommandService.class);
        BaseApplication.sApplication.bindService(intent, this, Context.BIND_AUTO_CREATE);
    }


    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        iWebViewProcessToMainProcessInterface = IWebViewProcessToMainProcessInterface.Stub.asInterface(iBinder);
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        iWebViewProcessToMainProcessInterface = null;
        initAidlConnection();
    }

    @Override
    public void onBindingDied(ComponentName name) {
        iWebViewProcessToMainProcessInterface = null;
        initAidlConnection();
    }


    public void executeCommand(String commandName, String params, final HybridWebView hybridWebView) {
        if (iWebViewProcessToMainProcessInterface != null) {
            try {
                iWebViewProcessToMainProcessInterface.handleWebCommand(commandName, params, new ICallbackFromMainprocessToWebViewProcessInterface.Stub() {
                    @Override
                    public void onResult(String callBackName, String response) {
                        hybridWebView.handleCallback(callBackName, response);
                    }
                });
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }
    }
}
