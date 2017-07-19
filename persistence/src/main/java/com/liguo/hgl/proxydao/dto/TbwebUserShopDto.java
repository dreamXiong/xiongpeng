package com.liguo.hgl.proxydao.dto;

import com.liguo.hgl.proxydao.model.TbWebUser;
import com.liguo.hgl.proxydao.util.Constant;
/**
 * 用户与shop表关联查询
 * @fiTbwebUserShopDto.java
 * @2016-4-23	
 * @author 周双双
 */
public class TbwebUserShopDto extends TbWebUser{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1837451397679747255L;
	
	private String companyName;
	private int city;
	private int province;
	
	private int country;
	private int street;
	private String addressName;
	private String producttype;
	private String legalRepresentative;
	
	//private String address;
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	
	public int getCity() {
		return city;
	}
	public void setCity(int city) {
		this.city = city;
	}
	public int getProvince() {
		return province;
	}
	public void setProvince(int province) {
		this.province = province;
	}
	public int getCountry() {
		return country;
	}
	public void setCountry(int country) {
		this.country = country;
	}
	public int getStreet() {
		return street;
	}
	public void setStreet(int street) {
		this.street = street;
	}
	public String getAddressName() {
		return addressName;
	}
	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}
	public String getProducttype() {
		return producttype;
	}
	public void setProducttype(String producttype) {
		this.producttype = producttype;
	}
	public String getLegalRepresentative() {
		return legalRepresentative;
	}
	public void setLegalRepresentative(String legalRepresentative) {
		this.legalRepresentative = legalRepresentative;
	}
	
	
	/*public String getAddress() {
		String address="";
		System.out.println(this.getProvince()+"---------------------");
		if(this.getProvince()>0&& this.getCity() >0 && this.getCountry()>0 && this.getStreet()>0){
		address = address+AddressUtil.getProvinceName(this.getProvince())+AddressUtil.getCityName(this.getCity())
				+AddressUtil.getCountryName(this.getCountry())+AddressUtil.getStreetName(this.getStreet());
		}
		
		address=address+this.getAddressName();
		return address;
		
	}*/
	
	
	

}
