package com.liguo.hgl.proxydao.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderSerialNo {
	  private static Object lockObj = "lockerOrder";  
	  /* 招商订单*/
	  public static final Integer MERCHANT_ORDER = 262; 
	    /* 订货订单*/
	  public static final Integer GOODS_ORDER = 264; 
	  /* 批次号*/
	  public static final Integer BATCH_NO = 266; 
	  public static String makeOrderNum(Integer orderType) {  
	    	 String finOrderNum = "";
	        try {  
	            // 最终生成的订单号  
	             
	            synchronized (lockObj) {  
	                long nowLong = Long.parseLong(new SimpleDateFormat("yyMMddHHmmssSSS").format(new Date()));  
	                for(int i=0;i<3;i++){
	                	finOrderNum =finOrderNum + (int)(Math.random()*10)+"";
	                }
	                if(orderType == MERCHANT_ORDER){
	                	String orderSerialNo ="Z"+nowLong+finOrderNum;
	                	 System.out.println(orderSerialNo);
	                }
	                if(orderType == GOODS_ORDER){
	                	String orderSerialNo ="L"+nowLong+finOrderNum;
	                	 System.out.println(orderSerialNo);
	                }
	                if(orderType == BATCH_NO){
	                    String orderSerialNo ="B"+nowLong+finOrderNum;
	                    finOrderNum=orderSerialNo;
	                    System.out.println(orderSerialNo);
	                }
	            }  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        return finOrderNum;
	    }  
}
