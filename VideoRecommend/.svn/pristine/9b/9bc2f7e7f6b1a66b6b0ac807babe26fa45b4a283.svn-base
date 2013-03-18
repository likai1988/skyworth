package com.skyworth.videointrolistactivity;

import com.skyworth.videorecommend.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class VideoIntroListAdapter extends BaseAdapter{
	private final static String TAG = "VideoIntroListAdapter";
	private LayoutInflater inflater;
	private String title = "";
	private String grade = "";
	private String intro = "";
	private String murl="";
	private Context mContext;
	
	public VideoIntroListAdapter(Context context,String title,String grade,String intro,String url){
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.title = title;
		this.grade = grade;
		this.intro = intro;
		this.murl = url;
		Log.i("murl",murl);
		mContext = context;
	}

	@Override
	public int getCount() {
	    return 3;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
	    return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Log.v(TAG, "getView");
		switch(position){
		case 0:
//			显示海报，播放按钮，片名，简介
			if(convertView == null){
				convertView = inflater.inflate(R.layout.movie_review_item_info, null);
			}
			TextView titleview = (TextView)convertView.findViewById(R.id.video_name);
			TextView gradeview = (TextView)convertView.findViewById(R.id.grade);
			TextView introview = (TextView)convertView.findViewById(R.id.intro);
			Button playbutton = (Button)convertView.findViewById(R.id.playbutton);
			Button test1button = (Button)convertView.findViewById(R.id.test1);
			Button test2button = (Button)convertView.findViewById(R.id.test2);
			
			titleview.setText(title);
			gradeview.setText(grade);
			introview.setText(intro);
			
			playbutton.setOnClickListener(new playOnClickListener());
			test1button.setOnClickListener(new test1OnClickListener());
			test2button.setOnClickListener(new test2OnClickListener());
			break;
		case 1:
			if(convertView == null){
				convertView = inflater.inflate(R.layout.movie_review_item_commend, null);
			}
			break;
		case 2:
			if(convertView == null){
				convertView = inflater.inflate(R.layout.movie_review_item_commend, null);
			}
			TextView text = (TextView)convertView.findViewById(R.id.tag);
			text.setText("测试");
			break;
		case 3:
			//TODO
			break;
		}
		return convertView;
	}
	
	public class playOnClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(Intent.ACTION_VIEW);
			Uri uri = Uri.parse(murl);
			intent.setDataAndType(uri, "video/*");
			mContext.startActivity(intent);
		}
	}
	
	public class test1OnClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
		}
	}
	
	public class test2OnClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
		}
	}
}
