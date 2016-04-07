package com.zq.upload;

import java.io.File;
import java.io.FileNotFoundException;

import org.apache.http.Header;
import org.apache.http.client.ResponseHandler;

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

	private EditText et ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		et = (EditText)findViewById(R.id.et);
		
	}

	public void click(View v) throws FileNotFoundException{
		

		String path = et.getText().toString().trim();
		File file = new File(path);
		if(file.exists()&&file.length()>0){
			AsyncHttpClient client = new AsyncHttpClient();
			
			RequestParams pa = new RequestParams();
			pa.put("profile_picture",file);
			client.post("http://192.168.88.104:8080/AndroidTest/UpFileServlet", pa
					,new AsyncHttpResponseHandler() {
						
						@Override
						public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
							Toast.makeText(MainActivity.this, "上传成功", 1).show();
							
						}
						
						@Override
						public void onFailure(int statusCode, Header[] headers,
								byte[] responseBody, Throwable error) {
							Toast.makeText(MainActivity.this, "上传shibai", 1).show();
							
						}
					});
			
			
			
		}
	}

}
