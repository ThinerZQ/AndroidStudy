package com.example.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.database.dao.PersonDao;
import com.example.domain.Person;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.app.Activity;
import android.graphics.Color;

public class MainActivity extends Activity {

	private ListView lv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		
		
		lv = (ListView) findViewById(R.id.lv);

		List<Map<String,Object>> data = new ArrayList<Map<String,Object>>();
		
		Map<String,Object> map1 = new HashMap<String, Object>();
		map1.put("nametext", "功能1");
		map1.put("icons", R.drawable.btn_zoom_up_pressed);
		
		Map<String,Object> map2 = new HashMap<String, Object>();
		map2.put("nametext", "功能2");
		map2.put("icons", R.drawable.button_onoff_indicator_on);
		
		Map<String,Object> map3 = new HashMap<String, Object>();
		map3.put("nametext", "功能3");
		map3.put("icons", R.drawable.call_contact);
		
		Map<String,Object> map4 = new HashMap<String, Object>();
		map4.put("nametext", "功能4");
		map4.put("icons", R.drawable.checkbox_on_background);
		
		data.add(map1);
		data.add(map2);
		data.add(map3);
		data.add(map4);
		
		
		
		lv.setAdapter(new SimpleAdapter(this, data, R.layout.list_item, new String[]{"icons","nametext"}, new int[]{R.id.iv,R.id.tv}));

	}
	

}
