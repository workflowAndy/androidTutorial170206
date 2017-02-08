package com.example.a.a13_location;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView myTextView;
    Geocoder geocoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //주소를 가져오기 위해서
        geocoder = new Geocoder(this);


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

                try {
                    // 맨마지막 10개는 해당 위,경도 에 해당하는 주소를 10개까지 return한다.
                    List<Address> list = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),10);

                    Address a = list.get(0); //첫번째것만 가져온다.
                    myTextView.append(a.toString());

                } catch (IOException e) {
                    e.printStackTrace();
                }

                String str = "lat : " + location.getLatitude() + " lon: " + location.getLongitude() +
                        " alt : " + location.getAltitude() +"\n";
                myTextView.append(str);
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

        //만약 s(LocationManager.GPS_PROVIDER,0,0,listener); 이면 로케이션매니저가 알아서 호출한다
        //또는 category를 이용하여, 목적에 맞게 선택할수도 있다. googling 해봐라
        //실제 device에서는 network_provider에서만 정보를 받아온다고 함- 강사

    }

    public void onBtnClick(View v){
        try {
            List<Address> list = geocoder.getFromLocationName("서울 구로구 구로3동",10);
            Address a = list.get(0);
            myTextView.setText(a.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
