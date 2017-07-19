package com.liguo.hgl.proxydao.dto;

import com.liguo.hgl.proxydao.model.TbCouponInfo;

public class CouponInfoDto extends TbCouponInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String from;
	private String to;
	private Double gainAmt; //优惠券收入
	private Double employAmt; //优惠券支出
	private Double remainingAmt; //优惠券剩余
	private Double currentGainAmt; //当前总收入
	private Double currentEmployAmt; //当前总支出
	
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
	public Double getRemainingAmt() {
		return remainingAmt;
	}
	public void setRemainingAmt(Double remainingAmt) {
		this.remainingAmt = remainingAmt;
	}
	public Double getGainAmt() {
		return gainAmt;
	}
	public void setGainAmt(Double gainAmt) {
		this.gainAmt = gainAmt;
	}
	public Double getEmployAmt() {
		return employAmt;
	}
	public void setEmployAmt(Double employAmt) {
		this.employAmt = employAmt;
	}
	public Double getCurrentGainAmt() {
		return currentGainAmt;
	}
	public void setCurrentGainAmt(Double currentGainAmt) {
		this.currentGainAmt = currentGainAmt;
	}
	public Double getCurrentEmployAmt() {
		return currentEmployAmt;
	}
	public void setCurrentEmployAmt(Double currentEmployAmt) {
		this.currentEmployAmt = currentEmployAmt;
	}
}
