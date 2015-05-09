package netlib.util;


	
	/*
	 * Copyright (c) 2013. Kevin Lee (www.buybal.com).
	 *
	 * Licensed under the Apache License, Version 2.0 (the "License");
	 * you may not use this file except in compliance with the License.
	 * You may obtain a copy of the License at
	 *
	 *      http://www.apache.org/licenses/LICENSE-2.0
	 *
	 * Unless required by applicable law or agreed to in writing, software
	 * distributed under the License is distributed on an "AS IS" BASIS,
	 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	 * See the License for the specific language governing permissions and
	 * limitations under the License.
	 */

	import android.annotation.SuppressLint;

	import java.security.SecureRandom;
	import java.text.DecimalFormat;
	import java.text.ParseException;
	import java.text.ParsePosition;
	import java.text.SimpleDateFormat;
	import java.util.Date;
	import java.util.Random;
	import java.util.regex.Pattern;

	/**
	 * Created with IntelliJ IDEA.
	 * User: william
	 * Date: 13-5-9
	 * Time: 下午3:23
	 * Mobi:18601920351
	 * Email: lishu@qdcf.com
	 */
	@SuppressLint("SimpleDateFormat")
	public class TextUtil {
	    private final static Pattern URL = Pattern.compile("[a-zA-z]+://[^\\s]*");// 网址
	    private final static Pattern IP_ADDRESS = Pattern
	            .compile("((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)");
	    private final static Pattern QQ_NUMBER = Pattern.compile("[1-9]\\d{4,}");
	    private final static Pattern HTML_TAG = Pattern
	            .compile("<(.*)(.*)>.*<\\/\\1>|<(.*) \\/>");
	    /*
	     * 密码(由数字/大写字母/小写字母/标点符号组成，四种都必有，8位以上)
	     */
	    private final static Pattern PASS_WORD = Pattern
	            .compile("(?=^.{8,}$)(?=.*\\d)(?=.*\\W+)(?=.*[A-Z])(?=.*[a-z])(?!.*\\n).*$");
	  
	    /*
	     * 密码（由数字/字母组成，两种都必有）
	     */
	    private final static Pattern PAYPASS_WORD = Pattern
	    		.compile(".*[a-zA-Z].*[0-9]|.*[0-9].*[a-zA-Z]");
	    
	    /*
	     * 日期(年-月-日)
	     */
	    private final static Pattern DATE_TYPE_1 = Pattern
	            .compile("(\\d{4}|\\d{2})-((0?([1-9]))|(1[1|2]))-((0?[1-9])|([12]([1-9]))|(3[0|1]))");
	    /*
	     * 日期(月/日/年)
	     */
	    private final static Pattern DATE_TYPE_2 = Pattern
	            .compile("((0?[1-9]{1})|(1[1|2]))/(0?[1-9]|([12][1-9])|(3[0|1]))/(\\d{4}|\\d{2})");
	    /*
	     * 时间(小时:分钟, 24小时制)
	     */
	    private final static Pattern TIME = Pattern
	            .compile("((1|0?)[0-9]|2[0-3]):([0-5][0-9])");
	    /*
	     * 汉字字符
	     */
	    private final static Pattern CHINESE = Pattern.compile("[\\u4e00-\\u9fa5]");
	    /*
	     * 中文及全角标点符号(字符)
	     */
	    private final static Pattern CHINESE_PUNCTUATION = Pattern
	            .compile("[\\u3000-\\u301e\\ufe10-\\ufe19\\ufe30-\\ufe44\\ufe50-\\ufe6b\\uff01-\\uffee]");

	    private final static Pattern EMAILER = Pattern
	            .compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*"); // 邮箱验证正则表达式
	    private final static Pattern PHONE_NUMBER = Pattern
	            .compile("((\\d{11})|^((\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1})|(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1}))$)");// 电话号码正则表达式

	    private static final Pattern loginPassword = Pattern
	            .compile("((\\d+[a-zA-Z]+)|([a-zA-Z]+\\d+))+"); // 登录密码校验

	    private static final Pattern payPassword = Pattern
	            .compile("(?:0(?![01])|1(?![012])|2(?![123])|3(?![234])|4(?![345])|5(?![456])|6(?![567])|7(?![678])|8(?![789])|9(?![89])){6}"); // 支付密码校验
	    //手机号码正则表达式
	    private static final Pattern moble = Pattern.compile("^1[3|4|5|7|8|9]\\d{9}$");

	    /**
	     * 判断是不是一个合法的电子邮件地址
	     *
	     * @param email
	     * @return
	     */
	    public static boolean isEmail(String email) {
	        if (email == null || email.trim().length() == 0)
	            return false;
	        return EMAILER.matcher(email).matches();
	    }

	    /**
	     * 判断是否必须含有字母和数字的支付密码
	     * @param paypwd
	     * @return
	     */
	    public static boolean isPaypassword(String paypwd){
	        if (paypwd == null || paypwd.trim().length() == 0)
	            return false;
	        return PAYPASS_WORD.matcher(paypwd).matches();
	    }
	    
	    /**
	     * 判断是不是电话号码
	     *
	     * @param phoneNumber
	     * @return
	     */
	    public static boolean isPhoneNumber(String phoneNumber) {
	        if (phoneNumber == null || phoneNumber.trim().length() == 0)
	            return false;
	        return PHONE_NUMBER.matcher(phoneNumber).matches();
	    }

	    /**
	     * 判断是不是手机号码
	     *
	     * @param mobleNumber
	     * @return
	     */
	    public static boolean isMobleNumber(String mobleNumber) {
	        if (mobleNumber == null || mobleNumber.trim().length() == 0)
	            return false;
	        return moble.matcher(mobleNumber).matches();
	    }

	    /**
	     * 判断是不是URL地址
	     *
	     * @param url
	     * @return
	     */
	    public static boolean isURL(String url) {
	        if (url == null || url.trim().length() == 0) {
	            return false;
	        }
	        return URL.matcher(url).matches();
	    }

	    /**
	     * 判断是不是IP地址
	     *
	     * @param IP
	     * @return
	     */
	    public static boolean isIPAddress(String IP) {
	        if (IP == null || IP.trim().length() == 0) {
	            return false;
	        }
	        return IP_ADDRESS.matcher(IP).matches();
	    }

	    /**
	     * 判断是不是QQ号码
	     *
	     * @param qq
	     * @return
	     */
	    public static boolean isQQNumber(String qq) {
	        if (qq == null || qq.trim().length() == 0) {
	            return false;
	        }
	        return QQ_NUMBER.matcher(qq).matches();
	    }

	    /**
	     * 判断是不是Html标签
	     *
	     * @param html
	     * @return
	     */
	    public static boolean isHtmlTag(String html) {
	        if (html == null || html.trim().length() == 0) {
	            return false;
	        }
	        return HTML_TAG.matcher(html).matches();
	    }

	    /**
	     * 密码(由数字/大写字母/小写字母/标点符号组成，四种都必有，8位以上)
	     *
	     * @param pwd
	     * @return
	     */
	    public static boolean isPassWord(String pwd) {
	        if (pwd == null || pwd.trim().length() == 0) {
	            return false;
	        }
	        return PASS_WORD.matcher(pwd).matches();
	    }

	    /**
	     * 判断字符串是否为空字符串
	     *
	     * @param str
	     * @return
	     */
	    public static boolean isEmpty(String str) {
	        if (str == null)
	            return true;
	        if (str.trim().length() == 0)
	            return true;
	        return false;
	    }

	    /**
	     * 将String类型的年月转换成日期
	     *
	     * @param ts
	     * @return
	     */
	    public static Date getStringToDateFormat(String ts) {
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	        try {
	            return formatter.parse(ts);
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }

	    /**
	     * 校验登录密码
	     *
	     * @param pwd
	     * @return
	     */
	    public static boolean isLoginPassword(String pwd) {
	        if (pwd == null || pwd.trim().length() == 0)
	            return false;
	        return loginPassword.matcher(pwd).matches();
	    }

	    /**
	     * 校验支付密码
	     *
	     * @param ppwd
	     * @return
	     */
	    public static boolean isPayPassword(String ppwd) {
	        if (ppwd == null || ppwd.trim().length() == 0) {
	            return false;
	        }
	        return payPassword.matcher(ppwd).matches();
	    }

	    /**
	     * 随机获取字符串长度
	     *
	     * @param length 字符串长度
	     * @return
	     */
	    public static String getRandomString(int length) {
	        String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
//	        Random random = new Random();
	        Random random = new SecureRandom();
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < length; i++) {
	            int num = random.nextInt(base.length());
	            sb.append(base.charAt(num));
	        }
	        return sb.toString();
	    }


//	    public static String getPosRandomString(){
//	        Random random =new Random();
//	        byte[] temp = new byte[3];
//	        random.nextBytes(temp);
//	        return StringUtil.byteArrayToHexString(temp);
//	    }

	    public static String byteArrayToHexString(byte[] b) {
	        StringBuffer sb = new StringBuffer(b.length * 2);
	        for (int i = 0; i < b.length; i++) {
	            int v = b[i] & 0xff;
	            if (v < 16) {
	                sb.append('0');
	            }
	            sb.append(Integer.toHexString(v));
	        }
	        return sb.toString().toUpperCase();
	    }

	    /**
	     * 将分转换为元
	     *
	     * @param amountLong
	     * @return
	     */
	    public static String parseAmountLong2Str(Long amountLong) {
	        if (amountLong == null) {
	            return "0.00";
	        }
	        DecimalFormat df = new DecimalFormat("0.00");
	        double d = amountLong / 100d;
	        String s = df.format(d);
	        return s;
	    }

	    /**
	     * 日期转换为自定义格式输出
	     */
	    public static String dateToString(Date date, String formatType) {
	        if (date == null) {
	            return null;
	        }
	        if (formatType == null || "".equals(formatType)) {
	            return null;
	        }
	        String dateStr = "";
	        try {
	            SimpleDateFormat sdf = new SimpleDateFormat(formatType);
	            dateStr = sdf.format(date);
	            return dateStr;
	        } catch (Exception e) {
	            return null;
	        }
	    }


	    /**
	     * 身份证验证
	     *
	     * @param idCard
	     * @return
	     */
	    public static boolean checkIdCard(String idCard) {
	        if (idCard.length() != 18) {
	            return false;
	        }
	        int[] wi = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};//WI
	        int[] ai = new int[17];//AI
	        int[] vi = {1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2};//校验码
	        int sum = 0;
	        for (int i = 0; i < 17; i++) {
	            ai[i] = Integer.parseInt(idCard.substring(i, i + 1));
	        }
	        for (int i = 0; i < wi.length; i++) {
	            sum = sum + wi[i] * ai[i];
	        }
	        int r = sum % 11;
	        String temp = "" + (r == 2 ? "X" : vi[r]);
	        return temp.equals(idCard.substring(17)) ? true : false;
	    }

	    public static Date stringToDate(String dateString) {
	        ParsePosition position = new ParsePosition(0);
	        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
	        Date dateValue = simpleDateFormat.parse(dateString, position);
	        return dateValue;
	    }

	    /**
	     * 将元字符串转换为分
	     *
	     * @param amountStr
	     * @return
	     */
	    public static long parseAmountStr2Long(String amountStr) {
	        if (amountStr == null || "".equals(amountStr)) {
	            return 0L;
	        }
	        double amount = Double.parseDouble(amountStr);
	        Double db = amount * 100;
	        DecimalFormat df = new DecimalFormat("#");
	        String s = df.format(db);
	        return Long.parseLong(s);
	    }

	    /**
	     * 将yyyy-MM-dd HH:mm:ss
	     *
	     * @param ts yyyy-MM-dd HH:mm:ss
	     * @return HH:mm
	     */
	    public static String getStringToTimeFormat(String ts) {
	        if (ts == null || "".equals(ts)) {
	            return null;
	        }
	        String dateStr = "";
	        ParsePosition position = new ParsePosition(0);
	        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	        Date dateValue = simpleDateFormat.parse(ts, position);
	        SimpleDateFormat formatters = new SimpleDateFormat("HH:mm");
	        try {
	            dateStr = formatters.format(dateValue);
	            return dateStr;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }

	    /**
	     * @param str
	     * @return
	     */
	    public static String getGameName(String str) {
	        String temp = str;
	        char[] chars = temp.toCharArray();
	        StringBuilder stringBuilder = new StringBuilder();
	        for (int i = 0; i < chars.length; i++) {
	            if ((chars[i] >= 19968 && chars[i] <= 40869)) {//|| (chars[i] >= 97 && chars[i] <= 122) || (chars[i] >= 65 && chars[i] <= 90)) {
	                stringBuilder.append(chars[i]);
	            }
	        }
	        temp = new String(stringBuilder);
	        return temp;
	    }
	    
	    /**
	     * 验证身份证有效性
	     * @param str
	     * @return
	     */
	    
	    public static boolean isId(String str)
	    {
	    	if(str.length()==15){
				return str.matches("[1-9]\\d{7}((0\\d)|(1[0-2]))([0|1|2]\\d|3[0-1])\\d{3}");  //15位
			}
			else if(str.length()==18){
				return	str.matches("[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])((\\d{4})|(\\d{3}(X|x)))");
			}
			// return	regularExpression.matches("/^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])((\\d{4})|\\d{3}[A-Z])$/");
	           return false;
	    }

	}

