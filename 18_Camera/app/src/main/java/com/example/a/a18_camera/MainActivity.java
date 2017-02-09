package com.example.a.a18_camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBtnClick(View v){

        //암시적 intent 사용 예제
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,100);
        
        
        
//        
//        String dirPath = Environment.getExternalStorageDirectory() + "/18Test";
//        File dir = new File(dirPath);
//        if (dir.exists()==false) {
//            dir.mkdirs();
//        }
//        String filePath = dirPath+"/myImage.jpg";
//
//        Uri uri = Uri.fromFile(new File(filePath));
//
//        //API24의 nugat에서 오류가 발생하기 때문에 하위버전에서 동작하게 한다
//        //emualtor도 23 version 마시멜로우를 사용하였다.
//        if (Build.VERSION.SDK_INT < 24) {
//            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//            //카메라호출 - android.media.action.IMAGE_CAPTURE
//            //C:\Users\a\AppData\Local\Android\sdk\platforms\android-25\data 의 activity_actions.txt 참고
//
//            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
//            startActivityForResult(intent,1);
//        }else{
//            //
//        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Toast.makeText(this, "result:" + requestCode , Toast.LENGTH_SHORT).show();
        if(resultCode == RESULT_OK){
            //단, 용량이 크면 넘어오는데 문제가 된다.
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            ImageView imageView = (ImageView) findViewById(R.id.photoView);
            imageView.setImageBitmap(bitmap);
        }
        //super.onActivityResult(requestCode, resultCode, data);
    }
}
