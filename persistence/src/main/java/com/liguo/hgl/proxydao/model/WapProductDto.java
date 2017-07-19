package com.liguo.hgl.proxydao.model;

import java.util.List;



public class WapProductDto extends TbProduct {
	
	private List<ProductInventory> productInventoryList ;

	public List<ProductInventory> getProductInventoryList() {
		return productInventoryList;
	}

	public void setProductInventoryList(List<ProductInventory> productInventoryList) {
		this.productInventoryList = productInventoryList;
	}
	
	
	
}
