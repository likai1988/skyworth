package com.skyworth.httputil;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * @author admin
 *
 */
public class HttpUtil {
	private final static String TAG = "HttpUtil";
	
	/**
	 * 客户端网络通路检查
	 * @param context
	 * @return
	 */
	public static boolean NetWorkStateCheck(Context context){
		Log.v(TAG,"HttpUtil ---> NetWorkStateCheck");
		ConnectivityManager con = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo NetInfo = con.getActiveNetworkInfo();
		if(NetInfo == null || !NetInfo.isAvailable()){
			Log.v(TAG,"FALSE");
			return false;
		}else{
			Log.v(TAG,"TRUE");
			return true;
		}
	}
	
	/*public static boolean PostReturnWithStatusCode(String URL,JSONArray array) throws Exception{
		HttpClient client = getHttpClient();
		HttpPost mHttpPost = new HttpPost(URL);
		mHttpPost.addHeader("Content-Type", "application/json");
		
		HttpResponse response;
		JSONArray jsonArray = JsonWrapper();
		StringEntity entity = new StringEntity(jsonArray.toString(), HTTP.UTF_8);
		Log.i("jsonarray",""+entity);					
		mHttpPost.setEntity(entity); 
		response = client.execute(mHttpPost);
		int statusCode = response.getStatusLine().getStatusCode();
		if(statusCode == 200){
			return true;
		}else{
			return false;
		}
	}*/
	
	/**
	 * 不带报头参数获取数据
	 * @param URL
	 * @return
	 * @throws IOException
	 * @throws JSONException
	 */
	public static HttpEntity httpGet(String URL) throws IOException, JSONException{
		HttpClient client = getHttpClient();
		HttpResponse response = client.execute(new HttpGet(URL));
		HttpEntity entity = response.getEntity();
		return entity;
	}
	
	/**
	 * 带报头参数获取数据
	 * @param URL,UserId
	 * @param UserId
	 * @return HttpEntity
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static HttpEntity httpGet(String URL,String UserId) throws ClientProtocolException, IOException{
		HttpClient client = getHttpClient();
		HttpGet request = new HttpGet(URL);
		request.addHeader("userid", UserId);
		HttpResponse response = client.execute(request);
		HttpEntity entity = response.getEntity();
		return entity;
	}
	
	/**
	 * 全局HttpClient连接参数设置
	 * @return
	 */
	private static HttpClient getHttpClient(){
		HttpClient client = new DefaultHttpClient();	
		/*HttpParams params = client.getParams();
		HttpConnectionParams.setConnectionTimeout(params, 10000);
		HttpConnectionParams.setSoTimeout(params, 10000);*/
		return client;
	}
	
	/**
	 * put 待整理
	 * @return
	 * @throws Exception
	 *//*
	private static JSONArray JsonWrapper() throws Exception{
		JSONObject obj = new JSONObject();
		obj.put("cntid", 1);
		obj.put("cnttype",1);
		obj.put("cntname", "测试内容");
		obj.put("cnturl", "http://player.youku.com/player.php/sid/XNDk5NjY4NTc2/v.swf");
		obj.put("comment", "这是测试推荐语");
		obj.put("keywords", "这是测试关键字");
		obj.put("description", "描述");
		obj.put("definition", 3);	
		obj.put("duration", 93);
		obj.put("status", 1);
		obj.put("recommendexp", 1);
		obj.put("creatorid", 1);
		obj.put("creatdate", "2013-01-08 14:53:29.0");
		obj.put("modifydate", "2013-01-08 14:53:37.0");
		Log.i("jsonobject","" + obj);
		
		JSONArray jsonArray = new JSONArray();
		jsonArray.put(obj);
		return jsonArray;
	}*/
}
