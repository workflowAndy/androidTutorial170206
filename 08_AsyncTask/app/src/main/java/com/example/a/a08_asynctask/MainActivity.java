package com.example.a.a08_asynctask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    class MyAsyncTask extends AsyncTask<Integer,Float,String>{

        @Override
        protected String doInBackground(Integer... params) {
            int startValue = params[0];
            for(;startValue < 100;startValue++){
                Log.d("AsysncTask","count" + startValue);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "do in background job";
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyAsyncTask myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute(30);
    }
}
