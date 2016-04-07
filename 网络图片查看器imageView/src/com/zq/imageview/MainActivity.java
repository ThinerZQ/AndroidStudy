
package com.zq.imageview;

import com.loopj.android.image.SmartImageView;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	private EditText et ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et = (EditText) findViewById(R.id.et);
		
	}

	public void click(View v){
		SmartImageView siv = (SmartImageView) findViewById(R.id.siv);
	
		siv.setImageUrl(et.getText().toString().trim(),R.drawable.ic_launcher, R.drawable.ic_launcher);
				
	
	
	}

}
