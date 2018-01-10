package com.base.components.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.base.components.bean.ContactBean;

import java.util.ArrayList;

/**
 * Created by admin on 2018/1/9.
 */

public class ContactDao {

    private MyDBHelper helper;

    public ContactDao(Context context) {
        if (helper == null) {
            helper = new MyDBHelper(context, null);
        }
    }

    /**
     * insert info
     *
     * @param bean
     */
    public void insertInfo(ContactBean bean) {
        SQLiteDatabase sdb = helper.getWritableDatabase();
        sdb.beginTransaction();
        try {
            sdb.delete(MyDBHelper.TABLE_CONTACT, null, null);
            if (bean != null) {
                ContentValues values = new ContentValues();
                values.put("name", bean.getName());
                values.put("age", bean.getAge());
                values.put("gender", bean.getGender());
                values.put("phone", bean.getPhone());
                sdb.insert(MyDBHelper.TABLE_CONTACT, "_id", values);
            }
            sdb.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sdb.endTransaction();
            sdb.close();
        }
    }

    /**
     * @return
     */
    public ArrayList<ContactBean> queryInfo() {
        SQLiteDatabase sdb = helper.getReadableDatabase();
        sdb.beginTransaction();
        try {
            ArrayList<ContactBean> list = new ArrayList<>();
            Cursor cursor = sdb.query(MyDBHelper.TABLE_CONTACT,null,null,null,null,null,null);
            if (!cursor.moveToFirst()) {
                return null;
            }
            while (cursor.moveToNext()) {
                ContactBean bean = new ContactBean();
                bean.setName(cursor.getString(cursor.getColumnIndex("name")));
                bean.setAge(cursor.getString(cursor.getColumnIndex("age")));
                bean.setGender(cursor.getString(cursor.getColumnIndex("gender")));
                bean.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
                list.add(bean);
            }
            sdb.endTransaction();
            sdb.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
