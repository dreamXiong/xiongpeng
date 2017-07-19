package com.liguo.hgl.proxydao.dto;

import com.liguo.hgl.proxydao.model.TbShopInfo;
import com.liguo.hgl.proxydao.util.Constant;

public class ShopWebUserDto extends TbShopInfo{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4058102083990044145L;
	private String userId;
	private String mobile;
	private int state;
	private String brandName;
	private String productType;
	private String address;
	private String userName;
	private String stateName;
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getStateName() {
		if(this.getAuthStatus()== Constant.WEB_AUTH_STATUS){
			return "待审核";
		}else if(this.getAuthStatus()==Constant.WEB_AUTH_STATUS1){
			return "审核通过";
		}else if(this.getAuthStatus()==Constant.WEB_AUTH_STATUS2){return "审核拒绝";
		}else if(this.getAuthStatus()==Constant.WEB_AUTH_STATUS3){return "关闭";
		} else{return "";}
		
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
