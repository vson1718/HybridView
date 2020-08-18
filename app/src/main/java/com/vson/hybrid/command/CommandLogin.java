package com.vson.hybrid.command;

import android.os.RemoteException;
import android.util.Log;

import com.google.auto.service.AutoService;
import com.google.gson.Gson;
import com.vson.center.UserCenterServiceImpl;
import com.vson.common.autoservice.IUserCenterService;
import com.vson.common.eventbus.LoginEvent;
import com.vson.framwork.autoservice.CommonServiceLoader;
import com.vson.hybrid.ICallbackFromMainprocessToWebViewProcessInterface;

import org.checkerframework.checker.units.qual.C;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;

/**
 * @author vson
 * @date 2020/8/18
 * Company:上海动博士企业发展有限公司
 * E-mail :vson1718@163.com 项目描述:
 */
@AutoService({Command.class})
public class CommandLogin implements Command {

    private IUserCenterService iUserCenterService = CommonServiceLoader.load(IUserCenterService.class);
    private ICallbackFromMainprocessToWebViewProcessInterface callback;
    private String callBackNameFromNativeJs;

    public CommandLogin() {
        EventBus.getDefault().register(this);
    }

    @Override
    public String name() {
        return "login";
    }

    @Override
    public void execute(Map parameters, ICallbackFromMainprocessToWebViewProcessInterface callback) {
        Log.d("CommandLogin", new Gson().toJson(parameters));
        if (iUserCenterService != null && !iUserCenterService.isLogin()) {
            iUserCenterService.login();
            this.callback = callback;
            this.callBackNameFromNativeJs = parameters.get("callBackName").toString();
        }
    }

    @Subscribe
    public void handleLoginEvent(LoginEvent loginEvent) {
        HashMap<String, String> hashMap = new HashMap(1);
        hashMap.put("accountName", loginEvent.userName);
        if (callback != null) {
            try {
                callback.onResult(callBackNameFromNativeJs, new Gson().toJson(hashMap));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

}
