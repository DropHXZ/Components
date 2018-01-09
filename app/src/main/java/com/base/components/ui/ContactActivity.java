package com.base.components.ui;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.base.components.R;
import com.base.components.adapter.ContactAdapter;
import com.base.components.bean.PhoneInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取联系人列表
 */
public class ContactActivity extends AppCompatActivity {

    private ContentResolver cr;
    private List<PhoneInfo> listContact;

    private ContactAdapter contactAdapter;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
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
            //拿到联系人id 跟name
            int id = cursor_query.getInt(cursor_query.getColumnIndex("_id"));
            String name = cursor_query.getString(cursor_query.getColumnIndex("display_name"));
            //得到这个id的所有数据（data表）
            Uri uri2 = Uri.parse("content://com.android.contacts/raw_contacts/" + id + "/data");
            Cursor cursor_query2 = cr.query(uri2, null, null, null, null);
            Map<String, String> maps = new HashMap<>();//实例化一个map
            while (cursor_query2.moveToNext()) {
                //得到data这一列 ，包括很多字段
                String data1 = cursor_query2.getString(cursor_query2.getColumnIndex("data1"));
                //得到data中的类型
                String type = cursor_query2.getString(cursor_query2.getColumnIndex("mimetype"));
                String str = type.substring(type.indexOf("/") + 1, type.length());//截取得到最后的类型
                PhoneInfo phoneInfo = new PhoneInfo();
                if ("name".equals(str)) {//匹配是否为联系人名字
                    maps.put("name", data1);
                    phoneInfo.setName(data1);
                }
                if ("phone_v2".equals(str)) {//匹配是否为电话
                    maps.put("phone", data1);
                    phoneInfo.setNumber(data1);
                }
                Log.i("provider", data1 + "       " + type);
                listContact.add(phoneInfo);
            }
        }
//        Log.i("provider", listContact.get(0).getName());
        setListAdapter();
    }

    private void setListAdapter() {
        if (listContact == null) {
            return;
        }
        if (contactAdapter == null) {
            contactAdapter = new ContactAdapter(listContact, this);
            recyclerView.setAdapter(contactAdapter);
        } else {
            contactAdapter.notifyDataSetChanged();
        }
    }

}
