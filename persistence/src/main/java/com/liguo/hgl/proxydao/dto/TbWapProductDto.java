package com.liguo.hgl.proxydao.dto;

import java.math.BigDecimal;

import com.liguo.hgl.proxydao.model.TbWapProduct;

public class TbWapProductDto extends TbWapProduct {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 规格数
	 */
	private int specNum;

	/**
	 * 该产品下有多少个规格
	 * */
	private int statusCount;

	private String brandName;

	/**
	 * 最高价格
	 */
	private BigDecimal maxPrice;

	/**
	 * 大类名称
	 */
	private String mainTypeName;

	private String firstProductTypeName;

	private String secondProductTypeName;

	private String thirdProductTypeName;

	// 距离
	private double distance;
	private Integer shopId;
	private String shopName;
	private String lon;
	private String lat;
	private String shopPhone;
	private String province;
	private String city;
	private String country;
	private String street;
	private String shopDistance;

	public int getStatusCount() {
		return statusCount;
	}

	public void setStatusCount(int statusCount) {
		this.statusCount = statusCount;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public int getSpecNum() {
		return specNum;
	}

	public void setSpecNum(int specNum) {
		this.specNum = specNum;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public BigDecimal getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(BigDecimal maxPrice) {
		this.maxPrice = maxPrice;
	}

	public String getMainTypeName() {
		return mainTypeName;
	}

	public void setMainTypeName(String mainTypeName) {
		this.mainTypeName = mainTypeName;
	}

	public String getFirstProductTypeName() {
		return firstProductTypeName;
	}

	public void setFirstProductTypeName(String firstProductTypeName) {
		this.firstProductTypeName = firstProductTypeName;
	}

	public String getSecondProductTypeName() {
		return secondProductTypeName;
	}

	public void setSecondProductTypeName(String secondProductTypeName) {
		this.secondProductTypeName = secondProductTypeName;
	}

	public String getThirdProductTypeName() {
		return thirdProductTypeName;
	}

	public void setThirdProductTypeName(String thirdProductTypeName) {
		this.thirdProductTypeName = thirdProductTypeName;
	}

	public Integer getShopId() {
		return shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getShopPhone() {
		return shopPhone;
	}

	public void setShopPhone(String shopPhone) {
		this.shopPhone = shopPhone;
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

	public String getShopDistance() {
		return shopDistance;
	}

	public void setShopDistance(String shopDistance) {
		this.shopDistance = shopDistance;
	}

}
