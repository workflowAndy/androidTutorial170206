package com.example.a.a20_weatherappwithteacher;

import android.os.AsyncTask;
import android.widget.BaseAdapter;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;
import java.util.ArrayList;

/**
 * Created by a on 2017-02-10.
 */

public class WeatherPullParser extends AsyncTask<String,Void,String> {

    enum DataType {none, hourType, dayType, tempType, wfKorType}
    DataType type =  DataType.none;


    ArrayList<WeatherData> list = new ArrayList<>();
    BaseAdapter adapter = null;

    public WeatherPullParser(ArrayList<WeatherData> list, BaseAdapter adapter) {
        this.list = list;
        this.adapter = adapter;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        String res = "";
        for(WeatherData data:list){
            res += data.toString() +"\n";
        }

        adapter.notifyDataSetChanged();

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
                                data.temperature = Float.parseFloat(xpp.getText());
                                break;
                            case wfKorType:
                                data.weather = xpp.getText();
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

