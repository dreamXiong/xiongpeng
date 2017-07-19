package com.liguo.hgl.proxydao.dto;

import java.util.List;

public class WapProductType {
	
	private String mainType;
	
	private Integer mainId;
	
	
	private List<WapBrandDto> wapBrandDtoList;
	
	private List<WapProductTypeDto> wapProductTypeDtoList;

	public String getMainType() {
		return mainType;
	}
	public void setMainType(String mainType) {
		this.mainType = mainType;
	}
	public Integer getMainId() {
		return mainId;
	}
	public void setMainId(Integer mainId) {
		this.mainId = mainId;
	}
	public List<WapBrandDto> getWapBrandDtoList() {
		return wapBrandDtoList;
	}

	public void setWapBrandDtoList(List<WapBrandDto> wapBrandDtoList) {
		this.wapBrandDtoList = wapBrandDtoList;
	}

	public List<WapProductTypeDto> getWapProductTypeDtoList() {
		return wapProductTypeDtoList;
	}

	public void setWapProductTypeDtoList(
			List<WapProductTypeDto> wapProductTypeDtoList) {
		this.wapProductTypeDtoList = wapProductTypeDtoList;
	}
}
