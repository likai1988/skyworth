package com.skyworth.httputil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.skyworth.activityutil.ActionBarActivity;
import com.skyworth.videorecommend.R;

public class HttpGetDemo extends ActionBarActivity{
	private Button button;
	private TextView info;
	private Thread mThread;
	private Handler mHandler;
	private final static int MSG_SUCCESS = 1;
	private final static int MSG_FAILURE = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.i("!!!!","oncreat");
		setContentView(R.layout.getinfo);
		info = (TextView)findViewById(R.id.info);
		button = (Button)findViewById(R.id.get);
		button.setOnClickListener(new GetOnClickListener());
		
		setTextContent();
	}
	
	private void setTextContent(){
		mHandler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				switch(msg.what){
				case MSG_SUCCESS:
					info.setText((String)msg.obj.toString());
					Log.i("!!!","handler string = "+(String)msg.obj.toString());
					break;
				case MSG_FAILURE:
					info.setText("ERROR");
					break;
				}
				super.handleMessage(msg);
			}
		};
	}
	
	public class GetOnClickListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			Log.i("!!!!","onclick");
			// TODO Auto-generated method stub
			mThread = new Thread(runnable);
			mThread.start();
		}
	}
	
	Runnable runnable = new Runnable(){
		@Override
		public void run() {
			// TODO Auto-generated method stub
			Log.i("!!!","run");
			StringBuffer sb = new StringBuffer();
			try {
				String URL = "http://172.22.198.198/SkyService/rest/content/1";
				String body = getContent(URL);
				sb = Json2Sb(body);
				Log.i("!!!","sb = "+sb.toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				mHandler.obtainMessage(MSG_FAILURE).sendToTarget();				
				return;
			}
			mHandler.obtainMessage(MSG_SUCCESS,sb.toString()).sendToTarget();
		}
	};
	
	public static String getContent(String URL) throws Exception{
		Log.i("!!!","getcontent");
		StringBuilder sb = new StringBuilder();
		HttpClient client = new DefaultHttpClient();	
		HttpParams params = client.getParams();
		HttpConnectionParams.setConnectionTimeout(params, 3000);
		HttpConnectionParams.setSoTimeout(params, 5000);
		HttpResponse response = client.execute(new HttpGet(URL));
		HttpEntity entity = response.getEntity();
		if(entity != null){
			BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(),"UTF-8"), 8192);
			String line = null;
			while((line = reader.readLine()) != null){
				sb.append(line+"\n");
			}
			reader.close();
		}
		return sb.toString();
	}
	
	private StringBuffer Json2Sb(String body) throws JSONException{
		JSONArray array = new JSONArray(body);
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<array.length();i++){
			JSONObject obj = array.getJSONObject(i);
			Log.i("!!!",""+obj);
			sb.append("id:").append(obj.getInt("cntid")).append("\t\n");
			sb.append("type").append(obj.getInt("cnttype")).append("\n");
			sb.append("name:").append(obj.getString("cntname")).append("\n");
			sb.append("url:").append(obj.getString("cnturl")).append("\n");
			sb.append("keywords:").append(obj.getString("keywords")).append("\n");
			sb.append("description:").append(obj.getString("description")).append("\n");
			sb.append("definition:").append(obj.getString("definition")).append("\n");
			sb.append("duration:").append(obj.getString("duration")).append("\n");
			sb.append("status:").append(obj.getString("status")).append("\n");
			sb.append("recommendexp:").append(obj.getString("recommendexp")).append("\n");
			sb.append("creatorid:").append(obj.getString("creatorid")).append("\n");
			sb.append("creatdate:").append(obj.getString("creatdate")).append("\n");
			sb.append("modifydate:").append(obj.getString("modifydate")).append("\n");
			sb.append("\n");
			}
		return sb;
	}
}
