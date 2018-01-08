package com.base.components.server;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * 绑定式服务
 * Created by admin on 2018/1/3.
 */

public class MyBindService extends Service {

    private MyBinder myBinder = new MyBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        byte[] bytes = intent.getByteArrayExtra("param_bind");
        Log.w("service","onBind");
        return myBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.w("service","onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.w("service","onDestroy");
        super.onDestroy();
    }

    public class MyBinder extends Binder {

        public MyBindService getService() {
            Log.i("service", "call1--------------");
            return MyBindService.this;
        }

        public void getData(){

        }
    }
}
