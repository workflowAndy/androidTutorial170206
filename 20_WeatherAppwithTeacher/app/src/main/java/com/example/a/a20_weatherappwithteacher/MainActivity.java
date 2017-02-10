package com.example.a.a20_weatherappwithteacher;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<WeatherData> weatherDataArrayList = new ArrayList<>();
    MyAdapter myAdapter = null;

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return weatherDataArrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return weatherDataArrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            if (convertView == null){
                Log.d("MainActivity","Null Postion: " + position);
                convertView = getLayoutInflater().inflate(R.layout.list_item,null);
            }else {
                Log.d("MainActivity","Postion: " + position);

            }

            TextView weather = (TextView) convertView.findViewById(R.id.weather);
            TextView temperature = (TextView) convertView.findViewById(R.id.temperature);
            TextView datetime = (TextView) convertView.findViewById(R.id.datetime);
            ImageView itemImage  = (ImageView) convertView.findViewById(R.id.itemImage);

            WeatherData weatherData = weatherDataArrayList.get(position);

            weather.setText(weatherData.weather);
            temperature.setText(""+weatherData.temperature);
            datetime.setText(""+weatherData.hour);
            itemImage.setImageResource(weatherData.Image);

            return convertView;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.myListView);
        myAdapter = new MyAdapter();
        listView.setAdapter(myAdapter);

        intData();
    }

    public void onBtnClick(View v){
        intData();
    }

    private void intData(){
        weatherDataArrayList.clear();

        String strUrl = "http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=4146351000";
        WeatherPullParser weatherPullParser = new WeatherPullParser(weatherDataArrayList,myAdapter);
        weatherPullParser.execute(strUrl);
    }

}
