package com.example.a.a11_sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView) findViewById(R.id.textView);
        TestSQLiteHandler dbHandler = new TestSQLiteHandler(this);

        dbHandler.insert("kim",10,"서울");
        dbHandler.insert("lee",20,"경기");
        dbHandler.insert("park",30,"seoul");

        dbHandler.update("kim",15);


        String str = dbHandler.selectAll();
        textView.setText(str);


    }
}
