package com.skyworth.http;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.animation.AnimatorSet.Builder;
import android.util.Log;

import com.skyworth.videorecommend.BuildConfig;

public abstract class BaseHttp<E> implements Runnable {
    private static final String TAG = "BaseHttp";
    /**
     * http request url address
     */
    protected String mUri = null;

    protected E mResult = null;

    @Override
    public void run() {
        Log.d(TAG, "========================");
        sendRequest();
    }

    private synchronized void sendRequest() {
        InputStream is = null;
        if (null == mUri) {
            return;
        }
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "mUri= " + mUri);
        }
        try {
            URL url = new URL(mUri);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod(HttpUtil.METHOD_GET);
            conn.setDoInput(true);
            conn.connect();
            int resultCode = conn.getResponseCode();

            // int resultCode = 0;
            // apache http request
            // URI uri = new URI(mUri);
            // HttpClient client = new DefaultHttpClient();
            // HttpParams httpPatams = client.getParams();
            // HttpConnectionParams.setConnectionTimeout(httpPatams, 15000);
            // HttpConnectionParams.setSoTimeout(httpPatams, 10000);
            // HttpResponse response = client.execute(new HttpGet(uri));
            // resultCode = response.getStatusLine().getStatusCode();

            if (BuildConfig.DEBUG) {
                Log.d(TAG, "response code: " + resultCode);
            }
            if (200 == resultCode) {
                is = conn.getInputStream();
                mResult = parse(is);
            } else {
                if (BuildConfig.DEBUG) {
                    Log.e(TAG, "request error");
                }
            }
        } catch (MalformedURLException e) {
            Log.e(TAG, "== MalformedURLException ==", e);
        } catch (IOException e) {
            Log.e(TAG, "open connection fail or get inputstream error", e);
        }
    }

    /**
     * Get the response parsed from
     * 
     * @return E set by class object created
     */
    public E getResult() {
        return mResult;
    }
    
    /**
     * set request url
     * 
     * @param url
     */
    public void setUrl(String url) {
        mUri = url;
    }

    /**
     * Method parse content that result from server, returns E object
     * 
     * @param is
     *            InputStream from network
     * @return E that user wants set on instance created
     */
    protected abstract E parse(InputStream is);
}
