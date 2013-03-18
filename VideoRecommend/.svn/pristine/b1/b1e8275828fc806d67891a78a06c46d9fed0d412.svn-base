package com.skyworth.recommend;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;

import com.skyworth.entity.Program;
import com.skyworth.entity.provider.ProgramProvider;
import com.skyworth.httputil.JsonUtil;
import com.skyworth.httputil.imageloadprovider.ImageUrls;
import com.skyworth.httputil.imageloadutil.ImageCache;
import com.skyworth.httputil.imageloadutil.ImageCache.ImageCacheParams;
import com.skyworth.httputil.imageloadutil.ImageFetcher;
import com.skyworth.search.SearchActivity;
import com.skyworth.videorecommend.R;

public class RecommendListActivity extends Activity{
    private final static int MSG_SUCCESS = 1;
    private final static int MSG_FAILURE = 0;
    private static final String CATEGORY_MOVIE = "dy";
    private static final String CATEGORY_TEVEPLAY = "dsj";
    private static final String CATEGORY_SHOW = "zy";
    private static final String CATEGORY_CARTOON = "dm";
	private RecommendListAdapter adapter;
	private LayoutInflater inflater;
	private View view = null;
	private ViewPager viewpager;
    private TextView textview;
    private List<ImageView> imageViews;
    private List<View> dots;
    private String[] titles;
    private int[] imageResIds;
    private int currentItem = 0;
    private ScheduledExecutorService mScheduledExecutorService;
    private MyListView listview;
    
    private ImageFetcher mImageFetcher;
    private int mImageThumbSize;
    
    private ProgramProvider mProgramProvider;
    private Program program = null;
    
    private Handler mHandler;
    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            viewpager.setCurrentItem(currentItem);
        }
    };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.recommendlist);
        
	    ImageCacheParams cacheParams = new ImageCacheParams(RecommendListActivity.this, "thumbs");
	    cacheParams.setMemCacheSizePercent(0.25f);
	    mImageThumbSize = getResources().getDimensionPixelSize(R.dimen.image_thumbnail_size);
	    mImageFetcher = new ImageFetcher(this,mImageThumbSize);
	    mImageFetcher.setLoadingImage(R.drawable.jackreacher);
	    mImageFetcher.addImageCache(this, cacheParams);
		initListView();
	}
	
	@Override
    protected void onStart() {
        super.onStart();
        mScheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        mScheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 1, 2, TimeUnit.SECONDS);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mScheduledExecutorService.shutdown();
    }

    private void initListView(){
		listview = (MyListView)findViewById(R.id.recommendlist);
		ImageButton searchButton = (ImageButton)findViewById(R.id.search);
		searchButton.setOnClickListener(new searchOnClickListener());
		
		inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.recommendlist_item_pager, null);
        listview.addHeaderView(view,null,false);
        
        viewpager = (ViewPager)view.findViewById(R.id.vp);
        textview = (TextView)view.findViewById(R.id.tv_title);
        imageResIds = new int[]{R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,
                R.drawable.e};
        
        titles = new String[imageResIds.length];
        titles[0] = "test0";
        titles[1] = "test1";
        titles[2] = "test2";
        titles[3] = "test3";
        titles[4] = "test4";
        
        dots = new ArrayList<View>();
        dots.add(view.findViewById(R.id.v_dot0));
        dots.add(view.findViewById(R.id.v_dot1));
        dots.add(view.findViewById(R.id.v_dot2));
        dots.add(view.findViewById(R.id.v_dot3));
        dots.add(view.findViewById(R.id.v_dot4));
        
        imageViews = new ArrayList<ImageView>();
        for(int i=0;i<imageResIds.length;i++){
            ImageView imageview = new ImageView(this);
            imageview.setImageResource(imageResIds[i]);
            imageview.setScaleType(ScaleType.CENTER_CROP);
            imageViews.add(imageview);
        }
        
        viewpager.setOnPageChangeListener(new pageChangeListener());
        viewpager.setAdapter(new viewpagerAdapter());
        
        //获取url列表线程，成功后设置adapter
        Thread thread = new Thread(runnable);
        thread.start();
        
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.i("RLA","msg.what"+msg.what);
                switch(msg.what){
                case MSG_SUCCESS:
                    Log.i("MSG_SUCCESS","MSG_SUCCESS");
                    adapter = new RecommendListAdapter(RecommendListActivity.this,mImageFetcher,mProgramProvider);
                    listview.setAdapter(adapter);
                    break;
                case MSG_FAILURE:
                    //TODO 
                    //添加异常处理
                    adapter = new RecommendListAdapter(RecommendListActivity.this,mImageFetcher,mProgramProvider);
                    listview.setAdapter(adapter);
                    break;
                }
            }
        };
	}
    
    Runnable runnable = new Runnable(){

        @Override
        public void run() {
            try {
                /*ImageUrls.initUrls("http://172.22.198.198/SkyService/rest/recommend?category=dsj");
                //ImageUrls.initUrls("http://172.22.198.198/SkyService/rest/recommend?category=dsj");
                //ImageUrls.initUrls("http://172.22.198.198/SkyService/rest/recommend?category=dm");
                //ImageUrls.initUrls("http://172.22.198.198/SkyService/rest/recommend?category=zy");
                 */
                mProgramProvider = ProgramProvider.getInstance();
                setProgramProvider("http://172.22.198.198/SkyService/rest/recommend?category=dsj", CATEGORY_TEVEPLAY);
                setProgramProvider("http://172.22.198.198/SkyService/rest/recommend?category=dy", CATEGORY_MOVIE);
                setProgramProvider("http://172.22.198.198/SkyService/rest/recommend?category=dm", CATEGORY_CARTOON);
                setProgramProvider("http://172.22.198.198/SkyService/rest/recommend?category=zy", CATEGORY_SHOW);
                Log.i("list nums",""+mProgramProvider.getProgramList("dsj"));
                Log.i("list nums",""+mProgramProvider.getProgramList("dy"));
                Log.i("list nums",""+mProgramProvider.getProgramList("dm"));
                Log.i("list nums",""+mProgramProvider.getProgramList("zy"));
                
                mHandler.obtainMessage(MSG_SUCCESS).sendToTarget();
            } catch (IOException e) {
                e.printStackTrace();
                mHandler.obtainMessage(MSG_FAILURE).sendToTarget();
            } catch (JSONException e) {
                e.printStackTrace();
                mHandler.obtainMessage(MSG_FAILURE).sendToTarget();
            }
        }
    };
    
    private void setProgramProvider(String url,String category) throws JSONException, IOException{
        JSONObject object = JsonUtil.getJsonObject(url);
        JSONArray array = object.getJSONArray("list");
        for(int i=0;i<array.length();i++){
            program = new Program();
            JSONObject obj = array.getJSONObject(i);
            program.mThumbUrl = obj.getString("thumb").toString();
            program.mTitle = obj.getString("title").toString();
            program.mId = Long.parseLong(obj.getString("id").toString());
            Log.i("mId",""+program.mId);
            Log.i("string",obj.getString("id").toString());
            mProgramProvider.addProgram(program,category);
        }
    }
	
	private class ScrollTask implements Runnable{
        @Override
        public void run() {
            currentItem = (currentItem + 1) % imageViews.size();
            handler.obtainMessage().sendToTarget(); 
        }
	}
	
	public class pageChangeListener implements OnPageChangeListener{
        private int oldPosition = 0;
        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int arg0) {
            currentItem = arg0;
            textview.setText(titles[arg0]);
            dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
            dots.get(arg0).setBackgroundResource(R.drawable.dot_focused);
            oldPosition = arg0;
        }
    }
	
	private class viewpagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return imageResIds.length;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public Object instantiateItem(View container, int position) {
            ((ViewPager)container).addView(imageViews.get(position));
            return imageViews.get(position);
        }

        @Override
        public void destroyItem(View container, int position, Object object) {
            ((ViewPager)container).removeView((View)object);
        }
    }
	
	class searchOnClickListener implements OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(RecommendListActivity.this, SearchActivity.class);
            startActivity(intent);
        }
	}

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        mProgramProvider.clear();
    }
}
