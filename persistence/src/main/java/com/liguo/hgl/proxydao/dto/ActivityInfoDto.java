package com.liguo.hgl.proxydao.dto;

import com.liguo.hgl.proxydao.model.TbActivityInfo;

public class ActivityInfoDto extends TbActivityInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String startYear;
	private String startMonth;
	private String startDay;
	private String startHour;
	private String startMin;
	private String endYear;
	private String endMonth;
	private String endDay;
	private String endHour;
	private String endMin;
	private String titleImage;
	private String detailImageOne;
	private String detailImageTwo;
	private String detailImageThree;
	
	public String getStartYear() {
		return startYear;
	}
	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}
	public String getStartMonth() {
		return startMonth;
	}
	public void setStartMonth(String startMonth) {
		this.startMonth = startMonth;
	}	
	public String getStartDay() {
		return startDay;
	}
	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}
	public String getStartHour() {
		return startHour;
	}
	public void setStartHour(String startHour) {
		this.startHour = startHour;
	}
	public String getStartMin() {
		return startMin;
	}
	public void setStartMin(String startMin) {
		this.startMin = startMin;
	}
	public String getEndYear() {
		return endYear;
	}
	public void setEndYear(String endYear) {
		this.endYear = endYear;
	}
	public String getEndMonth() {
		return endMonth;
	}
	public void setEndMonth(String endMonth) {
		this.endMonth = endMonth;
	}	
	public String getEndDay() {
		return endDay;
	}
	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}
	public String getEndHour() {
		return endHour;
	}
	public void setEndHour(String endHour) {
		this.endHour = endHour;
	}
	public String getEndMin() {
		return endMin;
	}
	public void setEndMin(String endMin) {
		this.endMin = endMin;
	}
	public String getTitleImage() {
		return titleImage;
	}
	public void setTitleImage(String titleImage) {
		this.titleImage = titleImage;
	}
	public String getDetailImageOne() {
		return detailImageOne;
	}
	public void setDetailImageOne(String detailImageOne) {
		this.detailImageOne = detailImageOne;
	}
	public String getDetailImageTwo() {
		return detailImageTwo;
	}
	public void setDetailImageTwo(String detailImageTwo) {
		this.detailImageTwo = detailImageTwo;
	}
	public String getDetailImageThree() {
		return detailImageThree;
	}
	public void setDetailImageThree(String detailImageThree) {
		this.detailImageThree = detailImageThree;
	}
		
}
