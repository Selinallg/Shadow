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

package com.nolovr.shadow.core.host;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.nolovr.shadow.core.plugin.IMyAidlInterface;
import com.tencent.shadow.dynamic.host.EnterCallback;
import com.nolovr.shadow.core.constant.Constant;

/**
 * 加载插件
 */
public class PluginLoadActivity extends Activity {

    private static final String TAG = "_PluginLoadActivity";

    private ViewGroup mViewGroup;

    private Handler mHandler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        Log.d(TAG, "onCreate: ");
        mViewGroup = findViewById(R.id.container);

        Intent intent = getIntent();
        String partKey = intent.getStringExtra(Constant.KEY_PLUGIN_PART_KEY);
        if (Constant.PART_KEY_PLUGIN_GS3D.equals(partKey)||
                Constant.PART_KEY_PLUGIN_CONTENT_PROVIDER.equals(partKey)
        ) {
            // zip2 插件包
            start_plugin2();
        } else if (Constant.PART_KEY_PLUGIN_SERVICE.equals(partKey)) {

            // zip1 插件包
            startPlugin(Constant.FROM_ID_START_SERVICE);
        } else {
            // zip1 插件包
            startPlugin(Constant.FROM_ID_START_ACTIVITY);
        }

    }

    // zip1 插件包
    public void startPlugin(int fromid) {
        Log.d(TAG, "startPlugin: ");
        PluginHelper.getInstance().singlePool.execute(new Runnable() {
            @Override
            public void run() {
                // 先加载 插件管理apk
                HostApplication.getApp().loadPluginManager(PluginHelper.getInstance().pluginManagerFile);

                Bundle bundle = new Bundle();
                // 通用的插件包
                bundle.putString(Constant.KEY_COMMON_ZIP_PATH, PluginHelper.getInstance().pluginCommonZipFile.getAbsolutePath());

                bundle.putString(Constant.KEY_PLUGIN_ZIP_PATH, PluginHelper.getInstance().pluginZipFile.getAbsolutePath());
                bundle.putString(Constant.KEY_PLUGIN_PART_KEY, getIntent().getStringExtra(Constant.KEY_PLUGIN_PART_KEY));
                bundle.putString(Constant.KEY_COMPONENT_CLASSNAME, getIntent().getStringExtra(Constant.KEY_COMPONENT_CLASSNAME));

                bundle.putBundle(Constant.KEY_EXTRAS, getIntent().getBundleExtra(Constant.KEY_EXTRAS)); // 要传入到插件里的参数

                HostApplication.getApp().getPluginManager()
                        .enter(PluginLoadActivity.this, fromid, bundle, new EnterCallback() {
                            @Override
                            public void onShowLoadingView(final View view) {
                                Log.d("PluginLoad", "onShowLoadingView: ");
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        mViewGroup.addView(view);
                                    }
                                });
                            }

                            @Override
                            public void onCloseLoadingView() {
                                Log.d("PluginLoad", "onCloseLoadingView: ");
                                finish();
                            }

                            @Override
                            public void onEnterComplete() {
                                Log.d("PluginLoad", "onEnterComplete: ");

                            }

                            @Override
                            public void onServiceDisconnected(ComponentName name) {

                                Log.i(TAG, "onServiceDisconnected: ");

                            }

                            @Override
                            public void onServiceConnected(ComponentName name, IBinder service) {
                                IMyAidlInterface iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
                                try {
                                    String s = iMyAidlInterface.basicTypes(1, 2, true, 4.0f, 5.0, "6");
                                    Log.i(TAG, "iMyAidlInterface.basicTypes : " + s);
                                } catch (RemoteException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        });
            }
        });
    }

    // zip2 插件包
    public void start_plugin2() {

        Log.d(TAG, "start_plugin2: ");
        PluginHelper.getInstance().singlePool.execute(new Runnable() {
            @Override
            public void run() {
                HostApplication.getApp().loadPluginManager(PluginHelper.getInstance().pluginManagerFile);

                /**
                 * @param context context
                 * @param formId  标识本次请求的来源位置，用于区分入口
                 * @param bundle  参数列表, 建议在参数列表加入自己的验证
                 * @param callback 用于从PluginManager实现中返回View
                 */
                Bundle bundle = new Bundle();//插件 zip，这几个参数也都可以不传，直接在 PluginManager 中硬编码
                bundle.putString(Constant.KEY_COMMON_ZIP_PATH, PluginHelper.getInstance().pluginCommonZipFile.getAbsolutePath());

                bundle.putString(Constant.KEY_PLUGIN_ZIP_PATH, PluginHelper.getInstance().plugin2ZipFile.getAbsolutePath());
                bundle.putString(Constant.KEY_PLUGIN_PART_KEY, getIntent().getStringExtra(Constant.KEY_PLUGIN_PART_KEY));// partKey 每个插件都有自己的 partKey 用来区分多个插件，如何配置在下面讲到
                bundle.putString(Constant.KEY_COMPONENT_CLASSNAME, getIntent().getStringExtra(Constant.KEY_COMPONENT_CLASSNAME));//要启动的插件的Activity页面

                bundle.putBundle(Constant.KEY_EXTRAS, getIntent().getBundleExtra(Constant.KEY_EXTRAS)); // 要传入到插件里的参数

                HostApplication.getApp().getPluginManager().enter(PluginLoadActivity.this, Constant.FROM_ID_START_ACTIVITY, bundle, new EnterCallback() {
                    @Override
                    public void onShowLoadingView(View view) {
                        Log.e("PluginLoad", "onShowLoadingView");
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                mViewGroup.addView(view);
                            }
                        });
                    }

                    @Override
                    public void onCloseLoadingView() {
                        Log.e("PluginLoad", "onCloseLoadingView");
                        finish();
                    }

                    @Override
                    public void onEnterComplete() {
                        // 启动成功
                        Log.e("PluginLoad", "onEnterComplete");
                    }

                    @Override
                    public void onServiceDisconnected(ComponentName name) {

                    }

                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {

                    }
                });
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
        HostApplication.getApp().getPluginManager().enter(this, Constant.FROM_ID_CLOSE, null, null);
        mViewGroup.removeAllViews();
    }
}
