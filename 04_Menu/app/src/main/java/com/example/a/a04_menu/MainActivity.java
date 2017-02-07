package com.example.a.a04_menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView textView = (TextView) findViewById(R.id.myTextView);
        registerForContextMenu(textView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_test1:
                Toast.makeText(this, "menu1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_test2:
                Toast.makeText(this, "menu2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_test3:
                Toast.makeText(this, "menu3", Toast.LENGTH_SHORT).show();
                break;

        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.setHeaderTitle("MyContextMenu");
        menu.add(0,1,0,"context menu1");
        menu.add(0,2,0,"context menu2");
        menu.add(0,3,0,"context menu3");

    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        super.onContextItemSelected(item);
        switch (item.getItemId()){
            case 1:
                Toast.makeText(this, "context 1 selected", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(this, "context 2 selected", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(this, "context 3 selected", Toast.LENGTH_SHORT).show();
                break;

        }
        return true;
    }
}
