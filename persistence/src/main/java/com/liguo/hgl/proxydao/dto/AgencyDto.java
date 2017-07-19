package com.liguo.hgl.proxydao.dto;

import com.liguo.hgl.proxydao.model.TbAgency;

public class AgencyDto extends TbAgency{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8959301321708520262L;
	private String shopName; 
	private String stateName;
	private String levelName;
	private String palceName;
	private String couponsStateName;
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getStateName() {
		if(this.getState() == 0){
			return "未生效";
		}else if(this.getState()==1){
			return "暂时生效";
		}else if(this.getExpLevel()==2){
			return "生效";
		}else if(this.getExpLevel() == 3){
			return "失效";
		}
		
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getLevelName() {
		if(this.getExpLevel()==1){
			return "省";
		}else if(this.getExpLevel()==2){
			return "市";
		}else if(this.getExpLevel() == 3){
			return "区县";
		}else if(this.getExpLevel() ==4){
			return "街道";
		}
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	public String getPalceName() {
		return palceName;
	}
	public void setPalceName(String palceNmae) {
		this.palceName = palceNmae;
	}
	public String getCouponsStateName() {
		return couponsStateName;
	}
	public void setCouponsStateName(String couponsStateName) {
		this.couponsStateName = couponsStateName;
	}
	
	
}
