package com.liguo.hgl.proxydao.dto;

import com.liguo.hgl.proxydao.model.TbProductType;

public class ProductTypeDto extends TbProductType {
	private Integer saveId;
	private String createdby;

	public Integer getSaveId() {
		return saveId;
	}

	public void setSaveId(Integer saveId) {
		this.saveId = saveId;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	
}
