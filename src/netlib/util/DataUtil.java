package netlib.util;import java.text.SimpleDateFormat;import java.util.Date;import java.util.Locale;/** * @author henzil * @version create time:2013-9-8_下午2:56:50 * @Description 时间 util */public class DataUtil {	 /**	  * 获取现在年月年月	  *	  * @return 返回短时间字符串格式MM-dd	  */	 public static String getStringMMDDDateShort() {	  Date currentTime = new Date();	  SimpleDateFormat formatter = new SimpleDateFormat("MM/dd");	  String dateString = formatter.format(currentTime);	  return dateString;	 }	 	 public static  String getStringDate(){		  Date currentTime = new Date();		  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");		  String dateString = formatter.format(currentTime);		  return dateString;	 }	 public static  String getStringDateDay(){		  Date currentTime = new Date();		  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");		  String dateString = formatter.format(currentTime);		  return dateString;	 }	 	 public static  String getYear(){		  Date currentTime = new Date();		  SimpleDateFormat formatter = new SimpleDateFormat("yyyy");		  String dateString = formatter.format(currentTime);		  return dateString;	 }}