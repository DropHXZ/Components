package com.base.components.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.base.components.R;

public class MainActivity extends AppCompatActivity {

    private Button btn_service;
    private Button btn_broadcast;
    private Button btn_provider;
    private Button btn_thread;
    private Button btn_db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_service = findViewById(R.id.btn_service);
        btn_broadcast = findViewById(R.id.btn_broadcast);
        btn_provider = findViewById(R.id.btn_provider);
        btn_thread = findViewById(R.id.btn_thread);
        btn_db = findViewById(R.id.btn_db);

        btn_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ServiceActivity.class));
            }
        });

        btn_broadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BroadCastActivity.class));
            }
        });

        btn_provider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ProviderActivity.class));
            }
        });

        btn_thread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btn_db.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DBActivity.class));
            }
        });
    }
}
