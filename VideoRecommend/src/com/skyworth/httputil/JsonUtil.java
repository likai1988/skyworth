package com.skyworth.httputil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpEntity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

/**
 * JsonUtil工具类
 * @author admin
 *
 */
public class JsonUtil {
	private final static String TAG = "JsonUtil";
	private static HttpEntity entity;
	private static List<String> list_name = null;
	
	/**
	 * 设置日期的转换格式
	 * @author lee
	 */
	@SuppressWarnings("unused")
	private static void setDateFormate(){
		
	} 
	
	/**
	 * 从JSONObject中获取name的值
	 * @param object
	 * @param name
	 * @return int
	 * @throws JSONException 
	 */
	public static int getIntValueFromJsonObject(JSONObject object,String name) throws JSONException{
		@SuppressWarnings("unused")
		int value = object.getInt(name);
		return 0;
	}
	
	/**
	 * 将http get获得的entity转化为JSONArray，不带HEADER参数的请求
	 * @param url
	 * @return JSONArray
	 * @throws IOException
	 * @throws JSONException
	 */
	public static JSONArray getJsonArray(String url) throws IOException, JSONException{
		entity = HttpUtil.httpGet(url);
		StringBuilder sb = new StringBuilder();
		if(entity != null){
			BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(),"UTF-8"), 8192);
			String line = null;
			while((line = reader.readLine()) != null){
				sb.append(line+"\n");
			}
			reader.close();
		}
		JSONArray array = new JSONArray(sb.toString());
		return array;
	}
	
	public static JSONObject getJsonObject(String url) throws IOException, JSONException{
		entity = HttpUtil.httpGet(url);
		StringBuilder sb = new StringBuilder();
		if(entity != null){
			BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(),"UTF-8"), 8192);
			String line = null;
			while((line = reader.readLine()) != null){
				sb.append(line+"\n");
			}
			reader.close();
		}
		JSONObject object = new JSONObject(sb.toString());
		return object;
	}
	
	/**
	 * 将http get获得的entity转化为JSONArray，携带HEADER参数的请求
	 * @param url
	 * @param UserId
	 * @return
	 * @throws IOException
	 * @throws JSONException
	 */
	public static JSONArray getJsonArray(String url,String UserId) throws IOException, JSONException{
		entity = HttpUtil.httpGet(url,UserId);
		StringBuilder sb = new StringBuilder();
		if(entity != null){
			BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(),"UTF-8"), 8192);
			String line = null;
			while((line = reader.readLine()) != null){
				sb.append(line+"\n");
			}
			reader.close();
		}
		JSONArray array = new JSONArray(sb.toString());
		return array;
	}
	
	public static List<String> getNames(String url) throws IOException, JSONException{
		JSONArray array = getJsonArray(url);
		for(int i = 0;i<array.length();i++){
			JSONObject object = array.getJSONObject(i);
			list_name.add(object.getString("title"));
		}
		Log.v(TAG,"list_name = "+list_name);
		return list_name;	
	}
}
