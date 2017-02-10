package com.example.a.a24_style;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView selectedTextView, workingTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selectedTextView = (TextView) findViewById(R.id.selectedTextView);
        workingTextView = (TextView) findViewById(R.id.workingTextView);


//        Button enterButton = (Button) findViewById(R.id.enterButton);

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

        TableLayout tableLayout = (TableLayout) findViewById(R.id.activity_main);

        // textview=0, textview=1, include=2 ,마지막줄은 뺌 (1~9까지 표시하고, 나머지 버튼3개는 다르게 함)
        int  num = 1;
        for (int i=2; i <tableLayout.getChildCount() -1; i++){
            TableRow tableRow = (TableRow) tableLayout.getChildAt(i);

            for (int k=0; k <tableRow.getChildCount(); k++){
                Button button = (Button) tableRow.getChildAt(k);
                button.setText(""+num);
                button.setOnClickListener(numberListner);
                num +=1;


            }


        }

        TableRow bottomRow = (TableRow) tableLayout.getChildAt(tableLayout.getChildCount() -1);
        Button deleteButton = (Button) bottomRow.getChildAt(0);
        Button zeroButton = (Button) bottomRow.getChildAt(1);
        Button enterButton = (Button) bottomRow.getChildAt(2);

        deleteButton.setText("delete");
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workingTextView.setText("0");
            }
        });

        zeroButton.setText("0");
        zeroButton.setOnClickListener(numberListner);

        enterButton.setText("enter");
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
