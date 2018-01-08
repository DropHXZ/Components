package com.base.components.ui;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.base.components.R;
import com.base.components.server.ForeGroundService;
import com.base.components.server.MyBindService;
import com.base.components.server.MyStartService;
import com.base.components.server.SampleService;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_intent;
    private Button btn_stop_intent;
    private Button btn_service;
    private Button btn_bind, btn_bind_stop;
    private Button btn_bind_foreground;

    private MyBindService myBindService = null;
    private MyServiceConn mConn;
    private MyBindService.MyBinder myBinder;

    private boolean isRegister = false;
    private Intent intent4;

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
        btn_bind_stop = findViewById(R.id.btn_bind_stop);
        btn_bind_foreground = findViewById(R.id.btn_bind_foreground);
        btn_intent.setOnClickListener(this);
        btn_stop_intent.setOnClickListener(this);
        btn_service.setOnClickListener(this);
        btn_bind.setOnClickListener(this);
        btn_bind_stop.setOnClickListener(this);
        btn_bind_foreground.setOnClickListener(this);

        mConn = new MyServiceConn();
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
                if (intent4 == null) {
                    intent4 = new Intent(this, MyBindService.class);
                }
                intent4.putExtra("param_bind", "P".getBytes());
                isRegister = bindService(intent4, mConn, BIND_AUTO_CREATE);
                break;
            case R.id.btn_bind_stop:
                if (intent4 != null && isRegister) {
                    unbindService(mConn);
                    isRegister = false;
                }
                break;
            case R.id.btn_bind_foreground:
                Intent intent3 = new Intent(this, ForeGroundService.class);
                startService(intent3);
//                startForegroundService(intent3);
                break;
            default:
                break;
        }
    }

    /**
     * 让activity和service产生联系
     */
    public class MyServiceConn implements ServiceConnection {
        /**
         * Called when a connection to the Service has been established, with
         * the {@link android.os.IBinder} of the communication channel to the
         * Service.
         *
         * @param name    The concrete component name of the service that has
         *                been connected.
         * @param service The IBinder of the Service's communication channel,
         *                which you can now make calls on.
         */
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i("service", "onServiceConnected");
            myBindService = ((MyBindService.MyBinder) service).getService();
            myBinder = (MyBindService.MyBinder) service;
            myBinder.getService();
            myBinder.getData();
        }

        /**
         * Called when a connection to the Service has been lost.  This typically
         * happens when the process hosting the service has crashed or been killed.
         * This does <em>not</em> remove the ServiceConnection itself -- this
         * binding to the service will remain active, and you will receive a call
         * to {@link #onServiceConnected} when the Service is next running.
         *
         * @param name The concrete component name of the service whose
         *             connection has been lost.
         */
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i("service", "onServiceDisconnected");
        }
    }
}
