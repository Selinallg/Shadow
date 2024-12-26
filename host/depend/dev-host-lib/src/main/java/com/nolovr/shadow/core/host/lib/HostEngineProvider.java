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

package com.nolovr.shadow.core.host.lib;

import static android.os.Process.myPid;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 这是一个打包到宿主中的类。插件调用宿主中的类
 */
public class HostEngineProvider {
    private static final String TAG = "HostEngineProvider";
    private static HostEngineProvider sInstance;

    private Context mHostApplicationContext;

    private static Map<String, GlobalListener> globalListeners = new ConcurrentHashMap<>();

    public static HostEngineProvider getInstance() {
        if (sInstance == null) {
            sInstance = new HostEngineProvider();
        }
        return sInstance;
    }


    private HostEngineProvider() {
        Log.d(TAG, "HostEngineProvider: " + this.hashCode());
    }


    // 宿主调用
    public void init(Context mHostApplicationContext) {
        this.mHostApplicationContext = mHostApplicationContext;
    }

    // 宿主调用
    public void release() {
        mHostApplicationContext = null;
    }


    //-----------------------业务接口------------------------------

    public void addGlobalListener(String who, GlobalListener globalListener) {
        Log.d(TAG, "setGlobalListener: " + globalListener + " who=" + who);
        if (!TextUtils.isEmpty(who)) {
            globalListeners.put(who, globalListener);
        }
    }

    public void removeGlobalListener(String who) {
        Log.d(TAG, "who: " + who);
        if (TextUtils.isEmpty(who)) {
            return;
        }
        if (globalListeners.containsKey(who)) {
            globalListeners.remove(who);
        }
    }

    // 同步方法
    public String action(String paramJson) {
        Log.d(TAG, "action: " + paramJson + " " + this.hashCode() + " " + getProcessName(mHostApplicationContext));
        Log.d(TAG, "action: " + mHostApplicationContext.getClass().getName());
        // TODO: 2024-12-25
        return paramJson;
    }

    // 异步方法
    public void request(String paramJson, RequestCallback requestCallback) {
        Log.d(TAG, "request: " + paramJson);
        Log.d(TAG, "request: " + mHostApplicationContext.getClass().getName());
        // TODO: 2024-12-25 requestCallback 异步回调
        new Handler().postDelayed(() -> requestCallback.onSuccess((paramJson + this.hashCode() + "  " + getProcessName(mHostApplicationContext))), 2000);
    }

    public View buildHostUiLayer() {
        return LayoutInflater.from(mHostApplicationContext)
                .inflate(R.layout.host_ui_layer_layout, null, false);
    }


    private String getProcessName(Context context) {
        String currentProcName = "";
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo processInfo : manager.getRunningAppProcesses()) {
            if (processInfo.pid == myPid()) {
                currentProcName = processInfo.processName;
                break;
            }
        }
        return currentProcName;
    }


    private void dispatchChange(String resultJson) {
        for (String key : globalListeners.keySet()) {
            GlobalListener listener = globalListeners.get(key);
            if (listener != null) {
                try {
                    listener.onResult(resultJson);
                } catch (Exception e) {
                    e.printStackTrace();
                    globalListeners.remove(key);
                    break;
                }
            }
        }
    }
}
