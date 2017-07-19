package com.liguo.hgl.proxydao.dto;

import com.liguo.hgl.proxydao.model.TbIntegralDetailed;

public class IntegralDetailedDto extends TbIntegralDetailed{
	
	private String typeString;

	private String dateString;
	
	private String dateTimeString;
	
	public String getDateString() {
		return dateString;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
	}

	public String getDateTimeString() {
		return dateTimeString;
	}

	public void setDateTimeString(String dateTimeString) {
		this.dateTimeString = dateTimeString;
	}

	public String getTypeString() {
		return typeString;
	}

	public void setTypeString(String typeString) {
		this.typeString = typeString;
	}
	
}
