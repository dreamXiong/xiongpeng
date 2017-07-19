package com.liguo.hgl.proxydao.dto;

import com.liguo.hgl.proxydao.model.TbWebUser;

public class UserRecommendedDto extends TbWebUser{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6434971918012842508L;
	private int type;//类型
	private double  earnings;//收益
	private Long createDate;//
	private String unit;//单位
	private double firstMoney;//首单金额
	private String userIdentity;//用户身份
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	public String getUnit() {
		if(getEarnings()>0){
			if(this.getType()==0){
				return (int)getEarnings()+" 积分";
			}else if(this.getType()==1){
				return getEarnings()+" 元";
			}
		}
		return "-";
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public double getEarnings() {
		return earnings;
	}
	public void setEarnings(double earnings) {
		this.earnings = earnings;
	}
	public Long getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Long createDate) {
		this.createDate = createDate;
	}
	public double getFirstMoney() {
		return firstMoney;
	}
	public void setFirstMoney(double firstMoney) {
		this.firstMoney = firstMoney;
	}
	public String getUserIdentity() {
		if(this.getTypeId()==104){
			return "经销商";
		}else if(this.getTypeId()==106){
			return "终端客户";
		}else if(this.getTypeId()==114){
			return "师傅";
		}else if(this.getTypeId()==102){
			return "厂家";
		}
		
		return userIdentity;
	}
	public void setUserIdentity(String userIdentity) {
		this.userIdentity = userIdentity;
	}

	
	
}
