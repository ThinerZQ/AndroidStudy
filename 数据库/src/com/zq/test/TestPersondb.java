package com.zq.test;

import com.zq.database.PersonSqlLite;

import android.test.AndroidTestCase;

public class TestPersondb extends AndroidTestCase{
	
	public void testCreateDB() throws Exception{
		
		PersonSqlLite p = new PersonSqlLite(getContext());
		p.getWritableDatabase();
	}

}
