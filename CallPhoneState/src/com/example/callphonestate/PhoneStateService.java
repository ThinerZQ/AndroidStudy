package com.example.callphonestate;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class PhoneStateService extends Service {

	/**
	 * �����ں�̨���е����
	 */
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		System.out.println("���񱻴���");
		super.onCreate();//
		
		//���ӵ绰״̬�仯
		//��ȡ�绰������
		TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		
		tm.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);
		
		
		
	}

	@Override
	public void onDestroy() {
		System.out.println("��������");
		super.onDestroy();
	}

	@Override
	@Deprecated
	public void onStart(Intent intent, int startId) {
	
		super.onStart(intent, startId);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		
		return super.onStartCommand(intent, flags, startId);
	}
	
	
	
	
	

}
