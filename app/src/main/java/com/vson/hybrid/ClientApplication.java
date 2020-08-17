package com.vson.hybrid;

import com.kingja.loadsir.core.LoadSir;
import com.vson.common.loadsir.CustomCallback;
import com.vson.common.loadsir.EmptyCallback;
import com.vson.common.loadsir.ErrorCallback;
import com.vson.common.loadsir.LoadingCallback;
import com.vson.common.loadsir.TimeoutCallback;
import com.vson.framwork.BaseApplication;


/**
 * @author vson
 */
public class ClientApplication extends BaseApplication {

    @Override
    public void onCreate(){
        super.onCreate();
        LoadSir.beginBuilder()
                .addCallback(new ErrorCallback())
                .addCallback(new EmptyCallback())
                .addCallback(new LoadingCallback())
                .addCallback(new TimeoutCallback())
                .addCallback(new CustomCallback())
                .setDefaultCallback(LoadingCallback.class)
                .commit();
    }
}
