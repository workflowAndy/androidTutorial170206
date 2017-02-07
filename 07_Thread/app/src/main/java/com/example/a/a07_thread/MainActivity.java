package com.example.a.a07_thread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    static final int MY_THREAD_TEST = 100;
    TextView myTextView;
    ProgressBar myProgressBar;

    class MyThread extends Thread{

        @Override
        public void run() {
            for(int i=0 ; i<20; i++){
                Log.d("Tread Test","count" +i);
          //      myTextView.setText("count"+i);

                Message msg = new Message();

                msg.what = MY_THREAD_TEST;
                msg.arg1 = i;
                handler.sendMessage(msg);

                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == MY_THREAD_TEST){
                myTextView.setText("count:" + msg.arg1);
                myProgressBar.setProgress(msg.arg1+1);
            }
            //super.handleMessage(msg);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myTextView = (TextView) findViewById(R.id.myTextView);
        myProgressBar = (ProgressBar) findViewById(R.id.myProcessBar);
    }


    public void onBtnClick(View view){
        MyThread myThread = new MyThread();
        myThread.start();
    }
}
