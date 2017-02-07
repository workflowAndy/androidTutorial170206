package com.example.a.a06_custom_listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    class MyData{
        String title;
        String desc;
        int imgIcon;
    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
            //return 0;
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
            //return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
           // return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null){
                convertView = getLayoutInflater().inflate(R.layout.item_view,null);
            }

            TextView titleTextView = (TextView) convertView.findViewById(R.id.titleTextView);
            TextView descTextView = (TextView) convertView.findViewById(R.id.descTextView);
            ImageView imgIcon = (ImageView) convertView.findViewById(R.id.itemIcon);


            MyData data = list.get(position);
            titleTextView.setText(data.title);
            descTextView.setText(data.desc);
            imgIcon.setImageResource(data.imgIcon);


            return convertView;
            //return null;
        }
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

            if (i%2 ==0) {
                data.imgIcon = R.mipmap.ic_launcher;
            }else{
                data.imgIcon = R.drawable.ic_accessibility_black_24dp;
            }
            list.add(data);
        }

        ListView listView = (ListView) findViewById(R.id.myListView);
        MyAdapter adapter = new MyAdapter();
        listView.setAdapter(adapter);

    }





}
