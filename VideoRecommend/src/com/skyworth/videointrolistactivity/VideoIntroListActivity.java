package com.skyworth.videointrolistactivity;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.skyworth.activityutil.ActionBarActivity;
import com.skyworth.controller.MidController;
import com.skyworth.entity.Program;
import com.skyworth.entity.provider.ProgramProvider;
import com.skyworth.http.identifier.ProgramHttp;
import com.skyworth.httputil.HttpUtil;
import com.skyworth.httputil.JsonUtil;
import com.skyworth.recommend.GridViewItemAdapter;
import com.skyworth.videorecommend.R;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class VideoIntroListActivity extends ActionBarActivity{
	private final static String TAG = "VideoIntroListActivity";
	private final static int MSG_SUCCESS = 1;
	private final static int MSG_FAILURE = 0;
	private final static int IMA_MSG_SUCCESS = 3;
	private final static int IMA_MSG_FAILURE = 4;
	
	private VideoIntroListAdapter adapter;
	private String title = "";
	private String grade = "";
	private String intro = "";
	private String url="";
	private List<String> names = null;
	private ListView listview = null;
	private ProgressBar loadbar;
	private TextView notice;
	private RelativeLayout layout;
	private JSONArray array;
	private JSONObject object;
	private Handler mhandler;
	private String thumburl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.v(TAG,"onCreat");
		setContentView(R.layout.movie_review_list);
		initActivity();
		initListView();
		listview.setAdapter(adapter);
		Thread thread = new Thread(runnable);
		thread.start();
		
		setContent();
	}
	
	private void initActivity(){
		loadbar = (ProgressBar)findViewById(R.id.progressbar);
		notice = (TextView)findViewById(R.id.nonettext);
		layout = (RelativeLayout)findViewById(R.id.showlayout);
		if(HttpUtil.NetWorkStateCheck(getBaseContext())){
			notice.setVisibility(View.INVISIBLE);
		}else{
			loadbar.setVisibility(View.INVISIBLE);
		}
	}
	
	private void initListView(){
		//TODO
		Log.v(TAG,"initListView");
		listview = (ListView)findViewById(R.id.mylist);
		title = "";
		grade = "dwadwada";
		intro = "";
		adapter = new VideoIntroListAdapter(this, title, grade, intro,url);
	}
	
	private void setContent(){
		mhandler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				switch(msg.what){
				case MSG_SUCCESS:
					//TODO
					Log.v(TAG,"MSG_SUCCESS");
					String string = msg.obj.toString();
					Log.v(TAG,"string = "+string);
					try {
						JSONObject object = new JSONObject(string);
						TextView name = (TextView)findViewById(R.id.video_name);
						TextView cate = (TextView)findViewById(R.id.grade);
						TextView intro = (TextView)findViewById(R.id.intro);
						name.setText(object.getString("title"));
						cate.setText(object.getString("cntsubtype"));
						intro.setText(object.getString("cntdesc"));
						url = object.getString("url");
						
						thumburl = object.getString("thumb");
						Thread myThread = new Thread(myRunnable);
						myThread.start();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					loadbar.setVisibility(View.INVISIBLE);
					layout.setVisibility(View.VISIBLE);
					break;
				case MSG_FAILURE:
					Log.v(TAG,"MSG_FAILURE");
					loadbar.setVisibility(View.INVISIBLE);
					notice.setVisibility(View.VISIBLE);
					break;
				case IMA_MSG_SUCCESS:
					ImageView image = (ImageView)findViewById(R.id.posterview);
					image.setImageBitmap((Bitmap) msg.obj);
					Log.v(TAG,"msg2success = "+msg.obj.toString());
				}
			}
		};
	}
	 
	Runnable runnable = new Runnable(){

		@Override
		public void run() {
			// TODO Auto-generated method stub
			String url = getURL();
			try {	
				//array = JsonUtil.getJsonObject(url);
				object = JsonUtil.getJsonObject(url);
//				thumburl = object.getString("thumb");
				mhandler.obtainMessage(MSG_SUCCESS, object).sendToTarget();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Log.v(TAG,TAG+"--->IOException");
				e.printStackTrace();
				mhandler.obtainMessage(MSG_FAILURE).sendToTarget();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				Log.v(TAG,TAG+"--->JSONException");
				e.printStackTrace();
				mhandler.obtainMessage(MSG_FAILURE).sendToTarget();
			}
		}
	};
	
	Runnable myRunnable = new Runnable(){

		@Override
		public void run() {
			// TODO Auto-generated method stub
//			String string = "123";
			Bitmap bitmap;
			try {
				bitmap = downloadBitmap(thumburl);
				mhandler.obtainMessage(IMA_MSG_SUCCESS, bitmap).sendToTarget();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				mhandler.obtainMessage(IMA_MSG_FAILURE).sendToTarget();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				mhandler.obtainMessage(IMA_MSG_FAILURE).sendToTarget();
			}
		}
	};
	
	private Bitmap downloadBitmap(String thumbUrl) throws IOException, JSONException{
		HttpEntity entity = HttpUtil.httpGet(thumbUrl);
		if(entity != null){
			InputStream stream = null;
			stream = entity.getContent();
			Bitmap bitmap = BitmapFactory.decodeStream(stream);
		return bitmap;
		}
		return null;
	}
	
	private String getURL(){
		Intent intent = this.getIntent();
		Bundle bundle = intent.getExtras();
		String url = bundle.getString("URL");
		return url;
	}
}
