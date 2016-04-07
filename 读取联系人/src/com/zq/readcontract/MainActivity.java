package com.zq.readcontract;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;

import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
public void click(View v){
	
	try{
		
		//获取联系人ID
				ContentResolver re = getContentResolver();
				//获取row_contacts 表对应的uri
				Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
				Uri uri1 = Uri.parse("content://com.android.contacts/data");
				Cursor cursor = re.query(uri, null, null, null, null);
				
				while(cursor.moveToNext()){
					String id = cursor.getString(cursor.getColumnIndex("contact_id"));
					System.out.println("----------------------------------");
					
				if(id!=null){	
					System.out.println(id);
					Cursor datacursor = re.query(uri1, null, "raw_contact_id=?", new String[]{id}, null);
					while(datacursor.moveToNext()){
						String data1 = datacursor.getString(datacursor.getColumnIndex("data1"));
						String mimetype = datacursor.getString(datacursor.getColumnIndex("mimetype"));
						System.out.println("data1 == "+data1);
						System.out.println("mimetype == "+mimetype);
					}
					datacursor.close();
				}	
					
				}
				cursor.close();
	}catch(Exception e){
		e.printStackTrace();
	}
}

	

}
