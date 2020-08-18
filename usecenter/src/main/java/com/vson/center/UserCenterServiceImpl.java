package com.vson.center;

import android.content.Intent;

import com.google.auto.service.AutoService;
import com.vson.center.ui.login.LoginActivity;
import com.vson.common.autoservice.IUserCenterService;
import com.vson.framwork.BaseApplication;

/**
 * @author vson
 * 项目描述:
 */
@AutoService({IUserCenterService.class})
public class UserCenterServiceImpl implements IUserCenterService {

    @Override
    public boolean isLogin() {
        return false;
    }

    @Override
    public void login() {
        Intent intent = new Intent(BaseApplication.sApplication, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        BaseApplication.sApplication.startActivity(intent);
    }
}
