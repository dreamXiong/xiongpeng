package com.liguo.hgl.proxydao.dto;

import java.math.BigDecimal;

import com.liguo.hgl.proxydao.model.TbProduct;

public class TbProductDto extends TbProduct {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 规格数
	 */
	private int specNum;

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
	
	private Integer saveId; //收藏id，查看产品是否被用户收藏
	
	private String createdBy; //收藏人

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

	public Integer getSaveId() {
		return saveId;
	}

	public void setSaveId(Integer saveId) {
		this.saveId = saveId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}	
}
