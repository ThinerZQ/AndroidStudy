package com.example.whea;


import java.io.InputStream;
import java.util.List;

import com.example.whea.service.WeatherService;
import com.zq.whea.domian.WeatherInfo;

import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TextView tv = (TextView)findViewById(R.id.tv);
		
		StringBuffer sb =null;
		
		try {
			InputStream is = MainActivity.class.getClassLoader().getResourceAsStream("wh.xml");
			List<WeatherInfo> infos = WeatherService
					.getWeatherInfo(is);
			sb = new StringBuffer();
			for (WeatherInfo info : infos) {
				String str = info.toString();
				sb.append(str);
				sb.append("\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(this, "Ê§°Ü",Toast.LENGTH_SHORT).show();
		}
		tv.setText(sb.toString());
		
	}



}
