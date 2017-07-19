package com.liguo.hgl.proxydao.dto;

import com.liguo.hgl.proxydao.model.TbStandLetter;

public class LetterActivityDto extends TbStandLetter{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7522950013931448042L;
	
	private Integer activityId;
	private String activityName;
	private String userNames;
	public Integer getActivityId() {
		return activityId;
	}
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public String getUserNames() {
		String[] userArray = this.getRecipientname().split(",");
		if(userArray.length<= 3){
			return this.getRecipientname();
		}else{
			String names="";
			 for (int i = 0; i <userArray.length; i++) {
				 if(i<3){
					 names=names+userArray[i]+",";
				 }
			 }
			 return names+"...等"+userArray.length+"人";
		}
	}
	public void setUserNames(String userNames) {
		this.userNames = userNames;
	}
	
	

}
