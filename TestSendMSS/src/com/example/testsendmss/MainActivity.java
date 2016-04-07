package com.example.testsendmss;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	//显示意图，用于自己的应用程序，。不同的引用程序使用隐士意图，或者激活别人的程序，或者希望自己的界面别别人激活
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
