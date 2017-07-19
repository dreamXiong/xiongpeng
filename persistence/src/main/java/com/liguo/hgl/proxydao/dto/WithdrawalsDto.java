package com.liguo.hgl.proxydao.dto;

import com.liguo.hgl.proxydao.model.TbWithdrawals;

public class WithdrawalsDto extends TbWithdrawals{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4797460857199434257L;
	
	private String userName;
	//private String realName;
	private String bankNo;//银行账号
	//private String bankName;//开会行
	private String branch;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getBankNo() {
		return bankNo;
	}
	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	
	
	
	

}
