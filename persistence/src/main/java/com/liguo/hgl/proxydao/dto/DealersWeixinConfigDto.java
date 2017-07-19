package com.liguo.hgl.proxydao.dto;

import java.io.Serializable;

import com.liguo.hgl.proxydao.model.TbDealersWeixinConfig;

public class DealersWeixinConfigDto extends TbDealersWeixinConfig implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String shopName;

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

}
