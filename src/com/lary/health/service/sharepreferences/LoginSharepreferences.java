package com.lary.health.service.sharepreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * @author zhaoyapeng
 * @version create time:2015-5-15下午10:23:11
 * @Email zhaoyp@witmob.com
 * @Description 登录
 */
public class LoginSharepreferences {
	private static String LOGIN_TABLE ="login_table";
	private static String USERID = "userId";//发现url
	
	/**
	 * 存储userId
	 * */
	public static void saveUserId(Context mContext,String userId){
		SharedPreferences sharedPreferences = mContext.getSharedPreferences(LOGIN_TABLE, Context.MODE_PRIVATE);
		Editor editor  = sharedPreferences.edit();
		editor.putString(USERID, userId);
		editor.commit();
	}
	
	/**
	 * 获取userId
	 * */
	public static String getUserId(Context mContext){
		SharedPreferences sharedPreferences = mContext.getSharedPreferences(LOGIN_TABLE, Context.MODE_PRIVATE);
		return  sharedPreferences.getString(USERID, "");
	}
}
