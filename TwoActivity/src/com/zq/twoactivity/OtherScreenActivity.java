package com.zq.twoactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

//activiy��ϵͳ��Ҫ�����ϵͳҪ�ҵ�activity���������嵥�ļ���������

public class OtherScreenActivity extends Activity{

	//��дoncreate���������ó�ʼ���������
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.towlayout);
		
		
	}
	public void click2(View view){
		
		Intent intent = new Intent();
		intent.setClassName("com.baidu.gallery3d", "com.baidu.gallery3d.app.Gallery");
		startActivity(intent);
	}

	
	
}
