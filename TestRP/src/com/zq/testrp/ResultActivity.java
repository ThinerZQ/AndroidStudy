package com.zq.testrp;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ResultActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result);
		
		TextView tv_result = (TextView) findViewById(R.id.tv_result);
		
		Intent intent = getIntent();
		String name = intent.getStringExtra("name");
		Random random = new Random();
		int rp = random.nextInt(101);
		tv_result.setText(name+":your rp is "+rp);
		
		ProgressBar pb = (ProgressBar) findViewById(R.id.pb);
		
		pb.setProgress(rp);
		
	}
}
