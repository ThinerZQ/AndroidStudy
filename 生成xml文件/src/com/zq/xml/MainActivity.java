package com.zq.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.xmlpull.v1.XmlSerializer;



import com.zq.domain.Info;


import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.util.Xml;

import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	private List<Info> mssInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mssInfo = new ArrayList<Info>();
		Random random = new Random();
		long number = 135000000;
		for (int i = 0; i < 10; i++) {
			mssInfo.add(new Info(System.currentTimeMillis(), random.nextInt(2),
					"短信内容" + i, Long.toString(number + i),i));
		}

	}

	// 备份手机短信
	public void backupmss(View v) {

		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>");
		sb.append("<smss>");

		for (Info info : mssInfo) {
			sb.append("<sms>");

			sb.append("<address>");
			sb.append(info.getAddress());
			sb.append("</address>");

			sb.append("<date>");
			sb.append(info.getDate());
			sb.append("</date>");

			sb.append("<body>");
			sb.append(info.getBody());
			sb.append("</body>");

			sb.append("<type>");
			sb.append(info.getType());
			sb.append("</type>");

			sb.append("</sms>");
		}

		sb.append("</smss>");

		try {
			File file = new File(Environment.getExternalStorageDirectory(),
					"backup.xml");
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(sb.toString().getBytes());
			fos.close();
			Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();

		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show();

		}
	}

	public void backupmss2(View v) {

		
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
				xmlSerializer.attribute(null, "id", info.getId()+"");
				
				xmlSerializer.startTag(null, "type");
				xmlSerializer.text(info.getType()+"");
				xmlSerializer.endTag(null, "type");
				
				xmlSerializer.startTag(null, "body");
				xmlSerializer.text(info.getBody()+"");
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
			Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show();
		}
	}

}
