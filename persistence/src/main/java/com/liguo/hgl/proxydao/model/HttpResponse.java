package com.liguo.hgl.proxydao.model;

import java.io.UnsupportedEncodingException;

import org.apache.commons.httpclient.Header;

import com.liguo.hgl.proxydao.util.StringUtil;

/**
 * 
 * @author wuqq
 *
 */
public class HttpResponse {

    /**
     * 返回中的Header信息
     */
    private Header[] responseHeaders;

    /**
     * String类型的result
     */
    private String stringResult;

    /**
     * btye类型的result
     */
    private byte[] byteResult;

    /**
     * 响应状态码
     */
    private int statusCode;

    /**
     * 默认编码
     */
    private static final String DEFAULT_CHARSET = "UTF-8";

    public Header[] getResponseHeaders() {
        return responseHeaders;
    }

    public void setResponseHeaders(Header[] responseHeaders) {
        this.responseHeaders = responseHeaders;
    }

    /**
     * getByteResult
     * <一句话功能简述>
     * @return byte[]
     */
    public byte[] getByteResult() {
        if (byteResult != null) {
            return (byte[]) byteResult.clone();
        }
        if (stringResult != null) {
            try {
                return stringResult.getBytes(DEFAULT_CHARSET);
            }
            catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e.getCause());
            }
        }
        return new byte[0];
    }

    
    public void setByteResult(byte[] byteResult) {
        this.byteResult = byteResult;
    }

    public String getStringResult() {
        return this.getStringResult(null);
    }

    /**
     * getStringResult
     * @param charset charset
     * @return String
     */
    public String getStringResult(String charset) {
        if (StringUtil.isBlank(charset)) {
            charset = "UTF-8";
        }
        try {
            if (stringResult != null) {
                return stringResult;
            }
            if (byteResult != null) {
                return new String(byteResult, charset);
            }
        }
        catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
        return null;
    }

    public void setStringResult(String stringResult) {
        this.stringResult = stringResult;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

}
