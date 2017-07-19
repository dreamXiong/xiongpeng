package com.liguo.hgl.proxydao.dto;

import com.liguo.hgl.proxydao.model.TbAdminUser;

public class TbAdminUserDto extends TbAdminUser {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String roleName;
	
	private String passwordRadio; //找回密码的方式

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getPasswordRadio() {
		return passwordRadio;
	}

	public void setPasswordRadio(String passwordRadio) {
		this.passwordRadio = passwordRadio;
	}
	
}
