package com.liguo.hgl.proxydao.dto;

public class BaiduMapDto {
	
	private String status;
	
	//用于百度地图
	private Result result;
	
	//用于高德地图
	private Result regeocode;
	
	public Result getRegeocode() {
		return regeocode;
	}

	public void setRegeocode(Result regeocode) {
		this.regeocode = regeocode;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
