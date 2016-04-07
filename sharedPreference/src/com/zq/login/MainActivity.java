package com.zq.login;

import java.util.Map;

import com.zq.login.service.SaveFile;
import com.zq.sharepreference.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText et_username;
	private EditText et_password;
	private CheckBox cb;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		et_username = (EditText) findViewById(R.id.username);
		et_password = (EditText) findViewById(R.id.password);
		cb = (CheckBox) findViewById(R.id.check);
		
		SharedPreferences sp = this.getSharedPreferences("config",this.MODE_PRIVATE);
		String username = sp.getString("username", "");
		et_username.setText(username);
		
		String password = sp.getString("password", "");
		et_password.setText(password);
		
		
	
	}

	public void login(View v){
		String username = et_username.getText().toString().trim();
		String password = et_password.getText().toString().trim();
		if(TextUtils.isEmpty(username)||TextUtils.isEmpty(password)){
			Toast.makeText(this, "�û�����������Ϊ��", Toast.LENGTH_SHORT).show();
		
		}else{
			if(cb.isChecked()){
				//�����û�������
				Log.i("zq","��Ҫ�����û���������");
				boolean b =SaveFile.saveUserInfo(this,username, password);
				System.out.print(b);
				if(b){
					Toast.makeText(this, "����ɹ�", Toast.LENGTH_SHORT).show();
				}else{
					Toast.makeText(this, "����ʧ��", Toast.LENGTH_SHORT).show();
				}
			}
			if(TextUtils.equals(username, "zq")&&TextUtils.equals(password, "zhengqiang")){
				Toast.makeText(this, "��½�ɹ�", Toast.LENGTH_SHORT).show();
			}else{
				Toast.makeText(this, "��½ʧ��", Toast.LENGTH_SHORT).show();
			}
		}
		
		
		
	}

}
