/*
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */

package com.liguo.hgl.proxydao.util;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;

/**
 * 字符串工具类型。<br>
 * 
 * @FileName StringUtil.java
 * @Language java
 * @date 2014-08-04
 * @author 陈远长
 */
public class StringUtil extends StringUtils {
    /**
     * 用于sql查询中搜索条件 内容还有% _ 在特殊字符进行转义
     * @param text
     * @return
     */
    public static String transformSql(String text) {

        return text.replace("%", "\\%").replace("_", "\\_");
    }

    /**
     * 将一个字节数组转换为一个16进制形式的字符串。
     * 
     * @param input
     *            字节数组
     * @return 16进制形式的字符串
     */
    public static String toHex(byte[] input) {

        if (input == null)
            return null;
        StringBuffer output = new StringBuffer(input.length * 2);
        for (int i = 0; i < input.length; i++) {
            int current = input[i] & 0xff;
            if (current < 16)
                output.append("0");
            output.append(Integer.toString(current, 16));
        }

        return output.toString();
    }

    /**
     * 获得一个文本中标记间的文本字符串
     * @param text 一个文本字符串
     * @param tag 一个标记
     * @return 标记间的文本字符串
     */
    public static String getTagText(String text, String tag) {

        String ret = "";
        int startIndex = text.indexOf("<" + tag.trim() + ">");
        int endIndex = text.indexOf("</" + tag.trim() + ">");
        if ((startIndex >= 0) && (endIndex >= 0) && (startIndex + tag.length() + 2 < endIndex)) {
            ret = text.substring(startIndex + tag.length() + 2, endIndex).trim();
        }
        if(ret.indexOf("<![CDATA[") > -1){
            ret = StringUtils.substringBetween(ret, "<![CDATA[", "]]>");
        }
        return ret;
    }

    /**
     * 设置一个Xml文本中标记间的文本字符串
     * @param str 源Xml字符串
     * @param aTag 一个标记
     * @param value 值
     * @return 设置完成的Xml字符串
     */
    public static String setTagValue(String str, String aTag, String value) {

        StringBuilder sb = new StringBuilder();
        int startIndex = str.indexOf("<" + aTag.trim() + ">");
        int endIndex = str.indexOf("</" + aTag.trim() + ">");
        if ((startIndex >= 0) && (endIndex >= 0) && (startIndex + aTag.length() + 2 <= endIndex)) {
            sb.append(str.substring(0, startIndex + aTag.length() + 2));
            sb.append(value);
            sb.append(str.substring(endIndex));
        } else {
            startIndex = str.indexOf("<" + aTag.trim() + "/>");
            if (startIndex >= 0) {
                sb.append(str.substring(0, startIndex));
                sb.append("<" + aTag.trim() + ">");
                sb.append(value);
                sb.append("</" + aTag.trim() + ">");
                sb.append(str.substring(startIndex + ("<" + aTag.trim() + "/>").length()));
            }
        }
        return sb.toString();
    }

    /**
     * 获取一个32位的UUID
     * @return
     */
    public static synchronized String getUUID() {

        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 从字符串右边截取多少位字符
     * @param word 源字符串
     * @param size 位数
     * @return
     */
    public static String cutRightWord(String word, int size) {

        if (word.trim().length() >= size) {
            return StringUtils.right(word, size);
        }
        return word;
    }

    /**
     * 把字符串第一个字母转成小写字母
     * @param str
     * @return
     */
    public static String firstCharToLower(String str){
        String str1 = str.substring(0, 1);
        return str.replaceFirst(str1, str1.toLowerCase());
    }

    public static String firstCharToLower(Class<?> className){
        return firstCharToLower(className.getSimpleName());
    }
    
    public static final String[] EMPTY_STRINGS = new String[0];

    public static final boolean isEmpty(String text)
    {
      return (text == null) || (text.length() == 0);
    }

    public static final boolean isNotEmpty(String text)
    {
      return !isEmpty(text);
    }

    public static final String replace(String text, String fromText, String toText)
    {
      if ((text == null) || (fromText == null) || (toText == null)) {
        return null;
      }
      StringBuffer buf = new StringBuffer(100);
      int pos = 0;
      int pos2 = 0;
      while (true) {
        pos = text.indexOf(fromText, pos2);
        if (pos == 0) {
          buf.append(toText);
          pos2 = fromText.length(); } else {
          if (pos <= 0) break;
          buf.append(text.substring(pos2, pos));
          buf.append(toText);
          pos2 = pos + fromText.length();
        }
      }
      buf.append(text.substring(pos2));

      return buf.toString();
    }

    public static String[] split(String str, String delim)
    {
      if (isEmpty(str)) {
        return EMPTY_STRINGS;
      }
      List list = new ArrayList();
      StringTokenizer st = new StringTokenizer(str, delim);
      while (st.hasMoreElements()) {
        list.add(st.nextElement());
      }
      return (String[])list.toArray(new String[list.size()]);
    }

    public static final String ltrim(String text)
    {
      return ltrim(text, null);
    }

    public static final String ltrim(String text, String trimText)
    {
      if (text == null) {
        return null;
      }
      if (trimText == null) {
        trimText = " ";
      }
      int pos = 0;
      while ((pos < text.length()) && 
        (trimText.indexOf(text.charAt(pos)) >= 0)) {
        pos++;
      }

      return text.substring(pos);
    }

    public static final String rtrim(String text)
    {
      return rtrim(text, null);
    }

    public static final String rtrim(String text, String trimText)
    {
      if (text == null) {
        return null;
      }
      if (trimText == null) {
        trimText = " ";
      }
      int pos = text.length() - 1;
      while ((pos >= 0) && 
        (trimText.indexOf(text.charAt(pos)) >= 0)) {
        pos--;
      }

      return text.substring(0, pos + 1);
    }

    public static final String trimSuffix(String text, String suffix)
    {
      if (text == null) {
        return null;
      }
      if (suffix == null) {
        return text;
      }
      if (text.endsWith(suffix)) {
        return text.substring(0, text.length() - suffix.length());
      }
      return text;
    }

    public static final String trimPrefix(String text, String prefix)
    {
      if (text == null) {
        return null;
      }
      if (prefix == null) {
        return text;
      }
      if (text.startsWith(prefix)) {
        return text.substring(prefix.length());
      }
      return text;
    }

    public static String decapitalize(String name)
    {
      if (isEmpty(name)) {
        return name;
      }
      char[] chars = name.toCharArray();
      if ((chars.length >= 2) && (Character.isUpperCase(chars[0])) && (Character.isUpperCase(chars[1])))
      {
        return name;
      }
      chars[0] = Character.toLowerCase(chars[0]);
      return new String(chars);
    }

    public static String capitalize(String name)
    {
      if (isEmpty(name)) {
        return name;
      }
      char[] chars = name.toCharArray();
      chars[0] = Character.toUpperCase(chars[0]);
      return new String(chars);
    }

    /** @deprecated */
    public static boolean startsWith(String text, String fragment)
    {
      return startsWithIgnoreCase(text, fragment);
    }

    public static boolean isBlank(String str)
    {
      if ((str == null) || (str.length() == 0)) {
        return true;
      }
      for (int i = 0; i < str.length(); i++) {
        if (!Character.isWhitespace(str.charAt(i))) {
          return false;
        }
      }
      return true;
    }

    public static boolean isNotBlank(String str)
    {
      return !isBlank(str);
    }

    public static boolean contains(String str, char ch)
    {
      if (isEmpty(str)) {
        return false;
      }
      return str.indexOf(ch) >= 0;
    }

    public static boolean contains(String s1, String s2)
    {
      if (isEmpty(s1)) {
        return false;
      }
      return s1.indexOf(s2) >= 0;
    }

    public static boolean equals(String target1, String target2)
    {
      return target1 == null ? false : target2 == null ? true : target1.equals(target2);
    }

    public static boolean equalsIgnoreCase(String target1, String target2)
    {
      return target1 == null ? false : target2 == null ? true : target1.equalsIgnoreCase(target2);
    }

    public static boolean endsWithIgnoreCase(String target1, String target2)
    {
      if ((target1 == null) || (target2 == null)) {
        return false;
      }
      int length1 = target1.length();
      int length2 = target2.length();
      if (length1 < length2) {
        return false;
      }
      String s1 = target1.substring(length1 - length2);
      return s1.equalsIgnoreCase(target2);
    }

    public static boolean startsWithIgnoreCase(String target1, String target2)
    {
      if ((target1 == null) || (target2 == null)) {
        return false;
      }
      int length1 = target1.length();
      int length2 = target2.length();
      if (length1 < length2) {
        return false;
      }
      String s1 = target1.substring(0, target2.length());
      return s1.equalsIgnoreCase(target2);
    }

    public static String substringFromLast(String str, String separator)
    {
      if ((isEmpty(str)) || (isEmpty(separator))) {
        return str;
      }
      int pos = str.lastIndexOf(separator);
      if (pos == -1) {
        return str;
      }
      return str.substring(0, pos);
    }

    public static String substringToLast(String str, String separator)
    {
      if ((isEmpty(str)) || (isEmpty(separator))) {
        return str;
      }
      int pos = str.lastIndexOf(separator);
      if (pos == -1) {
        return str;
      }
      return str.substring(pos + 1, str.length());
    }



    public static String toHex(int i)
    {
      StringBuffer buf = new StringBuffer();
      appendHex(buf, i);
      return buf.toString();
    }

    public static void appendHex(StringBuffer buf, byte i)
    {
      buf.append(Character.forDigit((i & 0xF0) >> 4, 16));
      buf.append(Character.forDigit(i & 0xF, 16));
    }

    public static void appendHex(StringBuffer buf, int i)
    {
      buf.append(Integer.toHexString(i >> 24 & 0xFF));
      buf.append(Integer.toHexString(i >> 16 & 0xFF));
      buf.append(Integer.toHexString(i >> 8 & 0xFF));
      buf.append(Integer.toHexString(i & 0xFF));
    }

    public static String camelize(String s)
    {
      if (s == null) {
        return null;
      }
      s = s.toLowerCase();
      String[] array = split(s, "_");
      if (array.length == 1) {
        return capitalize(s);
      }
      StringBuffer buf = new StringBuffer(40);
      for (int i = 0; i < array.length; i++) {
        buf.append(capitalize(array[i]));
      }
      return buf.toString();
    }

    public static String decamelize(String s)
    {
      if (s == null) {
        return null;
      }
      if (s.length() == 1) {
        return s.toUpperCase();
      }
      StringBuffer buf = new StringBuffer(40);
      int pos = 0;
      for (int i = 1; i < s.length(); i++) {
        if (Character.isUpperCase(s.charAt(i))) {
          if (buf.length() != 0) {
            buf.append('_');
          }
          buf.append(s.substring(pos, i).toUpperCase());
          pos = i;
        }
      }
      if (buf.length() != 0) {
        buf.append('_');
      }
      buf.append(s.substring(pos, s.length()).toUpperCase());
      return buf.toString();
    }

    public static boolean isNumber(String s)
    {
      if ((s == null) || (s.length() == 0)) {
        return false;
      }

      int size = s.length();
      for (int i = 0; i < size; i++) {
        char chr = s.charAt(i);
        if ((chr < '0') || ('9' < chr)) {
          return false;
        }
      }

      return true;
    }
    
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

}
