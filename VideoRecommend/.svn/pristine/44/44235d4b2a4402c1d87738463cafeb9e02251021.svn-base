package com.skyworth.recommend;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;

public class MyListView extends ListView {
    private Context mContext;
    private GestureDetector mGestureDetector;
    View.OnTouchListener mGestureListener;
    
    public MyListView(Context context) {
      super(context);
      mContext = context;
      mGestureDetector = new GestureDetector(new YScrollDetector());
      setFadingEdgeLength(0);
    }
    
    public MyListView(Context context, AttributeSet attrs) {
      super(context, attrs);
      mContext = context;
      mGestureDetector = new GestureDetector(new YScrollDetector());
      setFadingEdgeLength(0);
    }
    
    public MyListView(Context context, AttributeSet attrs, int defStyle) {
      super(context, attrs, defStyle);
      mContext = context;
      mGestureDetector = new GestureDetector(new YScrollDetector());
      setFadingEdgeLength(0); 
    }
    
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
      return super.onInterceptTouchEvent(ev) && mGestureDetector.onTouchEvent(ev);
    }
    
    class YScrollDetector extends SimpleOnGestureListener {
        Boolean flag = false;
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                if(distanceY!=0&&distanceX!=0){
               
                }
                if(Math.abs(distanceY) >= Math.abs(distanceX)) {
                    flag = true;
                }
                Log.i("viewpager",""+flag);
                return flag;
            }
        }
    }