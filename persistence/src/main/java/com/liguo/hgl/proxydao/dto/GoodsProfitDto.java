package com.liguo.hgl.proxydao.dto;

import java.io.Serializable;

import com.liguo.hgl.proxydao.model.TbGoodsProfit;

public class GoodsProfitDto extends TbGoodsProfit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer saleNumDay;
	private Integer startCount;
	private String yearName;

	public Integer getSaleNumDay() {
		return saleNumDay;
	}

	public void setSaleNumDay(Integer saleNumDay) {
		this.saleNumDay = saleNumDay;
	}

	public Integer getStartCount() {
		return startCount;
	}

	public void setStartCount(Integer startCount) {
		this.startCount = startCount;
	}

	public String getYearName() {
		return yearName;
	}

	public void setYearName(String yearName) {
		this.yearName = yearName;
	}

}
