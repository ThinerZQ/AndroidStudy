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
	//显示意图，必须制定开启组件的具体信息，包名，组件名
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ConnectivityManager cm = (ConnectivityManager) this
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo info = cm.getActiveNetworkInfo();

		if (info != null && info.isConnected()) {
			Toast.makeText(this, "网络可用", 1).show();
		} else {
			Toast.makeText(this, "网络不可用", 0).show();
			Intent intent = new Intent();
			intent.setClassName("com.android.settings", "com.android.settings.Settings");
			startActivity(intent);
			

		}
	}

}
