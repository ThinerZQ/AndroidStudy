package com.example.database;

import java.util.List;

import com.example.database.dao.PersonDao;
import com.example.domain.Person;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.ListView;
import android.widget.TextView;
import android.app.Activity;
import android.graphics.Color;

public class MainActivity extends Activity {

	private ListView lv;
	private List<Person> persons ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		PersonDao dao = new PersonDao(this);
		persons = dao.findAll();
		
		lv = (ListView) findViewById(R.id.lv);

		lv.setAdapter(new MyAdapter());

	}
	private class MyAdapter extends BaseAdapter{

		private static final String TAG = "MyAdapter";

		/**
		 * 控制listview中共有多少个条目
		 */
		@Override
		public int getCount() {
			
			return persons.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		/**
		 * 获取一个view对象，展现数据集合某一个位置对应的数据
		 */
		@Override
		public View getView(int position, View arg1, ViewGroup arg2) {
			Log.i(TAG, "返回view对象，位置："+position);
			
			Person person = persons.get(position);
			
			View view = View.inflate(MainActivity.this, R.layout.list_item, null);
			TextView tv_id = (TextView) view.findViewById(R.id.tv_id);
			tv_id.setText("id:"+person.getId());
			
			TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
			tv_name.setText("name:"+person.getName());
			
			TextView tv_phone = (TextView) view.findViewById(R.id.tv_phone);
			tv_phone.setText("phone:"+person.getNumber());
			
			return view;
		}
		
	}

}
