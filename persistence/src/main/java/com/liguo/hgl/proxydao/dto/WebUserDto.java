package com.liguo.hgl.proxydao.dto;

import java.util.List;

import com.liguo.hgl.proxydao.model.TbWebUser;

public class WebUserDto extends TbWebUser {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4384552034347801664L;
	private String test;
	/** 经验折算规则	 */
	private Double expProportion;
	/** 用户优惠折扣*/
	private Double userSale;
	/** 提现次数 */
	private Integer cashNum;
	/**等级名称 */
	private String levelName;
	/**等级 */
	private Integer expLevel;
	/**优惠卷使用规则 */
	private Double couponRule;
	/** 积分主表ID */
	private Integer integralId;
	/** 帐号主表ID */
	private Integer accountId;	
	/**出生年*/
	private Integer birthYear;
	
	/**出生月*/
	private Integer birthMonth;
	
	/**出生日*/
	private Integer birthDay;
	
	/**性别*/
	private Integer gender;
	
	/**旧密码*/
	private String oldPassword;
	
	/**角色名称*/
	private String roleName;
	
	/**师傅的技能*/
	private String skill;
	
	/**注册用户详情信息*/
	private String feedBack;
	
	private List<Integer> groupIdList;

	public List<Integer> getGroupIdList() {
		return groupIdList;
	}

	public void setGroupIdList(List<Integer> groupIdList) {
		this.groupIdList = groupIdList;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

	public Double getExpProportion() {
		return expProportion;
	}

	public void setExpProportion(Double expProportion) {
		this.expProportion = expProportion;
	}

	public Double getUserSale() {
		return userSale;
	}

	public void setUserSale(Double userSale) {
		this.userSale = userSale;
	}

	public Integer getCashNum() {
		return cashNum;
	}

	public void setCashNum(Integer cashNum) {
		this.cashNum = cashNum;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public Integer getExpLevel() {
		return expLevel;
	}

	public void setExpLevel(Integer expLevel) {
		this.expLevel = expLevel;
	}
	
	public Double getCouponRule() {
		return couponRule;
	}

	public void setCouponRule(Double couponRule) {
		this.couponRule = couponRule;
	}

	public Integer getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(Integer birthYear) {
		this.birthYear = birthYear;
	}

	public Integer getBirthMonth() {
		return birthMonth;
	}

	public void setBirthMonth(Integer birthMonth) {
		this.birthMonth = birthMonth;
	}

	public Integer getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Integer birthDay) {
		this.birthDay = birthDay;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public String getFeedBack() {
		return feedBack;
	}

	public void setFeedBack(String feedBack) {
		this.feedBack = feedBack;
	}

	public Integer getIntegralId() {
		return integralId;
	}

	public void setIntegralId(Integer integralId) {
		this.integralId = integralId;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

}
