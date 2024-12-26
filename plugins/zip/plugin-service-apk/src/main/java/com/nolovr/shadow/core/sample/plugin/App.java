package com.nolovr.shadow.core.sample.plugin;

import android.app.Application;
import android.content.Context;


public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
       // HostEngineProvider.getInstance().init(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
}
