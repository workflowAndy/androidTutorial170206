package com.example.a.a02_widget2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radio1:
                        Toast.makeText(MainActivity.this, "radio1 selected", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radio2:
                        Toast.makeText(MainActivity.this, "radio2 selected", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radio3:
                        Toast.makeText(MainActivity.this, "radio3 selected", Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        });

        CheckBox checkBox = (CheckBox) findViewById(R.id.myCheckBox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(MainActivity.this, "myCheckBox: " + isChecked, Toast.LENGTH_SHORT).show();
            }
        });

        Button button = (Button) findViewById(R.id.myButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "button clicked", Toast.LENGTH_SHORT).show();
            }
        });



    }
}
