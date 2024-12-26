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

import android.content.Context;
import android.util.Log;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PluginHelper {

    private static final String TAG = "PluginHelper";
    /**
     * 插件包的根目录名称
     */
    public final static String pluginsName = "nolo-plugins";

    /**
     * 动态加载的插件管理apk
     */
    public final static String sPluginManagerName = "pluginmanager.apk";

    /**
     * 动态加载的插件包，里面包含以下几个部分，插件apk，插件框架apk（loader apk和runtime apk）, apk信息配置关系json文件
     */
    // pluginCommon-debug.zip" 包含 loader.apk 和 runtime.apk 以及一些通用的依赖库
    public final static String sPluginCommonZip = BuildConfig.DEBUG ? "pluginCommon-debug.zip" : "pluginCommon-release.zip";
    public final static String sPluginZip = BuildConfig.DEBUG ? "plugin-debug.zip" : "plugin-release.zip";
    public final static String s2PluginZip = BuildConfig.DEBUG ? "plugin2-debug.zip" : "plugin2-release.zip";
    public final static String s3PluginZip = BuildConfig.DEBUG ? "pluginSo-debug.zip" : "pluginSo-release.zip";

    /**
     * 插件包的根目录
     */
    private File pluginRoot;

    public File pluginManagerFile;

    public File pluginCommonZipFile;
    public File pluginZipFile;
    public File plugin2ZipFile;
    public File plugin3ZipFile;

    public ExecutorService singlePool = Executors.newSingleThreadExecutor();

    private Context mContext;

    private static PluginHelper sInstance = new PluginHelper();

    public static PluginHelper getInstance() {
        return sInstance;
    }

    private PluginHelper() {

    }

    public void init(Context context) {

        pluginRoot = new File(context.getFilesDir(), pluginsName);
        if (!pluginRoot.exists()) {
            pluginRoot.mkdirs();
        }

        // manager
        pluginManagerFile = new File(pluginRoot, sPluginManagerName);
        // 插件包 common
        pluginCommonZipFile = new File(pluginRoot, sPluginCommonZip);
        // 插件包 1
        pluginZipFile = new File(pluginRoot, sPluginZip);
        // 插件包 2
        plugin2ZipFile = new File(pluginRoot, s2PluginZip);
        // 插件包 3
        plugin3ZipFile = new File(pluginRoot, s3PluginZip);

        mContext = context.getApplicationContext();

        singlePool.execute(new Runnable() {
            @Override
            public void run() {
                preparePlugin();
            }
        });

    }

    public File getPluginRoot() {
        return pluginRoot;
    }

    // pluginManagerFile=/data/user/0/com.tencent.shadow.sample.host/files/pluginmanager.apk
    // pluginCommonZipFile=/data/user/0/com.tencent.shadow.sample.host/files/pluginCommon-debug.zip
    // pluginZipFile=/data/user/0/com.tencent.shadow.sample.host/files/plugin-debug.zip
    private void preparePlugin() {
        // TODO: 2024-12-13 后期可以从网络上下载动态加载的插件包，然后解压到本地，然后再加载
        //  动态加载的插件包 plugin-debug.zip
        try {
            //  动态加载的插件管理apk pluginmanager.apk
            InputStream is = mContext.getAssets().open(sPluginManagerName);
            FileUtils.copyInputStreamToFile(is, pluginManagerFile);
            Log.d(TAG, "preparePlugin: pluginManagerFile=" + pluginManagerFile.getAbsolutePath());

            //  动态加载common的插件包 pluginCommon-debug.zip
            InputStream commonZip = mContext.getAssets().open(sPluginCommonZip);
            FileUtils.copyInputStreamToFile(commonZip, pluginCommonZipFile);
            Log.d(TAG, "preparePlugin: pluginCommonZipFile=" + pluginCommonZipFile.getAbsolutePath());


            //  动态加载的业务插件包 plugin2-debug.zip
            InputStream zip = mContext.getAssets().open(sPluginZip);
            FileUtils.copyInputStreamToFile(zip, pluginZipFile);
            Log.d(TAG, "preparePlugin: pluginZipFile=" + pluginZipFile.getAbsolutePath());

            //  动态加载的业务插件包 plugin2-debug.zip
            InputStream zip2 = mContext.getAssets().open(s2PluginZip);
            FileUtils.copyInputStreamToFile(zip2, plugin2ZipFile);
            Log.d(TAG, "preparePlugin: plugin2ZipFile=" + plugin2ZipFile.getAbsolutePath());

            //  动态加载的业务插件包 plugin2-debug.zip
            InputStream zip3 = mContext.getAssets().open(s3PluginZip);
            FileUtils.copyInputStreamToFile(zip3, plugin3ZipFile);
            Log.d(TAG, "preparePlugin: plugin3ZipFile=" + plugin3ZipFile.getAbsolutePath());


        } catch (IOException e) {
            throw new RuntimeException("从assets中复制apk出错", e);
        }
    }


}
