package com.liguo.hgl.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtil {
	/*
	 * 改变文件名称
	 * 如：oldName为asdfasdfsd.jpg
	 * 	 newName为123
	 * 返回结果为123.jpg
	 * */
	private static final Object LOCK_OBJ = "lockerOrder";
	public static String changeFileName(String newName ,String oldName){
		if(oldName == null || oldName.length() == 0){
			return null;
		}
		String	str = oldName;
		 int len = str.length();
		 String strReverse=new StringBuffer(str).reverse().toString(); 
		 int biao = 0;
		 for(int i=0 ; i< len ;i++){
			 System.out.print( strReverse.charAt(i));
			 if(strReverse.charAt(i) =='.'){
				 biao = i;
				break;
			 }
		 }
		 String newFileName = str.substring(len-biao-1);
		return newName+newFileName;
	}
	
	public static int resetPage(int pageCount,int pageSize){
		return (pageCount-1)/pageSize+1;
	}
	
	public static String makeOrderNum(String prefix) {
			String finOrderNum = "";
			try {
				// 最终生成的订单号
				synchronized (LOCK_OBJ) {
					long nowLong = Long.parseLong(new SimpleDateFormat(
							"yyMMddHHmmssSSS").format(new Date()));
					for (int i = 0; i < 3; i++) {
						finOrderNum = finOrderNum + (int) (Math.random() * 10) + "";
					}
						return prefix + nowLong + finOrderNum;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return finOrderNum;
		}
}
