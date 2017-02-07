package com.example.a.a05_listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    String[] list = {"Hello1","World1","Oracle1","java1",
            "Hello2","World2","Oracle2","java2",
            "Hello3","World3","Oracle3","java3",
            "Hello4","World4","Oracle4","java4"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.myListView);

    }
}
