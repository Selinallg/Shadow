package com.nolovr.shadow.core.sample.plugin;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.nolovr.shadow.core.host.lib.HostEngineProvider;
import com.nolovr.shadow.core.host.lib.RequestCallback;
import com.nolovr.shadow.core.plugin.IMyAidlInterface;

import org.json.JSONException;
import org.json.JSONObject;


public class ServiceMainActivity extends AppCompatActivity {
    private static final String TAG = "_ServiceMainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_service);
//        HostEngineProvider.getInstance().init(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void startService(View view) {
        ComponentName componentName = startService(new Intent(this, MyService.class));
        Log.i(TAG, "startService: " + componentName);
    }
    public void bindservice(View view) {
        boolean ret = this.bindService(new Intent(this, MyService.class), new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder iBinder) {
                Log.i(TAG, "onServiceConnected: ");
                IMyAidlInterface iMyAidlInterface = IMyAidlInterface.Stub.asInterface(iBinder);
                try {
                    String s = iMyAidlInterface.basicTypes(1, 2, true, 4.0f, 5.0, "6");
                    Log.i(TAG, "iMyAidlInterface.basicTypes : " + s);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.i(TAG, "onServiceDisconnected: ");

            }
        }, BIND_AUTO_CREATE);

        Log.i(TAG, "bindservice: " + ret);
    }

    public void syncMethod(View view) {
        try {
            JSONObject root = new JSONObject();
            root.put("key1", "value1");
            root.put("key2", "value2");
            String resultJson = HostEngineProvider.getInstance().action(root.toString());
            Log.d(TAG, "syncMethod: resultJson=" + resultJson);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }


    public void asyncMethod(View view) {
        try {
            JSONObject root = new JSONObject();
            root.put("key1", "value1");
            root.put("key2", "value2");
            HostEngineProvider.getInstance().request(root.toString(), new RequestCallback() {
                @Override
                public void onSuccess(Object result) {
                    Log.d(TAG, "onSuccess: resultJson=" + result.toString());
                }

                @Override
                public void onFailure(Object info) {
                    Log.d(TAG, "onFailure: resultJson=" + info.toString());

                }
            });

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}