package com.liguo.hgl.util;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;


public class NullU {
	public static boolean isNull(String o) {
        if (o == null || o.trim().equals("")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isNull(Object o) {
        return o == null;
    }

    public static boolean isNotNull(String o) {
        if (o == null || o.trim().equals("")) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 保证调用端代码风格统一，添加isNotNull之overload方法
     * 
     * @param o
     * @return
     */
    public static boolean isNotNull(Object o) {
        return o != null;
    }
    public static Boolean nvl(Boolean oldValue) {
        return oldValue == null ? false : oldValue;
    }

    public static Short nvl(Short oldValue) {
        return oldValue == null ? 0 : oldValue;
    }

    public static Integer nvl(Integer oldValue) {
        return oldValue == null ? 0 : oldValue;
    }

    public static Long nvl(Long oldValue) {
        return oldValue == null ? 0 : oldValue;
    }

    public static Float nvl(Float oldValue) {
        return oldValue == null ? 0 : oldValue;
    }

    public static Double nvl(Double oldValue) {
        return oldValue == null ? 0 : oldValue;
    }

    private static BigDecimal BD_ZERO = new BigDecimal("0");

    public static BigDecimal nvl(BigDecimal oldValue) {
        return oldValue == null ? BD_ZERO : oldValue;
    }

    public static String nvl(String oldValue) {
        return oldValue == null ? "" : oldValue;
    }

    public static <T> T nvl(T oldValue, T defaultValue) {
        return oldValue == null ? defaultValue : oldValue;
    }
    public static String getErrandCode(String code) {
        if (code == null && "".equals(code)) {
            return "0";
        } else {
            return code;
        }

    }
    public static String getErrandLimitCode(String code) {
        if (code == null && "".equals(code)) {
            return " 未设置 ";
        } else {
            return code;
        }

    }
    
    public static Object nullPro(Object obj){
		return obj == null ? "" : obj;
	}
    
    /** 
     * 判断对象或对象数组中每一个对象是否为空: 对象为null，字符序列长度为0，集合类、Map为empty 
     *  
     * @param obj 
     * @return 
     */  
    public static boolean isNullMap(Object obj) {  
        if (obj == null)  
            return true;  
  
        if (obj instanceof CharSequence)  
            return ((CharSequence) obj).length() == 0;  
  
        if (obj instanceof Collection)  
            return ((Collection) obj).isEmpty();  
  
        if (obj instanceof Map)  
            return ((Map) obj).isEmpty();  
  
        if (obj instanceof Object[]) {  
            Object[] object = (Object[]) obj;  
            if (object.length == 0) {  
                return true;  
            }  
            boolean empty = true;  
            for (int i = 0; i < object.length; i++) {  
                if (!isNullMap(object[i])) {  
                    empty = false;  
                    break;  
                }  
            }  
            return empty;  
        }  
        return false;  
    }  
}
