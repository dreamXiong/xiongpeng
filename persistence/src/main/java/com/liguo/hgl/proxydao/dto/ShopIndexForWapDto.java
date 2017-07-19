package com.liguo.hgl.proxydao.dto;

import java.util.List;

import com.liguo.hgl.proxydao.model.TbShopInfo;

public class ShopIndexForWapDto extends TbShopInfo{
	
	private String province;//省
	private String city;//市
	private String country;//区县
	private String street;//街道
	
	/*private List<TbWapProductDto>  wapProductDtoList;
	
	public List<TbWapProductDto> getWapProductDtoList() {
		return wapProductDtoList;
	}
	public void setWapProductDtoList(List<TbWapProductDto> wapProductDtoList) {
		this.wapProductDtoList = wapProductDtoList;
	}*/
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	
}
