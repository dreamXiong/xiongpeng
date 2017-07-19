/*
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */
package com.liguo.hgl.common;

import java.io.Serializable;
import java.util.List;

/**
 * <br>
 * 
 * @filename ResultDto.java<br>
 * @author 张勇<br>
 * @date 2016-1-11<br>
 * @Language java<br>
 */
public class ResultDto implements Serializable {

	private static final long serialVersionUID = 8149447992716796692L;

	private int code;
	private String message;
	private Object data;
	private List<Object> items;

	public ResultDto(String message) {

		this.message = message;
	}

	public ResultDto(int code, String message) {

		this.setCode(code);
		this.message = message;
	}

	public ResultDto(int code, String message, Object data) {

		this.setCode(code);
		this.message = message;
		this.data = data;
	}

	public Object getData() {

		return data;
	}

	public void setData(Object data) {

		this.data = data;
	}

	public String getMessage() {

		return message;
	}

	public int getCode() {

		return code;
	}

	public void setCode(int code) {

		this.code = code;
	}

	public List<Object> getItems() {

		return items;
	}

	public void setItems(List<Object> items) {

		this.items = items;
	}

	public void setMessage(String message) {

		this.message = message;
	}
}
