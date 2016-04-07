package com.zq.observer;

import java.util.Date;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.ContentObserver;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {

	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		
		
		
		new Thread(){
			public void run(){
				try{
					Thread.sleep(5000);
					ContentResolver re = getContentResolver();
					Uri uri = Uri.parse("content://sms/");
					ContentValues cv = new ContentValues();
					cv.put("address", "95533");
					cv.put("type", 1);
					cv.put("date", System.currentTimeMillis());
					Date d = new Date();
					cv.put("body", "�𾴵�֣���������β��024�Ľ��п���"+d.toLocaleString()+" ���������100,000,000,�������120,000,000");
					re.insert(uri, cv);
					
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}.start();
	
	
	ContentResolver re = getContentResolver();
	Uri uri  = Uri.parse("content://sms/");
	re.registerContentObserver(uri,true, new MyObserver(new Handler()));
	
	
	
	
	
	
	
	}
	private class MyObserver extends ContentObserver{

		public MyObserver(Handler handler) {
			super(handler);
			
		}
//�۲��߹۲쵽��Ϣ����������һ�����ݿ�仯֪ͨ����
		@Override
		public void onChange(boolean selfChange) {
			Toast.makeText(MainActivity.this, "���ű仯��", 1).show();
		}
		
	}

	

}
