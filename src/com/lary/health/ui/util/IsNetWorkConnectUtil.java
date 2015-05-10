/**
 * 
 */
package com.lary.health.ui.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @author feiyang
 * @version create time:2014-12-3上午10:06:38
 * @Email weify@witmob.com
 * @Description TODO
 */
public class IsNetWorkConnectUtil {
	// 判断是否有网络连接  
    public static boolean isNetworkConnected(Context context) {  
        if (context != null) {  
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context  
                    .getSystemService(Context.CONNECTIVITY_SERVICE);  
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();  
            if (mNetworkInfo != null) {  
                return mNetworkInfo.isAvailable();  
            }  
        }  
        return false;  
    }  
}
