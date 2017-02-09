package com.example.a.a18_camera;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBtnClick(View v){

        String dirPath = Environment.getExternalStorageDirectory() + "/18Test";
        File dir = new File(dirPath);
        if (dir.exists()==false) {
            dir.mkdirs();
        }
        String filePath = dirPath+"/myImage.jpg";

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //카메라호출 - android.media.action.IMAGE_CAPTURE
        //C:\Users\a\AppData\Local\Android\sdk\platforms\android-25\data 의 activity_actions.txt 참고


        Uri uri = Uri.fromFile(new File(filePath));

        //API24의 nugat에서 오류가 발생하기 때문에 하위버전에서 동작하게 한다
        //emualtor도 23 version 마시멜로우를 사용하였다.
        if (Build.VERSION.SDK_INT < 24) {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            startActivity(intent);
        }else{
            //
        }
    }
}
