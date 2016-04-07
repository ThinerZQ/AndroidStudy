package com.zq.testrp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText et_name;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		et_name = (EditText) findViewById(R.id.names);
	
	}

	public void click(View v){
		
		String name = et_name.getText().toString().trim();
		
		if(TextUtils.isEmpty(name)){
			Toast.makeText(this, "名字不恩能够为空", 1).show();
			return ;
		}
		
		Intent intent = new Intent(this,ResultActivity.class);
		intent.putExtra("name", name);
		
		startActivity(intent);
		
		
	}

}
