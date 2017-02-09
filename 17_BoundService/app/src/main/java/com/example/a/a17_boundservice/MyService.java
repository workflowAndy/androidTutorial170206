package com.example.a.a17_boundservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Random;

public class MyService extends Service {
    public MyService() {
    }

    //inner class생성
    public class MyBinder extends Binder{
        public  MyService getService(){
            return MyService.this;
        }
    }

    MyBinder myBinder = new MyBinder();

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return myBinder;
    }

    Random random = new Random();
    public  int getRandomNumber(){
        return random.nextInt(100);
    }
}
