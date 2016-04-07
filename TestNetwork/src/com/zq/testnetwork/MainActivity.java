package com.zq.testnetwork;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	//��ʾ��ͼ�������ƶ���������ľ�����Ϣ�������������
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ConnectivityManager cm = (ConnectivityManager) this
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo info = cm.getActiveNetworkInfo();

		if (info != null && info.isConnected()) {
			Toast.makeText(this, "�������", 1).show();
		} else {
			Toast.makeText(this, "���粻����", 0).show();
			Intent intent = new Intent();
			intent.setClassName("com.android.settings", "com.android.settings.Settings");
			startActivity(intent);
			

		}
	}

}
