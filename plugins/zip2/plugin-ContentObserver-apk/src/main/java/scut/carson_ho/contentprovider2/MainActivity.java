package scut.carson_ho.contentprovider2;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "_ObserverMainActivity";
    private static final String uriPathUser = "content://cn.scu.myprovider/user";
    private static final String uriPathJob = "content://cn.scu.myprovider/job";
//    private static final String uriPath = "content://com.nolovr.shadow.core.contentprovider.authority.dynamic";

    // com.noolovr.shadow.core.contentprovider.authority.dynamic

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ");

    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: oopp");
        /**
         * 对user表进行操作
         * cn.scu.myprovider
         *
         */

        // 设置URI
        Uri uri_user = Uri.parse(uriPathUser);

        // 插入表中数据
        ContentValues values = new ContentValues();
        values.put("_id", 4);
        values.put("name", "Jordan");


        // 获取ContentResolver
        ContentResolver resolver = getContentResolver();
//        // 通过ContentResolver 根据URI 向ContentProvider中插入数据
//        resolver.insert(uri_user,values);
        Log.d(TAG, "onResume: insert");
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
            Log.e(TAG, "onCreate: query book: cursor null");
        }

        /**
         * 对job表进行操作
         */
        // 和上述类似,只是URI需要更改,从而匹配不同的URI CODE,从而找到不同的数据资源
        // cn.scu.myprovider
        Uri uri_job = Uri.parse(uriPathJob);

        // 插入表中数据
        ContentValues values2 = new ContentValues();
        values2.put("_id", 4);
        values2.put("job", "NBA Player");

        // 获取ContentResolver
        ContentResolver resolver2 = getContentResolver();
        // 通过ContentResolver 根据URI 向ContentProvider中插入数据
        try {
            Log.i(TAG, "onResume: insert 2");
            resolver2.insert(uri_job, values2);
        } catch (Exception e) {
            //throw new RuntimeException(e);
            Log.e(TAG, "onResume: insert 2 --》"+ e.getMessage() );
        }

        // 通过ContentResolver 向ContentProvider中查询数据
        Cursor cursor2 = resolver2.query(uri_job, new String[]{"_id", "job"}, null, null, null);
        if (cursor2 != null) {

            while (cursor2.moveToNext()) {
                Log.e(TAG,"query job:" + cursor2.getInt(0) + " " + cursor2.getString(1));
                Log.e(TAG,"query job:" + cursor2.getInt(0) + " " + cursor2.getString(1));
                // 将表中数据全部输出

            }
            cursor2.close();
            // 关闭游标
        }else {
            Log.e(TAG, "onCreate: query job: cursor null");
        }
    }
}
