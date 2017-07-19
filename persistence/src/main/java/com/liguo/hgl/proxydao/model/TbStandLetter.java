package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbStandLetter implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;

    /**
     * 用户Id
     */
    private Integer userId;

    /**
     * 0 发送，1接受
     */
    private Integer letterType;

    /**
     * 对方用户id
     */
    private String recipientid;

    /**
     * 对方用户名
     */
    private String recipientname;

    /**
     * null 普通用户，大于0 ，经销商
     */
    private Integer shopid;

    /**
     * 类型0文字，1活动
     */
    private Integer type;

    /**
     * 内容
     */
    private String content;

    /**
     * 活动Id
     */
    private Integer activityId;

    /**
     * 预留
     */
    private Integer reserved;

    /**
     * 预留
     */
    private String reservedstr;

    /**
     * 创建时间
     */
    private Long createDt;

    /**
     * 版本
     */
    private Integer version;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id 
	 *            id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return 用户Id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId 
	 *            用户Id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return 0 发送，1接受
     */
    public Integer getLetterType() {
        return letterType;
    }

    /**
     * @param letterType 
	 *            0 发送，1接受
     */
    public void setLetterType(Integer letterType) {
        this.letterType = letterType;
    }

    /**
     * @return 对方用户id
     */
    public String getRecipientid() {
        return recipientid;
    }

    /**
     * @param recipientid 
	 *            对方用户id
     */
    public void setRecipientid(String recipientid) {
        this.recipientid = recipientid == null ? null : recipientid.trim();
    }

    /**
     * @return 对方用户名
     */
    public String getRecipientname() {
        return recipientname;
    }

    /**
     * @param recipientname 
	 *            对方用户名
     */
    public void setRecipientname(String recipientname) {
        this.recipientname = recipientname == null ? null : recipientname.trim();
    }

    /**
     * @return null 普通用户，大于0 ，经销商
     */
    public Integer getShopid() {
        return shopid;
    }

    /**
     * @param shopid 
	 *            null 普通用户，大于0 ，经销商
     */
    public void setShopid(Integer shopid) {
        this.shopid = shopid;
    }

    /**
     * @return 类型0文字，1活动
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type 
	 *            类型0文字，1活动
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * @return 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content 
	 *            内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * @return 活动Id
     */
    public Integer getActivityId() {
        return activityId;
    }

    /**
     * @param activityId 
	 *            活动Id
     */
    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    /**
     * @return 预留
     */
    public Integer getReserved() {
        return reserved;
    }

    /**
     * @param reserved 
	 *            预留
     */
    public void setReserved(Integer reserved) {
        this.reserved = reserved;
    }

    /**
     * @return 预留
     */
    public String getReservedstr() {
        return reservedstr;
    }

    /**
     * @param reservedstr 
	 *            预留
     */
    public void setReservedstr(String reservedstr) {
        this.reservedstr = reservedstr == null ? null : reservedstr.trim();
    }

    /**
     * @return 创建时间
     */
    public Long getCreateDt() {
        return createDt;
    }

    /**
     * @param createDt 
	 *            创建时间
     */
    public void setCreateDt(Long createDt) {
        this.createDt = createDt;
    }

    /**
     * @return 版本
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * @param version 
	 *            版本
     */
    public void setVersion(Integer version) {
        this.version = version;
    }
}