package com.liguo.hgl.util;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

public class JsonUtil {
	
	public static  <T> void setJson(List<T> list, HttpServletResponse response) {
        String json = "";
        try {
            json = JSON.toJSONString(list);
            response.setContentType("application/json;charset=UTF-8");
            response.setCharacterEncoding("utf-8");
            PrintWriter out;
            out = response.getWriter();
            out.println(json);
            out.flush();
            out.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
