package com.example.a.a05_listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "value: " + list[position], Toast.LENGTH_SHORT).show();
            }
        });


    }
}
