package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbFeedBack implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 动自增长的id
     */
    private Integer id;

    /**
     * 交提反馈信息人的id
     */
    private Integer userId;

    /**
     * 馈反信息
     */
    private String feedBack;

    /**
     * 建创时间
     */
    private Long createDt;

    /**
     * 本版id
     */
    private Integer version;

    /**
     * @return 动自增长的id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id 
	 *            动自增长的id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return 交提反馈信息人的id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId 
	 *            交提反馈信息人的id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return 馈反信息
     */
    public String getFeedBack() {
        return feedBack;
    }

    /**
     * @param feedBack 
	 *            馈反信息
     */
    public void setFeedBack(String feedBack) {
        this.feedBack = feedBack == null ? null : feedBack.trim();
    }

    /**
     * @return 建创时间
     */
    public Long getCreateDt() {
        return createDt;
    }

    /**
     * @param createDt 
	 *            建创时间
     */
    public void setCreateDt(Long createDt) {
        this.createDt = createDt;
    }

    /**
     * @return 本版id
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * @param version 
	 *            本版id
     */
    public void setVersion(Integer version) {
        this.version = version;
    }
}