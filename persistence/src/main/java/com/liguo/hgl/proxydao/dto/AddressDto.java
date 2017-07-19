package com.liguo.hgl.proxydao.dto;

import java.io.Serializable;

import com.liguo.hgl.proxydao.model.TbStreetInfo;

public class AddressDto extends TbStreetInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer provinceId;   		//省id
	private String provinceName;  		//省名称
	private Integer cityId;       		//市id
	private String cityName;      		//市名称
	private Integer cityParentId; 		//市父级id
	private Integer countryId;    		//区 id
	private String countryName;   		//区名称
	private Integer countryParentId; 	//区父级id
	private Integer streetId;			//街道id
	private String streetName;          //街道名称
	private Integer streetParentId;     //街道父级id
	
	
	public Integer getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public Integer getCityParentId() {
		return cityParentId;
	}
	public void setCityParentId(Integer cityParentId) {
		this.cityParentId = cityParentId;
	}
	public Integer getCountryId() {
		return countryId;
	}
	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public Integer getCountryParentId() {
		return countryParentId;
	}
	public void setCountryParentId(Integer countryParentId) {
		this.countryParentId = countryParentId;
	}
	public Integer getStreetId() {
		return streetId;
	}
	public void setStreetId(Integer streetId) {
		this.streetId = streetId;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public Integer getStreetParentId() {
		return streetParentId;
	}
	public void setStreetParentId(Integer streetParentId) {
		this.streetParentId = streetParentId;
	}
}
