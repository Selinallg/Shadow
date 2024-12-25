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

package com.nolovr.shadow.core.host.main;

import static com.nolovr.shadow.core.constant.Constant.PART_KEY_PLUGIN_BASE;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.nolovr.core.download.DownloadActivity;
import com.nolovr.shadow.core.constant.Constant;
import com.nolovr.shadow.core.host.PluginHelper;
import com.nolovr.shadow.core.host.PluginLoadActivity;
import com.nolovr.shadow.core.host.R;
import com.nolovr.shadow.core.host.dev.DevActivity;
import com.nolovr.shadow.core.host.plugin_view.HostAddPluginViewActivity;

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
                Constant.PART_KEY_PLUGIN_CONTENT_PROVIDER,
                Constant.PART_KEY_PLUGIN_CONTENT_OBSERVER,
                Constant.PART_KEY_PLUGIN_SO,
                Constant.PART_KEY_PLUGIN_P2HOST,
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
                    case Constant.PART_KEY_PLUGIN_CONTENT_PROVIDER:
                    case Constant.PART_KEY_PLUGIN_CONTENT_OBSERVER:
                    case Constant.PART_KEY_PLUGIN_SO:
                    case Constant.PART_KEY_PLUGIN_P2HOST:
                    case Constant.PART_KEY_PLUGIN_SERVICE:
                    case Constant.PART_KEY_PLUGIN_GS3D:
                        intent.putExtra(Constant.KEY_PLUGIN_PART_KEY, partKey);
                        break;
                }

                switch (partKey) {
                    case Constant.PART_KEY_PLUGIN_SERVICE:{
                        intent.putExtra(Constant.KEY_PLUGIN_ZIP_PATH, PluginHelper.getInstance().pluginZipFile.getAbsolutePath());
                        intent.putExtra(Constant.KEY_COMPONENT_CLASSNAME, Constant.ENTER_PART_KEY_PLUGIN_SERVICE);
                        break;
                    }
                    case Constant.PART_KEY_PLUGIN_DEMO:{
                        intent.putExtra(Constant.KEY_PLUGIN_ZIP_PATH, PluginHelper.getInstance().pluginZipFile.getAbsolutePath());
                        intent.putExtra(Constant.KEY_COMPONENT_CLASSNAME, Constant.ENTER_PART_KEY_PLUGIN_DEMO);
                        break;
                    }
                    case Constant.PART_KEY_PLUGIN_CONTENT_PROVIDER:{
                        intent.putExtra(Constant.KEY_PLUGIN_ZIP_PATH, PluginHelper.getInstance().plugin2ZipFile.getAbsolutePath());
                        intent.putExtra(Constant.KEY_COMPONENT_CLASSNAME, Constant.ENTER_PART_KEY_PLUGIN_CONTENT_PROVIDER);
                        break;
                    }

                    case Constant.PART_KEY_PLUGIN_CONTENT_OBSERVER:{
                        intent.putExtra(Constant.KEY_PLUGIN_ZIP_PATH, PluginHelper.getInstance().plugin2ZipFile.getAbsolutePath());
                        intent.putExtra(Constant.KEY_COMPONENT_CLASSNAME, Constant.ENTER_PART_KEY_PLUGIN_CONTENT_OBSERVER);
                        break;
                    }
                    case Constant.PART_KEY_PLUGIN_SO:{
                        intent.putExtra(Constant.KEY_PLUGIN_ZIP_PATH, PluginHelper.getInstance().plugin3ZipFile.getAbsolutePath());
                        intent.putExtra(Constant.KEY_COMPONENT_CLASSNAME, Constant.ENTER_PART_KEY_PLUGIN_SO);
                        break;
                    }case Constant.PART_KEY_PLUGIN_P2HOST:{
                        intent.putExtra(Constant.KEY_PLUGIN_ZIP_PATH, PluginHelper.getInstance().plugin3ZipFile.getAbsolutePath());
                        intent.putExtra(Constant.KEY_COMPONENT_CLASSNAME, Constant.ENTER_PART_KEY_PLUGIN_P2HOST);
                        break;
                    }
                    case Constant.PART_KEY_PLUGIN_GS3D:{
                        intent.putExtra(Constant.KEY_PLUGIN_ZIP_PATH, PluginHelper.getInstance().plugin2ZipFile.getAbsolutePath());
                        intent.putExtra(Constant.KEY_COMPONENT_CLASSNAME, Constant.ENTER_PART_KEY_PLUGIN_GS3D);
                        break;
                    }
                    //为了演示多进程多插件，其实两个插件内容完全一样，除了所在进程
                    case Constant.PART_KEY_PLUGIN_MAIN_APP:
                    case Constant.PART_KEY_PLUGIN_ANOTHER_APP:{
                        intent.putExtra(Constant.KEY_PLUGIN_ZIP_PATH, PluginHelper.getInstance().pluginZipFile.getAbsolutePath());
                        intent.putExtra(Constant.KEY_COMPONENT_CLASSNAME, Constant.ENTER_PART_KEY_PLUGIN_MAIN_APP);
                        break;
                    }
                }

                intent.putExtra(Constant.KEY_EXTRAS, new Bundle()); // 要传入到插件里的参数
                startActivity(intent);
            }
        });
        rootView.addView(startPluginButton);

        Button plugin2Button = new Button(this);
        plugin2Button.setText("宿主调试界面");
        plugin2Button.setOnClickListener(v -> {
            Intent intent = new Intent(this, DevActivity.class);
            startActivity(intent);
        });
        rootView.addView(plugin2Button);


        Button downloadButton = new Button(this);
        downloadButton.setText("插件下载");
        downloadButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, DownloadActivity.class);
            startActivity(intent);
//            Toast.makeText(this, "插件下载", Toast.LENGTH_SHORT).show();
        });
        rootView.addView(downloadButton);


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
