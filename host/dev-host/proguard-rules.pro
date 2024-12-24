# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-keep class org.slf4j.**{*;}
-dontwarn org.slf4j.impl.**

-keep class com.tencent.shadow.dynamic.host.**{*;}
-keep class com.tencent.shadow.core.common.**{*;}
-keep class com.tencent.shadow.core.runtime.container.**{*;}


-keep class com.nolovr.shadow.dynamic.host.**{*;}
-keep class com.nolovr.shadow.core.common.**{*;}
-keep class com.nolovr.shadow.core.runtime.container.**{*;}
-keep class com.tencent.shadow.sample.host.lib.**{*;}
-keep class com.tencent.shadow.**{*;}
-keep class com.nolovr.shadow.**{*;}





# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\AppData\Local\Android\sdk/tools/proguard/proguard-android.txt
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

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# 友盟

-keep class com.umeng.** {*;}

-keep class org.repackage.** {*;}

-keep class com.uyumao.** { *; }

-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}


#指定代码的压缩级别
-optimizationpasses 5

#包明不混合大小写
-dontusemixedcaseclassnames

#不去忽略非公共的库类
-dontskipnonpubliclibraryclasses

 #优化  不优化输入的类文件
-dontoptimize

 #预校验
-dontpreverify

 #混淆时是否记录日志
-verbose

 # 混淆时所采用的算法
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

#保护注解
-keepattributes *Annotation*
#内部类 不被混淆
-keepattributes InnerClasses

# 保持哪些类不被混淆
-keep public class * extends androidx.appcompat.app.AppCompatActivity
-keep public class * extends androidx.fragment.app.Fragment

-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class com.android.vending.licensing.ILicensingService
#如果有引用v4包可以添加下面这行
-keep public class * extends android.support.v4.app.Fragment


#忽略警告
-ignorewarnings

##记录生成的日志数据,gradle build时在本项目根目录输出##
#apk 包内所有 class 的内部结构
-dump proguard/class_files.txt
#未混淆的类和成员
-printseeds proguard/seeds.txt
#列出从 apk 中删除的代码
-printusage proguard/unused.txt
#混淆前后的映射
-printmapping proguard/mapping.txt
########记录生成的日志数据，gradle build时 在本项目根目录输出-end######

#如果引用了v4或者v7包
-dontwarn android.support.**

####混淆保护自己项目的部分代码以及引用的第三方jar包library-end####



#保持 native 方法不被混淆
-keepclasseswithmembernames class * {
    native <methods>;
}

#保持自定义控件类不被混淆
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

#保持自定义控件类不被混淆
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public void set*(...);
}

#保持 Parcelable 不被混淆
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

#保持 Serializable 不被混淆
-keepnames class * implements java.io.Serializable

#保持 Serializable 不被混淆并且enum 类也不被混淆
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    !private <fields>;
    !private <methods>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

#保持枚举 enum 类不被混淆
-keepclassmembers enum * {
  public static **[] values();
  public static ** valueOf(java.lang.String);
}

-keepclassmembers class * {
    public void *ButtonClicked(android.view.View);
}

#不混淆资源类
-keepclassmembers class **.R$* {
    public static <fields>;
}



#kotlin 相关
-dontwarn kotlin.**
-keep class kotlin.** { *; }
-keep interface kotlin.** { *; }
-keepclassmembers class kotlin.Metadata {
    public <methods>;
}
-keepclasseswithmembers @kotlin.Metadata class * { *; }
-keepclassmembers class **.WhenMappings {
    <fields>;
}
-assumenosideeffects class kotlin.jvm.internal.Intrinsics {
    static void checkParameterIsNotNull(java.lang.Object, java.lang.String);
}

-keep class kotlinx.** { *; }
-keep interface kotlinx.** { *; }
-dontwarn kotlinx.**
-dontnote kotlinx.serialization.SerializationKt

-keep class org.jetbrains.** { *; }
-keep interface org.jetbrains.** { *; }
-dontwarn org.jetbrains.**



# 避免混淆所有native的方法,涉及到C、C++
-keepclasseswithmembernames class * {
        native <methods>;
}



# 保留androidx下的所有类及其内部类
-keep class androidx.** {*;}
-keep public class * extends androidx.**
-keep interface androidx.** {*;}
-keep class com.google.android.material.** {*;}
-dontwarn androidx.**
-dontwarn com.google.android.material.**
-dontnote com.google.android.material.**

#避免混淆泛型 如果混淆报错建议关掉
#-keepattributes Signature

#移除Log类打印各个等级日志的代码，打正式包的时候可以做为禁log使用，这里可以作为禁止log打印的功能使用，另外的一种实现方案是通过BuildConfig.DEBUG的变量来控制
#-assumenosideeffects class android.util.Log {
#    public static *** v(...);
#    public static *** i(...);
#    public static *** d(...);
#    public static *** w(...);
#    public static *** e(...);
#}

#############################################################################################
########################                 以上通用           ##################################
#############################################################################################

#######################     常用第三方模块的混淆选项         ###################################
#gson
#如果用用到Gson解析包的，直接添加下面这几行就能成功混淆，不然会报错。
-keepattributes Signature
# Gson specific classes
-keep class sun.misc.Unsafe { *; }
# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.** { *; }
-keep class com.google.gson.stream.** { *; }

#mob
-keep class android.net.http.SslError
-keep class android.webkit.**{*;}
-keep class cn.sharesdk.**{*;}
-keep class com.sina.**{*;}
-keep class m.framework.**{*;}
-keep class **.R$* {*;}
-keep class **.R{*;}
-dontwarn cn.sharesdk.**
-dontwarn **.R$*

#butterknife
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

######引用的其他Module可以直接在app的这个混淆文件里配置

# 如果使用了Gson之类的工具要使被它解析的JavaBean类即实体类不被混淆。
-keep class com.matrix.app.entity.json.** { *; }
-keep class com.matrix.appsdk.network.model.** { *; }
-keep class com.nl.nolohome.bean.** { *; }
-keep class com.nl.bean.** { *; }
-keep class com.nl.gear.bean.** { *; }
-keep class com.nl.nolohome.core.dprovider.bluetooth.entity.** { *; }
-keep class com.nl.nolohome.core.bean.** { *; }
-keep class com.nl.nolohome.unityjar.engine.** { *; }
-keep class com.nl.nolohome.unityjar.bean.** { *; }
-keep class com.nl.nolohome.foundation.bean.** { *; }
-keep class com.nl.nolohome.huawei.bean.** { *; }
-keep class com.nl.nolohome.core.r1.bean.** { *; }
-keep class com.nl.nolohome.core.callback.** { *; }

#####混淆保护自己项目的部分代码以及引用的第三方jar包library#######
#如果在当前的application module或者依赖的library module中使用了第三方的库，并不需要显式添加规则
#-libraryjars xxx
#添加了反而有可能在打包的时候遭遇同一个jar多次被指定的错误，一般只需要添加忽略警告和保持某些class不被混淆的声明。
#以libaray的形式引用了开源项目,如果不想混淆 keep 掉，在引入的module的build.gradle中设置minifyEnabled=false
-keep class com.nineoldandroids.** { *; }
-keep interface com.nineoldandroids.** { *; }
-dontwarn com.nineoldandroids.**
# 下拉刷新
-keep class in.srain.cube.** { *; }
-keep interface in.srain.cube.** { *; }
-dontwarn in.srain.cube.**
# observablescrollview：tab fragment
-keep class com.github.ksoichiro.** { *; }
-keep interface com.github.ksoichiro.** { *; }
-dontwarn com.github.ksoichiro.**

#bugly
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}

# 友盟
-keep class com.umeng.commonsdk.** {*;}

#okhttp
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn javax.annotation.**
# A resource is loaded with a relative path so the package of this class must be preserved.
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase


#okhttp
-dontwarn okhttp3.**
-keep class okhttp3.**{*;}

#okio
-dontwarn okio.**
-keep class okio.**{*;}


#20180411 ping++
# Ping++ 混淆过滤
-dontwarn com.pingplusplus.**
-keep class com.pingplusplus.** {*;}

# 支付宝混淆过滤
-dontwarn com.alipay.**
-keep class com.alipay.** {*;}

# 微信或QQ钱包混淆过滤
-dontwarn  com.tencent.**
-keep class com.tencent.** {*;}

# 银联支付混淆过滤
-dontwarn  com.unionpay.**
-keep class com.unionpay.** {*;}

# 招行一网通混淆过滤
-keepclasseswithmembers class cmb.pb.util.CMBKeyboardFunc {
    public <init>(android.app.Activity);
    public boolean HandleUrlCall(android.webkit.WebView,java.lang.String);
    public void callKeyBoardActivity();
}

# 百付宝混淆过滤
-dontwarn com.baidu.**
-keep class com.baidu.** {*;}

# 内部WebView混淆过滤
-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}

# 新浪微博
-dontwarn com.sina.**
-keep class com.sina.** {*;}
# gear 签名
-dontwarn org.spongycastle.**
-dontwarn org.apache.**
-keep class org.spongycastle.** {*;}
-keep class org.apache.** {*;}
# Facebook
-dontwarn com.facebook.**
-keep class com.facebook.** {*;}
# twitter
-keep class com.twitter.** {*;}
-keep class io.fabric.** {*;}
# 高德地图
-keep class com.amap.api.** {*;}

-keepattributes EnclosingMetho


#retrofit

-dontwarn com.squareup.okhttp.**
-dontwarn com.google.appengine.api.urlfetch.**
-dontwarn rx.**
-dontwarn retrofit.**
-keepattributes Signature
-keepattributes *Annotation*
-keep class com.squareup.okhttp.** { *; }
-keep interface com.squareup.okhttp.** { *; }
-keep class retrofit.** { *; }
-keepclasseswithmembers class * {
    @retrofit.http.* <methods>;
}


# EventBus
-keepattributes *Annotation*
-keepclassmembers class ** {
    @org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

# unity
-keep class bitter.jnibridge.* { *; }
-keep class com.unity3d.player.* { *; }
-keep class org.fmod.* { *; }
-ignorewarnings


# vrHome
-keep class com.nolohome.callandroid.* { *; }
-keep class com.vrhome.* { *; }
-keep class com.nl.usbhost.* { *; }

# alvr
-keep class com.polygraphene.alvr.* { *; }
-dontwarn com.polygraphene.alvr.**

# IQIYI
-keep class com.iqiyi.** { *; }
-dontwarn com.iqiyi.**
-keep class com.qualcomm.** { *; }
-dontwarn com.qualcomm.**
-keep class com.android.qiyicontroller.** { *; }
-dontwarn com.android.qiyicontroller.**

# xiaomi
-keep class com.mi.dlabs.vr.** { *; }
-dontwarn com.mi.dlabs.vr.**

# gvr
-keep class com.google.** { *; }
-dontwarn com.google.**
-ignorewarnings

-keep class com.nl.nolohome.launcher.iqiyi.** { *; }
-keep class com.nl.nolohome.launcher.qualcomm.** { *; }
-keep class com.nl.nolohome.launcher.cardboard.** { *; }

-keep class com.RenderHeads.AVProVideo.** { *; }
-keep class com.android.vending.expansion.zipfile.** { *; }

# oculus
-keep class com.oculus.** { *; }

# vrseen
-keep class com.vrseen.sensor.** { *; }

# Skyworth
-keep class com.htc.vr.** { *; }
-keep class vive.wave.vr.** { *; }
-keep class com.nl.androidsdkclient.** { *; }
-keep class com.nl.jni.** { *; }


-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}
# tinker混淆规则
-dontwarn com.tencent.tinker.**
-keep class com.tencent.tinker.** { *; }

#okhttp
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn javax.annotation.**
# A resource is loaded with a relative path so the package of this class must be preserved.
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase


#okhttp
-dontwarn okhttp3.**
-keep class okhttp3.**{*;}

#okio
-dontwarn okio.**
-keep class okio.**{*;}

-keep class cn.sccl.app.iotsdk.**{*;}
-keep class org.greenrobot.**{*;}

# eventbus
#-keepclassmembers class ** { @org.greenrobot.eventbus.Subscribe ;}
-keep enum org.greenrobot.eventbus.ThreadMode { *; }

# 华为支付相关
-ignorewarnings
-keepattributes *Annotation*
-keepattributes Exceptions
-keepattributes InnerClasses
-keepattributes Signature
-keepattributes SourceFile,LineNumberTable
-keep class com.hianalytics.android.**{*;}
-keep class com.huawei.updatesdk.**{*;}
-keep class com.huawei.hms.**{*;}
-keep class com.huawei.android.hms.agent.**{*;}
-keep class com.huawei.gamebox.plugin.gameservice.**{*;}

# 华为unity相关
-keep class com.huawei.vrhandle.**{*;}
-keep class com.huawei.hvr.**{*;}
-keep class com.huawei.vrlab.**{*;}
-keep class com.huawei.hwvrexternaldevice.**{*;}

# 华为海思
-keep class mlab.android.decode.hisi_ldy_decode.**{*;}

# 节操播放器
#-keep public class cn.jzvd.** {*; }
#-keep public class cn.jzvd.JZMediaSystem {*; }
#-keep public class cn.jzvd.demo.CustomMedia.CustomMedia {*; }
#-keep public class cn.jzvd.demo.CustomMedia.JZMediaIjk {*; }
#-keep public class cn.jzvd.demo.CustomMedia.JZMediaSystemAssertFolder {*; }

-keep class tv.danmaku.ijk.media.player.** {*; }
-dontwarn tv.danmaku.ijk.media.player.*
-keep interface tv.danmaku.ijk.media.player.** { *; }


# nibiru 开始

-keep class ruiyue.controller.sdk.** {*;}
-keep class com.dlodlo.** {*;}
-keep class com.nibiru.** {*;}
-keep class com.sixdof.** {*;}
-keep class com.nibiru.lib.xr.unity.** {*;}
-keep class android.app.** {*;}

-keep class com.qualcomm.svrapi.** {*;}
-keep class com.qualcomm.snapdragonvrservice.** {*;}

-keep class com.sixdof.** {*;}


-keep class com.nibiru.lib.vr.NibiruVR.** {*;}

-keep class com.nibiru.lib.vr.verify.** {*;}

-keep class com.nibiru.service.** {*;}

-keep class com.nibiru.lib.vr.video.** {*;}

-keep class com.nibiru.voicesdk.** {*;}

-keep class ruiyue.gesture.sdk.** {*;}

-keep class com.nibiru.api.** {*;}

-keep class com.nibiru.audio360.** {*;}


-keep class com.nibiru.interaction.** {*;}


-keep class com.nibiru.event.** {*;}

-keep class com.nibiru.lib.vr.unity.** {*;}

-keep class x.core.ui.view.** {*;}

-keep class com.nibiru.other.** {*;}

-keep class com.nibiru.lib.vr.model.** {*;}

# nibiru 结束

# 去除日志 上线打开
#-assumenosideeffects class android.util.Log{
#    public static *** v(...);
#    public static *** i(...);
#    public static *** d(...);
#    public static *** w(...);
#    public static *** e(...);
#}

# com.blankj 工具类
-keep class com.blankj.utilcode.** { *; }
-keepclassmembers class com.blankj.utilcode.** { *; }
-dontwarn com.blankj.utilcode.**

# 搜狗输入法
-keep class com.sohu.inputmethod.sogou.** { *; }
-keepclassmembers class com.sohu.inputmethod.sogou.** { *; }
-dontwarn com.sohu.inputmethod.sogou.**

# audio 360
-keep class com.twobigears.** { *; }
-keepclassmembers class com.twobigears.** { *; }
-dontwarn com.twobigears.**

# avpro
-keep class com.renderheads.AVPro.** { *; }
-keepclassmembers class com.renderheads.AVPro.** { *; }
-dontwarn com.renderheads.AVPro.**

# vlc
-keep class org.videolan.** { *; }
-keepclassmembers class org.videolan.** { *; }
-dontwarn org.videolan.**

-keep class unitydirectionkit.universalmediaplayer.** { *; }
-keepclassmembers class unitydirectionkit.universalmediaplayer.** { *; }
-dontwarn unitydirectionkit.universalmediaplayer.**

# vlc resample-rh
-keep class com.renderheads.resamplerh.** { *; }
-keepclassmembers class com.renderheads.resamplerh.** { *; }
-dontwarn com.renderheads.resamplerh.**

# zip_file
-keep class com.android.vending.expansion.zipfile.** { *; }
-keepclassmembers class com.android.vending.expansion.zipfile.** { *; }
-dontwarn com.android.vending.expansion.zipfile.**

# 乐播 com.hpplay.sdk 无关紧要
-keep class com.hpplay.** { *; }
-keepclassmembers class com.hpplay.** { *; }
-dontwarn com.hpplay.**

# dm 入库
-keep class cn.richinfo.mt.** { *; }
-keepclassmembers class cn.richinfo.mt.** { *; }
-dontwarn cn.richinfo.mt.**


# unity 补充
-keep class com.unity.purchasing.common.** { *; }
-keepclassmembers class com.unity.purchasing.common.** { *; }
-dontwarn com.unity.purchasing.common.**

# exoplayer
-keep class com.google.android.** { *; }
-keepclassmembers class com.google.android.** { *; }
-dontwarn com.google.android.**

# sensoranaliytics
-keep class com.sensorsdata.analytics.android.sdk.** { *; }
-keepclassmembers class com.sensorsdata.analytics.android.sdk.** { *; }
-dontwarn com.sensorsdata.analytics.android.sdk.**


# 蓝牙实体类
-keep class com.nl.bean.** { *; }
-keepclassmembers class com.nl.bean.** { *; }
-dontwarn com.nl.bean.**

# 火狐
-keep class org.mozilla.** { *; }
-keepclassmembers class org.mozilla.** { *; }
-dontwarn org.mozilla.**

# aidl controller
-keep class com.nl.core.controller.IConnectStatusListener { *; }
-keep class com.nl.core.controller.IDeviceRawDataListener { *; }
-keep class com.nl.core.controller.IDeviceStatusListener { *; }
-keep class com.nl.core.controller.IDeviceVersionListener { *; }
-keep class com.nl.core.controller.IN3DoFDataListener { *; }
-keep class com.nl.core.controller.IN3DoFService { *; }
-keep class com.nl.core.controller.IRomVersionListener { *; }
-keep class com.nl.core.controller.IScanResultListener { *; }

# aidl scb
-keep class com.nl.scb.ICastCallback { *; }
-keep class com.nl.scb.IConnDeviceCallback { *; }
-keep class com.nl.scb.IExecResultCallback { *; }
-keep class com.nl.scb.InitSdkCallback { *; }
-keep class com.nl.scb.IScanDeviceCallback { *; }
-keep class com.nl.scb.IScreenRecordCallback { *; }
-keep class com.nl.scb.SCBSdk { *; }

# aidl scb
-keep class com.nl.core.glass.ipc.IMonitorCallback { *; }
-keep class com.nl.core.glass.ipc.IMonitorQuery { *; }
-keep class com.nl.core.glass.ipc.IScreenRecordCallback { *; }
-keep class com.nl.core.glass.ipc.SurfaceViewManager { *; }

-keep class com.nl.core.glass.ipc.handset.HandsetSurfaceViewManager { *; }

-keep class com.nl.core.glass.ipc.ota.IAppOtaCallback { *; }
-keep class com.nl.core.glass.ipc.ota.IAppOtaQuery { *; }
-keep class com.nl.core.glass.ipc.ota.IDeviceOtaCallback { *; }
-keep class com.nl.core.glass.ipc.ota.IDeviceOtaListener { *; }
-keep class com.nl.core.glass.ipc.ota.IDeviceOtaQuery { *; }
-keep class com.nl.core.glass.ipc.ota.IOtaBinderFactory { *; }

# usb
-keep class com.nl.core.glass.usb.ActionCallback { *; }
-keep class com.nl.core.glass.usb.ExtendCallBack { *; }
-keep class com.nl.core.glass.usb.USBDataCallBack { *; }
-keep class com.nl.core.glass.usb.USBServiceManager { *; }

# dex 依赖的 类
-keep class com.nl.core.glass.controller.HandSetDataListener { *; }
-keep class com.nl.core.glass.controller.HandSetElectricityDataListener { *; }
-keep class com.nl.core.glass.controller.ServerConnectedListener { *; }

-keep class com.nl.plugininf.hmd.TerminalInf { *; }
-keep class com.nl.plugininf.controller.ApiInf { *; }


# 接口
-keep class com.nl.core.api.engine.** { *; }
-keepclassmembers class com.nl.core.api.engine.** { *; }
-dontwarn com.nl.core.api.engine.**

# 实体
-keep class com.nl.foundation.** { *; }
-keepclassmembers class com.nl.foundation.** { *; }
-dontwarn com.nl.foundation.**

-keep class com.nl.geckoviewbroswer.** { *; }
-keepclassmembers class com.nl.geckoviewbroswer.** { *; }
-dontwarn com.nl.geckoviewbroswer.**


# JNI
-keep class com.nl.core.data.usb.UsbProxy{ *; }

-keep class com.nl.pfm.signal.** { *; }
-keepclassmembers class com.nl.pfm.signal.** { *; }
-dontwarn com.nl.pfm.signal.**


#DLNA：
#com.nolo.dlan.receiver.DLanReceiverApi
#com.nolo.dlan.sdk.platinum.PlatinumJni
#com.nolo.dlan.receiver.intf.InitSdkCallback
#com.nolo.dlan.receiver.intf.IDLanReceiver
#com.nolo.dlan.receiver.model.MediaInfo
#com.nolo.dlan.sdk.IMediaCastActionListener

-keep class com.nolo.dlan.receiver.DLanReceiverApi{
   <fields>;
   <methods>;
}

-keep class com.nolo.dlan.sdk.platinum.PlatinumJni{
   <fields>;
   <methods>;
}

-keep class com.nolo.dlan.receiver.intf.InitSdkCallback{
   <fields>;
   <methods>;
}

-keep class com.nolo.dlan.receiver.intf.IDLanReceiver{
   <fields>;
   <methods>;
}

-keep class com.nolo.dlan.receiver.model.MediaInfo{
   <fields>;
   <methods>;
}

-keep class com.nolo.dlan.sdk.IMediaCastActionListener{
   <fields>;
   <methods>;
}


# 简单粗暴处理，待优化
-keep class com.nl.system.explorer.** { *; }
-keepclassmembers class com.nl.system.explorer.** { *; }
-dontwarn com.nl.system.explorer.**

# 简单粗暴处理，待优化
-keep class com.ywl5320.wlmedia.** { *; }
-keepclassmembers class com.ywl5320.wlmedia.** { *; }
-dontwarn com.ywl5320.wlmedia.**

## 简单粗暴处理，待优化
#-keep class com.vstar3d.S3DApi.** { *; }
#-keepclassmembers class com.vstar3d.S3DApi.** { *; }
#-dontwarn com.vstar3d.S3DApi.**

## 简单粗暴处理，待优化
-keep class wseemann.media.** { *; }
-keepclassmembers class wseemann.media.** { *; }
-dontwarn wseemann.media.**


# 简单粗暴处理，待优化
-keep class com.nl.core.nolo3dplayer.entity.** { *; }
-keepclassmembers class com.nl.core.nolo3dplayer.entity.** { *; }
-dontwarn com.nl.core.nolo3dplayer.entity.**

# 简单粗暴处理，待优化
-keep class com.nl.core.glassless.bean.** { *; }
-keepclassmembers class com.nl.core.glassless.bean.** { *; }
-dontwarn com.nl.core.glassless.bean.**

# 简单粗暴处理，待优化 眼追相关
-keep class com.gs3d.core.detec.** { *; }
-keepclassmembers class com.gs3d.core.detec.** { *; }
-dontwarn com.gs3d.core.detec.**

# 简单粗暴处理，待优化 基腾相关
-keep class com.gs3d.core.calib.** { *; }
-keepclassmembers class com.gs3d.core.calib.** { *; }
-dontwarn com.gs3d.core.calib.**

# 基腾SDK，待优化 基腾相关
-keep class cn.jitengkeji.** { *; }
-keepclassmembers class cn.jitengkeji.** { *; }
-dontwarn cn.jitengkeji.**

# 蓝深SDK，待优化 基腾相关
-keep class com.gxiang3d.g3dlib.** { *; }
-keepclassmembers class com.gxiang3d.g3dlib.** { *; }
-dontwarn com.gxiang3d.g3dlib.**

# 蓝深SDK，待优化 基腾相关
-keep class com.vstar3d.s3dapi.** { *; }
-keepclassmembers class com.vstar3d.s3dapi.** { *; }
-dontwarn com.vstar3d.s3dapi.**

# 简单粗暴处理，待优化 裸眼sdk
-keep class com.nl.gsgf3d.sdk.** { *; }
-keepclassmembers class com.nl.gsgf3d.sdk.** { *; }
-dontwarn com.nl.gsgf3d.sdk.**

# 简单粗暴处理，待优化 标定参数管理
-keep class  androidx.core.** { *; }
-keepclassmembers class androidx.core.** { *; }
-dontwarn androidx.core.**

## 简单粗暴处理，待优化 标定参数管理
#-keep class com.glassless.sdk.mng.** { *; }
#-keepclassmembers class com.glassless.sdk.mng.** { *; }
#-dontwarn com.glassless.sdk.mng.**

# 简单粗暴处理，待优化 标定参数管理
-keep class com.compose.vr.common.bean.** { *; }
-keepclassmembers class com.compose.vr.common.bean.** { *; }
-dontwarn com.compose.vr.common.bean.**

# smb nas nas文件管理相关
-keep class com.nolovr.system.explorer.smb.ldd.model.** { *; }
-keepclassmembers class com.nolovr.system.explorer.smb.ldd.model.** { *; }
-dontwarn com.nolovr.system.explorer.smb.ldd.model.**

# smb nas nas文件管理相关
-keep class com.nolovr.system.explorer.parser.bean.** { *; }
-keepclassmembers class com.nolovr.system.explorer.parser.bean.** { *; }
-dontwarn com.nolovr.system.explorer.parser.bean.**

# smb nas nas文件管理相关
-keep class com.nolovr.system.explorer.smb.model.** { *; }
-keepclassmembers class com.nolovr.system.explorer.smb.model.** { *; }
-dontwarn com.nolovr.system.explorer.smb.model.**

-keep class com.cmterminal.res3d.impl.NasClientBean*{*;}

-keep class android.view.**{*;}
-keep class com.nl.gsgf3d.sdk.BuildConfig*{*;}
#-keep class com.cmterminal.networklib.ApiService*{*;}

-keep class com.cmterminal.res3d.impl.** { *; }
-keepclassmembers class com.cmterminal.res3d.impl.** { *; }
-dontwarn com.cmterminal.res3d.impl.**

# TODO 待优化
-keep class com.nolovr.** { *; }
-keepclassmembers class com.nolovr.** { *; }
-dontwarn com.nolovr.**

# 保留视频格式自动识别 NativeUtils 类 onResult方法
-keep class com.cmcc.gs3d.formatdetector.NativeUtils {
    void onResult(...);
}


-keep class com.sdk.** { *; }
-keepclassmembers class com.sdk.** { *; }
-dontwarn com.sdk.**

## ---------Retrofit混淆方法---------------
-dontwarn javax.annotation.**
-dontwarn javax.inject.**
# OkHttp3
-dontwarn okhttp3.logging.**
-keep class okhttp3.internal.**{*;}
-dontwarn okio.**
# Retrofit
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions
# RxJava RxAndroid
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
    long producerIndex;
    long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
    rx.internal.util.atomic.LinkedQueueNode consumerNode;
}

# Gson
-keep class com.google.gson.stream.** { *; }
-keepattributes EnclosingMethod








