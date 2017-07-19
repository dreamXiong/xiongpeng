/*
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */

package com.liguo.hgl.common.configs;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;


/**
 * 一个用于保存应用属性配置key/value集合的抽象类型类型。<br>
 * 
 * @FileName ConfigManager.java。<br>
 * @Language java。<br>
 * @date 2014-07-30。<br>
 * @author 陈远长
 */
public class ConfigManager {
	private static final Logger logger = Logger.getLogger(ConfigManager.class);
	/** 记录实例化次数 */
	private static AtomicInteger instanceCount = new AtomicInteger();
	/** 保存应用配置值 */
	private ConfigEntity configEntity = new ConfigEntity();
	/** 单例实体 */
	private static ConfigManager INSTANCE = null;
	
	/**
	 * 一个不允许外部调用的构造函数。该函数为实现单例而声明为私有。
	 * @param propertiesFiles 属性配置文件名
	 */
    private ConfigManager(String... propertiesFiles) {
		if (instanceCount.get() == 0) {
			instanceCount.getAndIncrement();
			init(propertiesFiles);
		} else {
			throw new java.lang.AssertionError();
		}
	}
	
	/**
	 * 获取一个属性配置管理类型的实例。通过该实例，可以访问获取属性配置的值。
	 * @param propertiesFiles 属性配置文件名
	 * @return 属性配置管理类型实例
	 */
	public static ConfigManager getInstance(String... propertiesFiles) {
		if (INSTANCE == null) {
			INSTANCE = new ConfigManager(propertiesFiles);
		}
		return INSTANCE;
	}
	
	/**
	 * 返回指定key的属性配置值。如果指定的key不存在，则返回一个Empty串。
	 * @param key 一个属性配置key
	 * @return 属性配置值
	 */
	public String getPropertValue(String key) {
		return configEntity.get(key);
	}
	
	/**
	 * 初始化ConfigManager。该方法会读取加载指定的属性配置文件。
	 * @param propertiesFiles 属性配置文件名
	 */
	private void init(String... propertiesFiles) {
		try {
			logger.info("-------------读取属性配置文件-------------");
			for (String fileName : propertiesFiles) {
				configEntity.readPropertiesFileToMap(fileName);
			}
			logger.info("-------------属性配置文件读取结束-------------");
		} catch(IOException e) {
			logger.error(e.getMessage(), e);
		}
	}
}
