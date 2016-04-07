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

	//点击按钮跳船到第二个界面
	public void click(View view){
		
		//Intent ,意图
		Intent intent = new Intent();
		intent.setClassName(this, "com.zq.twoactivity.OtherScreenActivity");
		startActivity(intent);
	}

}
