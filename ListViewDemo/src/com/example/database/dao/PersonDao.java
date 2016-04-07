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
	
	//�ڹ��췽�������helper��ʼ��
	public PersonDao(Context context){
	helper = new PersonSQLiteOpenHelper(context);
		
	}
	//��Ӽ�¼
	public void add(String name,String number){
		SQLiteDatabase db = helper.getWritableDatabase();
		db.execSQL("insert into person (name,number) values(?,?)",new Object[]{name,number});
		db.close();
		
		
	}
	/**
	 * ��ѯ��¼�Ƿ����
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
	 * ɾ��һ����¼
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
