package com.liguo.hgl.proxydao.dto;

import java.io.Serializable;

import com.liguo.hgl.proxydao.model.TbWapOrderGroup;

public class OrderGroupDetailDto extends TbWapOrderGroup implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String recipient;
	private String phone;
	private String extensionField;

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
