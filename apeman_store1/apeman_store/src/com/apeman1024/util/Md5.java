package com.apeman1024.util;
import org.apache.commons.codec.digest.DigestUtils;
public class Md5 {
	public static String getMD5(String  str){
		//str待加密的字符串
		return DigestUtils.md5Hex(str);
	} 
}


