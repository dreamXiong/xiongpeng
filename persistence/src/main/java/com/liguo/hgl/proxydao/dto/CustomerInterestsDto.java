package com.liguo.hgl.proxydao.dto;

import java.io.Serializable;

import com.liguo.hgl.proxydao.model.TbCustomerInterests;

public class CustomerInterestsDto extends TbCustomerInterests implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer startCount;

	public Integer getStartCount() {
		return startCount;
	}

	public void setStartCount(Integer startCount) {
		this.startCount = startCount;
	}

}
