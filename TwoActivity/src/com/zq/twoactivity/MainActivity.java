package com.zq.twoactivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	//�����ť�������ڶ�������
	public void click(View view){
		
		//Intent ,��ͼ
		Intent intent = new Intent();
		intent.setClassName(this, "com.zq.twoactivity.OtherScreenActivity");
		startActivity(intent);
	}

}