package com.liguo.hgl.proxydao.dto;

import com.liguo.hgl.proxydao.model.TbServiceType;


public class ServiceTypeDto extends TbServiceType{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer checked;
	
	private String skill;

	public Integer getChecked() {
		return checked;
	}

	public void setChecked(Integer checked) {
		this.checked = checked;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}	
}
