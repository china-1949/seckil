package com.dayup.seckil.util;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Utils {
	public static String salt ="springboot";
	public static String md5(String str){
		
		return DigestUtils.md5Hex(str);
		
	}
	//第一次加密
	public static String inputToBack(String Str){
		return md5(Str+salt);
	}
	
	//第二次加密
	public static String backToDb(String str,String dbSalt){
		return md5(str+dbSalt);
		
	}
	
	public static String inputToDb(String Str,String dbSalt){
		
		return backToDb(inputToBack(Str), dbSalt);
	}
	
}
