package com.liguo.hgl.proxydao.dto;

import com.liguo.hgl.proxydao.model.TbIntegralMallRecord;

public class IntegralMallRecordDto extends TbIntegralMallRecord {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String goodsName;
	private String userName;
	private String recipient;
	private String phone;
	private String extensionField;

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getExtensionField() {
		return extensionField;
	}

	public void setExtensionField(String extensionField) {
		this.extensionField = extensionField;
	}

}
