package com.example.a.a06_custom_listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    class MyData{
        String title;
        String desc;
        int imgIcon;
    }

    ArrayList<MyData> list = new ArrayList<MyData>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for(int i=0; i<20; i++){
            MyData data = new MyData();
            data.title = "My Title" + i;
            data.desc = "My Desc" + i;
            data.imgIcon = R.mipmap.ic_launcher;
            list.add(data);
        }

    }





}
