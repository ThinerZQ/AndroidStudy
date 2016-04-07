package com.zq.twoactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

//activiy是系统重要组件，系统要找到activity，必须在清单文件里面配置

public class OtherScreenActivity extends Activity{

	//重写oncreate方法，设置初始化程序界面
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.towlayout);
		
		
	}
	public void click2(View view){
		
		Intent intent = new Intent();
		intent.setClassName("com.baidu.gallery3d", "com.baidu.gallery3d.app.Gallery");
		startActivity(intent);
	}

	
	
}
