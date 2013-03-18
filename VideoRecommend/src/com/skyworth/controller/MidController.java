/**
 * 
 */
package com.skyworth.controller;

import com.skyworth.http.BaseHttp;
import com.skyworth.http.ThreadPoolUtil;
import com.skyworth.http.identifier.ProgramHttp;

import android.content.Context;

/**
 * Control class between ui and lower level, it is one single instance class
 * 
 * @author Ni-Guanhua
 * 
 */

public final class MidController {

    private static Object mObj = new Object();
    /**
     * single instance
     */
    private static MidController mController = null;
    /**
     * detail activity or other context subclass
     */
    private Context mContext;
    private BaseHttp mBaseHttp;

    private MidController(Context context, String tag) {
        mContext = context;
        if (ProgramHttp.TAG.equals(tag)) {
            mBaseHttp = new ProgramHttp();
        }
    }

    /**
     * It is a static method that returns the only instance of MidController
     * class
     * 
     * @param context
     * @return
     */
    public static MidController getInstance(Context context, String tag) {
        synchronized (mObj) {
            if (null == mController) {
                mController = new MidController(context, tag);
            }
        }
        return mController;
    }

    /**
     * send http request by url
     * 
     * @param url
     *            network address where resources com form
     */
    public void sendRequest(String url) {
        mBaseHttp.setUrl(url);
        ThreadPoolUtil.execute(mBaseHttp);
    }

    /**
     * Get the parsed results
     * 
     * @return
     */
    public Object getResult() {
        return mBaseHttp.getResult();
    }
}
