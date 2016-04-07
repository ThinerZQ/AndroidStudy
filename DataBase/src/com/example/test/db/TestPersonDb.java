package com.example.test.db;

import java.util.List;

import com.example.database.PersonSQLiteOpenHelper;
import com.example.database.dao.PersonDao;
import com.example.database.dao.PersonDao1;
import com.example.domain.Person;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

public class TestPersonDb extends AndroidTestCase {

	public void testCreateDb() throws Exception {
		PersonSQLiteOpenHelper helper = new PersonSQLiteOpenHelper(getContext());

		SQLiteDatabase db = helper.getWritableDatabase();
	}
	public void testAdd() throws Exception{
		
		PersonDao1 dao = new PersonDao1(getContext());
		dao.add("zq", "13003946708","5000");
		dao.add("lyy", "13003946709","2000");
		
	}
public void testFindByName() throws Exception{
		
		PersonDao dao = new PersonDao(getContext());
		boolean b = dao.find("zq");
		assertEquals(true, b);
		
	}
public void testUpdate() throws Exception{
	
	PersonDao dao = new PersonDao(getContext());
	dao.update("zq","111");
	
}
public void testDelete() throws Exception{
	
	PersonDao dao = new PersonDao(getContext());
	dao.delete("zq");
	
}
public void testFindAll() throws Exception{
	
	PersonDao dao = new PersonDao(getContext());
	List<Person> persons = dao.findAll();
	for(Person p:persons){
		System.out.println(p.toString());
	}
	
	
}
public void testTransction() throws Exception{
	PersonSQLiteOpenHelper helper = new PersonSQLiteOpenHelper(getContext());

	SQLiteDatabase db = helper.getWritableDatabase();
	db.beginTransaction();
	try{
		db.execSQL("update person set account=account-1000 where name=?",new Object[]{"zq"});
		db.execSQL("update person set account=account+1000 where name=?",new Object[]{"lyy"});
		db.setTransactionSuccessful();
	}catch(Exception e){
		
	}finally{
		db.endTransaction();
		db.close();
	}
	
}

}
