package com.liguo.hgl.proxydao.model;

import java.util.List;

public class WapOrderGroupDto extends TbWapOrderGroup {
	
	private List<WapOrderDetailDto> wapOrderDetailDtoList;
	
	private String balance;
	
	private String province;
	
	private String city;
	
	private String country;
	
	private String street;
	
	private String companyName;
	
	private String mobile;
	
	private String regAddress;
	
	
	public String getRegAddress() {
		return regAddress;
	}

	public void setRegAddress(String regAddress) {
		this.regAddress = regAddress;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

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

	public List<WapOrderDetailDto> getWapOrderDetailDtoList() {
		return wapOrderDetailDtoList;
	}

	public void setWapOrderDetailDtoList(
			List<WapOrderDetailDto> wapOrderDetailDtoList) {
		this.wapOrderDetailDtoList = wapOrderDetailDtoList;
	}
	
	
}
