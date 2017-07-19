package com.liguo.hgl.dto;

import java.util.List;

public class MyOrderForm {
	
	private String startTime;
	
	private String endTime;
	
	private Integer orderType;
	
	private String orderState;
	
	private String searchText;
	
	private Integer shopId;
	
	private Integer version;
	
	private Integer orderGroupId;
	
	private List<String> orderStateList;
	
	private List<Integer> shopIdList;
	
	private List<Integer> warehouseIdList;
	
	private String stopReason;
	
	private String images;
	
	private Integer userId;
	
	private double lon;
	
	private double lat;
	
	private String saleOrderFlag;
	
	public String getSaleOrderFlag() {
		return saleOrderFlag;
	}
	public void setSaleOrderFlag(String saleOrderFlag) {
		this.saleOrderFlag = saleOrderFlag;
	}
	public double getLon() {
		return lon;
	}
	public void setLon(double lon) {
		this.lon = lon;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public List<Integer> getWarehouseIdList() {
		return warehouseIdList;
	}
	public void setWarehouseIdList(List<Integer> warehouseIdList) {
		this.warehouseIdList = warehouseIdList;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public String getStopReason() {
		return stopReason;
	}
	public void setStopReason(String stopReason) {
		this.stopReason = stopReason;
	}
	public List<Integer> getShopIdList() {
		return shopIdList;
	}
	public void setShopIdList(List<Integer> shopIdList) {
		this.shopIdList = shopIdList;
	}
	public Integer getShopId() {
		return shopId;
	}
	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}
	public List<String> getOrderStateList() {
		return orderStateList;
	}
	public void setOrderStateList(List<String> orderStateList) {
		this.orderStateList = orderStateList;
	}
	public Integer getOrderGroupId() {
		return orderGroupId;
	}
	public void setOrderGroupId(Integer orderGroupId) {
		this.orderGroupId = orderGroupId;
	}
	/*public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}*/
	public Integer getOrderType() {
		return orderType;
	}
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	public String getOrderState() {
		return orderState;
	}
	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	public String getSearchText() {
		return searchText;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	/**
	 * 漏发图片
	 * @return
	 * @author zss
	 */
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	
	
}
