package com.example.a.a15_broadcastreceiver2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String action  = intent.getAction();
            if (action.equals(Intent.ACTION_BATTERY_CHANGED)) {
                int val = intent.getIntExtra("level", 0);  //베터리 정보가 넘어옴
                Toast.makeText(context, "battery : " + val, Toast.LENGTH_SHORT).show();
            }else if (action.equals(Intent.ACTION_BATTERY_LOW)){
                int val = intent.getIntExtra("level", 0);  //베터리 정보가 넘어옴
                Toast.makeText(context, "battery Low : " + val, Toast.LENGTH_SHORT).show();
            }else if (action.equals("packagename.ABC")){ //custome action에 대한 처리를 한다.
                Toast.makeText(context, "My Broadcast", Toast.LENGTH_SHORT).show();
            }
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
        filter.addAction(Intent.ACTION_BATTERY_LOW);  //low일때 발생 (15% 이하)

        filter.addAction("packagename.ABC"); //custome action 추가

        registerReceiver(receiver,filter);

    }

    @Override
    protected void onStop() {
        super.onStop();
        //화면에 안보이면, 해제한다.
        unregisterReceiver(receiver);
    }

    public void onBtnClick(View v){
        Intent intent = new Intent("packagename.ABC");
        sendBroadcast(intent);

    }
}
