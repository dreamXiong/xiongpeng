package com.liguo.hgl.proxydao.model;

import java.io.Serializable;


public class OrderGroupDetailDto extends TbOrderDetail implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer orderDetailId;
	
	private String orderSerialNo; //订单流水号
	
	private Integer buyNum;
	
	private Double money;
	
	private Double price;
	
	private Double detailPayMoney;
	
	private String code;
	
	private Integer oneboxCount;
	
	private Integer outstockPrice;
	
	private String spec;
	
	private String material;
	
	private String meterageUnit;
	
	private Integer inventoryId;
	
	private Double totalAmount; //总金额
	
	
	public Integer getOrderDetailId() {
		return orderDetailId;
	}
	public void setOrderDetailId(Integer orderDetailId) {
		this.orderDetailId = orderDetailId;
	}
		
	public String getOrderSerialNo() {
		return orderSerialNo;
	}
	public void setOrderSerialNo(String orderSerialNo) {
		this.orderSerialNo = orderSerialNo;
	}
	public Integer getInventoryId() {
		return inventoryId;
	}
	public void setInventoryId(Integer inventoryId) {
		this.inventoryId = inventoryId;
	}
	public String getMeterageUnit() {
		return meterageUnit;
	}
	public void setMeterageUnit(String meterageUnit) {
		this.meterageUnit = meterageUnit;
	}
	public Double getDetailPayMoney() {
		return detailPayMoney;
	}
	public void setDetailPayMoney(Double detailPayMoney) {
		this.detailPayMoney = detailPayMoney;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public Integer getBuyNum() {
		return buyNum;
	}
	public void setBuyNum(Integer buyNum) {
		this.buyNum = buyNum;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getOneboxCount() {
		return oneboxCount;
	}
	public void setOneboxCount(Integer oneboxCount) {
		this.oneboxCount = oneboxCount;
	}
	public Integer getOutstockPrice() {
		return outstockPrice;
	}
	public void setOutstockPrice(Integer outstockPrice) {
		this.outstockPrice = outstockPrice;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}	
}
