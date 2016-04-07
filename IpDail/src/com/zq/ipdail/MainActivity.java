package com.zq.ipdail;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText et_ipnumber;
	private SharedPreferences sp ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et_ipnumber = (EditText) findViewById(R.id.et_ipnumber);
		
		sp = getSharedPreferences("config", MODE_PRIVATE);
	
		et_ipnumber.setText(sp.getString("ipnumber", ""));
	
	}
	public void click(View view){
		String ipnumber = et_ipnumber.getText().toString().trim();
		
		Editor editor = sp.edit();
		
		editor.putString("ipnumber", ipnumber);
		
		editor.commit();
		Toast.makeText(this, "±£´æ³É¹¦", 1).show();
		
	}

	
}
