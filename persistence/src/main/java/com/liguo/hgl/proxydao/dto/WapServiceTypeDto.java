package com.liguo.hgl.proxydao.dto;

import java.io.Serializable;
import java.util.List;

import com.liguo.hgl.proxydao.model.TbServiceType;

public class WapServiceTypeDto extends TbServiceType{
	
	private String tstName;
	
	private Integer tstId;
	
	private List<TbServiceType> wapServiceType;
	
	public List<TbServiceType> getWapServiceType() {
		return wapServiceType;
	}
	public void setWapServiceType(List<TbServiceType> wapServiceType) {
		this.wapServiceType = wapServiceType;
	}
	public String getTstName() {
		return tstName;
	}
	public void setTstName(String tstName) {
		this.tstName = tstName;
	}
	public Integer getTstId() {
		return tstId;
	}
	public void setTstId(Integer tstId) {
		this.tstId = tstId;
	}
}
