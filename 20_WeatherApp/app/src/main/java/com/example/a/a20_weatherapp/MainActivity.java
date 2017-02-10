package com.example.a.a20_weatherapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MyAdapter myAdapter;
    Button myButton;

    /*<hour>18</hour>
    <day>0</day>
    <temp>-3.0</temp>
    <wfKor>구름 조금</wfKor>
    */
    class WeatherData {
        String info1; // hour - 시간
        String info2; // temp - 온도
        String info3; // wfkor - 날씨
    }

    ArrayList<WeatherData> wArrayList = new ArrayList<>();

    class MyAdapter extends BaseAdapter {
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

            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.weatheritem, null);
            }

            TextView wInfo1 = (TextView) convertView.findViewById(R.id.weatherInfo1);
            TextView wInfo2 = (TextView) convertView.findViewById(R.id.weatherInfo2);

            WeatherData wData = wArrayList.get(position);

            wInfo1.setText(wData.info1 + " " + wData.info2);
            wInfo2.setText(wData.info3);

            return convertView;
        }
    }


    class MyPullParserTask extends AsyncTask<String, Void, String> {


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            myButton.setText(s);
            myAdapter.notifyDataSetChanged();
        }

        @Override
        protected String doInBackground(String... params) {

            String title = "default value";

            try {
                XmlPullParser xpp = XmlPullParserFactory.newInstance().newPullParser();

                URL url = new URL(params[0]);
                xpp.setInput(url.openStream(), "utf-8");

                int eventType = xpp.getEventType();
                title = xpp.toString();

                //eof 일때까지 looping
                boolean bRead = false;
                boolean bRead2 = false;
                boolean bRead3 = false;

                int j = 0;

                WeatherData weatherData = null;

                while (eventType != XmlPullParser.END_DOCUMENT) {


                    switch (eventType) {
                        case XmlPullParser.START_TAG:
                            if ("pubDate".equals(xpp.getName())) {
                                bRead2 = true;
                            }

                            if ("category".equals(xpp.getName())) {
                                bRead3 = true;
                            }

                            if ("hour".equals(xpp.getName())) {
                                weatherData = new WeatherData();
                                j=1;
                            }else if ("temp".equals(xpp.getName())) {
                                j=2;
                            }else if ("wfKor".equals(xpp.getName())) {
                                j=3;
                            }
                            break;
                        case XmlPullParser.TEXT:

                            if (j==1) {
                                weatherData.info1 = "시간 : " + xpp.getText();
                            } else if (j==2) {
                                weatherData.info2 = "온도 : " + xpp.getText();
                            } else if (j==3) {
                                weatherData.info3 = "날씨 : " + xpp.getText();
                                wArrayList.add(weatherData);
                                j = 0;
                            }


                            if (bRead2) {
                                title = xpp.getText();
                                bRead2 = false;
                            }

                            if (bRead3) {
                                title += xpp.getText();
                                bRead3 = false;
                            }

                            break;
                        case XmlPullParser.END_TAG:
                            break;
                    }


                    eventType = xpp.next();

                }


            } catch (Exception e) {
                e.printStackTrace();
            }

            return title;

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myButton = (Button) findViewById(R.id.weatherBtn);

        //    loadData();

       //loadData2();

        ListView listView = (ListView) findViewById(R.id.wListView);
        myAdapter = new MyAdapter();
        listView.setAdapter(myAdapter);

    }

    private void loadData2() {
        String strUrl = "http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=4146351000";
        MyPullParserTask myPullParserTask = new MyPullParserTask();
        myPullParserTask.execute(strUrl);

    }


    private void loadData() {
        for (int i = 0; i < 20; i++) {
            WeatherData weatherData = new WeatherData();
            weatherData.info1 = "시간 : " + i;
            weatherData.info2 = "온도 : " + i;
            weatherData.info3 = "날씨 : " + i;

            wArrayList.add(weatherData);
        }


    }

    public void onBtnClick(View v) {
        //wArrayList.clear();

        loadData2();

        //1. 기상청으로 부터 XML Data를 가져온다
        //2. ListView를 clear한다
        //3. ListView에 넣는다
    }


}
