package com.nolovr.shadow.core.plugin.app.lib.usecases.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;

import com.nolovr.shadow.core.host.lib.HostAddPluginViewContainer;
import com.nolovr.shadow.core.host.lib.HostAddPluginViewContainerHolder;
import com.nolovr.shadow.core.plugin.app.lib.R;

public class HostAddPluginViewService extends IntentService {
    private final Handler uiHandler = new Handler(Looper.getMainLooper());

    public HostAddPluginViewService() {
        super("HostAddPluginViewService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        int id = intent.getIntExtra("id", 0);
        HostAddPluginViewContainer viewContainer
                = HostAddPluginViewContainerHolder.instances.remove(id);

        uiHandler.post(() -> {
            View view = LayoutInflater.from(this).inflate(
                    R.layout.layout_host_add_plugin_view, null, false);
            viewContainer.addView(view);
        });
    }
}
