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

import static com.nolovr.shadow.core.constant.Constant.PART_KEY_PLUGIN_BASE;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.nolovr.shadow.core.cb.ServiceBindCallback;
import com.nolovr.shadow.core.constant.Constant;
import com.nolovr.shadow.core.host.plugin_view.HostAddPluginViewActivity;
import com.nolovr.shadow.sample.plugin.IMyAidlInterface;

/**
 * 宿主应用的入口
 */
public class MainActivity extends Activity {

    private static final String TAG = "_MainActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.TestHostTheme);
        LinearLayout rootView = new LinearLayout(this);
        rootView.setOrientation(LinearLayout.VERTICAL);

        TextView infoTextView = new TextView(this);
        infoTextView.setText(R.string.main_activity_info);
        rootView.addView(infoTextView);


        // 两个测试 插件app
        final Spinner partKeySpinner = new Spinner(this);
        ArrayAdapter<String> partKeysAdapter = new ArrayAdapter<>(this, R.layout.part_key_adapter);
        partKeysAdapter.addAll(
                Constant.PART_KEY_PLUGIN_MAIN_APP,
                Constant.PART_KEY_PLUGIN_ANOTHER_APP,
                Constant.PART_KEY_PLUGIN_DEMO,
                Constant.PART_KEY_PLUGIN_SERVICE,
                Constant.PART_KEY_PLUGIN_GS3D
        );
        partKeySpinner.setAdapter(partKeysAdapter);
        rootView.addView(partKeySpinner);

        Button startPluginButton = new Button(this);
        startPluginButton.setText(R.string.start_plugin);
        startPluginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String partKey = (String) partKeySpinner.getSelectedItem();
                Intent intent = new Intent(MainActivity.this, PluginLoadActivity.class);
                switch (partKey) {
                    //为了演示多进程多插件，其实两个插件内容完全一样，除了所在进程
                    case Constant.PART_KEY_PLUGIN_MAIN_APP:
                        intent.putExtra(Constant.KEY_PLUGIN_PART_KEY, PART_KEY_PLUGIN_BASE);
                        break;
                    case Constant.PART_KEY_PLUGIN_ANOTHER_APP:
                    case Constant.PART_KEY_PLUGIN_DEMO:
                    case Constant.PART_KEY_PLUGIN_SERVICE:
                    case Constant.PART_KEY_PLUGIN_GS3D:
                        intent.putExtra(Constant.KEY_PLUGIN_PART_KEY, partKey);
                        break;
                }

                switch (partKey) {
                    case Constant.PART_KEY_PLUGIN_SERVICE:{
                        intent.putExtra(Constant.KEY_COMPONENT_CLASSNAME, "com.nolovr.shadow.sample.plugin.MyService");
                        break;
                    }
                    case Constant.PART_KEY_PLUGIN_DEMO:{
                        intent.putExtra(Constant.KEY_COMPONENT_CLASSNAME, "com.test.plugin_demo.SplashActivity");
                        break;
                    }
                    case Constant.PART_KEY_PLUGIN_GS3D:{
                        intent.putExtra(Constant.KEY_COMPONENT_CLASSNAME, "com.test.plugin_other.SplashActivity");
                        break;
                    }
                    //为了演示多进程多插件，其实两个插件内容完全一样，除了所在进程
                    case Constant.PART_KEY_PLUGIN_MAIN_APP:
                    case Constant.PART_KEY_PLUGIN_ANOTHER_APP:{
                        intent.putExtra(Constant.KEY_COMPONENT_CLASSNAME, "com.nolovr.shadow.core.plugin.app.lib.gallery.splash.SplashActivity");
                        break;
                    }

                }
                startActivity(intent);
            }
        });
        rootView.addView(startPluginButton);

//        Button plugin2Button = new Button(this);
//        plugin2Button.setText("启动插件包2");
//        plugin2Button.setOnClickListener(v -> {
//            start_plugin2();
//        });
//        rootView.addView(plugin2Button);


        Button startHostAddPluginViewActivityButton = new Button(this);
        startHostAddPluginViewActivityButton.setText("宿主添加插件View");
        startHostAddPluginViewActivityButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, HostAddPluginViewActivity.class);
            startActivity(intent);
        });
        rootView.addView(startHostAddPluginViewActivityButton);

        setContentView(rootView);

    }





}
