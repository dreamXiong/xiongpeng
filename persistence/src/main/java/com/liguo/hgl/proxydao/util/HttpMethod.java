package com.liguo.hgl.proxydao.util;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.liguo.hgl.proxydao.model.HttpResponse;

/**
 * HTTP工具类
 * 
 * @author 
 * 
 */
public class HttpMethod {

	private static Log log = LogFactory.getLog(HttpMethod.class);

	/**
	 * 定义编码格式 UTF-8
	 */
	public static final String URL_PARAM_DECODECHARSET_UTF8 = "UTF-8";

	/**
	 * 定义编码格式 GBK
	 */
	public static final String URL_PARAM_DECODECHARSET_GBK = "GBK";

	private static final String URL_PARAM_CONNECT_FLAG = "&";

	private static final String EMPTY = "";

	private static MultiThreadedHttpConnectionManager connectionManager = null;

	private static int connectionTimeOut = 25000;

	private static int socketTimeOut = 25000;

	private static int maxConnectionPerHost = 20;

	private static int maxTotalConnections = 20;

	private static HttpClient client;

	static{
		connectionManager = new MultiThreadedHttpConnectionManager();
		connectionManager.getParams().setConnectionTimeout(connectionTimeOut);
		connectionManager.getParams().setSoTimeout(socketTimeOut);
		connectionManager.getParams().setDefaultMaxConnectionsPerHost(maxConnectionPerHost);
		connectionManager.getParams().setMaxTotalConnections(maxTotalConnections);
		client = new HttpClient(connectionManager);
	}

	/**
	 * POST方式提交数据
	 * @param url
	 *             待请求的URL
	 * @param params
	 *             要提交的数据
	 * @param enc
	 *             编码
	 * @return
	 *             响应结果
	 * @throws IOException
	 *             IO异常
	 */
	public static String URLPost(String url, Map<String, String> params, String enc){

		String response = EMPTY;        
		PostMethod postMethod = null;
		try {
			postMethod = new PostMethod(url);
			postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=" + enc);
			//将表单的值放入postMethod中
			Set<String> keySet = params.keySet();
			for(String key : keySet){
				String value = params.get(key);
				postMethod.addParameter(key, value);
			}            
			//执行postMethod
			int statusCode = client.executeMethod(postMethod);
			if(statusCode == HttpStatus.SC_OK) {
				response = postMethod.getResponseBodyAsString();
			}else{
				log.error("响应状态码 = " + postMethod.getStatusCode());
			}
		}catch(HttpException e){
			log.error("发生致命的异常，可能是协议不对或者返回的内容有问题", e);
			e.printStackTrace();
		}catch(IOException e){
			log.error("发生网络异常", e);
			e.printStackTrace();
		}finally{
			if(postMethod != null){
				postMethod.releaseConnection();
				postMethod = null;
			}
		}

		return response;
	}

	/**
	 * GET方式提交数据
	 * @param url
	 *             待请求的URL
	 * @param params
	 *             要提交的数据
	 * @param enc
	 *             编码
	 * @return
	 *             响应结果
	 * @throws IOException
	 *             IO异常
	 */
	public static HttpResponse URLGet(String url, Map<String, String> params, String enc){

	    HttpResponse response = new HttpResponse();
		GetMethod getMethod = null;        
		StringBuffer strtTotalURL = new StringBuffer(EMPTY);

		if(strtTotalURL.indexOf("?") == -1) {
			strtTotalURL.append(url).append("?").append(getUrl(params, enc));
		} else {
			strtTotalURL.append(url).append("&").append(getUrl(params, enc));
		}
		log.debug("GET请求URL = \n" + strtTotalURL.toString());

		try {
			getMethod = new GetMethod(strtTotalURL.toString());
			getMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=" + enc);
			//执行getMethod
			int statusCode = client.executeMethod(getMethod);
			log.info(">>>>>>>>>>>>>>> statusCode  value:"+statusCode);
			if(statusCode == HttpStatus.SC_OK) {
			    log.info(">>>>>>>>>>>>>>> responseBody value:"+new String(getMethod.getResponseBody(),"utf-8"));
			    response.setStringResult(new String(getMethod.getResponseBody(),"utf-8"));
			}else{
				log.debug("响应状态码 = " + getMethod.getStatusCode());
			}
			response.setResponseHeaders(getMethod.getResponseHeaders());
            response.setStatusCode(getMethod.getStatusCode());
		}catch(HttpException e){
			log.error("发生致命的异常，可能是协议不对或者返回的内容有问题", e);
			e.printStackTrace();
		}catch(IOException e){
			log.error("发生网络异常", e);
			e.printStackTrace();
		}finally{
			if(getMethod != null){
				getMethod.releaseConnection();
				getMethod = null;
			}
		}

		return response;
	}    
	/**
	 * GET方式提交数据
	 * @param url
	 *             待请求的URL
	 * @param params
	 *             要提交的数据
	 * @param enc
	 *             编码
	 * @return
	 *             响应结果
	 * @throws IOException
	 *             IO异常
	 */
	public static HttpResponse URLGetObjectMap(String url, Map<String, Object> params, String enc){
	    
	    HttpResponse response = new HttpResponse();
	    GetMethod getMethod = null;        
	    StringBuffer strtTotalURL = new StringBuffer(EMPTY);
	    
	    if(strtTotalURL.indexOf("?") == -1) {
	        strtTotalURL.append(url).append("?").append(getObjectMapAsUrl(params, enc));
	    } else {
	        strtTotalURL.append(url).append("&").append(getObjectMapAsUrl(params, enc));
	    }
	    log.debug("GET请求URL = \n" + strtTotalURL.toString());
	    
	    try {
	        getMethod = new GetMethod(strtTotalURL.toString());
	        getMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=" + enc);
	        //执行getMethod
	        int statusCode = client.executeMethod(getMethod);
	        if(statusCode == HttpStatus.SC_OK) {
//				response = getMethod.getResponseBodyAsString();
	            response.setStringResult(new String(getMethod.getResponseBody(),"utf-8"));
	        }else{
	            log.debug("响应状态码 = " + getMethod.getStatusCode());
	        }
	        response.setResponseHeaders(getMethod.getResponseHeaders());
	        response.setStatusCode(getMethod.getStatusCode());
	    }catch(HttpException e){
	        log.error("发生致命的异常，可能是协议不对或者返回的内容有问题", e);
	        e.printStackTrace();
	    }catch(IOException e){
	        log.error("发生网络异常", e);
	        e.printStackTrace();
	    }finally{
	        if(getMethod != null){
	            getMethod.releaseConnection();
	            getMethod = null;
	        }
	    }
	    
	    return response;
	}    
	
	
	public static Map<String, Object> getResult(String url, Map<String, String> params, String enc){
	    HttpResponse response =URLGet(url, params, enc);
	    log.info(">>>>>>>>>>>>start http  getResult.....");
	    // 处理返回结果
        if (response.getStatusCode() == 200) {
            log.info(">>>>>>>> response code is 200 ");
            String resultStr = response.getStringResult();
//            resultStr = sb.toString();
            log.info(">>>>>>>> response result json  value  "+resultStr);
            if (!StringUtils.isEmpty(resultStr)) {
                StringBuffer sb = new StringBuffer();
                sb.append("[").append(resultStr).append("]");
                resultStr= sb.toString();
                // 解密
//                resultStr = Des3.decode(resultStr);
                log.info("End Post Call: " + url + " with result: " + resultStr);
                // 解析JSON
                JSONArray getJsonArray = JSONArray.parseArray(resultStr);
                JSONObject resultObj =getJsonArray.getJSONObject(0);
                if (resultObj != null) {
                    Field[] fields = resultObj.getClass().getDeclaredFields();
                    String fieldName = null;
                    Object fieldValue = null;
                    Map<String, Object> httpReturnMap = new HashMap<String, Object>();

                    for (int i = 0; i < fields.length; i++) {
                        fields[i].setAccessible(true);
                        fieldName = String.valueOf(fields[i].getName());
                        log.info("http response result fieldName value :"+fieldName);
                        try {
                            fieldValue = fields[i].get(resultObj);
                        log.info("http response result fieldValue value :"+fieldValue);
                        }
                        catch (Exception e) {
                            log.error("http result is error , error message :"+e.getMessage());
                            throw new RuntimeException();
                        }
                        httpReturnMap.put(fieldName, fieldValue);
                    }
                    httpReturnMap = (Map<String, Object>) httpReturnMap.get("map");
                    // 组装返回
                    return httpReturnMap;
                }
            }
    }
        return null;
	}

	/**
	 * 据Map生成URL字符串
	 * @param map
	 *             Map
	 * @param valueEnc
	 *             URL编码
	 * @return
	 *             URL
	 */
	private static String getUrl(Map<String, String> map, String valueEnc) {

		if (null == map || map.keySet().size() == 0) {
			return (EMPTY);
		}
		StringBuffer url = new StringBuffer();
		Set<String> keys = map.keySet();
		for (Iterator<String> it = keys.iterator(); it.hasNext();) {
			String key = it.next();
			if (map.containsKey(key)) {
				String val = map.get(key);
				String str = val != null ? val : EMPTY;
				try {
					str = URLEncoder.encode(str, valueEnc);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				url.append(key).append("=").append(str).append(URL_PARAM_CONNECT_FLAG);
			}
		}
		String strURL = EMPTY;
		strURL = url.toString();
		if (URL_PARAM_CONNECT_FLAG.equals(EMPTY + strURL.charAt(strURL.length() - 1))) {
			strURL = strURL.substring(0, strURL.length() - 1);
		}

		return (strURL);
	}
	/**
	 * 据Map生成URL字符串
	 * @param map
	 *             Map
	 * @param valueEnc
	 *             URL编码
	 * @return
	 *             URL
	 */
	private static String getObjectMapAsUrl(Map<String, Object> map, String valueEnc) {
	    
	    if (null == map || map.keySet().size() == 0) {
	        return (EMPTY);
	    }
	    StringBuffer url = new StringBuffer();
	    Set<String> keys = map.keySet();
	    for (Iterator<String> it = keys.iterator(); it.hasNext();) {
	        String key = it.next();
	        if (map.containsKey(key)) {
//	            String val = map.get(key);
	            if(map.get(key)==null){
	              continue;  
	            }
	            String val ="";
	            if(map.get(key) instanceof java.lang.String){
                    val = String.valueOf(map.get(key));
                }
	            else if(map.get(key) instanceof java.util.Date){
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    val =sdf.format(map.get(key));
                }
	            else{
	                continue;
	            }
	            String str = val != null ? val : EMPTY;
	            try {
	                str = URLEncoder.encode(str, valueEnc);
	            } catch (UnsupportedEncodingException e) {
	                e.printStackTrace();
	            }
	            url.append(key).append("=").append(str).append(URL_PARAM_CONNECT_FLAG);
	        }
	    }
	    String strURL = EMPTY;
	    strURL = url.toString();
	    if (URL_PARAM_CONNECT_FLAG.equals(EMPTY + strURL.charAt(strURL.length() - 1))) {
	        strURL = strURL.substring(0, strURL.length() - 1);
	    }
	    
	    return (strURL);
	}
	
	public static void main(String[] args) {
//	    String str = "[{\"packageValue\":\"prepay_id=wx20160704150105f6b24f8fc00261172164\",\"package\":\"prepay_id=wx20160704150105f6b24f8fc00261172164\",\"nonceStr\":\"66biFd2WUZhHAdwa\",\"appId\":\"wx9dd0df5b1621ec44\",\"timeStamp\":\"1467615695514\",\"paySign\":\"D8DC03B694419C830AF80ED762BCF6EF\",\"signType\":\"MD5\"}]";
//	    String str = "{\"packageValue\":\"prepay_id=wx20160704150105f6b24f8fc00261172164\",\"package\":\"prepay_id=wx20160704150105f6b24f8fc00261172164\",\"nonceStr\":\"66biFd2WUZhHAdwa\",\"appId\":\"wx9dd0df5b1621ec44\",\"timeStamp\":\"1467615695514\",\"paySign\":\"D8DC03B694419C830AF80ED762BCF6EF\",\"signType\":\"MD5\"}";
//	    JSONArray getJsonArray = JSONArray.parseArray(str);
//	    System.out.println(getJsonArray);
	    StringBuilder sb = new StringBuilder();
	    System.out.println(StringUtils.isEmpty(sb.toString()));
    }
}