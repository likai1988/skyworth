package com.skyworth.notification;

import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.skyworth.httputil.JsonUtil;
import com.skyworth.recommend.FavouriteVideoChoseActivity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

public class NotificationCheckService extends Service{
	private Handler mHandler;
	private final static int ID = 1;
	private JSONObject object = null;
	private Notification notification;
	private final static int MSG_SUCCESS = 1;
	private final static int MSG_FAILURE = 0;
	private NotificationManager mNotificationmanager;
	
	@Override
	@Deprecated
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		Log.i("NotificationCheckService","--->OnStart");
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.i("NotificationCheckService","--->OnCreat");
		Thread thread = new Thread(runnable);
		thread.start();
		send();
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i("NotificationCheckService","--->OnDestory");
	}

	private void send(){
		mHandler = new Handler(){

			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				switch(msg.what){
				case MSG_SUCCESS:
					JSONObject mObject = (JSONObject) msg.obj;
					if(!(mObject == null)){
						try {
							String context = mObject.getString("content");
							initNotification();
							sendNotification("视频推荐的推送",context);
							stopSelf();
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							stopSelf();
						}
					}else{
						stopSelf();
					}
					break;
				case MSG_FAILURE:
					stopSelf();
					break;
				}
			}
		};
	}
	
	Runnable runnable = new Runnable(){

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				object = getJsonObject("http://172.22.198.198/SkyService/rest/notification","1");
				mHandler.obtainMessage(MSG_SUCCESS, object).sendToTarget();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				mHandler.obtainMessage(MSG_FAILURE).sendToTarget();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				mHandler.obtainMessage(MSG_FAILURE).sendToTarget();
			}
		}
	};
	
	private JSONObject getJsonObject(String url,String UserId) throws IOException, JSONException{
		JSONObject mObject = null;
		JSONArray array = JsonUtil.getJsonArray(url,UserId);
		mObject = array.getJSONObject(0);
		return mObject;
	}
	
	private void initNotification(){
		notification = new Notification();
		notification.icon = android.R.drawable.arrow_up_float;
		notification.tickerText = "视频推荐的推送";
		notification.when = System.currentTimeMillis();
	}
	
	@SuppressWarnings({ "deprecation"})
	private void sendNotification(String contextTitle ,String contextText){
		mNotificationmanager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
		Intent intent = new Intent(NotificationCheckService.this,FavouriteVideoChoseActivity.class);
		PendingIntent pi = PendingIntent.getActivity(NotificationCheckService.this, 0, intent, 0);
		notification.setLatestEventInfo(NotificationCheckService.this, contextTitle, contextText, pi);
		mNotificationmanager.notify(ID,notification);
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
}
