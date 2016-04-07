package com.example.database.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.database.PersonSQLiteOpenHelper;
import com.example.domain.Person;

public class PersonDao1 {
	
	private PersonSQLiteOpenHelper helper;
	
	//在构造方法中完成helper初始化
	public PersonDao1(Context context){
		helper = new PersonSQLiteOpenHelper(context);
		
	}
	//添加记录
	public long add(String name,String number,String money){
		SQLiteDatabase db = helper.getWritableDatabase();
		//db.execSQL("insert into person (name,number) values(?,?)",new Object[]{name,number});
		
		ContentValues cv = new ContentValues();
		cv.put("name", name);
		cv.put("number", number);
		cv.put("account", money);
				
		long id = db.insert("person", null, cv);
		db.close();
		return id;
		
	}
	/**
	 * 查询记录是否存在
	 */
	public boolean find(String name){
		SQLiteDatabase db = helper.getWritableDatabase();
//		Cursor cursor = db.rawQuery("select * from person where name=?", new String[]{name});
//		boolean b = cursor.moveToNext();
//		cursor.close();
		Cursor cursor = db.query("person", null, "name=?", new String[]{name}, null, null, null);
		boolean b = cursor.moveToNext();
		cursor.close();
		db.close();
		return b;
	}
	/**
	 * update record
	 */
	public int update (String name,String newnumber){
		SQLiteDatabase db = helper.getWritableDatabase();
		//db.execSQL("update person set number=? where name=?",new Object[]{newnumber,name});
		
		ContentValues cv = new ContentValues();
		cv.put("number", newnumber);
		int number = db.update("person",cv , "name=?", new String[]{name});
		
		db.close();
		return number;
	}
	/**
	 * 删除一条记录
	 * 
	 */
	public int delete(String name){
		SQLiteDatabase db = helper.getWritableDatabase();
		//db.execSQL("delete from person where name=?",new Object[]{name});
		
		int number= db.delete("person", "name=?", new String[]{name});
		
		
		db.close();
		return number;
	}
	/**
	 * findall
	 */
	public List<Person> findAll(){
		SQLiteDatabase db = helper.getWritableDatabase();
		//Cursor cursor = db.rawQuery("select * from person",null);
		Cursor cursor = db.query("person", new String[]{"name","id","number"}, null, null, null, null, null);
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
