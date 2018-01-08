package com.base.components.ui;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.base.components.R;
import com.base.components.bean.PhoneInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取联系人列表
 */
public class ContactActivity extends AppCompatActivity {

    private ContentProvider cp;
    private ContentResolver cr;
    private List<PhoneInfo> listContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        try {
            getContact();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getContact() {
        cr = getContentResolver();
        listContact = new ArrayList<>();
        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
        Cursor cursor_query = cr.query(uri, null, null, null, null);
        while (cursor_query.moveToNext()) {
            //读取通讯录的姓名
            String name = cursor_query.getString(cursor_query
                    .getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            //读取通讯录的号码
            String number = cursor_query.getString(cursor_query
                    .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            PhoneInfo phoneInfo = new PhoneInfo();
            phoneInfo.setName(name);
            phoneInfo.setNumber(number);
            listContact.add(phoneInfo);
        }
        Log.i("provider",listContact.get(0).getNumber());
    }

}
