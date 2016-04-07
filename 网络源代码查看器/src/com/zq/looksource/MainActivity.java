package com.zq.looksource;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


import com.zq.utils.StreamTools;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	protected static final int ERROR = 1;
	protected static final int SUCCESS = 2;
	private TextView tv_content;
	private EditText et ;
	
	//1.主线程创建消息处理器
	private Handler handler =new Handler(){

			@Override
			public void handleMessage(Message msg) {
				
				switch (msg.what) {
				case ERROR:
					Toast.makeText(MainActivity.this, "获取图片失败", 1).show();
					break;

				case SUCCESS:
					tv_content.setText((String)msg.obj);
					break;
				}
			}
			
		};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et = (EditText) findViewById(R.id.et);
		tv_content = (TextView) findViewById(R.id.tv_content);
	}

	public void click(View view){
		
		final String path = et.getText().toString().trim();
		if(TextUtils.isEmpty(path)){
			Toast.makeText(this, "path can not be empty", 1).show();
		}else{
			//网络不能放在主线程里面
			new Thread(){

				@Override
				public void run() {
					//连接服务器，获取资源文件
					try{
						URL url = new URL(path);
						HttpURLConnection huc = (HttpURLConnection) url.openConnection();
						
						huc.setRequestMethod("GET");
						huc.setConnectTimeout(5000);
						huc.setReadTimeout(5000);
						huc.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36");
					
						int code = huc.getResponseCode();
					if(code==200){
						InputStream is = huc.getInputStream();
						
						String result = StreamTools.readInputStream(is);
						
						 
						//tell main thread to help me modify view
						Message msg = new Message();
						msg.what=SUCCESS;
						msg.obj=result;
						handler.sendMessage(msg);
						
						
						
					}else{
						Message msg = new Message();
						msg.what=ERROR;
						handler.sendMessage(msg);
					}
						
					}catch(Exception e){
						e.printStackTrace();
						Message msg = new Message();
						msg.what=ERROR;
						handler.sendMessage(msg);
						
					}
				}
	
			}.start();
		}
	}

}
