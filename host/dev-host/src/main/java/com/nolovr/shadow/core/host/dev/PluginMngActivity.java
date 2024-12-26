package com.nolovr.shadow.core.host.dev;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.nolovr.shadow.core.host.PluginHelper;
import com.nolovr.shadow.core.host.R;

import java.io.File;

public class PluginMngActivity extends Activity {

    private static final String TAG = "_PluginMngActivity";
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plugin_mng);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void checkPluginList(View view) {
        File pluginRoot = PluginHelper.getInstance().getPluginRoot();
        if (pluginRoot.exists() && pluginRoot.isDirectory() && pluginRoot.listFiles() != null) {
            for (File file : pluginRoot.listFiles()) {
                Log.d(TAG, "checkPluginList: " + file.getAbsolutePath());
            }
        }

    }
    public void installPlugin(View view) {

    }
    public void uninstallPlugin(View view) {

    }
}
