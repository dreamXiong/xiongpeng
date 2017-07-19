/**
 * hofan.cn Inc.
 * Copyright (c) 2006-2014 All Rights Reserved.
 */
package com.linkon.hgl.web.common;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @fiIpUtil.java
 * @2016-4-7	
 * @author 周双双
 */
public class IpUtil {

	public static String getRemoteHost(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {

			ip = request.getHeader("Proxy-Client-IP");

		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {

			ip = request.getHeader("WL-Proxy-Client-IP");

		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {

			ip = request.getRemoteAddr();

		}

		return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
	}
}
