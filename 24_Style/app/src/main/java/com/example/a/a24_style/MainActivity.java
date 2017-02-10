package com.example.a.a24_style;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView selectedTextView, workingTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selectedTextView = (TextView) findViewById(R.id.selectedTextView);
        workingTextView = (TextView) findViewById(R.id.workingTextView);

        Button zeroButton = (Button) findViewById(R.id.zeroButton);
        Button oneButton = (Button) findViewById(R.id.oneButton);
        Button enterButton = (Button) findViewById(R.id.enterButton);

        View.OnClickListener numberListner = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = (Button) v;

                String working = workingTextView.getText().toString();
                String text = btn.getText().toString();

                if (working.equals("0")){
                    workingTextView.setText(text);
                }else{
                    workingTextView.append(text);
                }

            }
        };

        zeroButton.setOnClickListener(numberListner);
        oneButton.setOnClickListener(numberListner);

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = workingTextView.getText().toString();
                selectedTextView.setText(value);
                workingTextView.setText("0");
            }
        });


    }
}
