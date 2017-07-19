package com.liguo.hgl.dto;

import com.liguo.hgl.proxydao.model.TbProduct;

public class ProductDto extends TbProduct {
	
	/*二级分类ID*/
	private Integer parentId;
	/*三级分类ID*/
	private Integer thirdType;
	
	public Integer getThirdType() {
		return thirdType;
	}
	public void setThirdType(Integer thirdType) {
		this.thirdType = thirdType;
	}
	
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	
	

}
