package com.example.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PersonSQLiteOpenHelper extends SQLiteOpenHelper {

	private static final String TAG = "PersonSQLiteOpenHelper";

	public PersonSQLiteOpenHelper(Context context) {
		super(context, "person.db", null, 3);
		// TODO Auto-generated constructor stub
	}

	//数据库第一次被创建的时候调用
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		db.execSQL("create table person (id integer primary key autoincrement,name varchar(20),number varchar(20)) ");
	}

	//数据库版本号发生变化的时候调用
	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
			
		Log.i(TAG,"数据库的版本变化 了");
		db.execSQL("alter table person add account varchar(20)");

	}

}
