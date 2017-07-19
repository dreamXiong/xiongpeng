package com.liguo.hgl.proxydao.dto;

import java.util.List;

import com.liguo.hgl.proxydao.model.TbWapOrderServiceDetail;

public class WapOrderServiceDetailDto extends TbWapOrderServiceDetail {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String pImgOne;
	
	private String attributes;
	
	private List<Attribute> attrs;


	public String getpImgOne() {
		return pImgOne;
	}

	public void setpImgOne(String pImgOne) {
		this.pImgOne = pImgOne;
	}

	public String getAttributes() {
		return attributes;
	}

	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}
	
	public List<Attribute> getAttrs() {
		return attrs;
	}

	public void setAttrs(List<Attribute> attrs) {
		this.attrs = attrs;
	}	
		
}
