package com.liguo.hgl.proxydao.util;

import org.apache.log4j.Logger;
public class Log {
	static Logger logger = Logger.getLogger(Log.class);
	private static long log_seqNumber = 900000000;
	private   Log(){}
	private static long getSeqNumber(long vaule)
	{
		if(vaule>0)return vaule;
		log_seqNumber++;
		if(log_seqNumber>=1000000000)log_seqNumber=900000001;
		return log_seqNumber;
		
	}
	
	public static void log(String msg)
	{
		logger.info(msg);
	}
	/*
	public static void error(String msg)
	{
		logger.error(msg);
	}
	public static void trace(String msg)
	{
		logger.trace(msg);
	}	
	
	public static void log(long seqNum,String msg)
	{
		logger.info("["+seqNum+"]"+msg);
	}
	public static void error(long seqNum,String msg)
	{
		logger.error("["+seqNum+"]"+msg);
	}
	public static void trace(long seqNum,String msg)
	{
		logger.trace("["+seqNum+"]"+msg);
	}	
	//
	public static void log(Object classobject,String msg)
	{
		logger.info("["+classobject.getClass().getSimpleName()+"]"+msg);
	}
	public static void error(Object classobject,String msg)
	{
		classobject.getClass().getSimpleName();
		logger.error("["+classobject.getClass().getSimpleName()+"]"+msg);
	}
	public static void trace(Object classobject,String msg)
	{
		classobject.getClass().getSimpleName();
		logger.trace("["+classobject.getClass().getSimpleName()+"]"+msg);
	}	
	*/
	
	public static void log(Object classobject,long seqNum,String msg)
	{
		logger.info("["+getSeqNumber(seqNum)+"]"+"["+classobject.getClass().getSimpleName()+"]"+msg);
	}
	public static void error(Object classobject,long seqNum,String msg)
	{
		logger.error("["+getSeqNumber(seqNum)+"]"+"["+classobject.getClass().getSimpleName()+"]"+msg);
	}
	public static void trace(Object classobject,long seqNum,String msg)
	{
		logger.trace("["+getSeqNumber(seqNum)+"]"+"["+classobject.getClass().getSimpleName()+"]"+msg);
	}	
}
