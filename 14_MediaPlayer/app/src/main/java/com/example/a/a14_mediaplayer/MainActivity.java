package com.example.a.a14_mediaplayer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SeekBar;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mp = null;
    SeekBar seekBar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //번외-특정폴더의 File 목록 가져오기
//        String path = Environment.getExternalStorageDirectory() + "/Download";
//        File dir = new File(path);
//        File[] files = dir.listFiles();
//        files[0].getName(); //이런식으로 목록을 가져올수 있다.

        seekBar = (SeekBar) findViewById(R.id.playSeekBar);

        //seekbar를 움직이면, 음원의 소리가 바뀌어야 한다.
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            //움직이는 중
            //seekbar의 변수가 변화될때
            //fromUser는 사람이 변경한건이다.
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //사람이 변경한경우만.. 아래의 thread에서 변경하는것은 무시한다.
                if(fromUser){
                    if (mp !=null){
                        mp.seekTo(progress); //해당 밀리세컨드 단위로 이동함
                    }
                }
            }

            //눌렀을때
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            //손을 땔때
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void onPlayClick(View v){
        mp = new MediaPlayer();

        //root path를 찾아온다.
        String path = Environment.getExternalStorageDirectory()
                + "/Samsung/Music/Over the Horizon.mp3";

        try {
            mp.setDataSource(path);
            mp.prepare();
            mp.start();

            //음원의 밀리세컨드 단위를 return
            seekBar.setMax(mp.getDuration());

            //간단하게 스레드 만들기
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (mp != null) {

                        //현재 음원의 위치를 밀리 세컨드 단위로 return
                        //start 상태일때만 mp.getCurrentPosition() 를 가져올수 있다.
                        seekBar.setProgress(mp.getCurrentPosition());

                        try {
                            Thread.sleep(100); //0.1 초마다 아래것을 수행
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        /* 강사는 아래와 같이 처리함. = 퉁 쳐서 처리
                           원래는 mediaplay의 상태값을 고려해서 coding이 되어야함
                           하기 참조 필요
                           https://developer.android.com/reference/android/media/MediaPlayer.html
                        try {
                            //현재 음원의 위치를 밀리 세컨드 단위로 return
                            seekBar.setProgress(mp.getCurrentPosition());

                            Thread.sleep(100); //0.1 초마다 아래것을 수행
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        */


                    }
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }

        //기타 : 특정폴더의 음악리스트를 만들어서 별도의 activity를 띄어서, 뮤직플레이어를 만들수 잇다.

    }

    public void onStopClick(View v){
        if(mp != null) {
            mp.stop();
            mp.release();
            mp = null;
        }
    }
}
