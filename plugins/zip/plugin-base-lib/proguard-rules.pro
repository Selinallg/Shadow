# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/cubershi/Library/Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# 这是供sample-app模块依赖的类
-keep class com.nolovr.shadow.core.plugin.app.lib.gallery.cases.UseCaseManager{*;}
-keep class com.nolovr.shadow.core.plugin.app.lib.gallery.cases.entity.*{*;}
-keep class com.nolovr.shadow.core.plugin.app.lib.gallery.BaseActivity{*;}
-keep class com.nolovr.shadow.core.plugin.app.lib.gallery.util.*{*;}