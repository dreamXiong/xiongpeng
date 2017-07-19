package com.liguo.hgl.proxydao.model;

import java.util.List;


public class TbProductTestDto extends TbProduct {

	private List<TbProductInventory> orderList ;

	public List<TbProductInventory> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<TbProductInventory> orderList) {
		this.orderList = orderList;
	}
	
	
}
