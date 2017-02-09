package com.example.a.a17_boundservice;

import android.app.IntentService;
import android.content.Intent;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class MyIntentService2 extends IntentService {

    public MyIntentService2() {
        super("MyIntentService2");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            //잠깐 실행되는 background 서비스
            //데용량 데이터 download에 사용
            //실행후 자기의 할일을 다했을때 종료되는 용도
        }
    }


}
