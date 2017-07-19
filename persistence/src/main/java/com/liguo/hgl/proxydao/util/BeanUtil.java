/*
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */
package com.liguo.hgl.proxydao.util;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.ui.Model;









import com.alibaba.fastjson.JSONObject;
import com.liguo.hgl.proxydao.model.Criteria;

/**
 * 类与Map之间的属性拷贝<br>
 *
 * @author 王丹
 * @FileName BeanUtil.java<br>
 * @Language Java<br>
 * @date 2014-10-30<br>
 */
public class BeanUtil {

    private static Logger logger = Logger.getLogger(BeanUtil.class);

    private static Map<String, Object> srcFieldMap = new HashMap<String, Object>();

    private static Map<String, Object> destFieldMap = new HashMap<String, Object>();
    
    private static Map<String, SimpleDateFormat> SIMPLE_DATA_FORMAT_MAP = new HashMap(2);
    
    private static final String[] FIELD_NAME_NOT_CONTAIN;
    
    private static final String[] FIELD_TYPE;

    /**
     * 递归设置 对象的属性到map集合
     * @param targetObj
     * @param fieldMap
     */
    private static void setFieldsByClass(Object targetObj, Map<String, Object> fieldMap){
        Class targetClass = targetObj.getClass();
        Class superClass = targetClass.getSuperclass();
        Field[] sourceFields = targetClass.getDeclaredFields();
        for(Field field : sourceFields){
            fieldMap.put(field.getName(), field);
        }
        if(superClass != Object.class && superClass != null) {
            try {
                setFieldsByClass(superClass.newInstance(), fieldMap);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 验证对象是否为空
     * @param src
     * @param dest
     */
    private static void validate(Object src, Object dest){
        if (src == null) {
            throw new NullPointerException(src.toString() + "为空");
        }
        if (dest == null) {
            throw new NullPointerException(dest.toString() + "为空");
        }
    }

    /**
     * 根据字段名设置目标对象的值
     * @param targetObj 目标对象
     * @param fieldName 字段名
     * @param value     值
     */
    private static void setValue(Object targetObj, String fieldName, Object value){
        try {
            if(targetObj instanceof Map){
                ((Map)targetObj).put(fieldName, value);
            }else {
                Field destField = (Field)destFieldMap.get(fieldName);
                destField.setAccessible(true);
                int modifier = destField.getModifiers();
                if(Modifier.isFinal(modifier) && Modifier.isStatic(modifier)){
                    return;
                }
                destField.set(targetObj, value);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取来源对象中字段名的值
     * @param sourceObj 来源对象
     * @param fieldName 字段名
     * @return
     */
    private static Object getValue(Object sourceObj, String fieldName){
        Object retValue = null;
        try {
            if(sourceObj instanceof Map){
                retValue = ((Map)sourceObj).get(fieldName);
            }else {
                Field srcField = (Field)srcFieldMap.get(fieldName);
                srcField.setAccessible(true);
                retValue = srcField.get(sourceObj);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return retValue;
    }

    /**
     * 对来源对象的属性值进行拷贝到目标对象
     * @param src  来源对象
     * @param dest 目标对象
     */
    private static void copy(Object src, Object dest){
        boolean destHasMap = false;
        Map<String, Object> srcMap;
        if(src instanceof Map){
            srcMap = (Map)src;
        }else {
            srcMap = srcFieldMap;
        }
        if(dest instanceof Map){
            destHasMap = true;
        }
        for(String srcKey : srcMap.keySet()){
            if(!destHasMap && !destFieldMap.containsKey(srcKey)){
                continue;
            }
            Object srcValue = getValue(src, srcKey);
            setValue(dest, srcKey, srcValue);
        }
    }

    /**
     * 从源对象拷贝属性到目标对象，源对象和目标对象必须先实例化
     * @param src  源对象
     * @param dest 目标对象
     */
    public static void CopyBeanToBean(Object src, Object dest) {
        validate(src, dest);
        srcFieldMap.clear();
        setFieldsByClass(src, srcFieldMap);
        destFieldMap.clear();
        setFieldsByClass(dest, destFieldMap);
        copy(src, dest);

    }

    /**
     * 从Map对象拷贝属性到目标对象，源对象和目标对象必须先实例化
     * @param src  源对象
     * @param dest 目标对象
     */
    public static void CopyMapToBean(Map<String, Object> src, Object dest) {
        validate(src, dest);
        destFieldMap.clear();
        setFieldsByClass(dest, destFieldMap);
        copy(src, dest);
    }

    /**
     * 从源对象拷贝属性到Map目标对象，源对象和目标对象必须先实例化
     * @param src  源对象
     * @param dest 目标对象
     */
    public static void CopyBeanToMap(Object src, Map<String, Object> dest) {
        validate(src, dest);
        srcFieldMap.clear();
        setFieldsByClass(src, srcFieldMap);
        copy(src, dest);
    }
    /**
     * 从源对象拷贝属性到Map目标对象，源对象和目标对象必须先实例化
     * @param src  源对象
     * @param dest 目标对象
     */
    public static void CopyBeanToMapByString(Object src, Map<String, String> dest) {
        validate(src, dest);
        srcFieldMap.clear();
        setFieldsByClass(src, srcFieldMap);
        copy(src, dest);
    }
    
	public static Criteria setSearchCondition(Model model, 
			Object queryForm) {
		return setSearchCondition(model, queryForm, null);
	}
    /*****
     * 实现将实体复制到Criteria与Model中
     * @param model modelAndView
     * @param queryForm bean
     * @param datePattern 格式，可选
     * @return
     */
    public static Criteria setSearchCondition(Model model,
			Object queryForm,String datePattern){
    	Criteria criteria = new Criteria();
		if (queryForm == null) {
			return criteria;
		}
		Field[] fields = queryForm.getClass().getDeclaredFields();
		Object fieldName = null;
		Object fieldValue = null;
		String key = null;
		String value = null;
		for (int j = 0; j < fields.length; ++j) {
			fields[j].setAccessible(true);

			fieldName = fields[j].getName();
			if (Arrays.binarySearch(FIELD_NAME_NOT_CONTAIN, fieldName) > -1) {
				continue;
			}

			for (int i = 0; i < FIELD_TYPE.length; ++i) {
				if (!(FIELD_TYPE[i].equalsIgnoreCase(fields[j].getType()
						.getName())))
					continue;
				try {
					fieldValue = fields[j].get(queryForm);
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e.getCause());
				}
				if (logger.isDebugEnabled()) {
					logger.debug(fieldName + ":" + fieldValue);
				}

				if ((fieldName == null) || (fieldValue == null))
					continue;
				key = String.valueOf(fieldName);

				if ((!(StringUtil.isBlank(datePattern)))
						&& (fieldValue instanceof Date)) {
					SimpleDateFormat simpleDateFormat = (SimpleDateFormat) SIMPLE_DATA_FORMAT_MAP
							.get(datePattern);
					if (simpleDateFormat == null) {
						simpleDateFormat = new SimpleDateFormat(datePattern);
						SIMPLE_DATA_FORMAT_MAP.put(datePattern,
								simpleDateFormat);
					}
					value = simpleDateFormat.format(fieldValue);
				} else {
					value = String.valueOf(fieldValue);
				}

				if (StringUtil.isBlank(value)) {
					logger.debug("field value is blank.");
				} else {
					model.addAttribute(key, value);
					criteria.put(key, fieldValue);
				}
			}
		}
		return criteria;
	}
    
    static {
		SIMPLE_DATA_FORMAT_MAP.put("yyyy-MM-dd", new SimpleDateFormat(
				"yyyy-MM-dd"));
		SIMPLE_DATA_FORMAT_MAP.put("yyyy-MM-dd HH:mm:ss", new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss"));

		FIELD_TYPE = new String[] { "int", "java.lang.String", "boolean",
				"char", "float", "double", "long", "short", "byte",
				"java.lang.Integer", "java.lang.String", "java.lang.Boolean",
				"java.lang.Character", "java.lang.Float", "java.lang.Double",
				"java.lang.Long", "java.lang.Short", "java.lang.Byte",
				"java.util.Date","java.math.BigDecimal"};

		FIELD_NAME_NOT_CONTAIN = new String[] { "serialVersionUID" };
	}
//    ObjectMapper objectMapper = new ObjectMapper();
//
//    try {
//        T t = objectMapper.readValue(productJsonStr, clazz);
//        tList.add(t);
//    }
//    catch (Exception e) {
//        logger.error(">>>>>>>>>>>>>>>>解析转换错误!!");
//    }
    
    /****
     * 
     * <不考虑类型的Copy>
     * @param src
     * @param clazz
     * @return
     * @author wzt
     * @since   2016年6月6日
     */
//    public static <T> T CopyMapToBeanByJson(Map<String, Object> src, Class<T> clazz) {
//        String json =JSONObject.toJSONString(src);
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            objectMapper.readValue(json, clazz);
//        }
//        catch (IOException e) {
//            
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//            
//        }
//        return (T) clazz;
//        
//    }
    
    
    
    public static <T> T CopyMapToBeanByJson(Map<String, Object> list, Class<T> clazz) {
        
            String productJsonStr = JSONObject.toJSONString(list);
            ObjectMapper objectMapper = new ObjectMapper();
            T t = null ;
                try {
                    t = objectMapper.readValue(productJsonStr, clazz);
                }
                catch (JsonParseException e) {
                    logger.error("error message :"+e.getMessage());
                }
                catch (JsonMappingException e) {
                    logger.error("error message :"+e.getMessage());
                    
                }
                catch (IOException e) {
                    logger.error("error message :"+e.getMessage());
                    
                }
        return t;

    }
    
}
