package com.base.components.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.base.components.db.MyDBHelper;

/**
 * Android为我们提供了ContentProvider来实现数据的共享，一个程序如果想让别的程序可以操作自己的数据，
 * 就定义自己的 ContentProvider，然后在AndroidManifest.xml中注册，其他application可以通过获取
 * ContentResolver通过Uri来操作上一程序的数据。
 * 通过实现一个ContentProvider的抽象接口将自己的数据完全暴露出去
 * Created by admin on 2018/1/8.
 */

public class MyProvider extends ContentProvider {

    private static final String AUTHORITY = "com.base.components.provider.MyProvider";

    private static final int SINGLE_CODE = 2;   //返回单个记录的匹配码
    private static final int MULTIPLE_CODE = 1; //返回多个的记录的匹配码

    //UriMatcher 类主要用于匹配Uri.
    private UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

    {
        matcher.addURI(AUTHORITY, "person", MULTIPLE_CODE);
        //#号是通配符
        matcher.addURI(AUTHORITY, "person/#", SINGLE_CODE);
    }

    private MyDBHelper dbHelper;

    @Override
    public boolean onCreate() {
        dbHelper = new MyDBHelper(getContext(), null);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        //match方法匹配后会返回一个匹配码Code，即在使用注册方法addURI时传入的第三个参数
        switch (matcher.match(uri)) {
            case MULTIPLE_CODE:
                SQLiteDatabase dbm = dbHelper.getReadableDatabase();
                return dbm.query(MyDBHelper.TABLE_CONTACT, null, null, null, null, null, null);
            case SINGLE_CODE:
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                return db.query(MyDBHelper.TABLE_CONTACT, null, null, null, null, null, null);
        }
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        //match方法匹配后会返回一个匹配码Code，即在使用注册方法addURI时传入的第三个参数
        switch (matcher.match(uri)) {
            case MULTIPLE_CODE:
                break;
            case SINGLE_CODE:
                break;
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        //match方法匹配后会返回一个匹配码Code，即在使用注册方法addURI时传入的第三个参数
        switch (matcher.match(uri)) {
            case MULTIPLE_CODE:
                break;
            case SINGLE_CODE:
                break;
            default:
                break;
        }
        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
