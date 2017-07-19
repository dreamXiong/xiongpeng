/*
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */

package com.liguo.hgl.common.configs;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 一个用于保存应用属性配置key/value集合的抽象类型类型。<br>
 * 
 * @FileName ConfigEntity.java。<br>
 * @Language java。<br>
 * @date 2014-07-30。<br>
 * @author 陈远长
 */
public class ConfigEntity {
	private static final Logger logger = Logger.getLogger(ConfigEntity.class);
	/** 属性配置文件编码 */
	private static final String CHARSET = "utf-8";
	
	/** 保存属性配置key/value集合的map */
	private Map<String, String> mpProps = new HashMap<String, String>();
	
	/**
	 * 读取属性配置文件中的内容，并将读取的内容保存到一个Map中。该方法ConfigEntity的父类加载器路径中查找，如果父类加载器为null,
	 * 会在JVM的内置类加载器中路径中查找应用属性配置文件。如果指定没有找到属性配置文件，将会返回一个没有任何key/value对的Properties对象。
	 * @param propertiesFileName 属性配置文件名
	 * @return 一个属性配置文件的key/value集合
	 * @throws IOException 
	 * 在从输入流中读取属性配置文件时发生错误，抛出该异常。
	 * 关闭输入流发生错误，抛出该异常。
	 */
	protected void readPropertiesFileToMap(String propertiesFileName) throws IOException   {
		Properties props = new Properties();
		String propFilePath = "";
		
		try {
			propFilePath = 
				ConfigEntity.class.getClassLoader().getResource(propertiesFileName).toString();
			logger.info("属性配置文件:" + propFilePath);
		} catch(NullPointerException e) {
			logger.info("没有找到属性配置文件:" + propFilePath);
			return;
		}
		
		//去掉路径中'file:'前缀
		File file = 
				new File(URLDecoder.decode(propFilePath.substring(5, propFilePath.length()), CHARSET));
		if (file.exists()) {
			InputStream in = new FileInputStream(file);
			InputStreamReader read = new InputStreamReader(in, CHARSET);
			props.load(read);
			logger.info("属性配置文件的值:" + props);
			read.close();
			in.close();
		}
		
		for (Entry<Object, Object> entry : props.entrySet()) {
			mpProps.put(entry.getKey().toString(), entry.getValue().toString());
		}
	}
	
	/**
	 * 根据key返回属性配置key/value对中的value。如果该key不存在，则返回一个Empty串。
	 * @param key 一个表示key的字符串
	 * @return 属性配置的某个value
	 */
	public String get(String key) {
		if (mpProps.containsKey(key)) {
			return mpProps.get(key);
		}
		return "";
	}
}
