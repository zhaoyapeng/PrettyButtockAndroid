package com.lary.health.service.sharepreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * @author zhaoyapeng
 * @version create time:2015-5-13下午8:36:32
 * @Email zhaoyp@witmob.com
 * @Description 首页Sharepreferences
 */
public class HomeSharepreferences {

	private static String HOME_TABLE ="home_table";
	private static String HOME_URL = "home_url";//发现url
	
	/**
	 * 存储发现url
	 * */
	public static void saveUrl(Context mContext,String url){
		SharedPreferences sharedPreferences = mContext.getSharedPreferences(HOME_TABLE, Context.MODE_PRIVATE);
		Editor editor  = sharedPreferences.edit();
		editor.putString(HOME_URL, url);
		editor.commit();
	}
	
	/**
	 * 获取发现url
	 * */
	public static String getUrl(Context mContext){
		SharedPreferences sharedPreferences = mContext.getSharedPreferences(HOME_TABLE, Context.MODE_PRIVATE);
		return  sharedPreferences.getString(HOME_URL, "");
	}
}
