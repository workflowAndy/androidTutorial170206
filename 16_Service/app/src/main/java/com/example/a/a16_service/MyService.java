package com.example.a.a16_service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }

    //http://rdcworld-android.blogspot.kr/2011/07/service-lifecycle.html  참고 (라이프사이클)


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("myService","onCreate");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("myService","onDestroy");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("myService","onStartCommand");
        //return super.onStartCommand(intent, flags, startId);
        return START_STICKY;

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");

    }
}
