package com.liguo.hgl.proxydao.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.liguo.hgl.proxydao.dto.BaiduMapDto;
/**
 * 
 * */
public class GaoDeMapUtil {
	
	static double DEF_PI = 3.14159265359; // PI
    static double DEF_2PI= 6.28318530712; // 2*PI
    static double DEF_PI180= 0.01745329252; // PI/180.0
    static double DEF_R =6370693.5; // radius of earth
	private static final String GAODE_APP_KEY = "f1e5832b10efb36914bc150c67f472b6";
	private static Pattern PATTERN = Pattern.compile("\"location\":\"(\\d+\\.\\d+),(\\d+\\.\\d+)\"");  
	/**
	 * 
	 */
	public static Map<String, String> getLatitude(String address) {
		Map<String, String> map = null;
		try {
			address = URLEncoder.encode(address, "UTF-8");
			URL resjson = new URL(" http://restapi.amap.com/v3/geocode/geo?address="
					+ address + "&output=json&key=" + GAODE_APP_KEY);
			BufferedReader in = new BufferedReader(new InputStreamReader(resjson.openStream(),"UTF-8"));
			String res;
			StringBuilder sb = new StringBuilder("");
			while ((res = in.readLine()) != null) {
				sb.append(res.trim());
			}
			in.close();
			String str = sb.toString();
			System.out.println("return json:" + str);
			Matcher matcher = PATTERN.matcher(str);  
				if (matcher.find() && matcher.groupCount() == 2) {  
					map = new HashMap<String, String>();
	                double[] gps = new double[2];  
	                gps[0] = Double.valueOf(matcher.group(1));  
	                gps[1] = Double.valueOf(matcher.group(2));  
	                map.put("lon", gps[0]+"");
	                map.put("lat", gps[1]+"");
	                return map;
	            }
			}catch (Exception e) {
		         e.printStackTrace(); 
		         return null;  
			}
		 return null;
	}
	/**
	 * */
	  public static double GetShortDistance(double lon1, double lat1, double lon2, double lat2)
      {
          double ew1, ns1, ew2, ns2;
          double dx, dy, dew;
          double distance;
          ew1 = lon1 * DEF_PI180;
          ns1 = lat1 * DEF_PI180;
          ew2 = lon2 * DEF_PI180;
          ns2 = lat2 * DEF_PI180;
          // 缁忓害宸�
          dew = ew1 - ew2;
          if (dew > DEF_PI)
          dew = DEF_2PI - dew;
          else if (dew < -DEF_PI)
          dew = DEF_2PI + dew;
          dx = DEF_R * Math.cos(ns1) * dew; 
          dy = DEF_R * (ns1 - ns2); 
          distance = Math.sqrt(dx * dx + dy * dy);
          DecimalFormat df = new DecimalFormat("#.##");
          Double juli = Double.parseDouble(df.format((distance + distance*0.25)/1000));
          return juli;
      }
	  /**
	   * 
	   * */
	  public static BaiduMapDto selectAddBycoordinate(double lon,double lat) throws  IOException{
		 String urlInfo = "http://restapi.amap.com/v3/geocode/regeo?key="+GAODE_APP_KEY+"&callback=renderReverse&location="+lon+","+lat+"&output=json&extensions=all&radius=1000";
		  URL resjson = new URL(urlInfo);
		  BufferedReader in = new BufferedReader(new InputStreamReader(resjson.openStream(),"UTF-8"));
		  String res;
		  StringBuilder sb = new StringBuilder("");
		  while ((res = in.readLine()) != null) {
				sb.append(res.trim());
			}
			in.close();
			String str = sb.toString();
			String strReverse=new StringBuffer(str).toString(); 
			 System.out.println(str);
			 int biao = 0;
			 int len = str.length();
			 for(int i=0 ; i< len ;i++){
				 if(strReverse.charAt(i) =='('){
					 biao = i;
					break;
				 }
			 }
			String name = strReverse.substring(biao+1, len-1);
			String address = name.replaceAll("\\[]" ,"isBlank");
			Gson g=new Gson(); 
			BaiduMapDto dto = g.fromJson(address, BaiduMapDto.class);
			if("isBlank".equals(dto.getRegeocode().getAddressComponent().getDistrict())){
				String township = dto.getRegeocode().getAddressComponent().getTownship();
				dto.getRegeocode().getAddressComponent().setDistrict(township);
			}
			return dto;
	  }
	  
	public static void main(String args[]) throws  IOException{
		System.out.println(GaoDeMapUtil.getLatitude("广东省深圳市罗湖区翠竹街道"));
	}
}