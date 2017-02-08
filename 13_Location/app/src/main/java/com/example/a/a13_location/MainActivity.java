package com.example.a.a13_location;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView myTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myTextView = (TextView) findViewById(R.id.myTextView);
        String str = "";

        LocationManager manager = (LocationManager) getSystemService(LOCATION_SERVICE);
        List<String> providers = manager.getAllProviders();

        //위치정보를 제공해주는 3가지 정도의 프로바이더가 나온다
        //gps, network, passive

        for(String p : providers){
            str += "provider : " + p + " state : " + manager.isProviderEnabled(p) + "\n";
        }
        myTextView.setText(str);

        //google에서  "google play location api" 로 검색한다.

        //로케이션 리스너를 만들어서 callback 받게 하다.
        LocationListener listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            //int status - 받을수 있는지, 못받는지..의 값을 return한다.
                // https://developer.android.com/reference/android/location/LocationListener.html

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        //1초마다 돌면서 1미터 차이가 발생하면 리스너를 호출해라
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000,1.0f,listener);
        manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,1000,1.0f,listener);

    }
}
