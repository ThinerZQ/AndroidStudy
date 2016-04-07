package com.zq.dial;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText  et_number;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button bt_dail = (Button)findViewById(R.id.button1);
		et_number = (EditText)findViewById(R.id.edittext1);
		bt_dail.setOnClickListener(new MyListener());
		
		
	}
	
	private class MyListener implements OnClickListener{

		//按钮被点击的时候调用方法
		public void onClick(View v) {
			
			String number = et_number.getText().toString().trim();
			
			if(TextUtils.isEmpty(number)){
				
				Toast.makeText(MainActivity.this, "号码不能为空", Toast.LENGTH_SHORT).show();
				return;
			}
			//intent
			Intent intent = new Intent();
		    intent.setAction(Intent.ACTION_CALL);
		    intent.setData(Uri.parse("tel:"+number));
		    
		    startActivity(intent);
		    
		}
		
	}

	

}
