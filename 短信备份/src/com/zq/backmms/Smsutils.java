package com.zq.backmms;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.xmlpull.v1.XmlSerializer;

import android.content.Context;
import android.os.Environment;
import android.util.Xml;
import android.view.View;
import android.widget.Toast;

import com.zq.domain.Info;

public class Smsutils {

public static void  backupmss2(List<Info> mssInfo,Context context) {

		
		try {
			XmlSerializer xmlSerializer = Xml.newSerializer();
			File file = new File(Environment.getExternalStorageDirectory(),
					"backup2.xml");
			FileOutputStream fos = new FileOutputStream(file);

			xmlSerializer.setOutput(fos, "utf-8");
			
			xmlSerializer.startDocument("utf-8", true);
			
			xmlSerializer.startTag(null, "smss");
			
			for (Info info : mssInfo) {
				
				xmlSerializer.startTag(null, "sms");
				
				
				xmlSerializer.startTag(null, "type");
				xmlSerializer.text(info.getType()+"");
				xmlSerializer.endTag(null, "type");
				
				xmlSerializer.startTag(null, "body");
				String ss = info.getBody().replace("\n\r", "\n");
				System.out.println(ss);
				xmlSerializer.text(ss);
				xmlSerializer.endTag(null, "body");
				
				xmlSerializer.startTag(null, "date");
				xmlSerializer.text(info.getDate()+"");
				xmlSerializer.endTag(null, "date");
				
				xmlSerializer.startTag(null, "address");
				xmlSerializer.text(info.getAddress());
				xmlSerializer.endTag(null, "address");
				
				xmlSerializer.endTag(null, "sms");
				
			}
			
			xmlSerializer.endTag(null, "smss");
			xmlSerializer.endDocument();
			fos.close();
			Toast.makeText(context, "success", Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
		}
	}
}
