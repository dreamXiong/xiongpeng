package com.liguo.hgl.proxydao.model;

public class MyOrderStatesCount {
	
	//待确认状态
	private Integer staus200;
	//待付款状态
	private Integer staus202;
	//待补发货状态
	private Integer staus204;
	
	//待发货状态
	private Integer staus208;
	//待确认收货状态
	private Integer staus210;
	//待专家确认终止
	private Integer staus218;
	//交易完成状态
	private Integer staus220;
	
	
	public Integer getStaus208() {
		return staus208;
	}
	public void setStaus208(Integer staus208) {
		this.staus208 = staus208;
	}
	public Integer getStaus200() {
		return staus200;
	}
	public void setStaus200(Integer staus200) {
		this.staus200 = staus200;
	}
	public Integer getStaus202() {
		return staus202;
	}
	public void setStaus202(Integer staus202) {
		this.staus202 = staus202;
	}
	public Integer getStaus204() {
		return staus204;
	}
	public void setStaus204(Integer staus204) {
		this.staus204 = staus204;
	}
	public Integer getStaus210() {
		return staus210;
	}
	public void setStaus210(Integer staus210) {
		this.staus210 = staus210;
	}
	public Integer getStaus218() {
		return staus218;
	}
	public void setStaus218(Integer staus218) {
		this.staus218 = staus218;
	}
	public Integer getStaus220() {
		return staus220;
	}
	public void setStaus220(Integer staus220) {
		this.staus220 = staus220;
	}
}
