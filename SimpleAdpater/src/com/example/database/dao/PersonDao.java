package com.example.database.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.database.PersonSQLiteOpenHelper;
import com.example.domain.Person;

public class PersonDao {
	
	private PersonSQLiteOpenHelper helper;
	
	//在构造方法中完成helper初始化
	public PersonDao(Context context){
	helper = new PersonSQLiteOpenHelper(context);
		
	}
	//添加记录
	public void add(String name,String number){
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL("insert into person (name,number) values(?,?)",new Object[]{name,number});
		db.close();
		
		
	}
	/**
	 * 查询记录是否存在
	 */
	public boolean find(String name){
		SQLiteDatabase db = helper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from person where name=?", new String[]{name});
		boolean b = cursor.moveToNext();
		cursor.close();
		db.close();
		return b;
	}
	/**
	 * update record
	 */
	public void update (String name,String newnumber){
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL("update person set number=? where name=?",new Object[]{newnumber,name});
		db.close();
	}
	/**
	 * 删除一条记录
	 * 
	 */
	public void delete(String name){
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL("delete from person where name=?",new Object[]{name});
		db.close();
	}
	/**
	 * findall
	 */
	public List<Person> findAll(){
		SQLiteDatabase db = helper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from person",null);
		List<Person> persons = new ArrayList<Person>();
		while(cursor.moveToNext()){
			
			int id =cursor.getInt(cursor.getColumnIndex("id"));
			String name =cursor.getString(cursor.getColumnIndex("name"));
			String number =cursor.getString(cursor.getColumnIndex("number"));
			Person p = new Person(id,name,number);
			persons.add(p);
		}
		cursor.close();
		db.close();
		return persons;
		
	}

}
