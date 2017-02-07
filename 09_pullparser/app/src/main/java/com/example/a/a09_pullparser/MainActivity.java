package com.example.a.a09_pullparser;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.AsyncLayoutInflater;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    TextView weatherTextView;

    class MyPullParserTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... params) {
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            weatherTextView.setText(s);
        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weatherTextView = (TextView) findViewById(R.id.weatherTextView);

        //개발방향
        MyPullParserTask myPullParserTask = new MyPullParserTask();
        myPullParserTask.execute("http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=4146351000");


        //pullparser
        //https://developer.android.com/reference/org/xmlpull/v1/XmlPullParser.html

        //기상청- 홈 > 날씨> 생활과산업> 서비스 > 인터넷 > RSS
        //http://www.kma.go.kr/weather/lifenindustry/sevice_rss.jsp?sido=4100000000&gugun=4146300000&dong=4146351000&x=24&y=10
        //http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=4146351000

        try {
            URL url = new URL("http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=4146351000");
            XmlPullParserFactory factory =  XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(url.openStream(),"UTF-8");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }


    }
}
