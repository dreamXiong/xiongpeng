/**
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */
package com.liguo.hgl.proxydao.util;

import org.apache.commons.lang.time.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 类的功能描述<br>
 * 
 * @author 王丹
 * @FileName DateUtil.java<br>
 * @Language Java<br>
 * @date 2016-01-08<br>
 */
public class DateUtil extends DateUtils {

	public static String parseLong(Long date, String pattern) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(date);
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.format(c.getTime());
	}

	public static String getNowDateTime(String format) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(Calendar.getInstance().getTime());
	}
}
