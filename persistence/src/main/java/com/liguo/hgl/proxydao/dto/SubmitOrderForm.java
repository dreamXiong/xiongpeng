package com.liguo.hgl.proxydao.dto;

import java.io.Serializable;
import java.util.Map;

public class SubmitOrderForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cartIdList[];      //购物车ID(多个)
	private String buyMessageList[];  //用户给店铺留言信息(店铺ID=留言信息)
	private String addressId;         //收货地址ID
	private String payTypeList[];     //用户选择的支付类型(店铺ID=支付类型)
	private String goodsTypeList[];   //用户选择的收货方式(店铺ID=收货类型)
	private String distributionList[];//用户选择的折扣(店铺ID=折扣比例)
	private String recipient;		  //收货人
	private String phone;			  //收货人手机号
	private Integer recommendShopId;  //绑定的店铺ID
	private Map<Integer, CalulateFreightDto> calulateFreightMap; //存储邮费map
	
	public Integer getRecommendShopid() {
		return recommendShopId;
	}

	public void setRecommendShopid(Integer recommendShopId) {
		this.recommendShopId = recommendShopId;
	}

	public String[] getCartIdList() {
		return cartIdList;
	}

	public void setCartIdList(String[] cartIdList) {
		this.cartIdList = cartIdList;
	}

	public String[] getBuyMessageList() {
		return buyMessageList;
	}

	public void setBuyMessageList(String[] buyMessageList) {
		this.buyMessageList = buyMessageList;
	}

	public String getAddressId() {
		return addressId;
	}

	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	public String[] getPayTypeList() {
		return payTypeList;
	}

	public void setPayTypeList(String[] payTypeList) {
		this.payTypeList = payTypeList;
	}

	public String[] getGoodsTypeList() {
		return goodsTypeList;
	}

	public void setGoodsTypeList(String[] goodsTypeList) {
		this.goodsTypeList = goodsTypeList;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Map<Integer, CalulateFreightDto> getCalulateFreightMap() {
		return calulateFreightMap;
	}

	public void setCalulateFreightMap(
			Map<Integer, CalulateFreightDto> calulateFreightMap) {
		this.calulateFreightMap = calulateFreightMap;
	}

	public String[] getDistributionList() {
		return distributionList;
	}

	public void setDistributionList(String[] distributionList) {
		this.distributionList = distributionList;
	}

}
