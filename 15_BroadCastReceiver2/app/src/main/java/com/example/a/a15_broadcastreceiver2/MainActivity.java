package com.example.a.a15_broadcastreceiver2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int val = intent.getIntExtra("level",0);  //베터리 정보가 넘어옴
            Toast.makeText(context, "battery : " + val, Toast.LENGTH_SHORT).show();
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
        //화면이 보일때만 receiver가 동작하게 하고 싶은경우 , 등록한다.
        IntentFilter filter = new IntentFilter();
        //filter.addAction("android.intent.action.BATTERY_CHANGED");  아래와 같이 쓴다
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);

        registerReceiver(receiver,filter);

    }

    @Override
    protected void onStop() {
        super.onStop();
        //화면에 안보이면, 해제한다.
        unregisterReceiver(receiver);
    }
}
