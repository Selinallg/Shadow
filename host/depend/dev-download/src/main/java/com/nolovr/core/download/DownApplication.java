package com.nolovr.core.download;

import android.app.Application;
import android.content.Context;

import com.arialyy.aria.core.Aria;

public class DownApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Aria.init(this);
        // 修改配置参数
        //Aria.get(pContext).getDownloadConfig().setMaxTaskNum(3);
        Aria.get(this).getDownloadConfig().setConvertSpeed(true);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
}
