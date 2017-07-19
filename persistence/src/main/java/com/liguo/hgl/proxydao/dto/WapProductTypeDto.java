package com.liguo.hgl.proxydao.dto;

import java.io.Serializable;

public class WapProductTypeDto implements Serializable {
	
	private String thirdType ;
	
	private Integer thirdId;
	
	private String secondId;
	
	private String secondType;
	
	
	public String getThirdType() {
		return thirdType;
	}
	public void setThirdType(String thirdType) {
		this.thirdType = thirdType;
	}
	public Integer getThirdId() {
		return thirdId;
	}
	public void setThirdId(Integer thirdId) {
		this.thirdId = thirdId;
	}
	public String getSecondId() {
		return secondId;
	}
	public void setSecondId(String secondId) {
		this.secondId = secondId;
	}
	public String getSecondType() {
		return secondType;
	}
	public void setSecondType(String secondType) {
		this.secondType = secondType;
	}
}
