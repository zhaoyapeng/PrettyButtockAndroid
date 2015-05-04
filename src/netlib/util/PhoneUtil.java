package netlib.util;

import java.lang.reflect.Method;
import java.util.Locale;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;

public class PhoneUtil {

	public static String getUserAgent() {
		return getPhoneType();
	}

	/**
	 * 获取设备唯一标示码
	 * 
	 * @param context
	 * @return
	 */
	public static String getDeviceID(Context context) {
		// final TelephonyManager tm = (TelephonyManager)
		// context.getSystemService(Context.TELEPHONY_SERVICE);
		//
		// final String tmDevice, tmSerial, androidId;
		// tmDevice = "" + tm.getDeviceId();
		// tmSerial = "" + tm.getSimSerialNumber();
		// androidId = "" +
		// android.provider.Settings.Secure.getString(context.getContentResolver(),
		// android.provider.Settings.Secure.ANDROID_ID);
		//
		// UUID deviceUuid = new UUID(androidId.hashCode(),
		// ((long)tmDevice.hashCode() << 32) | tmSerial.hashCode());
		// String deviceId = deviceUuid.toString();
		// return deviceId;
		TelephonyManager tm = (TelephonyManager) context.getSystemService(Activity.TELEPHONY_SERVICE);
		String imei = tm.getDeviceId();
		if (imei == null)
			imei = "";
		return imei;
	}

	/**
	 * 获取手机IMEI码
	 * 
	 * @param context
	 * @return
	 */
	public static String getIMEI(Context context) {
		TelephonyManager tm = (TelephonyManager) context.getSystemService(Activity.TELEPHONY_SERVICE);
		String imei = tm.getDeviceId();
		if (imei == null)
			imei = "";
		return imei;
	}

	/**
	 * 获取手机IMSI码
	 * 
	 * @param context
	 * @return
	 */
	public static String getIMSI(Context context) {
		TelephonyManager tm = (TelephonyManager) context.getSystemService(Activity.TELEPHONY_SERVICE);
		String imsi = tm.getSubscriberId();
		if (imsi == null)
			imsi = "";
		return imsi;
	}

	/**
	 * 获取手机网络型号
	 * 
	 * @param context
	 * @return
	 */
	public static String getNetworkOperatorName(Context context) {
		TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		return tm.getNetworkOperatorName();
	}

	/**
	 * 获取手机机型:i9250
	 * 
	 * @return
	 */
	public static String getPhoneType() {
		String type = Build.MODEL;
		if (type != null) {
			type = type.replace(" ", "");
		}
		return type.trim();
	}

	public static String getDevice() {
		return Build.DEVICE;
	}

	public static String getProduct() {
		return Build.PRODUCT;
	}

	public static String getType() {
		return Build.TYPE;
	}

	/**
	 * 获取手机操作系统版本名：如2.3.1
	 * 
	 * @return
	 */
	public static String getSDKVersionName() {
		return Build.VERSION.RELEASE;
	}

	/**
	 * 获取手机操作系统版本号：如4
	 * 
	 * @return
	 */
	public static String getSDKVersion() {
		return Build.VERSION.SDK;
	}

	/**
	 * 获取手机操作系统版本号：如4
	 * 
	 * @return
	 */
	public static int getAndroidSDKVersion() {
		return Build.VERSION.SDK_INT;
	}

	/**
	 * 获取手机号码
	 * 
	 * @param context
	 * @return
	 */
	public static String getNativePhoneNumber(Context context) {
		TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		String nativePhoneNumber = telephonyManager.getLine1Number();
		if (nativePhoneNumber == null) {
			nativePhoneNumber = "";
		}
		return nativePhoneNumber;
	}

	/**
	 * 获取屏幕尺寸，如:320x480
	 * 
	 * @param context
	 * @return
	 */
	public static String getResolution(Context context) {
		DisplayMetrics dm = new DisplayMetrics();
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		wm.getDefaultDisplay().getMetrics(dm);
		return dm.widthPixels + "x" + dm.heightPixels;
	}

	/**
	 * 获取屏幕高度
	 * 
	 * @param context
	 * @return
	 */
	public static int getDisplayWidth(Context context) {
		DisplayMetrics dm = new DisplayMetrics();
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		wm.getDefaultDisplay().getMetrics(dm);
		return dm.widthPixels;
	}

	/**
	 * 获取屏幕高度
	 * 
	 * @param context
	 * @return
	 */
	public static int getDisplayHeight(Context context) {
		DisplayMetrics dm = new DisplayMetrics();
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		wm.getDefaultDisplay().getMetrics(dm);
		return dm.heightPixels;
	}

	/**
	 * 获取屏幕密度
	 * 
	 * @param context
	 * @return
	 */
	public static float getDisplayDensity(Context context) {
		DisplayMetrics dm = new DisplayMetrics();
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		wm.getDefaultDisplay().getMetrics(dm);
		return dm.density;
	}

	/**
	 * 获取手机语言
	 * 
	 * @return
	 */
	public static String getPhoneLanguage() {
		String language = Locale.getDefault().getLanguage();
		if (language == null) {
			language = "";
		}
		return language;
	}

	/**
	 * 获取手机MAC地址
	 * 
	 * @param context
	 * @return
	 */
	public static String getLocalMacAddress(Context context) {
		WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		WifiInfo info = wifi.getConnectionInfo();
		if (info == null)
			return "";
		return info.getMacAddress();
	}

	/**
	 * 获取基带版本
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	public static String getBaseand() {
		try {
			Class cl = Class.forName("android.os.SystemProperties");
			Object invoker = cl.newInstance();
			Method m = cl.getMethod("get", new Class[] { String.class, String.class });
			Object result = m.invoke(invoker, new Object[] { "gsm.version.baseband", "no message" });
			return result.toString();
		} catch (Exception e) {
		}
		return "";
	}

	public static int getCacheSize(Context context) {
		return 1024 * 1024 * ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass() / 8;
	}

	/**
	 * 获取当前分辨率下指定单位对应的像素大小（根据设备信息） px,dip,sp -> px
	 * 
	 * @param context
	 * @param unit
	 * @param size
	 * @return
	 */
	public static int getRawSize(Context context, int unit, float size) {
		Resources resources;
		if (context == null) {
			resources = Resources.getSystem();
		} else {
			resources = context.getResources();
		}
		return (int) TypedValue.applyDimension(unit, size, resources.getDisplayMetrics());
	}

	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 * 
	 * @param context
	 * @param dpValue
	 * @return
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 * 
	 * @param context
	 * @param pxValue
	 * @return
	 */

	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 获取当前应用版本号
	 * */
	public static String getVersionName(Context context) {
		// 获取packagemanager的实例
		PackageManager packageManager = context.getPackageManager();
		// getPackageName()是你当前类的包名，0代表是获取版本信息
		PackageInfo packInfo = null;
		try {
			packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String version = packInfo.versionName;
		return version;
	}

	public static int getPhoneOperator(Context context) {
		TelephonyManager telManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		/**
		 * 获取SIM卡的IMSI码 SIM卡唯一标识：IMSI 国际移动用户识别码（IMSI：International Mobile
		 * Subscriber Identification Number）是区别移动用户的标志，
		 * 储存在SIM卡中，可用于区别移动用户的有效信息。IMSI由MCC、MNC、MSIN组成，其中MCC为移动国家号码，由3位数字组成，
		 * 唯一地识别移动客户所属的国家，我国为460；MNC为网络id，由2位数字组成，
		 * 用于识别移动客户所归属的移动网络，中国移动为00，中国联通为01,中国电信为03；MSIN为移动客户识别码，采用等长11位数字构成。
		 * 唯一地识别国内GSM移动通信网中移动客户。所以要区分是移动还是联通，只需取得SIM卡中的MNC字段即可
		 */
		String imsi = telManager.getSubscriberId();
		if (imsi != null) {
			if (imsi.startsWith("46000") || imsi.startsWith("46002")) {// 因为移动网络编号46000下的IMSI已经用完，所以虚拟了一个46002编号，134/159号段使用了此编号
				// 中国移动
				return 0;
			} else if (imsi.startsWith("46001")) {
				// 中国联通
				return 1;
			} else if (imsi.startsWith("46003")) {
				// 中国电信
				return 2;
			}
		}
		return 0;
	}

	public static String getNetworkCountryCode(Context context) {
		TelephonyManager telManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		return telManager.getNetworkCountryIso();
	}

	/**
	 * 返回SIM卡运营商的国家代码
	 * */
	public static String getSimCountryCode(Context context) {
		TelephonyManager telManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		return telManager.getSimCountryIso();
	}

}
