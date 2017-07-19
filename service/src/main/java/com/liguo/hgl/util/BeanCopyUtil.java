package com.liguo.hgl.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BeanCopyUtil {
	@SuppressWarnings("unchecked")  
    public static void copy(Object target, Object source) throws Exception {  
  
        /* 
         * 分别获得源对象和目标对象的Class类型对象,Class对象是整个反射机制的源头和灵魂！ 
         *  
         * Class对象是在类加载的时候产生,保存着类的相关属性，构造器，方法等信息 
         */  
  
        Class sourceClz = source.getClass();  
  
        Class targetClz = target.getClass();  
  
        // 得到Class对象所表征的类的所有属性(包括私有属性)  
  
        Field[] fields = sourceClz.getDeclaredFields();  
  
        if (fields.length == 0) {  
            fields = sourceClz.getSuperclass().getDeclaredFields();  
        }  
        for (int i = 0; i < fields.length; i++) {  
  
            String fieldName = fields[i].getName();  
  
            Field targetField = null;  
  
            // 得到targetClz对象所表征的类的名为fieldName的属性，不存在就进入下次循环  
  
            try {  
                targetField = targetClz.getDeclaredField(fieldName);  
  
            } catch (NoSuchFieldException e) {  
                targetField = targetClz.getSuperclass().getDeclaredField(  
                        fieldName);  
            }  
            // 判断sourceClz字段类型和targetClz同名字段类型是否相同  
  
            if (fields[i].getType() == targetField.getType()) {  
  
                // 由属性名字得到对应get和set方法的名字  
  
                String getMethodName = "get"  
                        + fieldName.substring(0, 1).toUpperCase()  
                        + fieldName.substring(1);  
  
                String setMethodName = "set"  
                        + fieldName.substring(0, 1).toUpperCase()  
                        + fieldName.substring(1);  
  
                // 由方法的名字得到get和set方法的Method对象  
  
                Method getMethod;  
                Method setMethod;  
                try {  
  
                    try {  
                        getMethod = sourceClz.getDeclaredMethod(getMethodName,  
                                new Class[] {});  
  
                    } catch (NoSuchMethodException e) {  
                        getMethod = sourceClz.getSuperclass()  
                                .getDeclaredMethod(getMethodName,  
                                        new Class[] {});  
                    }  
  
                    try {  
                        setMethod = targetClz.getDeclaredMethod(setMethodName,  
                                fields[i].getType());  
  
                    } catch (NoSuchMethodException e) {  
                        setMethod = targetClz.getSuperclass()  
                                .getDeclaredMethod(setMethodName,  
                                        fields[i].getType());  
                    }  
  
                    // 调用source对象的getMethod方法  
  
                    Object result = getMethod.invoke(source, new Object[] {});  
  
                    // 调用target对象的setMethod方法  
  
                    setMethod.invoke(target, result);  
  
                } catch (SecurityException e) {  
  
                } catch (NoSuchMethodException e) {  
                     
  
                } catch (IllegalArgumentException e) {  
  
                } catch (IllegalAccessException e) {  
  
                } catch (InvocationTargetException e) {  
  
                }  
  
            } else {  
  
                throw new Exception("同名属性类型不匹配！");  
  
            }  
  
        }  
  
    }  
		 /** 
		 
	    @parameter Object obj1,Object obj2 
	    @return Object  
		    用到反射机制 
		    此方法将调用obj1的getter方法，将得到的值作为相应的参数传给obj2的setter方法 
		    注意，obj1的getter方法和obj2方法必须是public类型 
	     */  
	  public static Object CopyBeanToBean(Object obj1,Object obj2) throws Exception{  
		  
	        Method[] method1=obj1.getClass().getMethods();  
	  
	        Method[] method2=obj2.getClass().getMethods();  
	  
	        String methodName1;  
	  
	        String methodFix1;  
	  
	        String methodName2;  
	  
	        String methodFix2;  
	  
	        for(int i=0;i<method1.length;i++){  
	  
	            methodName1=method1[i].getName();  
	  
	            methodFix1=methodName1.substring(3,methodName1.length());  
	  
	            if(methodName1.startsWith("get")){  
	  
	                for(int j=0;j<method2.length;j++){  
	  
	                    methodName2=method2[j].getName();  
	  
	                    methodFix2=methodName2.substring(3,methodName2.length());  
	  
	                    if(methodName2.startsWith("set")){  
	  
	                        if(methodFix2.equals(methodFix1)){  
	  
	                            Object[] objs1=new Object[0];  
	  
	                            Object[] objs2=new Object[1];  
	  
	                            objs2[0]=method1[i].invoke(obj1,objs1);//激活obj1的相应的get的方法，objs1数组存放调用该方法的参数,此例中没有参数，该数组的长度为0  
	  
	                            method2[j].invoke(obj2,objs2);//激活obj2的相应的set的方法，objs2数组存放调用该方法的参数  
	  
	                            continue;                                      
	  
	                        }  
	                    }  
	                }  
	            }  
	        }  
	        return obj2;  
	    }  
}  