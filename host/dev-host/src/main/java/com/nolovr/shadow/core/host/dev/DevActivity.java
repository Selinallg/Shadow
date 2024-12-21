package com.nolovr.shadow.core.host.dev;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import com.nolovr.shadow.core.constant.Constant;
import com.nolovr.shadow.core.host.HostApplication;
import com.nolovr.shadow.core.host.PluginHelper;
import com.nolovr.shadow.core.host.PluginLoadActivity;
import com.nolovr.shadow.core.host.R;
import com.nolovr.shadow.core.plugin.IMyAidlInterface;
import com.tencent.shadow.dynamic.host.EnterCallback;

public class DevActivity extends Activity {
    private static final String TAG = "_DevActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dev);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    public void checkService(View view) {
        PluginHelper.getInstance().singlePool.execute(new Runnable() {
            @Override
            public void run() {
                // 先加载 插件管理apk
                HostApplication.getApp().loadPluginManager(PluginHelper.getInstance().pluginManagerFile);

                Bundle bundle = new Bundle();
                // 通用的插件包

                bundle.putString(Constant.KEY_COMMON_ZIP_PATH, PluginHelper.getInstance().pluginCommonZipFile.getAbsolutePath());

                bundle.putString(Constant.KEY_PLUGIN_ZIP_PATH, PluginHelper.getInstance().pluginZipFile.getAbsolutePath());
                bundle.putString(Constant.KEY_PLUGIN_PART_KEY, Constant.PART_KEY_PLUGIN_SERVICE);
                bundle.putString(Constant.KEY_COMPONENT_CLASSNAME, "com.nolovr.shadow.core.sample.plugin.MyService");

                bundle.putBundle(Constant.KEY_EXTRAS, getIntent().getBundleExtra(Constant.KEY_EXTRAS)); // 要传入到插件里的参数

                HostApplication.getApp().getPluginManager()
                        .enter(DevActivity.this, Constant.FROM_ID_START_SERVICE, bundle, new EnterCallback() {
                            @Override
                            public void onShowLoadingView(final View view) {
                                Log.d("PluginLoad", "onShowLoadingView: ");

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
    public void checkContentProvder(View view) {
        final String uriPathUser = "content://cn.scu.myprovider/user";
        // 设置URI
        Uri uri_user = Uri.parse(uriPathUser);

        // 插入表中数据
        ContentValues values = new ContentValues();
        values.put("_id", 4);
        values.put("name", "Jordan");


        // 获取ContentResolver
        ContentResolver resolver = getContentResolver();
//        // 通过ContentResolver 根据URI 向ContentProvider中插入数据
        try {
            Log.d(TAG, "checkContentProvder: insert");
            resolver.insert(uri_user,values);
        } catch (Exception e) {
            Log.e(TAG, "checkContentProvder: insert error: " + e.getMessage());
        }
        // 通过ContentResolver 向ContentProvider中查询数据
        Cursor cursor = resolver.query(uri_user, new String[]{"_id", "name"}, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Log.e(TAG,"query book:" + cursor.getInt(0) + " " + cursor.getString(1));
                Log.e(TAG,"query book:" + cursor.getInt(0) + " " + cursor.getString(1));
                // 将表中数据全部输出
            }
            cursor.close();
            // 关闭游标
        }else {
            Log.e(TAG, "checkContentProvder: query book: cursor null");
        }

    }
}
