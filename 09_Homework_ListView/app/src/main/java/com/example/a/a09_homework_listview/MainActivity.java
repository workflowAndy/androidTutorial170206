package com.example.a.a09_homework_listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    class WeatherData{
        String weather;
        float temperature;
        int day;
        int hour;
        int Image;

    }
    ArrayList<WeatherData> weatherDataArrayList = new ArrayList<>();

    class WeatherAdapter extends BaseAdapter{

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
                convertView = getLayoutInflater().inflate(R.layout.list_item2,null);
            }

            TextView wetherInfo1 = (TextView) convertView.findViewById(R.id.weatherInfo1);
            TextView wetherInfo2 = (TextView) convertView.findViewById(R.id.weatherInfo2);

            WeatherData data = weatherDataArrayList.get(position);

            String info1 = "날씨: " + data.weather + " 온도: " + data.temperature;

            Date date = new Date();
            date.setDate(data.hour);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 HH시");
            String info2 = sdf.format(date);

            //String info2 = "day " + data.day + " hour: " + data.hour;

            wetherInfo1.setText(info1);
            wetherInfo2.setText(info2);

            return convertView;
        }
    }




    class MyData {
        String weather;
        String temperature;
        String datetime;
        int Image;
    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return arrayList.get(position);
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

            MyData myData = arrayList.get(position);

            weather.setText(myData.weather);
            temperature.setText(myData.temperature);
            datetime.setText(myData.datetime);
            itemImage.setImageResource(myData.Image);

            return convertView;
        }
    }



    ArrayList<MyData> arrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //기초 data만들기1
        for(int i=0; i < 20; i++){
            MyData myData = new MyData();

            if (i%2 == 0) {
                myData.weather = "날씨: 맑음";
                myData.temperature = "온도: 11도";
                myData.datetime = "2017년1월1일 오전 1시";
                myData.Image = R.drawable.clean;
            }else{
                myData.weather = "날씨: 비";
                myData.temperature = "온도: 10도";
                myData.datetime = "2017년1월2일 오전 2시";
                myData.Image =  R.drawable.rainy;
            }
            arrayList.add(myData);

        }

        ListView listView = (ListView) findViewById(R.id.myListView);
        MyAdapter myAdapter = new MyAdapter();
        listView.setAdapter(myAdapter);


        //기초 data만들기2
        for(int i=0; i < 20; i++) {
            WeatherData weatherData = new WeatherData();

            switch (i%4){
                case 0:
                    weatherData.weather = "맑음";
                    weatherData.Image = R.drawable.clean;
                    break;
                case 1:
                    weatherData.weather = "흐림";
                    weatherData.Image = R.drawable.cloudy;
                    break;
                case 2:
                    weatherData.weather = "비";
                    weatherData.Image = R.drawable.rainy;
                    break;
                case 3:
                    weatherData.weather = "눈";
                    weatherData.Image = R.drawable.snow;
                    break;
            }

            weatherData.temperature = 10.0f;
            weatherData.day = (i*3)/24;
            weatherData.hour = (i*3)%24;

            weatherDataArrayList.add(weatherData);
         }

        ListView listView2 = (ListView) findViewById(R.id.myListView2);
        WeatherAdapter weatherAdapter = new WeatherAdapter();
        listView2.setAdapter(weatherAdapter);

    }
}
