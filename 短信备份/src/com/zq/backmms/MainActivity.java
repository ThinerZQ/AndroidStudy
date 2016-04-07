package com.zq.backmms;

import java.util.ArrayList;
import java.util.List;

import com.zq.domain.Info;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void click(View view){
		Uri uri = Uri.parse("content://sms/");
		ContentResolver resolver = getContentResolver();
		List<Info> infos = new ArrayList<Info>();
		Cursor cursor = resolver.query(uri, new String[]{"address","date","type","body"}, null, null, null);
		while(cursor.moveToNext()){
			 String address=cursor.getString(0);
			 long date = cursor.getLong(1);
			 int type = cursor.getInt(2);
			 String body = cursor.getString(3);
			 Info info = new Info(date, type, body, address);
			 infos.add(info);
		}
		cursor.close();
		Smsutils.backupmss2(infos, this);
		
	}
	

}
