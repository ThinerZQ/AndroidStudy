package com.zq.simpleasynhttp;

import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

public class MyHandler extends Handler{

	public void onFailure(String content){
		
	}
public void onSuccess(String content){
		
	}
@Override
public void handleMessage(Message msg) {
	String content;
	switch (msg.what) {
	case 1:
		content = (String)msg.obj;
		onSuccess(content);
		break;

	case 2:
		content = (String)msg.obj;
		onFailure(content);
		break;
	}
}

}
