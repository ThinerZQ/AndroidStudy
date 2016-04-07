package com.zq.ipdail;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class OutClallReselver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		
		System.out.println("11111111111111111111111111111111111111111111");
		//得到外播电话号码
		String number =	getResultData();
		SharedPreferences sp = context.getSharedPreferences("config", context.MODE_PRIVATE);
		
		String ipnumber = sp.getString("ipnumber", "");
		String newnumber = ipnumber+number;
		
		System.out.println("newnumber = "+newnumber);
		setResultData(newnumber);
		
	
	
	}

}
