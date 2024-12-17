package com.nolovr.shadow.core.cb;

import android.content.ComponentName;
import android.os.IBinder;

public class ServiceBindCallback {

    private static Callback sCallback;

    public static void setCallback(Callback callback) {
        sCallback = callback;
    }

    public static Callback getCallback() {
        return sCallback;
    }

    public interface Callback {
        void onServiceConnected(ComponentName name, IBinder service);

        void onServiceDisconnected(ComponentName name);
    }
}
