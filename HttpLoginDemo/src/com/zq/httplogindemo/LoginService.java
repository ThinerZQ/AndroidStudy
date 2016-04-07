package com.zq.httplogindemo;

import java.io.InputStream;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.zq.utils.StreamTools;

public class LoginService {

	public static String loginByClientGet(String username,String password){
		try {
			//1,open a browser
			HttpClient client = new DefaultHttpClient();
			//2,input address
			
			String path = "http://192.168.88.104:8080/AndroidTest/LoginServlet?username="+URLEncoder.encode(username, "UTF-8")+"&password="
									+URLEncoder.encode(password, "UTF-8");
			HttpGet httpget = new HttpGet(path);
			
			//3 enter 回车键
			HttpResponse response = client.execute(httpget);
			//get response statuscode
			int code = response.getStatusLine().getStatusCode();
			//request success
			if(code==200){
				InputStream is = response.getEntity().getContent();
				String text = StreamTools.readInputStream(is);
				return text;
			}else{
				return null;
			}
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	
	
	
	
	public static String loginByClientPOST(String username,String password){
		try {
			//1,open a browser
			HttpClient client = new DefaultHttpClient();
			//2,input address
			
			String path = "http://192.168.88.104:8080/AndroidTest/LoginServlet";
			HttpPost httppost = new HttpPost(path);
			
			//指定要提交的数据实体
			List<NameValuePair> paralist= new ArrayList<NameValuePair>();
			paralist.add(new BasicNameValuePair("username", username));
			paralist.add(new BasicNameValuePair("password", password));
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paralist,"UTF-8");
			httppost.setEntity(entity);
			
			
			
			//3 enter 回车键
			HttpResponse response = client.execute(httppost);
			//get response statuscode
			int code = response.getStatusLine().getStatusCode();
			//request success
			if(code==200){
				InputStream is = response.getEntity().getContent();
				String text = StreamTools.readInputStream(is);
				return text;
			}else{
				return null;
			}
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			return null;
		}
	}
}
