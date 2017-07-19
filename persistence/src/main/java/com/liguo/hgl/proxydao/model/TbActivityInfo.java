package com.liguo.hgl.proxydao.model;

import java.io.Serializable;
import java.util.Date;

public class TbActivityInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 自动增长的id
     */
    private Integer id;

    /**
     * 铺店id
     */
    private Integer shopId;

    /**
     * 活动名称
     */
    private String activityName;

    /**
     * 活动详情
     */
    private String activityDetail;

    /**
     * 动活开始日期
     */
    private Date startDate;

    /**
     * 动活结束日期
     */
    private Date endDate;

    /**
     * 动活开始时间
     */
    private Date startTime;

    /**
     * 动活结束时间
     */
    private Date endTime;

    /**
     * 否是显示倒计时(900:是,902:否)
     */
    private Integer displayBegin;

    /**
     * 否是显示剩余量(1000:是,1002:否)
     */
    private Integer displayRemaining;

    /**
     * 动活标题图片
     */
    private String titleImage;

    /**
     * 动活情详图片
     */
    private String image;

    /**
     * 动活状态(1100:是,1102:否)
     */
    private Integer status;

    /**
     * 动活创建人
     */
    private Integer createdBy;

    /**
     * 动活创建时间
     */
    private Long createdDate;

    /**
     * 本版ID
     */
    private Integer version;

    /**
     * @return 自动增长的id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id 
	 *            自动增长的id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return 铺店id
     */
    public Integer getShopId() {
        return shopId;
    }

    /**
     * @param shopId 
	 *            铺店id
     */
    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    /**
     * @return 活动名称
     */
    public String getActivityName() {
        return activityName;
    }

    /**
     * @param activityName 
	 *            活动名称
     */
    public void setActivityName(String activityName) {
        this.activityName = activityName == null ? null : activityName.trim();
    }

    /**
     * @return 活动详情
     */
    public String getActivityDetail() {
        return activityDetail;
    }

    /**
     * @param activityDetail 
	 *            活动详情
     */
    public void setActivityDetail(String activityDetail) {
        this.activityDetail = activityDetail == null ? null : activityDetail.trim();
    }

    /**
     * @return 动活开始日期
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate 
	 *            动活开始日期
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return 动活结束日期
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate 
	 *            动活结束日期
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return 动活开始时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * @param startTime 
	 *            动活开始时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * @return 动活结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @param endTime 
	 *            动活结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * @return 否是显示倒计时(900:是,902:否)
     */
    public Integer getDisplayBegin() {
        return displayBegin;
    }

    /**
     * @param displayBegin 
	 *            否是显示倒计时(900:是,902:否)
     */
    public void setDisplayBegin(Integer displayBegin) {
        this.displayBegin = displayBegin;
    }

    /**
     * @return 否是显示剩余量(1000:是,1002:否)
     */
    public Integer getDisplayRemaining() {
        return displayRemaining;
    }

    /**
     * @param displayRemaining 
	 *            否是显示剩余量(1000:是,1002:否)
     */
    public void setDisplayRemaining(Integer displayRemaining) {
        this.displayRemaining = displayRemaining;
    }

    /**
     * @return 动活标题图片
     */
    public String getTitleImage() {
		return titleImage;
	}

    /**
     * @param titleImage 
	 *            动活标题图片
     */
	public void setTitleImage(String titleImage) {
		this.titleImage = titleImage;
	}
    
    /**
     * @return 动活情详图片
     */
    public String getImage() {
        return image;
    }


	/**
     * @param image 
	 *            动活情详图片
     */
    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    /**
     * @return 动活状态(1100:是,1102:否)
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status 
	 *            动活状态(1100:是,1102:否)
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return 动活创建人
     */
    public Integer getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy 
	 *            动活创建人
     */
    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return 动活创建时间
     */
    public Long getCreatedDate() {
        return createdDate;
    }

    /**
     * @param createdDate 
	 *            动活创建时间
     */
    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * @return 本版ID
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * @param version 
	 *            本版ID
     */
    public void setVersion(Integer version) {
        this.version = version;
    }
}