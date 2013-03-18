package com.skyworth.httputil;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONObject;

import com.skyworth.activityutil.ActionBarActivity;
import com.skyworth.videorecommend.R;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HttpPostDemo extends ActionBarActivity{
	private EditText InputValue;
	private Button PostButton;
	private String url;
	private Thread thread;
	private Handler handler;
	private final static int MSG_SUCCESS = 1;
	private final static int MSG_FAILURE = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.post);
		InputValue = (EditText)findViewById(R.id.edit1);
		PostButton = (Button)findViewById(R.id.post);
		PostButton.setOnClickListener(new PostOnClickListener());
		
		handler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				switch(msg.what)
				{
				case MSG_SUCCESS:
					Toast.makeText(HttpPostDemo.this, "POST SUCCESS", Toast.LENGTH_SHORT).show();
					break;
				case MSG_FAILURE:
					Toast.makeText(HttpPostDemo.this, "ERROR", Toast.LENGTH_SHORT).show();
					break;
				}
				super.handleMessage(msg);
			}
		};
	}
	
	public class PostOnClickListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			thread = new Thread(runnable);
			thread.start();
		}
	}
	
	Runnable runnable = new Runnable(){
		@Override
		public void run() {
			// TODO Auto-generated method stub
			url = "http://172.22.198.198/SkyService/rest/content";
			postContent(url);
		}
	};
	
	private void postContent(String url){
		HttpClient client = new DefaultHttpClient();
		HttpParams params = client.getParams();
		HttpConnectionParams.setConnectionTimeout(params, 3000);
		HttpConnectionParams.setSoTimeout(params, 5000);
		
		HttpPost mHttpPost = new HttpPost(url);
		mHttpPost.addHeader("Content-Type", "application/json");

		HttpResponse response;
		try {
			JSONArray jsonArray = JsonWrapper();
			StringEntity entity = new StringEntity(jsonArray.toString(), HTTP.UTF_8);
			/*entity.setContentEncoding("utf-8");
			entity.setContentType("application/json");*/
			Log.i("jsonarray",""+entity);					
			mHttpPost.setEntity(entity); 
			response = client.execute(mHttpPost);
			int statusCode = response.getStatusLine().getStatusCode();
			if(statusCode == 200){
				handler.obtainMessage(MSG_SUCCESS).sendToTarget();
			}
			else{
				handler.obtainMessage(MSG_FAILURE).sendToTarget();
				Log.i("post","statuscode = "+statusCode);
			}
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private JSONArray JsonWrapper() throws Exception{
		JSONObject obj = new JSONObject();
		obj.put("cntid", 1);
		obj.put("cnttype",1);
		obj.put("cntname", "测试内容");
		obj.put("cnturl", "http://player.youku.com/player.php/sid/XNDk5NjY4NTc2/v.swf");
		obj.put("comment", "这是测试推荐语");
		obj.put("keywords", "这是测试关键字");
		obj.put("description", "描述");
		obj.put("definition", 3);	
		obj.put("duration", 93);
		obj.put("status", 1);
		obj.put("recommendexp", 1);
		obj.put("creatorid", 1);
		obj.put("creatdate", "2013-01-08 14:53:29.0");
		obj.put("modifydate", "2013-01-08 14:53:37.0");
		Log.i("jsonobject","" + obj);
		
		JSONArray jsonArray = new JSONArray();
		jsonArray.put(obj);
		return jsonArray;
	}
}
