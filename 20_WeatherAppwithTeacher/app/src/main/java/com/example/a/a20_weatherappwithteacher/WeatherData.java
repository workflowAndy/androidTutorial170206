package com.example.a.a20_weatherappwithteacher;

/**
 * Created by a on 2017-02-10.
 */

public class WeatherData {
    String weather;
    float temperature;
    int day;
    int hour;
    int Image;

    public String toString(){
        String a= " weather: " + weather + " temperature: " +temperature + " day: " + day + " hour: " +hour + " Image: " +Image;
        return  a;
    }
}
