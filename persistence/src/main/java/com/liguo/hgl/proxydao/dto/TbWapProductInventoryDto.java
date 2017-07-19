package com.liguo.hgl.proxydao.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.liguo.hgl.proxydao.model.TbWapProductInventory;

public class TbWapProductInventoryDto extends TbWapProductInventory {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String firstProductTypeName;
	private String secondProductTypeName;
	private String thirdProductTypeName;
	private String brandName;
	private String productName;
	private String attributes;
	private BigDecimal discountPrice;
	private Integer buyNum;
	private BigDecimal subtotalPrice;
	private Integer cartId;
	private Boolean shopType;
	private Integer shopFlag;
	private Integer pushFlag;
	private Integer masterId;
	private List<KeyValueDto> attributeValueList;

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

	public List<KeyValueDto> getAttributeValueList() {
		String attributes = getAttributes();
		String attributeVaules = getAttributesValues();
		List<KeyValueDto> list = new ArrayList<KeyValueDto>();
		if (attributes != null && !"".equals(attributes)) {
			String[] attrs = attributes.split("/");
			String[] values = null;
			if (attributeVaules != null && !"".equals(attributeVaules)) {
				values = attributeVaules.split(";");
			}
			for (int i = 0; i < attrs.length; i++) {
				KeyValueDto dto = new KeyValueDto();
				dto.setKey(attrs[i]);
				if (values != null && values.length > i) {
					dto.setValue(values[i]);
				}
				list.add(dto);
			}
		}
		return list;
	}

	public void setAttributeValueList(List<KeyValueDto> attributeValueList) {
		this.attributeValueList = attributeValueList;
	}

	public String getAttributes() {
		return attributes;
	}

	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}

	public BigDecimal getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(BigDecimal discountPrice) {
		this.discountPrice = discountPrice;
	}

	public Integer getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(Integer buyNum) {
		this.buyNum = buyNum;
	}

	public BigDecimal getSubtotalPrice() {
		return subtotalPrice;
	}

	public void setSubtotalPrice(BigDecimal subtotalPrice) {
		this.subtotalPrice = subtotalPrice;
	}

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public Boolean getShopType() {
		return shopType;
	}

	public void setShopType(Boolean shopType) {
		this.shopType = shopType;
	}

	public Integer getShopFlag() {
		return shopFlag;
	}

	public void setShopFlag(Integer shopFlag) {
		this.shopFlag = shopFlag;
	}

	public Integer getPushFlag() {
		return pushFlag;
	}

	public void setPushFlag(Integer pushFlag) {
		this.pushFlag = pushFlag;
	}

	public Integer getMasterId() {
		return masterId;
	}

	public void setMasterId(Integer masterId) {
		this.masterId = masterId;
	}

}
