package com.liguo.hgl.proxydao.dto;

import java.io.Serializable;
/**
 * 普通用户的消息列表
 * @fiUserLetterDto.java
 * @2016-7-4	
 * @author 周双双
 */
public class UserLetterDto  implements Serializable{


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8868738291249041025L;
	
	private Integer userId;
	private String userName;
	private Integer shopId;
	private String shopName;
	private Integer letterCount;
	private String shopImage;//店铺头像图片
	public Integer getShopId() {
		return shopId;
	}
	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public Integer getLetterCount() {
		return letterCount;
	}
	public void setLetterCount(Integer letterCount) {
		this.letterCount = letterCount;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getShopImage() {
		return shopImage;
	}
	public void setShopImage(String shopImage) {
		this.shopImage = shopImage;
	}
	
	
	
	
	
	
	

}
