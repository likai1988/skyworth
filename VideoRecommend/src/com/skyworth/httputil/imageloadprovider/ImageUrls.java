package com.skyworth.httputil.imageloadprovider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Entity;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.skyworth.httputil.JsonUtil;

public class ImageUrls {
    private final static String TAG = "ImageUrls";
    private static JSONObject object;
    
    public static ArrayList<String> urls = new ArrayList<String>(1);
    
    public static void initUrls(String httpurl) throws IOException, JSONException{
        Log.i(TAG,"initUrls");       
        object = JsonUtil.getJsonObject(httpurl);
        JSONArray array = object.getJSONArray("list");
        for(int i=0;i<array.length();i++){
            Log.i("ImageUrls",array.getJSONObject(i).getString("thumb"));
            urls.add(array.getJSONObject(i).getString("thumb"));
        }
    }
}
