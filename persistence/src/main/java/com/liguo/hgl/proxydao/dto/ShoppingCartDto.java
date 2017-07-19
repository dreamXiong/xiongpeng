package com.liguo.hgl.proxydao.dto;

import java.io.Serializable;
import java.util.List;

public class ShoppingCartDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer brandId;
	private String brandName;
	private Boolean shoppingType;
	private Integer merchantsId;

	private List<ProductInfoDto> productInfoList;

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public List<ProductInfoDto> getProductInfoList() {
		return productInfoList;
	}

	public void setProductInfoList(List<ProductInfoDto> productInfoList) {
		this.productInfoList = productInfoList;
	}

	public Boolean getShoppingType() {
		return shoppingType;
	}

	public void setShoppingType(Boolean shoppingType) {
		this.shoppingType = shoppingType;
	}

	public Integer getMerchantsId() {
		return merchantsId;
	}

	public void setMerchantsId(Integer merchantsId) {
		this.merchantsId = merchantsId;
	}

}
