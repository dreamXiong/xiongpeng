package com.liguo.hgl.proxydao.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.liguo.hgl.proxydao.model.Token;

import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

public class CommonUtil {
	private static Logger log = LoggerFactory.getLogger(CommonUtil.class);
    // access_token_url,获取token的url
	public final static String WEIXIN_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    // menu_create_url,创建菜单url
    public static String WEIXIN_MENU_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    // menu_delete_url,删除菜单url
    public static String WEIXIN_MENU_DELETE_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
    
	/**
	 * 发送https请求
	 * @param requestUrl 请求地址
	 * @param requestMethod 请求方式（GET、POST）
	 * @param outputStr 提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpsRequest(String requestUrl,String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		try {
			String str = httpsRequestStr(requestUrl,requestMethod,outputStr);
			jsonObject = JSONObject.parseObject(str);
		} catch (Exception e) {
			log.error("https请求异常：{}", e);
		}
		return jsonObject;
	}
	
	/**
     * 发送https请求
     * @param requestUrl 请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr 提交的数据
     * @return 返回微信服务器响应的信息
     */
	public static String httpsRequestStr(String requestUrl, String requestMethod,String outputStr) {
		String resultStr = null;
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			URL url = new URL(requestUrl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setSSLSocketFactory(ssf);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			conn.setRequestMethod(requestMethod);
			// 当outputStr不为null时向输出流写数据
			if (null != outputStr) {
				OutputStream outputStream = conn.getOutputStream();
				// 注意编码格式
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}
			// 从输入流读取返回内容
			InputStream inputStream = conn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);
			String str = null;
			StringBuffer buffer = new StringBuffer();
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			// 释放资源
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream = null;
			conn.disconnect();
			resultStr = buffer.toString();
		} catch (ConnectException ce) {
			log.error("连接超时：{}", ce);
		} catch (Exception e) {
			log.error("https请求异常：{}", e);
		}
		return resultStr;
	}

	/**
	 * 获取接口访问凭证
	 * @param appid 凭证
	 * @param appsecret 密钥
	 * @return
	 */
	public static Token getToken(String appid, String appsecret) {
		Token token = null;
		String requestUrl = WEIXIN_ACCESS_TOKEN_URL.replace("APPID", appid).replace("APPSECRET", appsecret);
		// 发起GET请求获取凭证
		JSONObject jsonObject = httpsRequest(requestUrl, "GET", null);
		if (null != jsonObject) {
			try {
				token = new Token();
				token.setAccessToken(jsonObject.getString("access_token"));
				token.setExpiresIn(jsonObject.getInteger("expires_in"));
			} catch (Exception e) {
				token = null;
				// 获取token失败
				log.error("获取token失败 errcode:{} errmsg:{}",jsonObject.getInteger("errcode"),jsonObject.getString("errmsg"));
			}
		}
		return token;
	}
	
	/**
	 * 创建菜单
	 * @param menu 菜单实例
	 * @param accessToken 有效的access_token
	 * @return 0表示成功，其他值表示失败
	 */
	public static int createMenu(String jsonMenu, String accessToken) {
		int result = 0;
		// 拼装创建菜单的url
		String requestUrl = WEIXIN_MENU_CREATE_URL.replace("ACCESS_TOKEN", accessToken);
		// 调用接口创建菜单
		JSONObject jsonObject = httpsRequest(requestUrl, "POST", jsonMenu);
		if (null != jsonObject) {
			if (0 != jsonObject.getInteger("errcode")) {
				result = jsonObject.getInteger("errcode");
				// 创建菜单失败
				log.error("创建菜单失败 errcode:{} errmsg:{}",jsonObject.getInteger("errcode"),jsonObject.getString("errmsg"));
			}
		}
		return result;
	}
	
	/**
	 * 删除菜单
	 * @param accessToken 有效的access_token
	 * @return 0表示成功，其他值表示失败
	 */
	 public static int deleteMenu(String accessToken) throws Exception {
	    int result = 0;
		// 拼装删除菜单的url
		String requestUrl = WEIXIN_MENU_DELETE_URL.replace("ACCESS_TOKEN", accessToken);
		// 调用接口创建菜单
		JSONObject jsonObject = httpsRequest(requestUrl, "POST", null);
		if (null != jsonObject) {
			if (0 != jsonObject.getInteger("errcode")) {
				result = jsonObject.getInteger("errcode");
				// 删除菜单失败
				log.error("删除菜单失败 errcode:{} errmsg:{}",jsonObject.getInteger("errcode"),jsonObject.getString("errmsg"));
			}
		}
		return result;
	 }

	/**
	 * URL编码（utf-8）
	 * @param source
	 * @return
	 */
	public static String urlEncodeUTF8(String source) {
		String result = source;
        try {
             result = java.net.URLEncoder.encode(source,"utf-8");
        } catch (UnsupportedEncodingException e) {
        	 log.error("urlEncodeUTF8" + e.getMessage());
             e.printStackTrace();
        }
        return result;
	}
	
    /**
     * 判断是否来自微信, 5.0 之后的支持微信支付
     *
     * @param request
     * @return
     */
    public static boolean isWeiXin(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        if (StringUtils.isNotBlank(userAgent)) {
            Pattern p = Pattern.compile("MicroMessenger/(\\d+).+");
            Matcher m = p.matcher(userAgent);
            String version = null;
            if (m.find()) {
                version = m.group(1);
            }
            return (null != version && NumberUtils.toInt(version) >= 5);
        }
        return false;
    }
    
    /**
     * 判断是否来自微信浏览器
     *
     * @param request
     * @return
     */
    public static boolean isWeiXinBrowser(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        if (userAgent != null && userAgent.contains("MicroMessenger")) {
           return true;
        }
        return false;
    }

	/**
	 * 根据内容类型判断文件扩展名
	 * 
	 * @param contentType
	 *            内容类型
	 * @return
	 */
	public static String getFileExt(String contentType) {
		String fileExt = "";
		if ("image/jpeg".equals(contentType))
			fileExt = ".jpg";
		else if ("audio/mpeg".equals(contentType))
			fileExt = ".mp3";
		else if ("audio/amr".equals(contentType))
			fileExt = ".amr";
		else if ("video/mp4".equals(contentType))
			fileExt = ".mp4";
		else if ("video/mpeg4".equals(contentType))
			fileExt = ".mp4";
		return fileExt;
	}
	
	/**
	 * 判断是否为图片
	 * @param fileName
	 * @return
	 */
	public static boolean isImage(String fileName) {
		boolean flag = false;
		InputStream inputStream = null;
		try {
			//获取输入流
			URL url = new URL(fileName);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			inputStream = connection.getInputStream();
			BufferedImage bufreader = ImageIO.read(inputStream);
			int width = bufreader.getWidth();
			int height = bufreader.getHeight();
			if (width == 0 || height == 0) {
				flag = false;
			} else {
				flag = true;
			}
		} catch (IOException e) {
			flag = false;
		} catch (Exception e) {
			flag = false;
		}finally{
			try {
				if(inputStream != null){	  //关闭输入流
					inputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}
	
	/**
	 * 读写图片方法
	 * @param photoUrl
	 * @param filePath
	 * @return
	 */
	public static boolean saveUrlAs(String picRootDirectory,String photoUrl, String filePath) {
		DataInputStream in = null;
		DataOutputStream out = null;
		try {
			//判断是否是http开头的
			if(!photoUrl.startsWith("http")){
				return false;
			}
			
			//调用判断是否是图片方法
//			if(!isImage(photoUrl)){
//				return false;
//			}
			
			//获取输入流
			URL url = new URL(photoUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			in = new DataInputStream(connection.getInputStream());
			
			//判断文件是否存在,不存在创建
			String fileDirectory = picRootDirectory + (filePath != null ? filePath.substring(0, filePath.lastIndexOf("/")) : "");
			//System.out.println(fileDirectory);
			File remoteFile = new File(fileDirectory);
			if (!remoteFile.exists()) {
				remoteFile.mkdirs();
			}
			remoteFile = new File(picRootDirectory + filePath);
			//System.out.println(remoteFile);
			
			//获取输出流
			out = new DataOutputStream(new FileOutputStream(remoteFile));
			byte[] buffer = new byte[in.available()];
			int count = 0;
			while ((count = in.read(buffer)) > 0) {
				out.write(buffer, 0, count);
			}
			return true;
		} catch (Exception e) {
			System.err.println("抓取图片异常: " + e.getMessage());
			return false;
		}finally{
			try {
				if(out != null){  //关闭输出流
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if(in != null){	  //关闭输入流
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		saveUrlAs(System.getProperty("user.home") + File.separator + "wapAlbumSpace"+ File.separator,"http://192.168.0.133:8080/web/generateProductImage?id=103&imgName=pImgOne.gif","123/ad1.png");
	}

}