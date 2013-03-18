package com.skyworth.recommend;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.widget.GridView;

public class NoYScrollGridView extends GridView{
    private GestureDetector mGestureDetector;

    /**
     * @param context
     * @param attrs
     * @param defStyle
     */
    public NoYScrollGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
        mGestureDetector = new GestureDetector(context, new YScrollDetector());
    }

    /**
     * @param context
     * @param attrs
     */
    public NoYScrollGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        mGestureDetector = new GestureDetector(context, new YScrollDetector());
    }

    /**
     * @param context
     */
    public NoYScrollGridView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        mGestureDetector = new GestureDetector(context, new YScrollDetector());
    }
    
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
        
        return super.onInterceptTouchEvent(ev)&&mGestureDetector.onTouchEvent(ev);
    }

    class YScrollDetector extends SimpleOnGestureListener{

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                float distanceX, float distanceY) {
            // TODO Auto-generated method stub
            Boolean flag = true;
            if(Math.abs(distanceY)>Math.abs(distanceX)){
                flag = false;
            }
            Log.i("scroll",""+flag);
            return flag;
        }
    }
}
