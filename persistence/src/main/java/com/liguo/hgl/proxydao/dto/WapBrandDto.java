package com.liguo.hgl.proxydao.dto;

import java.io.Serializable;

public class WapBrandDto implements Serializable {
	
	private String brandName ;
	
	private Integer brandId;

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}
	
}
