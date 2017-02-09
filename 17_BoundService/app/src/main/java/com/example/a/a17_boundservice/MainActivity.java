package com.example.a.a17_boundservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //http://rdcworld-android.blogspot.kr/2011/07/service-lifecycle.html
    //MainActivity에서 Service의 Public Method를 호출 할수 있다.

    MyService myService;
    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //myservice의  public IBinder onBind(Intent intent) 의 iBinder가 넘오옮
            MyService.MyBinder binder = (MyService.MyBinder) service;
            myService = binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this,MyService.class);
        bindService(intent,conn,BIND_AUTO_CREATE); //bind시 자동으로 서비스를 create한다.
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(conn);
    }

    public void onBtnClick(View v){
        int num = myService.getRandomNumber();
        Toast.makeText(myService, "num" + num, Toast.LENGTH_SHORT).show();
    }
}
