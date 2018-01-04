package com.base.components.ui;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.base.components.R;
import com.base.components.server.MyBindService;
import com.base.components.server.MyStartService;
import com.base.components.server.SampleService;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_intent;
    private Button btn_stop_intent;
    private Button btn_service;
    private Button btn_bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        init();
    }

    private void init() {
        btn_intent = findViewById(R.id.btn_intent);
        btn_stop_intent = findViewById(R.id.btn_stop_intent);
        btn_service = findViewById(R.id.btn_service);
        btn_bind = findViewById(R.id.btn_bind);
        btn_intent.setOnClickListener(this);
        btn_stop_intent.setOnClickListener(this);
        btn_service.setOnClickListener(this);
        btn_bind.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_intent:
                Intent intent = new Intent(this, SampleService.class);
                startService(intent);
                break;
            case R.id.btn_stop_intent:
                Intent intent1 = new Intent(this, SampleService.class);
                stopService(intent1);
                break;
            case R.id.btn_service:
                Intent intent2 = new Intent(this, MyStartService.class);
                startService(intent2);
                break;
            case R.id.btn_bind:
                Intent intent4 = new Intent(this, MyBindService.class);
                bindService(intent4, new MyServiceConn(), BIND_AUTO_CREATE);
                break;
            default:
                break;
        }
    }

    public class MyServiceConn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}
