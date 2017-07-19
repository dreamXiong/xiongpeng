package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbStatistical implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 统计id
     */
    private Integer id;

    /**
     * 招商id
     */
    private Integer merchansid;

    /**
     * 类型
     */
    private Integer type;

    /**
     * ip地址
     */
    private String ip;

    /**
     * 浏览器
     */
    private String browser;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 状态
     */
    private Integer state;

    /**
     * 预留字段int
     */
    private Integer reserved;

    /**
     * 预留字段String
     */
    private String reserved1;

    private Integer version;

    /**
     * @return 统计id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id 
	 *            统计id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return 招商id
     */
    public Integer getMerchansid() {
        return merchansid;
    }

    /**
     * @param merchansid 
	 *            招商id
     */
    public void setMerchansid(Integer merchansid) {
        this.merchansid = merchansid;
    }

    /**
     * @return 类型
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type 
	 *            类型
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * @return ip地址
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip 
	 *            ip地址
     */
    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    /**
     * @return 浏览器
     */
    public String getBrowser() {
        return browser;
    }

    /**
     * @param browser 
	 *            浏览器
     */
    public void setBrowser(String browser) {
        this.browser = browser == null ? null : browser.trim();
    }

    /**
     * @return 创建时间
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime 
	 *            创建时间
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    /**
     * @return 状态
     */
    public Integer getState() {
        return state;
    }

    /**
     * @param state 
	 *            状态
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * @return 预留字段int
     */
    public Integer getReserved() {
        return reserved;
    }

    /**
     * @param reserved 
	 *            预留字段int
     */
    public void setReserved(Integer reserved) {
        this.reserved = reserved;
    }

    /**
     * @return 预留字段String
     */
    public String getReserved1() {
        return reserved1;
    }

    /**
     * @param reserved1 
	 *            预留字段String
     */
    public void setReserved1(String reserved1) {
        this.reserved1 = reserved1 == null ? null : reserved1.trim();
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}