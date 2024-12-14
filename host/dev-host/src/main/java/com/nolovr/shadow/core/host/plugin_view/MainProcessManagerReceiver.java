package com.nolovr.shadow.core.host.plugin_view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.nolovr.shadow.core.host.HostApplication;
import com.tencent.shadow.dynamic.host.EnterCallback;
import com.nolovr.shadow.core.constant.Constant;

public class MainProcessManagerReceiver extends BroadcastReceiver {
    private static final String TAG = "MainProcessManagerReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive: ");
        HostApplication.getApp().getPluginManager()
                .enter(context, Constant.FROM_ID_LOAD_VIEW_TO_HOST, intent.getExtras(), new EnterCallback() {
                    @Override
                    public void onShowLoadingView(View view) {
                        Log.d(TAG, "onShowLoadingView: ");
                    }

                    @Override
                    public void onCloseLoadingView() {
                        Log.d(TAG, "onCloseLoadingView: ");
                    }

                    @Override
                    public void onEnterComplete() {
                        Log.d(TAG, "onEnterComplete: ");
                    }
                });
    }
}
