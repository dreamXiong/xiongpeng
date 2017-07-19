package com.liguo.hgl.proxydao.dto;

import com.liguo.hgl.proxydao.model.TbCashAccount;

public class CashAccountDto extends TbCashAccount {
	
	private String platformString;
	
	private String cashString;

	private String dateString;
	
	private String dateTimeString;
	
	
	public String getDateTimeString() {
		return dateTimeString;
	}

	public void setDateTimeString(String dateTimeString) {
		this.dateTimeString = dateTimeString;
	}

	public String getDateString() {
		return dateString;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
	}

	public String getPlatformString() {
		return platformString;
	}

	public void setPlatformString(String platformString) {
		this.platformString = platformString;
	}

	public String getCashString() {
		return cashString;
	}

	public void setCashString(String cashString) {
		this.cashString = cashString;
	}
	
}
