package com.zq.paint;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings.System;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Bitmap.CompressFormat;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private ImageView iv;
	private Bitmap baseBitmap;
	private Canvas canvas;
	private Paint paint;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv = (ImageView) findViewById(R.id.iv);
		
		paint = new Paint();
		paint.setStrokeWidth(5);
		paint.setColor(Color.GREEN);
		
		//创建一个可以被修改的bitmap
		baseBitmap = Bitmap.createBitmap(720,1280,Bitmap.Config.ARGB_8888);
		canvas = new Canvas(baseBitmap);
		canvas.drawColor(Color.WHITE);
		
		
		//知道用户手指在屏幕上移动的轨迹
		iv.setOnTouchListener(new OnTouchListener() {
			//定义手指开始的坐标
			int startX;
			int startY;
			@Override
			public boolean onTouch(View view, MotionEvent event) {
				
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					
					startX = (int) event.getX();
					startY = (int) event.getY();
					
					
					break;

				case MotionEvent.ACTION_MOVE:
					int newX = (int) event.getX();
					int newY = (int) event.getY();
					
					canvas.drawLine(startX, startY, newX, newY, paint);
				//重新更新画笔开始位置
					startX = (int) event.getX();
					startY = (int) event.getY();
					
					
					
					
					iv.setImageBitmap(baseBitmap);
					
					
					break;
				case MotionEvent.ACTION_UP:
					
					break;
				default:
					break;
				}
				
				
				
				return true;
			}
		});
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

	public void save(View view){
		
		try {
			File file = new File(Environment.getExternalStorageDirectory(),java.lang.System.currentTimeMillis()+".jpg");
			FileOutputStream fos = new FileOutputStream(file);
			baseBitmap.compress(CompressFormat.JPEG, 100, fos);
			fos.close();
			Toast.makeText(this, "保存成功", 1).show();
			
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_MEDIA_MOUNTED);
			intent.setData(Uri.fromFile(Environment.getExternalStorageDirectory()));
			sendBroadcast(intent);
			
			
		} catch (Exception e) {
			Toast.makeText(this, "保存失败", 1).show();
			e.printStackTrace();
		}
	}
	

}
