package com.liguo.hgl.proxydao.model;

public class CustomerServiceDto extends TbCustomerService {
	
	private String tstName;
	
	private String bName;

	public String getbName() {
		return bName;
	}

	public void setbName(String bName) {
		this.bName = bName;
	}

	public String getTstName() {
		return tstName;
	}

	public void setTstName(String tstName) {
		this.tstName = tstName;
	}
	
	
}
