package com.base.components.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.base.components.R;
import com.base.components.adapter.Contact2Adapter;
import com.base.components.bean.ContactBean;
import com.base.components.db.ContactDao;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库操作
 */
public class DBActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<ContactBean> contactBeanList;
    private ContactDao helper;
    private Contact2Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);

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
                startActivityForResult(new Intent(DBActivity.this, DB2Activity.class), 0x01);
            }
        });
        fillData();
    }

    private void fillData() {
        contactBeanList = new ArrayList<>();
        if (helper == null) {
            helper = new ContactDao(this);
        }
        contactBeanList = helper.queryInfo();
        if (contactBeanList == null) {
            return;
        }
        if (adapter == null) {
            adapter = new Contact2Adapter(contactBeanList, this);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0x01 && resultCode == 0x02) {
            data.getBundleExtra("bundle").get("name");
            ContactBean bean = new ContactBean();
            bean.setName(data.getBundleExtra("bundle").get("name").toString());
            bean.setAge(data.getBundleExtra("bundle").get("age").toString());
            bean.setGender(data.getBundleExtra("bundle").get("gender").toString());
            bean.setPhone(data.getBundleExtra("bundle").get("phone").toString());
            if (helper == null) {
                helper = new ContactDao(this);
            }
            helper.insertInfo(bean);
        }
    }
}
