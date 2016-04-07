package com.example.smslistener;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SmsReslver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		System.out.println("短信收到了");

		Object[] pdus = (Object[]) intent.getExtras().get("pdus");
		
		for(Object pdu:pdus){
			SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdu);
			String body = smsMessage.getMessageBody();
			String sender = smsMessage.getOriginatingAddress();
			
			System.out.println("body:"+body);
			System.out.println("sender:"+sender);
		}
		
		
	}

}
