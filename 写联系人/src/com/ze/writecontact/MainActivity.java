package com.ze.writecontact;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void click(View v){
		
		//王raw_contact 中添加联系人ID
		ContentResolver re = getContentResolver();
		//获取row_contacts 表对应的uri
		Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
		Uri uri1 = Uri.parse("content://com.android.contacts/data");
		
		ContentValues cv = new ContentValues();
		//必须要知道最后一条联系人的ID是多少
		
		Cursor cursor =re.query(uri, new String[]{"contact_id"}, null, null, null);
		
		
		cursor.moveToLast();
		int lastid = cursor.getInt(0);
		int newid =lastid+1;
		
		cv.put("contact_id", newid);
		re.insert(uri, cv);

	//使用添加的联系人的ID，向data表添加数据
		
		ContentValues phonevalue = new ContentValues();
		phonevalue.put("data1", "123456789");
		phonevalue.put("mimetype", "vnd.android.cursor.item/phone_v2");
		phonevalue.put("raw_contact_id", newid);
		re.insert(uri1, phonevalue);
		

		ContentValues emailvalue = new ContentValues();
		emailvalue.put("data1", "601097836@qq.com");
		emailvalue.put("mimetype", "vnd.android.cursor.item/email_v2");
		emailvalue.put("raw_contact_id", newid);
		re.insert(uri1, emailvalue);
		

		ContentValues namevalue = new ContentValues();
		namevalue.put("data1", "郑强哈哈哈");
		namevalue.put("mimetype", "vnd.android.cursor.item/name");
		namevalue.put("raw_contact_id", newid);
		re.insert(uri1, namevalue);
	
		Toast.makeText(this, "添加成功", 1).show();
	}
	

}
