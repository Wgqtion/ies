package com.vsc.util;


import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

public class MD5Util {
	
	public  static String MD5(String s) {
		if (StringUtils.isEmpty(s) || StringUtils.isBlank(s))
		{
			return null;
		}
		
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
				'9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] btInput = s.getBytes();
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(btInput);
			byte[] md = mdInst.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String getHashCode(Object object) throws IOException{
		if(object == null) return "";
		
		String ss = null;
		ObjectOutputStream s = null;
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
			try {
				s = new ObjectOutputStream(bo);
				s.writeObject(object);
				s.flush();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if(s != null) {
					s.close();
					s = null;
				}
			}
		ss = MD5(bo.toString());
		return ss;
	}
	
	public static void main(String[] args) {
		String str = "admin";
		System.out.println(MD5Util.MD5(str));
		str = "user";
		System.out.println(MD5Util.MD5(str));
		
		Calendar a=Calendar.getInstance();
		a.set(2011, 11, 30);
		System.out.println(DateUtils.truncatedCompareTo(a.getTime(), new Date(), Calendar.DATE));
		
		
		int dayofweek=a.get(Calendar.DAY_OF_WEEK);
		if(dayofweek>Calendar.WEDNESDAY){
			a.add(Calendar.DAY_OF_YEAR, Calendar.WEDNESDAY+7-dayofweek);
		}else{
			a.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
		}
			
		
		
		System.out.println(a.getTime());
		
		System.out.println(5/0);
	}
}