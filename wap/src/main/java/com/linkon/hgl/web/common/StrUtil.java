package com.linkon.hgl.web.common;

import java.util.Random;

import org.springframework.util.StringUtils;

public class StrUtil {

	public StrUtil() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 获取一个随机的6位长的数字
	 */
	public static String getRandom6Number() {
		Random random = new Random();
		int code = 0;
		while (code == 0 || code == 999999) {
			code = random.nextInt(999999);
		}
		
		if(code < 10)				code = code * 100000;
		else if(code < 100)		code = code * 10000;
		else if(code < 1000)		code = code * 1000;
		else if(code < 10000)	code = code * 100;
		else if(code < 100000)	code = code * 10;
		 
		return String.valueOf(code);
	}
	
	/**
	 * 获取一个随机的4位长的数字
	 */
	public static String getRandom4Number() {
		Random random = new Random();
		int code = 0;
		while (code == 0 || code == 9999) {
			code = random.nextInt(9999);
		}
		
		if(code < 10)				code = code * 1000;
		else if(code < 100)		code = code * 100;
		else if(code < 1000)		code = code * 10;
		
		return String.valueOf(code);
	}
	
	/**
	 * Check whether the given CharSequence has actual text.
	 * More specifically, returns <code>true</code> if the string not <code>null</code>,
	 * its length is greater than 0, and it contains at least one non-whitespace character.
	 * <p><pre>
	 * StrUtil.hasText(null) = false
	 * StrUtil.hasText("") = false
	 * StrUtil.hasText(" ") = false
	 * StrUtil.hasText("12345") = true
	 * StrUtil.hasText(" 12345 ") = true
	 * </pre>
	 * @param str the CharSequence to check (may be <code>null</code>)
	 * @return <code>true</code> if the CharSequence is not <code>null</code>,
	 * its length is greater than 0, and it does not contain whitespace only
	 */
	public static boolean hasText(CharSequence str) {
		return StringUtils.hasText(str);
	}
}
