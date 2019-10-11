package com.example.mxm.myapplication.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class db_helper extends SQLiteOpenHelper{
    private static final int DB_VERSION = 1;
    public static final String DB_NAME = "novel_db";
    public static final String TABLE_NAME = "novel_list";

    public db_helper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // create table Orders(Id integer primary key, CustomName text, OrderPrice integer, Country text);
        // (id, url, title, img,intro,author,category,updateTime,novelId)
        String sql = "create table if not exists " + TABLE_NAME + " (id integer primary key, url text, title text, img text,intro text,author text,category text,updateTime text,novelId integer)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }

}
