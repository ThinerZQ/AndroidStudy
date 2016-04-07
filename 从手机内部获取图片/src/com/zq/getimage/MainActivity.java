package com.zq.getimage;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {

	private ImageView iv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv = (ImageView) findViewById(R.id.iv);
	}
public void selectimage(View view){
	
	//����ϵͳͼ��Ӧ�ã�ѡ����Ƭ
	Intent intent = new Intent();
	intent.setAction(Intent.ACTION_PICK);
	intent.setType("image/*");
	startActivityForResult(intent, 0);
	
	
	
	
}
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	
	if(data!=null){
		Uri uri = data.getData();
		iv.setImageURI(uri);
		//�õ�����ͼ
		//Bitmap bitmap = data.getParcelableExtra("data");
	}

	super.onActivityResult(requestCode, resultCode, data);
}
	

}
