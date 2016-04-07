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
		//����һ���µ�activity
		//startActivity(intent);
		//�����µ�activity�����һ�ȡ���ؽ��
		startActivityForResult(intent, 0);
	}
	//���¿�����activity�رյ�ʱ����õķ���
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(data!=null){
			String number  = data.getStringExtra("number");
			
			
			et_numebr.setText(number);
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	

}
