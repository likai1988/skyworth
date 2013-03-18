package com.skyworth.recommend;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.skyworth.entity.Program;
import com.skyworth.entity.provider.ProgramProvider;
import com.skyworth.httputil.imageloadutil.ImageFetcher;
import com.skyworth.httputil.imageloadutil.RecyclingImageView;
import com.skyworth.videorecommend.R;

public class GridViewItemAdapter extends BaseAdapter{
	private Context mContext;
	private ImageFetcher mImageFetcher;
	private ArrayList<Program> list;
	
	public GridViewItemAdapter(Context context,ArrayList<Program> list,ImageFetcher imageFetcher){
		mContext = context;
		mImageFetcher = imageFetcher;
		
		//mProgramProvider = programProvider;
		this.list = new ArrayList<Program>();
		Log.i("xxxxx",""+list.size() );
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
	    Log.i("grid adapter",""+list.size());
		return list.size();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view = View.inflate(mContext, R.layout.grid_item, null);
		LinearLayout mLayout = (LinearLayout)view.findViewById(R.id.itemview);
		
		RecyclingImageView posterViews = (RecyclingImageView)mLayout.findViewById(R.id.poster);
		TextView videoNames = (TextView)mLayout.findViewById(R.id.moviename);
		//posterViews.setImageResource(thePosterIds[position]);
		
		Log.i("!!!!!",list.get(position).mTitle);
		videoNames.setText(list.get(position).mTitle);
		mImageFetcher.loadImage(list.get(position).mThumbUrl, posterViews); 
		//
		return mLayout;
	}
	
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
}
