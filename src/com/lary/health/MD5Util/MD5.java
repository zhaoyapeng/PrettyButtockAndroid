package com.lary.health.MD5Util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MD5 {

    public static String getMD5(String content) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(content.getBytes());
            return getHashString(digest);
            
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private static String getHashString(MessageDigest digest) {
        StringBuilder builder = new StringBuilder();
        for (byte b : digest.digest()) {
            builder.append(Integer.toHexString((b >> 4) & 0xf));
            builder.append(Integer.toHexString(b & 0xf));
        }
        return builder.toString();
    }
    
    public static String MD5Encode(String origin) {
		StringBuffer sb = new StringBuffer();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(origin.getBytes("utf8"));
			byte[] result = md.digest();
			for (int i = 0; i < result.length; i++) {
				//int val = result[i] & 0xff;
				//sb.append(Integer.toHexString(val));
				int val = (result[i] & 0x000000ff) | 0xffffff00;
				sb.append(Integer.toHexString(val).substring(6));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sb.toString();
	} 
}