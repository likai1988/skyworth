package com.skyworth.notification;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

public class NotificationService extends Service{
//	private AlarmManager mAlarmManager;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.i("NotificationService","--->OnCreat");
		setAlarm();
	}
	
	private void setAlarm(){
		Log.i("NotificationService","--->setAlarm");
		AlarmManager mAlarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(NotificationService.this,NotificationCheckService.class);
		PendingIntent pendIntent = PendingIntent.getService(NotificationService.this, 0, intent, 0);
		Log.i("NotificationService","pendIntent = "+pendIntent);
		
		long triggerAtMillis = SystemClock.elapsedRealtime() + 3*1000;
		long intervalMillis = setIntervalMillis(1000*60);
		mAlarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME, triggerAtMillis, intervalMillis, pendIntent);
	}
	
	private long setIntervalMillis(long intervalMillis){
		return intervalMillis;
	}

	@Override  
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}
