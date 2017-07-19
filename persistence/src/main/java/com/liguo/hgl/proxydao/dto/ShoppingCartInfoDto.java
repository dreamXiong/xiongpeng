package com.liguo.hgl.proxydao.dto;

import java.io.Serializable;

public class ShoppingCartInfoDto extends TbProductInventoryDto implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer brandId;
	private String brandName;
	private String productName;
	private String productTypeName;
	private Integer cartMerchantsId;
	private Double cartMerchantsMoney;

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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductTypeName() {
		return productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}

	public Integer getCartMerchantsId() {
		return cartMerchantsId;
	}

	public void setCartMerchantsId(Integer cartMerchantsId) {
		this.cartMerchantsId = cartMerchantsId;
	}

	public Double getCartMerchantsMoney() {
		return cartMerchantsMoney;
	}

	public void setCartMerchantsMoney(Double cartMerchantsMoney) {
		this.cartMerchantsMoney = cartMerchantsMoney;
	}

	
}
