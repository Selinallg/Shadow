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

package com.nolovr.shadow.core.constant;

final public class Constant {
    public static final String KEY_COMMON_ZIP_PATH = "commonZipPath";
    public static final String KEY_PLUGIN_ZIP_PATH = "pluginZipPath";
    public static final String KEY_COMPONENT_CLASSNAME = "KEY_COMPONENT_CLASSNAME";
//    public static final String KEY_SERVICE_CLASSNAME = "KEY_SERVICE_CLASSNAME";
    public static final String KEY_EXTRAS = "KEY_EXTRAS";
    public static final String KEY_PLUGIN_PART_KEY = "KEY_PLUGIN_PART_KEY";


    // partKey
    public static final String PART_KEY_PLUGIN_MAIN_APP = "dev-plugin-app";
    public static final String PART_KEY_PLUGIN_ANOTHER_APP = "dev-plugin-app2";
    public static final String PART_KEY_PLUGIN_DEMO = "plugin-demo-apk"; // demo 用，一条龙添加
    public static final String PART_KEY_PLUGIN_BASE = "plugin-base-apk";
    public static final String PART_KEY_PLUGIN_COMMON = "plugin-common";
    public static final String PART_KEY_PLUGIN_SERVICE = "plugin-service-apk";

    public static final String PART_KEY_PLUGIN_GS3D = "dev-plugin-gs3d"; // 与 zip压缩包json 文件里的配置一定要对应上
    public static final String PART_KEY_PLUGIN_CONTENT_PROVIDER = "plugin-ContentProvider-demo";
    public static final String PART_KEY_PLUGIN_CONTENT_OBSERVER = "plugin-ContentObserver-demo";

    public static final int FROM_ID_NOOP = 1000;
    public static final int FROM_ID_START_ACTIVITY = 1001;
    public static final int FROM_ID_START_SERVICE = 1002;
    public static final int FROM_ID_CLOSE = 1003;
    public static final int FROM_ID_LOAD_VIEW_TO_HOST = 1004;
}