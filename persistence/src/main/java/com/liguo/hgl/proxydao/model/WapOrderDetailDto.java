package com.liguo.hgl.proxydao.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class WapOrderDetailDto extends TbWapOrderDetail {
	
	private String brandName;
	
	private String productName;
	
	private String material;
	
	private String spec;
	
	private String attributesValues;
	
	private String attributes;
	
	private String balance;
	
	private String pImgOne;
	
	private Integer productId;
	
	private Integer inventoryId;
	
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public Integer getInventoryId() {
		return inventoryId;
	}
	public void setInventoryId(Integer inventoryId) {
		this.inventoryId = inventoryId;
	}
	private List<Map<String,String>> attrList;
	
	public List<Map<String, String>> getAttrList() {
		return attrList;
	}
	public void setAttrList(List<Map<String, String>> attrList) {
		this.attrList = attrList;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getpImgOne() {
		return pImgOne;
	}
	public void setpImgOne(String pImgOne) {
		this.pImgOne = pImgOne;
	}
	public String getAttributesValues() {
		return attributesValues;
	}
	public void setAttributesValues(String attributesValues) {
		this.attributesValues = attributesValues;
	}
	public String getAttributes() {
		return attributes;
	}
	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
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
	
}
