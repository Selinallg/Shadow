package com.nolovr.shadow.core.host.dev;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.nolovr.shadow.core.constant.Constant;
import com.nolovr.shadow.core.host.HostApplication;
import com.nolovr.shadow.core.host.PluginHelper;
import com.nolovr.shadow.core.host.R;
import com.tencent.shadow.dynamic.host.PluginManager;

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
        HostApplication.getApp().loadPluginManager(PluginHelper.getInstance().pluginManagerFile);
        PluginManager pluginManager = HostApplication.getApp().getPluginManager();
        pluginManager.enter(context, Constant.FROM_ID_QUERY_PLUGIN_INFO, null, null);

    }

    // manager 模块进行 SamplePluginManager
    public void installPlugin(View view) {

        // final PluginConfig pluginConfig = installPluginFromZip(new File(zip), hash);

//        InstalledPlugin installedPlugin = installPlugin(commonZipPath, null, true);//这个调用是阻塞的
//        loadPlugin(installedPlugin.UUID, Constant.PART_KEY_PLUGIN_COMMON);

    }

    // manager 模块进行 删除文件、删除数据库
    public void uninstallAllPlugin(View view) {
        HostApplication.getApp().loadPluginManager(PluginHelper.getInstance().pluginManagerFile);
        PluginManager pluginManager = HostApplication.getApp().getPluginManager();
        pluginManager.enter(context, Constant.FROM_ID_DEL_ALL_PLUGIN, null, null);
    }

    // 删除文件、不删除数据库
    public void uninstallAllPluginPart(View view) {
        HostApplication.getApp().loadPluginManager(PluginHelper.getInstance().pluginManagerFile);
        PluginManager pluginManager = HostApplication.getApp().getPluginManager();
        pluginManager.enter(context, Constant.FROM_ID_DEL_ALL_PLUGINPART, null, null);
    }

    // 删除文件、不删除数据库
    public void uninstallPluginPart(View view) {
        HostApplication.getApp().loadPluginManager(PluginHelper.getInstance().pluginManagerFile);
        PluginManager pluginManager = HostApplication.getApp().getPluginManager();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.KEY_PLUGIN_PART_KEY, Constant.PART_KEY_PLUGIN_SERVICE);
        pluginManager.enter(context, Constant.FROM_ID_DEL_PART_KEY_PLUGINPART, bundle, null);
    }
}
