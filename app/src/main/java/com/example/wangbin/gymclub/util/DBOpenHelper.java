package com.example.wangbin.gymclub.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {
    private static final String DATABASENAME = "club.db"; //数据库名称
    private static final int DATABASEVERSION = 4;//数据库版本

    public DBOpenHelper(Context context) {
        super(context, DATABASENAME, null, DATABASEVERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE article (id integer primary key ,title varchar(50),time varchar(25), content text,author varchar(20))");//执行有更改的sql语句
        db.execSQL("CREATE TABLE teacher (id integer primary key ,name varchar(50),phone varchar(25), email varchar(25),type varchar(20)," +
                "intro varchar(255),context varchar(255))");
    }
    //数据库版本或表结构改变会被调用
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS article");
        db.execSQL("DROP TABLE IF EXISTS teacher");
        onCreate(db);
    }


}