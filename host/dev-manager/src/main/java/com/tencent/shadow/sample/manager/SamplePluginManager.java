/*
 * Tencent is pleased to support the open source community by making Tencent Shadow available.
 * Copyright (C) 2019 THL A29 Limited, a Tencent company.  All rights reserved.
 *
 * Licensed under the BSD 3-Clause License (the "License"); you may not use
 * this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 *     https://opensource.org/licenses/BSD-3-Clause
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.tencent.shadow.sample.manager;

import static com.nolovr.shadow.core.constant.Constant.PART_KEY_PLUGIN_ANOTHER_APP;
import static com.nolovr.shadow.core.constant.Constant.PART_KEY_PLUGIN_BASE;
import static com.nolovr.shadow.core.constant.Constant.PART_KEY_PLUGIN_MAIN_APP;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.nolovr.shadow.sample.plugin.IMyAidlInterface;
import com.tencent.shadow.core.common.Logger;
import com.tencent.shadow.core.common.LoggerFactory;
import com.tencent.shadow.core.manager.installplugin.InstalledPlugin;
import com.tencent.shadow.dynamic.host.EnterCallback;
import com.nolovr.shadow.core.constant.Constant;
import com.tencent.shadow.dynamic.host.FailedException;
import com.tencent.shadow.dynamic.loader.PluginServiceConnection;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;


public class SamplePluginManager extends FastPluginManager {

    Logger mLogger = LoggerFactory.getLogger(SamplePluginManager.class);

    private static final String TAG = "SamplePluginManager";
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    private Context mCurrentContext;

    public SamplePluginManager(Context context) {
        super(context);
        mCurrentContext = context;
    }

    /**
     * @return PluginManager实现的别名，用于区分不同PluginManager实现的数据存储路径
     */
    @Override
    protected String getName() {
        return "nolo-dynamic-manager";
    }

    /**
     * @return 宿主中注册的PluginProcessService实现的类名
     */
    @Override
    protected String getPluginProcessServiceName(String partKey) {
        mLogger.info("getPluginProcessServiceName partKey=" + partKey + "");
        if (PART_KEY_PLUGIN_MAIN_APP.equals(partKey)||
                Constant.PART_KEY_PLUGIN_COMMON.equals(partKey)||
                Constant.PART_KEY_PLUGIN_SERVICE.equals(partKey)||
                Constant.PART_KEY_PLUGIN_DEMO.equals(partKey)) {
            return "com.nolovr.shadow.core.host.PluginProcessPPS";
        }
        if (Constant.PART_KEY_PLUGIN_GS3D.equals(partKey)) {
            return "com.nolovr.shadow.core.host.Plugin3ProcessPPS";
        } else if (PART_KEY_PLUGIN_BASE.equals(partKey)) {
            return "com.nolovr.shadow.core.host.PluginProcessPPS";
        } else if (PART_KEY_PLUGIN_ANOTHER_APP.equals(partKey)) {
            return "com.nolovr.shadow.core.host.Plugin2ProcessPPS";//在这里支持多个插件
        } else {
            //如果有默认PPS，可用return代替throw
            throw new IllegalArgumentException("unexpected plugin load request: " + partKey);
        }
    }

    @Override
    public void enter(final Context context, long fromId, Bundle bundle, final EnterCallback callback) {
        if (fromId == Constant.FROM_ID_NOOP) {
            //do nothing.
        } else if (fromId == Constant.FROM_ID_START_ACTIVITY) {
            onStartActivity(context, bundle, callback);
        } else if (fromId == Constant.FROM_ID_START_SERVICE) {
            callPluginService(context, bundle, callback);
        } else if (fromId == Constant.FROM_ID_CLOSE) {
            close();
        } else if (fromId == Constant.FROM_ID_LOAD_VIEW_TO_HOST) {
            loadViewToHost(context, bundle);
        } else {
            throw new IllegalArgumentException("不认识的fromId==" + fromId);
        }
    }

    private void loadViewToHost(final Context context, Bundle bundle) {
        Intent pluginIntent = new Intent();
        pluginIntent.setClassName(
                context.getPackageName(),
                "com.tencent.shadow.sample.plugin.app.lib.usecases.service.HostAddPluginViewService"
        );
        pluginIntent.putExtras(bundle);
        try {
            mPluginLoader.startPluginService(pluginIntent);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    private void onStartActivity(final Context context, Bundle bundle, final EnterCallback callback) {

        final String pluginZipPath = bundle.getString(Constant.KEY_PLUGIN_ZIP_PATH);
        final String partKey = bundle.getString(Constant.KEY_PLUGIN_PART_KEY);
        final String className = bundle.getString(Constant.KEY_COMPONENT_CLASSNAME);
        final String commonZipPath = bundle.getString(Constant.KEY_COMMON_ZIP_PATH);
        Log.i(TAG, "onStartActivity: commonZipPath="+commonZipPath);
        Log.d(TAG, "onStartActivity: pluginZipPath=" + pluginZipPath + ", partKey=" + partKey + ", className=" + className);
        if (className == null) {
            throw new NullPointerException("className == null");
        }
        final Bundle extras = bundle.getBundle(Constant.KEY_EXTRAS);

        if (callback != null) {
            final View view = LayoutInflater.from(mCurrentContext).inflate(R.layout.activity_load_plugin, null);
            callback.onShowLoadingView(view);
        }

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {



                    List<InstalledPlugin> installedPlugins = getInstalledPlugins(100);
                    Log.i(TAG, "run: size:" + installedPlugins.size());
                    for (InstalledPlugin plugin : installedPlugins) {
                        Log.d(TAG, "for run: " + plugin.plugins.size() + " " + plugin.UUID + " " + plugin.UUID_NickName);
//                        boolean ret = deleteInstalledPlugin(plugin.UUID);
//                        Log.d(TAG, "for run: ret="+ret);
                    }

                    installCommon(commonZipPath);

                    InstalledPlugin installedPlugin = installPlugin(pluginZipPath, null, true);

                    Log.d(TAG, "run: " + installedPlugin.plugins.size() + " " + installedPlugin.UUID + " " + installedPlugin.UUID_NickName);

                    if (!partKey.equals(Constant.PART_KEY_PLUGIN_GS3D)) {
                        loadPlugin(installedPlugin.UUID, PART_KEY_PLUGIN_BASE);
                        loadPlugin(installedPlugin.UUID, PART_KEY_PLUGIN_MAIN_APP);

                        callApplicationOnCreate(PART_KEY_PLUGIN_BASE);
                        callApplicationOnCreate(PART_KEY_PLUGIN_MAIN_APP);

                        loadPlugin(installedPlugin.UUID, Constant.PART_KEY_PLUGIN_DEMO);
                        callApplicationOnCreate(Constant.PART_KEY_PLUGIN_DEMO);

                    } else {
                        loadPlugin(installedPlugin.UUID, Constant.PART_KEY_PLUGIN_GS3D);
                        callApplicationOnCreate(Constant.PART_KEY_PLUGIN_GS3D);
                        Log.d(TAG, "run: loadPlugin gs3d");
                    }

                    Intent pluginIntent = new Intent();
                    pluginIntent.setClassName(
                            context.getPackageName(),
                            className
                    );
                    if (extras != null) {
                        pluginIntent.replaceExtras(extras);
                    }
                    Intent intent = mPluginLoader.convertActivityIntent(pluginIntent);
                    Log.d(TAG, "run: intent=" + intent);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mPluginLoader.startActivityInPluginProcess(intent);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                if (callback != null) {
                    callback.onCloseLoadingView();
                }
            }
        });
    }


    private void callPluginService(final Context context, Bundle bundle, final EnterCallback callback) {
//        final String pluginZipPath = "/data/local/tmp/plugin-debug.zip";
//        final String partKey = "sample-plugin";
//        final String className = "com.tencent.shadow.sample.plugin.MyService";

        final String pluginZipPath = bundle.getString(Constant.KEY_PLUGIN_ZIP_PATH);
        final String partKey = bundle.getString(Constant.KEY_PLUGIN_PART_KEY);
        final String className = bundle.getString(Constant.KEY_COMPONENT_CLASSNAME);
        final String commonZipPath = bundle.getString(Constant.KEY_COMMON_ZIP_PATH);
        Log.i(TAG, "callPluginService: commonZipPath="+commonZipPath);
        Log.d(TAG, "callPluginService: pluginZipPath=" + pluginZipPath + ", partKey=" + partKey + ", className=" + className);


//        Intent pluginIntent = new Intent();
//        pluginIntent.setClassName(context.getPackageName(), className);

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {



                    installCommon(commonZipPath);


                    InstalledPlugin installedPlugin
                            = installPlugin(pluginZipPath, null, true);//这个调用是阻塞的

                    loadPlugin(installedPlugin.UUID, partKey);

                    Intent pluginIntent = new Intent();
                    pluginIntent.setClassName(context.getPackageName(), className);

                    ComponentName componentName = (ComponentName) mPluginLoader.startPluginService(pluginIntent);
                    Log.i(TAG, "run: startPluginService componentName=" + componentName);

                    boolean callSuccess = mPluginLoader.bindPluginService(pluginIntent, new PluginServiceConnection() {
                        @Override
                        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                            // TODO: 2024-12-17 回调到业务层，解耦
                            IMyAidlInterface iMyAidlInterface = IMyAidlInterface.Stub.asInterface(iBinder);
                            try {
                                String s = iMyAidlInterface.basicTypes(1, 2, true, 4.0f, 5.0, "6");
                                Log.i(TAG, "iMyAidlInterface.basicTypes : " + s);
                            } catch (RemoteException e) {
                                throw new RuntimeException(e);
                            }
                        }

                        @Override
                        public void onServiceDisconnected(ComponentName componentName) {
                            throw new RuntimeException("onServiceDisconnected");
                        }
                    }, Service.BIND_AUTO_CREATE);

                    Log.e(TAG, "run: bindPluginService "+callSuccess);

                    if (!callSuccess) {
                        throw new RuntimeException("bind service失败 className==" + className);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }



    // 加载通用模块
    private void installCommon(String commonZipPath) throws JSONException, IOException, ExecutionException, InterruptedException, FailedException, RemoteException, TimeoutException {
        InstalledPlugin installedPlugin = installPlugin(commonZipPath, null, true);//这个调用是阻塞的
        loadPlugin(installedPlugin.UUID, Constant.PART_KEY_PLUGIN_COMMON);
    }
}
