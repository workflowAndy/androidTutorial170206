package com.example.a.a19_notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onBtnClick(View v){

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Intent intent = new Intent(this,NotiActivity.class); //다른 activity를 띠우기 위해서 생성
        PendingIntent pIntent = PendingIntent.getActivity(this,0,intent,0);


        //Notification.Builder builder = new Notification.Builder(this);
        //하위버전도 사용할수 있도록 하기와 같이 NotificationCompat 을 사용
        NotificationCompat.Builder  builder1 = new NotificationCompat.Builder(this);

        builder1.setContentTitle("Title");
        builder1.setContentText("TEXT");
        builder1.setSubText("Sub Text");
        builder1.setSmallIcon(R.mipmap.ic_launcher);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.lghthouse);
        builder1.setLargeIcon(bitmap);

        builder1.setContentIntent(pIntent); //notification click시 intent수행
        builder1.setAutoCancel(true);  //클릭시 사라짐

        manager.notify(1234,builder1.build());  // Notification.Builder builder = new Notification.Builder(this); 를 쓰면 하위버젼을 16이상으로 하라고 오류발생

    }


}
