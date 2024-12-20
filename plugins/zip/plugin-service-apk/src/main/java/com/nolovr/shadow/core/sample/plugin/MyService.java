package com.nolovr.shadow.core.sample.plugin;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.nolovr.shadow.core.plugin.IMyAidlInterface;

public class MyService extends Service {
    private static final String TAG = "_MyService";
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: ");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind: ");

        Intent goIntent = new Intent(this, ServiceMainActivity.class);
        goIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(goIntent);



        return new IMyAidlInterface.Stub() {
            @Override
            public String basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
                return Integer.toString(anInt) + aLong + aBoolean + aFloat + aDouble + aString;
            }
        };
    }
}
