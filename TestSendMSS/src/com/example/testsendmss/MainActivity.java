package com.example.testsendmss;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	//��ʾ��ͼ�������Լ���Ӧ�ó��򣬡���ͬ�����ó���ʹ����ʿ��ͼ�����߼�����˵ĳ��򣬻���ϣ���Լ��Ľ������˼���
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void click(View v){
		Intent intent  = new Intent();
		
				intent.setAction("android.intent.action.SENDTO");
				intent.addCategory("android.intent.category.DEFAULT");
				intent.setData(Uri.parse("sms:110"));
				startActivity(intent);
	}

}
