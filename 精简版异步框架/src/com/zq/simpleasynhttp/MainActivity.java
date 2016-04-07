package com.zq.simpleasynhttp;

import java.net.URLEncoder;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void click(View v){
		try{
		//1 open sub thread execute a http request
		//on sub thread
		AsyncHttpClient client = new AsyncHttpClient();
		String path = "http://192.168.88.104:8080/AndroidTest/LoginServlet?username="+URLEncoder.encode("zhengqiang", "UTF-8")+"&password="
				+URLEncoder.encode("123", "UTF-8");
		client.get(path, new MyHandler(){

			@Override
			public void onFailure(String content) {
				Toast.makeText(MainActivity.this, content, 1).show();
			}

			@Override
			public void onSuccess(String content) {
				Toast.makeText(MainActivity.this, content, 1).show();
			}
			
		});
		//2 after sub thread execute must notify ui to update
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
