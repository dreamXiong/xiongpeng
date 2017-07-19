package com.liguo.hgl.util;

import java.io.File;
import java.io.UnsupportedEncodingException;

public class IpConvertUtil {

    /**
     * @param args
     */
    public static void main(String[] args) {

//        getIpLocation();
    }

  /*  public static String getIpLocation(String ip) {
        String location = "";
        String propFilePath = "";
        try {
            propFilePath = IpConvertUtil.class.getClassLoader().getResource("qqwry.dat").toString();
        } catch (NullPointerException e) {
            return location;
        }

        //去掉路径中'file:'前缀
        try {
            File file = new File(propFilePath.substring(5, propFilePath.length()));
            if (file.exists()) {
                IPSeeker ipSeeker = new IPSeeker(file);
                location =ipSeeker.getAddress(ip);
            } else {
                return location;
            }
        } catch (UnsupportedEncodingException e) {
            return location;
        } catch (Exception e) {
            return location;
        }
        
        return location;
    }*/
    public static String getBrowserByAgent(String userAgent) {

        String browser = "";
        if (userAgent.toLowerCase().indexOf("ms") != -1) {
            //IE
            browser = "IE浏览器";
        } else if (userAgent.toLowerCase().indexOf("firefox") != -1) {
            //火狐
            browser = "火狐浏览器";
        } else if (userAgent.toLowerCase().indexOf("chrome") != -1) {
            //谷歌
            browser = "谷歌浏览器";
        } else if (userAgent.toLowerCase().indexOf("opera") != -1) {
            //opera
            browser = "opera浏览器";
        } else if (userAgent.toLowerCase().indexOf("qqbrowser") != -1) {
            //qqbrowser
            browser = "QQ浏览器";
        } else if (userAgent.toLowerCase().indexOf("safari") != -1) {
            //safari
            browser = "safari浏览器";
        } else if (userAgent.toLowerCase().indexOf("maxthon") != -1) {
            //maxthon
            browser = "遨游浏览器";
        } else {
            browser = "其他";
        }

        return browser;
    }
}
