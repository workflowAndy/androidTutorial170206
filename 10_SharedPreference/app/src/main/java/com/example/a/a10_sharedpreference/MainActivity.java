package com.example.a.a10_sharedpreference;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences pref = getSharedPreferences("mySetting",MODE_PRIVATE);
        String id = pref.getString("id","");

        Toast.makeText(this, "id: " + id, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences pref = getSharedPreferences("mySetting",0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("id","abcd");
        editor.commit();

    }
}
