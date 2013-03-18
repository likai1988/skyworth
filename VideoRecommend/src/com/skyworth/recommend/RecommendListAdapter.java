package com.skyworth.recommend;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.skyworth.entity.provider.ProgramProvider;
import com.skyworth.httputil.imageloadprovider.ImageUrls;
import com.skyworth.httputil.imageloadutil.ImageFetcher;
import com.skyworth.videointrolistactivity.VideoIntroListActivity;
import com.skyworth.videorecommend.R;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class RecommendListAdapter extends BaseAdapter{
    private final static String TAG = "RecommendListAdapter";
	private LayoutInflater inflater;
	private NoYScrollGridView mGridView;
	private TextView typetext;
	private GridViewItemAdapter adapter;
	private Context mContext;
	private ImageFetcher mImageFetcher;
	private ProgramProvider mProgramProvider;
	private int position;
	
	public RecommendListAdapter(Context context,ImageFetcher imageFetcher,ProgramProvider programProvider){
		inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mContext = context;
		mImageFetcher = imageFetcher;
		mProgramProvider = programProvider;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public Object getItem(int position) {
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
	    Log.i(TAG,"position:"+position);
	    this.position = position;
	    convertView = inflater.inflate(R.layout.recommendlist_item, null);
	    switch(position){
	    case 0:
	        setview(convertView,"dsj");
	        break;
	    case 1:
	        setview(convertView,"dy");
	        break;
	    case 2:
	        setview(convertView,"zy");
	        break;
	    case 3:
	        setview(convertView,"dm");
	        break;
	    }
		//setview(convertView);
		return convertView;
	}
	
	private void setview(View view,String category){
	    //view = inflater.inflate(R.layout.recommendlist_item, null);
	    Log.i(TAG,category+"list:"+mProgramProvider.getProgramList(category));
        adapter = new GridViewItemAdapter(mContext,mProgramProvider.getProgramList(category),mImageFetcher);
        typetext = (TextView)view.findViewById(R.id.title);
        setTextViewContent(position);
        
        mGridView = (com.skyworth.recommend.NoYScrollGridView)view.findViewById(R.id.regridview);
        mGridView.setAdapter(adapter);
        setGridView();
        mGridView.setOnItemClickListener(new ItemClickListener());
	}
	
	private void setTextViewContent(int position){
	    String[] types = new String[]{"电视剧","电影","综艺","动漫"};
	    typetext.setText(types[position]);
	}
	
	private void setGridView(){
	    LayoutParams params = new LayoutParams(adapter.getCount()*(200+10),300);
        mGridView.setLayoutParams(params);
        mGridView.setColumnWidth(200);
        mGridView.setHorizontalSpacing(10);
        mGridView.setStretchMode(GridView.NO_STRETCH);
        mGridView.setNumColumns(adapter.getCount());
	}
	
	public class ItemClickListener implements OnItemClickListener{
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
		    int id = (int)mProgramProvider.findProgramByPosition(position, "dsj").mId;
		    Log.i("id",""+id);
			String url = "http://172.22.198.198/SkyService/rest/content/3";
			Intent intent = new Intent();
			Bundle bundle = new Bundle();
			bundle.putString("URL",url);
			intent.putExtras(bundle);
			intent.setClass(mContext, VideoIntroListActivity.class);
			mContext.startActivity(intent);
		}
	}
}
