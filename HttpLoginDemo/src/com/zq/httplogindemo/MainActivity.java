package com.zq.httplogindemo;

import java.net.URLEncoder;

import org.apache.http.Header;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText et_username;
	private EditText et_password;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et_username = (EditText) findViewById(R.id.et_username);
		et_password = (EditText) findViewById(R.id.et_password);
		
	}

	public void clientclickget(View v){
		
		final String username = et_username.getText().toString().trim();
		final String password = et_password.getText().toString().trim();
		
		new Thread(){
			public void run(){
				final String result = LoginService.loginByClientGet(username, password);
				if(result!=null){
					//request success
					runOnUiThread(new Runnable(){
						@Override
						public void run() {
							Toast.makeText(MainActivity.this, result, 0).show();
						}
					});
				}else{
					//request failed
					runOnUiThread(new Runnable(){
						@Override
						public void run() {
							Toast.makeText(MainActivity.this, "«Î«Û ß∞‹", 0).show();
						}
					});
				}
			}
		}.start();
	}
	
	
	public void clientclickpost(View v){
		
		final String username = et_username.getText().toString().trim();
		final String password = et_password.getText().toString().trim();
		
		new Thread(){
			public void run(){
				final String result = LoginService.loginByClientPOST(username, password);
				if(result!=null){
					//request success
					runOnUiThread(new Runnable(){
						@Override
						public void run() {
							Toast.makeText(MainActivity.this, result, 0).show();
						}	
					});
				}else{
					//request failed
					runOnUiThread(new Runnable(){
						@Override
						public void run() {
							Toast.makeText(MainActivity.this, "«Î«Û ß∞‹", 0).show();
						}
						
					});
				}
			}
		}.start();
	}
	
public void asynpost(View v){
		
		final String username = et_username.getText().toString().trim();
		final String password = et_password.getText().toString().trim();
		
		AsyncHttpClient client = new AsyncHttpClient();
		
		String path = "http://192.168.88.104:8080/AndroidTest/LoginServlet";
		RequestParams params = new RequestParams();
		params.add("username", "zhengqiang");
		params.add("password", "123");
		
		
		client.post(path, params,new AsyncHttpResponseHandler(){ 
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				Toast.makeText(MainActivity.this, new String(responseBody), 1).show();
				
			}
			
			@Override
			public void onFailure(int statusCode, Header[] headers,
					byte[] responseBody, Throwable error) {
				Toast.makeText(MainActivity.this, new String(responseBody), 1).show();
				
			}
		});
		
	}

}
