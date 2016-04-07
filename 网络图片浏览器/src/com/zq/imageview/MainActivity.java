package com.zq.imageview;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.w3c.dom.Text;


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
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

	protected static final int CHANGE_UI = 0;
	private EditText et;
	private ImageView iv;
	
	//1.主线程创建消息处理器
	private Handler handler =new Handler(){

		@Override
		public void handleMessage(Message msg) {
			
			if(msg.what==CHANGE_UI){
				Bitmap bitmap = (Bitmap)msg.obj;
				iv.setImageBitmap(bitmap);
			}
		}
		
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et=(EditText) findViewById(R.id.et);
		iv=(ImageView) findViewById(R.id.iv);
	}

	public void click(View v){
		
		final String path = et.getText().toString().trim();
		if(TextUtils.isEmpty(path)){
			Toast.makeText(this, "路径不能为空", 1);
		}else{
			//网络不能放在主线程里面
			new Thread(){

				@Override
				public void run() {
					//连接服务器，获取图片
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
						Bitmap bitmap = BitmapFactory.decodeStream(is);
						
						//tell main thread to help me modify view
						Message msg = new Message();
						msg.what=CHANGE_UI;
						msg.obj=bitmap;
						handler.sendMessage(msg);
						
						
						
					}else{
						Toast.makeText(MainActivity.this, "显示图片失败", 1);
					}
						
					}catch(Exception e){
						e.printStackTrace();
						Toast.makeText(MainActivity.this, "出现错误", 1);
					}
				}
	
			}.start();
		}
		
	}

}
