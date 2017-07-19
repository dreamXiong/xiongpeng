package com.liguo.hgl.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Utils {
	 private static byte[] encryptMD5(byte[] data) throws Exception {
	        MessageDigest md5 = MessageDigest.getInstance("MD5");
	        md5.update(data);
	        return md5.digest();
	    }
	 private static String toHex(byte input[])
	    {
	        if(input == null)
	            return null;
	        StringBuffer output = new StringBuffer(input.length * 2);
	        for(int i = 0; i < input.length; i++)
	        {
	            int current = input[i] & 0xff;
	            if(current < 16)
	                output.append("0");
	            output.append(Integer.toString(current, 16));
	        }

	        return output.toString();
	    }
	   public static String encrypt(String str,String key){
		   String word = "";
		   try {
			   word=  toHex(encryptMD5((str+key).getBytes()));
		} catch (Exception e) {
			word = "!!!!!!!!!!!!!!加密失败!!!!!!!!!!!!";
		}
		   return word.substring(8,24);
	   }
	   
		/**
		 * 
		 * 
		 * @param text 信息明文
		 * @param charSet 字符集
		 */
		public static String md5(String text, String charSet) {
	        return DigestUtils.md5Hex(getContentBytes(text, charSet));
	    }
		
	    private static byte[] getContentBytes(String content, String charset) {
	        if (charset == null || "".equals(charset)) {
	            return content.getBytes();
	        }

	        try {
	            return content.getBytes(charset);
	        } catch (UnsupportedEncodingException e) {
	            throw new RuntimeException("签名过程中出现错误, 指定的编码集不对, 您目前指定的编码集是:" + charset);
	        }
	    }
	    
	    private static String byteArrayToHexString(byte b[]) {
	        StringBuffer resultSb = new StringBuffer();
	        for (int i = 0; i < b.length; i++)
	            resultSb.append(byteToHexString(b[i]));

	        return resultSb.toString();
	    }

	    private static String byteToHexString(byte b) {
	        int n = b;
	        if (n < 0)
	            n += 256;
	        int d1 = n / 16;
	        int d2 = n % 16;
	        return hexDigits[d1] + hexDigits[d2];
	    }

	    public static String MD5Encode(String origin, String charsetname) {
	        String resultString = null;
	        try {
	            resultString = new String(origin);
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            if (charsetname == null || "".equals(charsetname))
	                resultString = byteArrayToHexString(md.digest(resultString
	                        .getBytes()));
	            else
	                resultString = byteArrayToHexString(md.digest(resultString
	                        .getBytes(charsetname)));
	        } catch (Exception exception) {
	        }
	        return resultString;
	    }
	    
	    private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",
	        "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
}
