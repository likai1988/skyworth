package com.skyworth.http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public final class HttpUtil {
    /**
     * send http request with "get" manner
     */
    public static final String METHOD_GET = "GET";

    /**
     * send http request with "post" manner
     */
    public static final String METHOD_POST = "POST";

    private static final String TAG = "HttpUtil";

    /**
     * Simple network connection check.
     * 
     * @param context
     *            detail context
     */
    public static boolean checkConnection(Context context) {
        final ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isConnectedOrConnecting()) {
            return false;
        }
        return true;
    }
}
