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

	//���ݿ��һ�α�������ʱ�����
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		db.execSQL("create table person (id integer primary key autoincrement,name varchar(20),number varchar(20)) ");
	}

	//���ݿ�汾�ŷ����仯��ʱ�����
	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
			
		Log.i(TAG,"���ݿ�İ汾�仯 ��");
		db.execSQL("alter table person add account varchar(20)");

	}

}
