package com.zq.insertsms;

import java.util.Date;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		new Thread(){
			public void run(){
				try{
					Thread.sleep(10000);
					ContentResolver re = getContentResolver();
					Uri uri = Uri.parse("content://sms/");
					ContentValues cv = new ContentValues();
					cv.put("address", "95533");
					cv.put("type", 1);
					cv.put("date", System.currentTimeMillis());
					Date d = new Date();
					cv.put("body", "尊敬的郑先生，你的尾号024的建行卡于"+d.toLocaleString()+" 收入人民币100,000,000,活期余额120,000,000");
					re.insert(uri, cv);
					
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}.start();
		
		
		
	}

	

}
