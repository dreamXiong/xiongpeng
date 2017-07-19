package com.liguo.hgl.proxydao.dto;

import java.io.Serializable;
import java.util.List;

import com.liguo.hgl.proxydao.dto.TbProductInventoryDto;

public class ProductInfoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer productId;
	private String productName;
	private String logoName;
	private String meterageUnit;
	private String productTypeName;

	private List<TbProductInventoryDto> productInventoryList;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getLogoName() {
		return logoName;
	}

	public void setLogoName(String logoName) {
		this.logoName = logoName;
	}

	public String getMeterageUnit() {
		return meterageUnit;
	}

	public void setMeterageUnit(String meterageUnit) {
		this.meterageUnit = meterageUnit;
	}

	public String getProductTypeName() {
		return productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}

	public List<TbProductInventoryDto> getProductInventoryList() {
		return productInventoryList;
	}

	public void setProductInventoryList(
			List<TbProductInventoryDto> productInventoryList) {
		this.productInventoryList = productInventoryList;
	}

}
