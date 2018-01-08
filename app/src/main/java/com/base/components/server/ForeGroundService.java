package com.base.components.server;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.base.components.ui.MainActivity;
import com.base.components.R;

/**
 * 前台服务
 * Created by admin on 2018/1/8.
 */

public class ForeGroundService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 在API11之后构建Notification的方式
        Notification.Builder builder = new Notification.Builder(getApplicationContext()); //获取一个Notification构造器
        Intent nfIntent = new Intent(getApplicationContext(), MainActivity.class);
        builder
//        .setContentIntent(PendingIntent.getActivity(getApplicationContext(), 0, nfIntent, 0)) // 设置PendingIntent
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher)) // 设置下拉列表中的图标(大图标)
                .setContentTitle("Title") // 设置下拉列表里的标题
                .setSmallIcon(R.mipmap.ic_launcher) // 设置状态栏内的小图标
                .setContentText("要显示的内容") // 设置上下文内容
                .setWhen(System.currentTimeMillis()); // 设置该通知发生的时间

        Notification notification = builder.build(); // 获取构建好的Notification
        notification.defaults = Notification.DEFAULT_SOUND; //设置为默认的声音
        startForeground(110, notification);
        return super.onStartCommand(intent, flags, startId);
    }
}
