package com.base.components.db;

import android.content.ContentValues;
import android.content.Context;
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
            sdb.delete(MyDBHelper.DROP_STUDY_TABLE, null, null);
            if (bean != null) {
                ContentValues values = new ContentValues();
                values.put("name", bean.getName());
                values.put("age", bean.getAge());
                values.put("gender", bean.getGender());
                values.put("phone", bean.getPhone());
                sdb.insert(MyDBHelper.DROP_STUDY_TABLE, "_id", values);
            }
            sdb.setTransactionSuccessful();
        } catch (Exception e) {

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
        try {

        } catch (Exception e) {

        } finally {

        }
        return null;
    }
}
