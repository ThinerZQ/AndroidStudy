package com.zq.mss;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.telephony.SmsManager;
import android.text.TextUtils;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private EditText  et_number;
	private EditText et_content;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		et_number= (EditText)findViewById(R.id.et_input_number);
		et_content= (EditText)findViewById(R.id.et_content_input);
		
		Button bt_send = (Button)findViewById(R.id.send);
		bt_send.setOnClickListener(this);
	
	
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.send:
			String number = et_number.getText().toString().trim();
			String contents = et_content.getText().toString().trim();
			
			
			if(TextUtils.isEmpty(number)||TextUtils.isEmpty(contents)){
				Toast.makeText(this, "号码或者内容不能为空", Toast.LENGTH_SHORT).show();
				return;
			}else{
				SmsManager sms = SmsManager.getDefault();
				ArrayList<String> content = sms.divideMessage(contents);
				for(String str:content){
				 sms.sendTextMessage(number, null, str, null, null);
				}
			}
			break;

		
		}
		
	}
}
