package com.example.a.a03_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        Intent intent = getIntent();
        String strid = intent.getStringExtra("id");
        Toast.makeText(this, "id : " + strid, Toast.LENGTH_SHORT).show();

        Intent intent2 = new Intent();
        intent2.putExtra("myResult","111111111");
        setResult(RESULT_OK,intent2);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
