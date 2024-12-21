package com.nolovr.shadow.core.host.dev;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.nolovr.shadow.core.host.R;

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
