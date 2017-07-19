package com.liguo.hgl.proxydao.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * XML自动解析抽象类(copy right for LeoX)
 * 参数值支持URL编码规范
 * 实体类的变量名的第1个字母必须小写
 * XML子节点名称不区分大小写
 * XML同级子节点名称不允许重复
 * XML支持多级子节点
 * XML父节点属性名称—子节点属性名称对应实体类的变量名，属性值对应变量值
 * XML主节点名和实体类的类名必须相同才能自动生成实例，否则只能实现赋值
 * 实体类必须符合规范的get和set方法，才能自动化分析 
 * 实体类和抽象类所在的包必须相同才能自动生成实例，否则只能实现赋值
 * 实体类的变量类型只支持字符串
 * XML树<a><b><c><data>data</data></c></b></a>的data节点
 * 实体类变量名为String a_b_c_data;
 */
public abstract class XmlReadEntity {
    private final String rootname = this.getClass().getSimpleName();

    /////////////////////////////
    private String url_charset = "UTF-8";

    /////////////////////////
    public String EntityName() {

        return rootname;
    }

    public String UrlCharset() {

        return url_charset;
    }

    public void UrlCharset(String url_charset) {

        this.url_charset = url_charset;
    }

    private static String doFiledName(String name) {

        char chars[] = name.toCharArray();
        chars[0] = Character.toLowerCase(chars[0]);
        return new String(chars);
    }

    protected String[] autoNameList() {

        String[] list = null;
        StringBuilder sb = new StringBuilder();
        Method[] methods = this.getClass().getMethods();
        try {
            for (int i = 0; i < methods.length; i++) {
                Method m = methods[i];
                String methodName = m.getName();
                if (methodName.startsWith("set")) {
                    if (m.getParameterTypes().length != 1 || methodName.equals("setClass") || m.getReturnType() != void.class) {
                        continue;
                    }
                    String propertyName = doFiledName(methodName.substring(3));
                    propertyName = propertyName.toLowerCase();
                    if (sb.length() > 0)
                        sb.append("&");
                    sb.append(propertyName);
                }
            }
            list = sb.toString().split("&");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        return list;
    }

    @SuppressWarnings("unchecked")
    protected void autoPutVauleFromMap(Map setMap) {

        Method[] methods = this.getClass().getMethods();
        try {
            for (int i = 0; i < methods.length; i++) {
                Method m = methods[i];
                String methodName = m.getName();
                if (methodName.startsWith("set")) {
                    if (m.getParameterTypes().length != 1 || methodName.equals("setClass") || m.getReturnType() != void.class) {
                        continue;
                    }
                    String propertyName = doFiledName(methodName.substring(3));
                    propertyName = propertyName.toLowerCase();
                    Object propertyVaule = setMap.get(propertyName);
                    m.invoke(this, propertyVaule);
                }
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();

        }
    }

    @SuppressWarnings("unchecked")
    public static XmlReadEntity generateEntity(String xmlstring) {

        XmlReadEntity xmlReadEntity = null;
        Properties prop = new Properties();
        ParseXML myRead = new ParseXML();

        try {
            myRead.parseString(xmlstring);
            //myRead.parseFile(xmlstring); 
            prop = myRead.getProps();
            String className = prop.getProperty("rootname");
            xmlReadEntity = (XmlReadEntity) Class.forName(XmlReadEntity.class.getPackage().getName() + "." + className).newInstance();
            if (xmlReadEntity != null) {
                Map paramsMap = new HashMap();
                String[] nodeName = xmlReadEntity.autoNameList();
                for (int i = 0; i < nodeName.length; i++) {
                    if (nodeName[i] == null || nodeName[i].isEmpty())
                        continue;
                    String vaule = prop.getProperty(nodeName[i]);
                    paramsMap.put(nodeName[i], vaule == null ? "" : vaule);
                }
                xmlReadEntity.autoPutVauleFromMap(paramsMap);
                paramsMap.clear();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        prop.clear();
        return xmlReadEntity;
    }

    @SuppressWarnings("unchecked")
    public boolean readVauleFormXMLString(String xmlstring) {

        boolean result = false;
        Properties prop = new Properties();
        ParseXML myRead = new ParseXML();
        try {
            myRead.parseString(xmlstring);
            prop = myRead.getProps();
            Map paramsMap = new HashMap();
            String[] nodeName = autoNameList();
            for (int i = 0; i < nodeName.length; i++) {
                if (nodeName[i] == null || nodeName[i].isEmpty())
                    continue;
                String vaule = prop.getProperty(nodeName[i]);
                paramsMap.put(nodeName[i], vaule == null ? "" : vaule);
            }
            autoPutVauleFromMap(paramsMap);
            paramsMap.clear();
            result = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        prop.clear();
        return result;
    }

    @SuppressWarnings("unchecked")
    public boolean readVauleFormXMLFile(String xmlstring) {

        boolean result = false;
        Properties prop = new Properties();
        ParseXML myRead = new ParseXML();
        try {
            myRead.parseFile(xmlstring);
            prop = myRead.getProps();
            Map paramsMap = new HashMap();
            String[] nodeName = autoNameList();
            for (int i = 0; i < nodeName.length; i++) {
                if (nodeName[i] == null || nodeName[i].isEmpty())
                    continue;
                String vaule = prop.getProperty(nodeName[i]);
                paramsMap.put(nodeName[i], vaule == null ? "" : vaule);
            }
            autoPutVauleFromMap(paramsMap);
            paramsMap.clear();
            result = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        prop.clear();
        return result;
    }
}
