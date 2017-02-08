package com.example.a.a11_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by a on 2017-02-08.
 */

public class TestSQLiteHandler {
    TestSQLiteOpenHelper openHelper;

    public TestSQLiteHandler(Context context){
        openHelper = new TestSQLiteOpenHelper(context,"dbname",null,1);
    }

    public void insert(String name, int age, String address){
        SQLiteDatabase db = openHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("age",age);
        values.put("address",address);

        db.insert("student",null,values);

    }

    public void delete(String name){
        SQLiteDatabase db = openHelper.getWritableDatabase();
        db.delete("student","name=?",new String[] {name});
    }

    public void update(String name,int newAge){
        SQLiteDatabase db = openHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("age",newAge);

        db.update("student",contentValues,"name=?",new String[] {name});
    }

    public String selectAll(){
        SQLiteDatabase db = openHelper.getReadableDatabase();
        String res = "";

        Cursor c = db.query("student",null,null,null,null,null,null);

        while (c.moveToNext()){
            String name = c.getString(c.getColumnIndex("name"));
            int age = c.getInt(c.getColumnIndex("age"));
            String address = c.getString(c.getColumnIndex("address"));

            res += "name: " + name + " age: " + age + " address : " + address + "\n";
        }

        return res;
    }

}
