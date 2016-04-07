package com.zq.internet22;

import android.net.Uri;
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

	public void click(View view){
		
		Intent intent = new Intent();
		//隐士意图
		//描述动作行为
		intent.setAction(Intent.ACTION_VIEW);
		intent.setData(Uri.parse("http://www.baidu.com"));
		startActivity(intent);
	}

}
