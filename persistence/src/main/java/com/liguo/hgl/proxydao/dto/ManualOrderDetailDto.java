package com.liguo.hgl.proxydao.dto;

import com.liguo.hgl.proxydao.model.TbManualorderDetail;

public class ManualOrderDetailDto extends TbManualorderDetail {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer orderId;  		//订单id号
	private String orderSerialNo; //订单流水号
	private String userName;
	private String from;
	private String to;
	
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getOrderSerialNo() {
		return orderSerialNo;
	}
	public void setOrderSerialNo(String orderSerialNo) {
		this.orderSerialNo = orderSerialNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	
}
