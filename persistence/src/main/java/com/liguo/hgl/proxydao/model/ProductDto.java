package com.liguo.hgl.proxydao.model;

import java.util.List;


public class ProductDto extends TbProduct {
	
   private String productName;
   
   private List<OrderGroupDetailDto> orderDetailList ;
   //购买总数 用于合计
   private Integer buyNumCount;
   
   //购买金额 用于合计
   private double buyMoneyCount;
   
   private Integer productId;
   
   private String pImageOne;

   
   public Integer getBuyNumCount() {
		return buyNumCount;
	}
	
	public void setBuyNumCount(Integer buyNumCount) {
		this.buyNumCount = buyNumCount;
	}
	
	public double getBuyMoneyCount() {
		return buyMoneyCount;
	}
	
	public void setBuyMoneyCount(double buyMoneyCount) {
		this.buyMoneyCount = buyMoneyCount;
	}
	
	public List<OrderGroupDetailDto> getOrderDetailList() {
		return orderDetailList;
	}

	public void setOrderDetailList(List<OrderGroupDetailDto> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}

	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getpImageOne() {
		return pImageOne;
	}

	public void setpImageOne(String pImageOne) {
		this.pImageOne = pImageOne;
	}
	
}
