package com.liguo.hgl.proxydao.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderGroupDto extends TbOrderGroup {
    private static final long serialVersionUID = 1L;
	
	private String brandName;
	
	private Integer productId;
	
	private String productName;
	
	private String pImageOne;
	
	private Integer buyCount;
	
	private String userName;
	
	private String mobile;
	
	private Long from;
	
	private Long to;
	
	private String statusBefore; //修改之前的状态
	
	private List<ProductDto> productList ;
	
	private List<String> reissueImageList ;

 //订单明细的个数
   private Integer detailCount;
   
   
	public Integer getBuyCount() {
	return buyCount;
}

public void setBuyCount(Integer buyCount) {
	this.buyCount = buyCount;
}

	public Integer getDetailCount() {
		return detailCount;
	}
	
	public void setDetailCount(Integer detailCount) {
		this.detailCount = detailCount;
	}

	public List<ProductDto> getProductList() {
		return productList;
	}

	public void setProductList(List<ProductDto> productList) {
		this.productList = productList;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getpImageOne() {
		return pImageOne;
	}

	public void setpImageOne(String pImageOne) {
		this.pImageOne = pImageOne;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Long getFrom() {
		return from;
	}

	public void setFrom(Long from) {
		this.from = from;
	}

	public Long getTo() {
		return to;
	}

	public void setTo(Long to) {
		this.to = to;
	}

	public String getStatusBefore() {
		return statusBefore;
	}

	public void setStatusBefore(String statusBefore) {
		this.statusBefore = statusBefore;
	}

	public List<String> getReissueImageList() {
		String[] reissueImage = this.getReissueImage().split(",");
		setReissueImageList(Arrays.asList(reissueImage));
		return reissueImageList;
	}

	public void setReissueImageList(List<String> reissueImageList) {
		this.reissueImageList = reissueImageList;
	}
	
}
