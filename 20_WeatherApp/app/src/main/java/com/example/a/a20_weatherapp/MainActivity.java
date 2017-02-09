package com.example.a.a20_weatherapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MyAdapter myAdapter;
    /*<hour>18</hour>
    <day>0</day>
    <temp>-3.0</temp>
    <wfKor>구름 조금</wfKor>
    */
    class WeatherData {
        String info1; // wfkor
        String info2; //hour , temp , wfkor
    }

    ArrayList<WeatherData> wArrayList = new ArrayList<>();

    class MyAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return wArrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return wArrayList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null){
                convertView = getLayoutInflater().inflate(R.layout.weatheritem,null);
            }

            TextView wInfo1 = (TextView) convertView.findViewById(R.id.weatherInfo1);
            TextView wInfo2 = (TextView) convertView.findViewById(R.id.weatherInfo2);

            WeatherData wData = wArrayList.get(position);

            wInfo1.setText(wData.info1);
            wInfo2.setText(wData.info2);

            return convertView;
        }
    }

    class MyPullParserTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... params) {

            try {
                XmlPullParser xpp = XmlPullParserFactory.newInstance().newPullParser();

                URL url = new URL(params[0]);
                xpp.setInput(url.openStream(),"utf-8");

                int eventType = xpp.getEventType();

                //eof 일때까지 looping
                while(eventType != XmlPullParser.END_DOCUMENT){

                }


            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }


            return null;
        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadData();

        ListView listView = (ListView) findViewById(R.id.wListView);
        myAdapter = new MyAdapter();
        listView.setAdapter(myAdapter);

    }

    private void loadData(){
        for (int i=0; i < 20 ; i++){
            WeatherData weatherData = new WeatherData();
            weatherData.info1 = "날씨 : " + i;
            weatherData.info2 = "시간 : " + i + " 온도: " + i;

            wArrayList.add(weatherData);
        }


    }
    public void  onBtnClick(View v){
        wArrayList.clear();

        //loadData();

        //1. 기상청으로 부터 XML Data를 가져온다
        //2. ListView를 clear한다
        //3. ListView에 넣는다
    }


}
