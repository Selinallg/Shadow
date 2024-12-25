package com.nolovr.core.download;

import android.app.Application;
import android.content.Context;

import com.arialyy.aria.core.Aria;

public class DownApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Aria.init(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
}
