package com.base.components.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 数据库
 * Created by admin on 2018/1/9.
 */

public class MyDBHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "go_study.db";
    private static int DATABASE_VERSION = 1;

    public static final String _ID = "_id";

    public static String CREATE_STUDY_TABLE = "CREATE TABLE CONTACT(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            "name TEXT,age INTEGER,gender TEXT,phone TEXT)";
    public static String DROP_STUDY_TABLE = "DROP TABLE IF EXISTS CONTACT";

    public MyDBHelper(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_STUDY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_STUDY_TABLE);

        db.execSQL(CREATE_STUDY_TABLE);
    }
}
