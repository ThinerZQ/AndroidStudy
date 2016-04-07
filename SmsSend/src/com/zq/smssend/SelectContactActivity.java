package com.zq.smssend;

import java.util.List;

import com.zq.smssend.domain.ContactInfo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SelectContactActivity extends Activity {

	private ListView lv_select_contact;
	private List<ContactInfo> contactInfos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.selectcontact);

		lv_select_contact = (ListView) findViewById(R.id.lv_select_contact);

		contactInfos = ContactInfoService.getContactInfos(this);

		lv_select_contact.setAdapter(new ContactAdapter());
		//给listview条目设置点击事件
		
		lv_select_contact.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				
				ContactInfo info = contactInfos.get(position);
				String number = info.getNumber();
				
				
				//传递数据给调用它的activity
				Intent data = new Intent();
				data.putExtra("number", number);
				setResult(0, data);
				
				//关闭当前activity，返回数据给调用activity的某个方法
				finish();
			}
		});
		

	}

	private class ContactAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return contactInfos.size();
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

		@Override
		public View getView(int position, View arg1, ViewGroup arg2) {

			ContactInfo info = contactInfos.get(position);

			View view = View.inflate(getApplicationContext(),
					R.layout.contact_item, null);

			TextView tv_name = (TextView) view
					.findViewById(R.id.tv_contact_name);

			TextView tv_phone = (TextView) view
					.findViewById(R.id.tv_contact_phone);

			tv_name.setText(info.getName());
			tv_phone.setText(info.getNumber());

			return view;
		}

	}
}
