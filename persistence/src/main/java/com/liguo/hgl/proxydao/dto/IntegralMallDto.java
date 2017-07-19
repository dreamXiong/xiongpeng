package com.liguo.hgl.proxydao.dto;

import com.liguo.hgl.proxydao.model.TbIntegralMall;

public class IntegralMallDto extends TbIntegralMall{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String goodsTypeName;

	public String getGoodsTypeName() {
		return goodsTypeName;
	}

	public void setGoodsTypeName(String goodsTypeName) {
		this.goodsTypeName = goodsTypeName;
	}

	
	
}
