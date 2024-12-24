package com.nolovr.shadow.core.plugin.app.lib;

import static com.nolovr.shadow.core.plugin.app.lib.gallery.cases.UseCaseManager.useCases;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.nolovr.shadow.core.plugin.app.lib.gallery.cases.UseCaseManager;
import com.nolovr.shadow.core.plugin.app.lib.gallery.cases.entity.UseCase;
import com.nolovr.shadow.core.plugin.app.lib.gallery.cases.entity.UseCaseCategory;
import com.nolovr.shadow.core.plugin.app.lib.usecases.activity.TestActivityOnCreate;
import com.nolovr.shadow.core.plugin.app.lib.usecases.activity.TestActivityOptionMenu;
import com.nolovr.shadow.core.plugin.app.lib.usecases.activity.TestActivityOrientation;
import com.nolovr.shadow.core.plugin.app.lib.usecases.activity.TestActivityReCreate;
import com.nolovr.shadow.core.plugin.app.lib.usecases.activity.TestActivityReCreateBySystem;
import com.nolovr.shadow.core.plugin.app.lib.usecases.activity.TestActivitySetTheme;
import com.nolovr.shadow.core.plugin.app.lib.usecases.activity.TestActivityWindowSoftMode;
import com.nolovr.shadow.core.plugin.app.lib.usecases.context.ActivityContextSubDirTestActivity;
import com.nolovr.shadow.core.plugin.app.lib.usecases.context.ApplicationContextSubDirTestActivity;
import com.nolovr.shadow.core.plugin.app.lib.usecases.dialog.TestDialogActivity;
import com.nolovr.shadow.core.plugin.app.lib.usecases.fragment.TestDialogFragmentActivity;
import com.nolovr.shadow.core.plugin.app.lib.usecases.fragment.TestDynamicFragmentActivity;
import com.nolovr.shadow.core.plugin.app.lib.usecases.fragment.TestXmlFragmentActivity;
import com.nolovr.shadow.core.plugin.app.lib.usecases.host_communication.PluginUseHostClassActivity;
import com.nolovr.shadow.core.plugin.app.lib.usecases.packagemanager.TestPackageManagerActivity;
import com.nolovr.shadow.core.plugin.app.lib.usecases.provider.TestDBContentProviderActivity;
import com.nolovr.shadow.core.plugin.app.lib.usecases.provider.TestFileProviderActivity;
import com.nolovr.shadow.core.plugin.app.lib.usecases.receiver.TestDynamicReceiverActivity;
import com.nolovr.shadow.core.plugin.app.lib.usecases.receiver.TestReceiverActivity;
import com.nolovr.shadow.core.plugin.app.lib.usecases.webview.WebView7Activity;
import com.nolovr.shadow.core.plugin.app.lib.usecases.webview.WebViewActivity;

public class UseCaseApplication extends Application {
    private static final String TAG = "UseCaseApplication";
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: "+ getApplicationContext().getClass().getName());
        initCase();
    }
    
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        Log.d(TAG, "attachBaseContext: ");
    }

    private static void initCase() {

        if (UseCaseManager.sInit) {
            throw new RuntimeException("不能重复调用init");
        }

        UseCaseManager.sInit = true;

        UseCaseCategory activityCategory = new UseCaseCategory("Activity测试用例", new UseCase[]{
                new TestActivityOnCreate.Case(),
                new TestActivityReCreate.Case(),
                new TestActivityReCreateBySystem.Case(),
                new TestActivityOrientation.Case(),
                new TestActivityWindowSoftMode.Case(),
                new TestActivitySetTheme.Case(),
                new TestActivityOptionMenu.Case(),
                new WebViewActivity.Case()
        });
        useCases.add(activityCategory);

        UseCaseCategory broadcastReceiverCategory = new UseCaseCategory("广播测试用例", new UseCase[]{
                new TestReceiverActivity.Case(),
                new TestDynamicReceiverActivity.Case()
        });
        useCases.add(broadcastReceiverCategory);


        UseCaseCategory providerCategory = new UseCaseCategory("ContentProvider测试用例", new UseCase[]{
                new TestDBContentProviderActivity.Case(),
                new TestFileProviderActivity.Case()
        });
        useCases.add(providerCategory);


        UseCaseCategory fragmentCategory = new UseCaseCategory("fragment测试用例", new UseCase[]{
                new TestDynamicFragmentActivity.Case(),
                new TestXmlFragmentActivity.Case(),
                new TestDialogFragmentActivity.Case()
        });
        useCases.add(fragmentCategory);

        UseCaseCategory dialogCategory = new UseCaseCategory("Dialog测试用例", new UseCase[]{
                new TestDialogActivity.Case(),
        });
        useCases.add(dialogCategory);

        UseCaseCategory packageManagerCategory = new UseCaseCategory("PackageManager测试用例", new UseCase[]{
                new TestPackageManagerActivity.Case(),
        });
        useCases.add(packageManagerCategory);


        UseCaseCategory contextCategory = new UseCaseCategory("Context相关测试用例", new UseCase[]{
                new ActivityContextSubDirTestActivity.Case(),
                new ApplicationContextSubDirTestActivity.Case(),
        });
        useCases.add(contextCategory);

        UseCaseCategory communicationCategory = new UseCaseCategory("插件和宿主通信相关测试用例", new UseCase[]{
                new PluginUseHostClassActivity.Case(),
        });
        useCases.add(communicationCategory);

        UseCaseCategory webviewCategory = new UseCaseCategory("webview", new UseCase[]{
                //new WebViewActivity.Case(),
                new WebView7Activity.Case(),
        });
        useCases.add(webviewCategory);
    }
}
