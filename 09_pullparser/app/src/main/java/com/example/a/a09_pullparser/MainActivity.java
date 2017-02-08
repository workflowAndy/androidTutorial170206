package com.example.a.a09_pullparser;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;
import java.util.ArrayList;

import static com.example.a.a09_pullparser.R.id.weatherTextView;

public class MainActivity extends AppCompatActivity {
    class WeatherData{
        int day;
        int hour;
        float temp;
        String wfKor;

        @Override
        public String toString() {
            String res = "day:" +day + " hour:" + hour + " temp:" + temp + " wfkor" +wfKor;
            return res;
        }
    }

    ArrayList<WeatherData> list = new ArrayList<>();

    enum DataType {none, hourType, dayType, tempType, wfKorType}
    DataType type =  DataType.none;

    TextView weatherTextView;

    class MyPullParserTask extends AsyncTask<String,Void,String>{

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            String res = "";
            for(WeatherData data:list){
                res += data.toString() +"\n";
            }

            weatherTextView.setText(res);
        }

        @Override
        protected String doInBackground(String... params) {
            String res = "";

            try {
                XmlPullParser xpp = XmlPullParserFactory.newInstance().newPullParser();
                URL url = new URL(params[0]);
                xpp.setInput(url.openStream(),"utf-8");
                int eventType = xpp.getEventType();

                boolean bRead = false;

                WeatherData data = null;

                while (eventType != XmlPullParser.END_DOCUMENT){
                    switch (eventType){
                        case XmlPullParser.START_TAG:
                            xpp.getName(); // tag명을 알수 있음
                            if(xpp.getName().equals("hour")){
                                type = DataType.hourType;
                                data = new WeatherData();
                                list.add(data); // data생성
                            }else if(xpp.getName().equals("wfKor")){
                                type = DataType.wfKorType;
                            }else if(xpp.getName().equals("day")) {
                                type = DataType.dayType;
                            }else if(xpp.getName().equals("temp")) {
                                type = DataType.tempType;
                            }

                            break;
                        case XmlPullParser.TEXT:
                            switch (type){
                                case hourType:
                                    data.hour = Integer.parseInt(xpp.getText());
                                    break;
                                case dayType:
                                    data.day = Integer.parseInt(xpp.getText());
                                    break;
                                case tempType:
                                    data.temp = Float.parseFloat(xpp.getText());
                                    break;
                                case wfKorType:
                                    data.wfKor = xpp.getText();
                                    break;
                            }
                            type = DataType.none;
//                            if (bRead) {
//                                xpp.getText(); // 텍스트를 알수 있음.
//                                res += "날씨 : " + xpp.getText() + "\n";
//                                bRead = false;
//                            }
                            break;
                        case XmlPullParser.END_TAG:
                            break;
                    }
                    eventType = xpp.next();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return res;
        }


    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weatherTextView = (TextView) findViewById(weatherTextView);

        //개발방향
        MyPullParserTask myPullParserTask = new MyPullParserTask();
        myPullParserTask.execute("http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=4146351000");


        //pullparser
        //https://developer.android.com/reference/org/xmlpull/v1/XmlPullParser.html

        //기상청- 홈 > 날씨> 생활과산업> 서비스 > 인터넷 > RSS
        //http://www.kma.go.kr/weather/lifenindustry/sevice_rss.jsp?sido=4100000000&gugun=4146300000&dong=4146351000&x=24&y=10
        //http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=4146351000

//        try {
//            URL url = new URL("http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=4146351000");
//            XmlPullParserFactory factory =  XmlPullParserFactory.newInstance();
//            XmlPullParser xpp = factory.newPullParser();
//            xpp.setInput(url.openStream(),"UTF-8");
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (XmlPullParserException e) {
//            e.printStackTrace();
        }


    }
