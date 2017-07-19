package com.liguo.hgl.proxydao.dto;

import java.util.ArrayList;
import java.util.List;

import com.liguo.hgl.proxydao.model.TbWapProductInventory;

public class WapProductInventoryDto extends TbWapProductInventory {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String productTypeName;
	private String logoName;
	private String brandName;
	private String productName;
	private String attributes;
	private Integer buyNum;
	private Integer cartId;
	private Integer pushFlag;
	private Integer masterId;
	private List<KeyValueDto> attributeValueList;

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

	public Integer getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(Integer buyNum) {
		this.buyNum = buyNum;
	}

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public String getLogoName() {
		return logoName;
	}

	public void setLogoName(String logoName) {
		this.logoName = logoName;
	}

	public String getProductTypeName() {
		return productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
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
