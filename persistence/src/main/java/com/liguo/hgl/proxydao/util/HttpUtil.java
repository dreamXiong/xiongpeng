package com.liguo.hgl.proxydao.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {
    static private int HttpConnectTimeout = 5000;
    static private int HttpReadTimeout = 10000;

    /**
     * @return the httpConnectTimeout
     */
    public static int getHttpConnectTimeout() {

        return HttpConnectTimeout;
    }

    /**
     * @return the httpReadTimeout
     */
    public static int getHttpReadTimeout() {

        return HttpReadTimeout;
    }

    /**
     * @param httpConnectTimeout the httpConnectTimeout to set
     */
    public static void setHttpConnectTimeout(int httpConnectTimeout) {

        HttpConnectTimeout = httpConnectTimeout;
    }

    /**
     * @param httpReadTimeout the httpReadTimeout to set
     */
    public static void setHttpReadTimeout(int httpReadTimeout) {

        HttpReadTimeout = httpReadTimeout;
    }

    public static String getBodyData(String urlStr, String param, String username, String password) {

        HttpURLConnection conn = null;
        String result = null;
        try {
            String newurl = urlStr;
            if (param != null)
                newurl = urlStr + "?" + param;

            URL url = new URL(newurl);
            //URLConnection conn = url.openConnection();
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            if (username != null && password != null) {
                String userPassword = username + ":" + password;
                userPassword = Base64.encodeBytes(userPassword.getBytes());
                //设置身份验证
                conn.setRequestProperty("Authorization", "Basic " + userPassword);
            }
            conn.setConnectTimeout(HttpConnectTimeout * 10);//（单位：毫秒）jdk 1.5连接超时
            conn.setReadTimeout(HttpReadTimeout * 10);//（单位：毫秒）jdk 1.5读操作超时
            conn.connect();
            // Get the response
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "gb2312"));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }

            rd.close();
            result = sb.toString();
            // result=new String(sb.toString().getBytes("gb2312"));
        } catch (IOException e) {
            result = null;
            System.out.println(e);
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return result;
    }

    public static String postBodyData(String urlStr, String data, String username, String password) {

        HttpURLConnection conn = null;
        String result = null;
        try {
            // Send data,http post
            URL url = new URL(urlStr);
            //URLConnection conn = url.openConnection();
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept", "*/*");
            conn.setRequestProperty("Content-Type", "text/xml");
            conn.setRequestProperty("Content-Length", "" + data.length());
            //            conn.setRequestProperty("Content-Language", "en-US");
            if (username != null && password != null) {
                String userPassword = username + ":" + password;
                userPassword = Base64.encodeBytes(userPassword.getBytes());
                //设置商户身份验证
                conn.setRequestProperty("Authorization", "Basic " + userPassword);
            }
            conn.setConnectTimeout(HttpConnectTimeout);//（单位：毫秒）jdk 1.5连接超时
            conn.setReadTimeout(HttpReadTimeout);//（单位：毫秒）jdk 1.5读操作超时
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(data);
            wr.flush();
            // Get the response
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            wr.close();
            rd.close();
            result = sb.toString();
            //result=new String(s.getBytes("gb2312"));
        } catch (IOException e) {
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return result;
    }

    public static String postBodyData(String urlStr, String data) {

        HttpURLConnection conn = null;
        String result = null;
        try {
            // Send data,http post
            URL url = new URL(urlStr);
            //URLConnection conn = url.openConnection();
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept", "*/*");
            conn.setRequestProperty("Content-Type", "text/xml");
            conn.setRequestProperty("Content-Length", "" + data.length());
            //            conn.setRequestProperty("Content-Language", "en-US");
            conn.setUseCaches(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setConnectTimeout(HttpConnectTimeout);//（单位：毫秒）jdk 1.5连接超时
            conn.setReadTimeout(HttpReadTimeout);//（单位：毫秒）jdk 1.5读操作超时
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream(), "utf-8");
            wr.write(data);
            wr.flush();
            // Get the response
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            wr.close();
            rd.close();
            result = sb.toString();
            //result=new String(s.getBytes("gb2312"));
        } catch (IOException e) {
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return result;
    }

    /*
    public static String postBodyData1(String url, String data){

    	String result=null;
    	HttpClient httpclient;
    	PostMethod post = null ;
    	try 
    	{
    		httpclient = new HttpClient();
    		post = new PostMethod(url);
    		RequestEntity entity = new StringRequestEntity(data, "text/xml",
    		"UTF-8");
    		post.setRequestEntity(entity);
    		httpclient.executeMethod(post);
    		int code = post.getStatusCode();
    		if (code == HttpStatus.SC_OK)
    			result = new String(post.getResponseBodyAsString());
    	} catch (Exception ex) {
    		Log.error( ex.toString() );
    		ex.printStackTrace();
    	} finally {
    		post.releaseConnection();
    	}
    	return result;
    }
    //*/

    /* ----------------------------------------------------------------------------------------- */

    public static String doHttpPost(String url, String fixture, int connectTimeout, int readTimeout) {

        String ret = null;

        HttpURLConnection httpURLConnection = null;
        OutputStream out = null;
        InputStream in = null;

        try {

            URL serverURL = new URL(url);
            httpURLConnection = (HttpURLConnection) serverURL.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Accept", "*/*");
            httpURLConnection.setRequestProperty("Content-Type", "text/xml");
            httpURLConnection.setRequestProperty("Content-Length", "" + fixture.length());
            //            httpURLConnection.setRequestProperty("Content-Language", "en-US");
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setConnectTimeout(connectTimeout);//（单位：毫秒）jdk 1.5连接超时
            httpURLConnection.setReadTimeout(readTimeout);//（单位：毫秒）jdk 1.5读操作超时
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);

            out = httpURLConnection.getOutputStream();
            String query = serverURL.getQuery();
            if (query != null && query.contains("format=gzip"))
                out.write(GZipUtil.toGzippedBytes(fixture));
            else {
                Writer bufferedWriter = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
                bufferedWriter.write(fixture);
                bufferedWriter.flush();
            }

            in = httpURLConnection.getInputStream();

            if (query != null && query.contains("format=gzip")) {
                String response = GZipUtil.fromGzippedBytes(in);
                ret = response;
            } else
                ret = receivePlain(in);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null)
                    out.close();
                if (in != null)
                    in.close();
                if (httpURLConnection != null)
                    httpURLConnection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ret;

    }

    public static String doHttpPost(String url, String fixture) {

        return doHttpPost(url, fixture, HttpConnectTimeout, HttpReadTimeout);
    }

    /* ----------------------------------------------------------------------------------------- */

    private static String receivePlain(InputStream in) {

        try {
            Reader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));

            Writer stringWriter = new StringWriter();
            Writer bufferedWriter = new BufferedWriter(stringWriter);
            for (int i = 0; (i = reader.read()) > 0;)
                bufferedWriter.write(i);
            bufferedWriter.close();

            return stringWriter.toString();
        } catch (Exception ex) {
            return null;
        }
    }

    public static String byteArrToHexStr(byte[] arrB) throws Exception {

        int iLen = arrB.length;
        // 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
        StringBuffer sb = new StringBuffer(iLen * 2);
        for (int i = 0; i < iLen; i++) {
            int intTmp = arrB[i];
            //System.out.println(intTmp);
            // 把负数转换为正数
            while (intTmp < 0) {
                intTmp = intTmp + 256;
            }
            // 小于0F的数需要在前面补0
            if (intTmp < 16) {
                sb.append("0");
            }
            sb.append(Integer.toString(intTmp, 16));
        }
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {

        //doPostHttpData("http://172.0.0.59/azp_server_bridge/prepareOrder/post/?format=gzip",
        //		"<?xml version=\"1.0\" encoding=\"UTF-8\"?><MobilePay><appName>ANGELL-IVR</appName><bindPay>0</bindPay><cardId>1111111111111111111</cardId><cardName></cardName><merId>201112231003513</merId><mobileOperators></mobileOperators><msgType>PO</msgType><orderAddtion></orderAddtion><orderCoding></orderCoding><orderId>09001009</orderId><orderInfo>%E6%89%8B%E6%9C%BA%E5%85%85%E5%80%BC</orderInfo><orderType>880000</orderType><orderUserId></orderUserId><personId>111111111111111111</personId><transAmount>1.00</transAmount><transCurrency>156</transCurrency><transDate>20121115153010</transDate><userId>11111111111</userId><signCode>F6ADCF2C64FE40C3BE79D717D98EBEE41B7627F2E318B716768070903AE5F55245FACFEC9E7B3BEA</signCode></MobilePay>"	);
        postBodyData("http://172.168.3.114:8080/EC-Transaction/payCallback/", "<?xml version=\"1.0\" encoding=\"UTF-8\"?><MobilePay><userId>13755056631</userId><mobileOperators>1100</mobileOperators><transDate>20121115165734</transDate><signCode>F6ADCF2C64FE40C3BE79D717D98EBEE4D65857F327D2FC7D768070903AE5F55262C37356AD4D0FDF</signCode><bindPay>0</bindPay><personId>610100198506020996</personId><cardId>9558809229290383202</cardId><orderType>880001</orderType><orderUserId></orderUserId><orderCoding></orderCoding><orderAddtion></orderAddtion><msgType>PO</msgType><appName>ANGELL-IVR</appName><merId>201112231003513</merId><orderId>00000013</orderId><orderInfo>交易缴费</orderInfo><transAmount>1.00</transAmount><transCurrency>156</transCurrency><cardName>cardName</cardName></MobilePay>");
    }
}
