package com.liguo.hgl.proxydao.dto;

import com.liguo.hgl.proxydao.model.TbWapOrderService;

public class WapOrderServiceDto extends  TbWapOrderService{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3097782694853228107L;
	
	private String userName;//用户名
	private String phone;//用户电话
	private String serciceName;//服务类型
	private String stateName;//订单状态
	private String place;//
	private String address;
	private double distance;
	private String serviceStatus;
	private Integer serciceId;//服务类型Id
	private Integer addressId;//wap_address 主键
	
	private String sfUserName;//师傅的名字
	private String sfPhone;//师傅电话号码
	
	private boolean customer=false;//是否是客户
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSerciceName() {
		return serciceName;
	}
	public void setSerciceName(String serciceName) {
		this.serciceName = serciceName;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public String getServiceStatus() {
		if(getOrderStatus().equals(800)  ){
			return "待接单";
		}else if(getOrderStatus() == 802){
			return "待服务";
		}else if(getOrderStatus() == 804){
			return "服务中";
		}else if(getOrderStatus() == 806){
			return "挂起中";
		}else if(getOrderStatus() == 808){
			return "待支付";
		}else if(getOrderStatus() == 810){
			return "待评价";
		}else if(getOrderStatus() == 812){
			return "完成";
		}else if(getOrderStatus() == 814){
			return "关闭";
		}else if(getOrderStatus() == 816){
			return "待确认";
		}
		return serviceStatus;
	}
	public void setServiceStatus(String serviceStatus) {
		this.serviceStatus = serviceStatus;
	}
	public Integer getSerciceId() {
		return serciceId;
	}
	public void setSerciceId(Integer serciceId) {
		this.serciceId = serciceId;
	}
	public Integer getAddressId() {
		return addressId;
	}
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}
	public String getSfUserName() {
		return sfUserName;
	}
	public void setSfUserName(String sfUserName) {
		this.sfUserName = sfUserName;
	}
	public String getSfPhone() {
		return sfPhone;
	}
	public void setSfPhone(String sfPhone) {
		this.sfPhone = sfPhone;
	}
	public boolean isCustomer() {
		return customer;
	}
	public void setCustomer(boolean customer) {
		this.customer = customer;
	}
	
	
	
	
	
	
	

}
