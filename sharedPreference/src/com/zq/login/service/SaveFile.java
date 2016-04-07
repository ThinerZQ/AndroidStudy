package com.zq.login.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SaveFile {

	public static boolean saveUserInfo(Context context,String username,String password){
		
		SharedPreferences sp = context.getSharedPreferences("config", context.MODE_PRIVATE);
		Editor ed = sp.edit();
		ed.putString("username", username);
		ed.putString("password", password);
		ed.commit();
		return true;
		
	}
	
}
