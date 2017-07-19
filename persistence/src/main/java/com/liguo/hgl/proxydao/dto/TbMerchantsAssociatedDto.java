package com.liguo.hgl.proxydao.dto;

import com.liguo.hgl.proxydao.model.TbMerchants;
/**
 * 招商与品牌关联
 * @fiTbMerchantsBrandDto.java
 * @2016-4-12	
 * @author 周双双
 */
public class TbMerchantsAssociatedDto extends TbMerchants{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String brandName;
	private String provinceName;
	private String cityName;
	private String countryName;
	private String streetName;
	private String addressName;
	private String levelName;
	private String producttypeName;
	private String placesName;//用于通过地区id查询地址
	private int remainingPlaceNumber;
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	
	public String getAddressName() {
		String name=this.getProvinceName()+"-"+this.getCityName();
		if(this.getCountryName() !=null){
			name=name+"-"+this.getCountryName();
		}
		if(this.getStreetName()!=null){
			name=name+"-"+this.getStreetName();
		}
		return name;
	}
	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}
	
	public String getLevelName() {
		if(this.getLevel()==1){
			return "省";
		}else if(this.getLevel()==2){
			return "市";
		}else if(this.getLevel() == 3){
			return "区县";
		}
		return "街道";
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	public String getProducttypeName() {
		return producttypeName;
	}
	public void setProducttypeName(String producttypeName) {
		this.producttypeName = producttypeName;
	}
	
	public String getPlacesName() {
		return placesName;
	}
	public void setPlacesName(String placesName) {
		this.placesName = placesName;
	}
	public int getRemainingPlaceNumber() {
		return remainingPlaceNumber;
	}
	public void setRemainingPlaceNumber(int remainingPlaceNumber) {
		this.remainingPlaceNumber = remainingPlaceNumber;
	}
	
	
	
}
