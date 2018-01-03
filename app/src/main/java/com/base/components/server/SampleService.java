package com.base.components.server;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * IntentService 是继承自 Service 并处理异步请求的一个类，在 IntentService
 * 内有一个工作线程来处理耗时操作，当任务执行完后，IntentService 会自动停止，
 * 不需要我们去手动结束。如果启动 IntentService 多次，那么每一个耗时操作会以工
 * 作队列的方式在 IntentService 的 onHandleIntent 回调方法中执行，依次去执行，执行完自动结束。
 * Created by admin on 2018/1/3.
 */

public class SampleService extends IntentService {

    private boolean isRunning;
    private int num;

    public SampleService() {
        super("SampleService");
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public SampleService(String name) {
        super(name);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("service", "onCreate");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.i("service", "onHandleIntent");
        try {
            Thread.sleep(1000);
            Log.d("service", "ThreadName--------" + Thread.currentThread().getId());
            isRunning = true;
            num = 0;
            while (isRunning) {
                num++;
                if (num >= 10) {
                    isRunning = false;
                }
                Log.i("service", "num======================" + num);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("service", "onDestroy");
    }
}
