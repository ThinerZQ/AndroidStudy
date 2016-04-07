
package com.zq.simpleasynhttp;

import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.DefaultClientConnection;

import com.zq.utils.StreamTools;

import android.os.Handler;
import android.os.Message;

public class AsyncHttpClient {

	public void get(final String path,final Handler myhandler){
		new Thread(){
			public void run(){
				HttpClient client = new DefaultHttpClient();
				HttpGet httpget = new HttpGet(path);
			try{
				HttpResponse response = client.execute(httpget);
				InputStream is = response.getEntity().getContent();
				String content = StreamTools.readInputStream(is);
				//execute success
				Message msg = new Message();
				msg.what=1;
				msg.obj=content;
				myhandler.sendMessage(msg);
			}catch(Exception e){
				//failed
				Message msg = new Message();
				msg.what=1;
				msg.obj="«Î«Û ß∞‹";
				myhandler.sendMessage(msg);
				e.printStackTrace();
			}
			
			
			
			
			
			
			
			
			}
		}.start();
	}
}
