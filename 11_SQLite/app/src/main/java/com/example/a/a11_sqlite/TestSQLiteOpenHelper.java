package com.example.a.a11_sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by a on 2017-02-08.
 */

public class TestSQLiteOpenHelper extends SQLiteOpenHelper {

    public TestSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
                        //db이름
        super(context, name, factory, version);
        //자동적으로 db가 없으면  생성해 준다
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Table 생성한다.
        //app 이 인스톨될때 호출된다.
        String strSQL = "CREATE TABLE student(id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "name TEXT , age INTEGER, address TEXT);";
        db.execSQL(strSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //oldversion 과 newversion이 다를때 처리를 하는 로직이 들어간다.
        //alert table
        //
        String strSQL = "DROP Table if exist student";
        db.execSQL(strSQL);
        onCreate(db);
    }
}
