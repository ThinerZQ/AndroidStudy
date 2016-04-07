package com.zq.smssend;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.zq.smssend.domain.ContactInfo;

public class ContactInfoService {

	public static List<ContactInfo> getContactInfos(Context context){
		
		List<ContactInfo> contactInfos = new ArrayList<ContactInfo>();
		try{
			
			//获取联系人ID
					ContentResolver re = context.getContentResolver();
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
						
						ContactInfo contactInfo = new ContactInfo();
						while(datacursor.moveToNext()){
							String data1 = datacursor.getString(datacursor.getColumnIndex("data1"));
							String mimetype = datacursor.getString(datacursor.getColumnIndex("mimetype"));
							System.out.println("data1 == "+data1);
							System.out.println("mimetype == "+mimetype);
							
							
							if("vnd.android.cursor.item/name".equals(mimetype)){
								contactInfo.setName(data1);
							}else if("vnd.android.cursor.item/phone_v2".equals(mimetype)){
								contactInfo.setNumber(data1);
							}
						}
						contactInfos.add(contactInfo);
						datacursor.close();
						
					}	
						
					}
					cursor.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return contactInfos;
	}
	
}
