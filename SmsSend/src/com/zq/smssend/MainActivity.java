package com.zq.smssend;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	private EditText et_numebr ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et_numebr = (EditText) findViewById(R.id.et_number);
	}

	public void selectContact(View view){
		Intent intent = new Intent(this,SelectContactActivity.class);
		//开启一个新的activity
		//startActivity(intent);
		//开启新的activity，并且获取返回结果
		startActivityForResult(intent, 0);
	}
	//当新开启的activity关闭的时候调用的方法
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(data!=null){
			String number  = data.getStringExtra("number");
			
			
			et_numebr.setText(number);
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	

}
