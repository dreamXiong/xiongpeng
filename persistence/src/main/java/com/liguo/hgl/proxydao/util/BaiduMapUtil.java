package com.liguo.hgl.proxydao.util;

import java.io.IOException;
import java.text.DecimalFormat;
/**
 * 将传进来的地址转换成纵横坐标
 * 
 * */
public class BaiduMapUtil {
	
	static double DEF_PI = 3.14159265359; // PI
    static double DEF_2PI= 6.28318530712; // 2*PI
    static double DEF_PI180= 0.01745329252; // PI/180.0
    static double DEF_R =6370693.5; // radius of earth

	/**
	 * 求两点之间的距离
	 * */
	  public static double GetShortDistance(double lon1, double lat1, double lon2, double lat2)
      {
          double ew1, ns1, ew2, ns2;
          double dx, dy, dew;
          double distance;
          // 角度转换为弧度
          ew1 = lon1 * DEF_PI180;
          ns1 = lat1 * DEF_PI180;
          ew2 = lon2 * DEF_PI180;
          ns2 = lat2 * DEF_PI180;
          // 经度差
          dew = ew1 - ew2;
          // 若跨东经和西经180 度，进行调整
          if (dew > DEF_PI)
          dew = DEF_2PI - dew;
          else if (dew < -DEF_PI)
          dew = DEF_2PI + dew;
          dx = DEF_R * Math.cos(ns1) * dew; // 东西方向长度(在纬度圈上的投影长度)
          dy = DEF_R * (ns1 - ns2); // 南北方向长度(在经度圈上的投影长度)
          // 勾股定理求斜边长
          distance = Math.sqrt(dx * dx + dy * dy);
          DecimalFormat df = new DecimalFormat("#.##");
          Double juli = Double.parseDouble(df.format((distance + distance*0.25)/1000));
          return juli;
      }
	  
	public static void main(String args[]) throws  IOException{
		System.out.println(BaiduMapUtil.GetShortDistance(112.021121, 22.4633311, 114.3546436, 24.6354634));
	}
}