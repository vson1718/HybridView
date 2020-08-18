package com.vson.hybrid.main;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

/**
 * @author vson
 * @date 2020/8/18
 * Company:上海动博士企业发展有限公司
 * E-mail :vson1718@163.com
 * 项目描述:
 */
public class MainProcessCommandService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return MainProcessCommandsManager.getsInstance();
    }
}
