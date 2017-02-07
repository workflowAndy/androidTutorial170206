package com.example.a.a07_thread;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    class MyThread extends Thread{

        @Override
        public void run() {
            for(int i=0 ; i<20; i++){
                Log.d("Tread Test","count" +i);
                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onBtnClick(View view){
        MyThread myThread = new MyThread();
        myThread.start();
    }
}
