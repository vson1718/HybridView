package com.vson.hybrid.command;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.google.auto.service.AutoService;
import com.vson.framwork.BaseApplication;
import com.vson.hybrid.ICallbackFromMainprocessToWebViewProcessInterface;

import java.util.Map;

/**
 * @author vson
 * @date 2020/8/18
 * Company:上海动博士企业发展有限公司
 * E-mail :vson1718@163.com
 * 项目描述:
 */
@AutoService({Command.class})
public class CommandShowToast implements Command {
    @Override
    public String name() {
        return "showToast";
    }

    @Override
    public void execute(Map parameters, ICallbackFromMainprocessToWebViewProcessInterface callback) {
        new Handler(Looper.getMainLooper()).post(() -> {
            Toast.makeText(BaseApplication.sApplication, String.valueOf(parameters.get("message")), Toast.LENGTH_LONG).show();
        });
    }
}
