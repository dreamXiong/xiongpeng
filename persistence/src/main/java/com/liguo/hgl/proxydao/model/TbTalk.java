package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbTalk implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;

    /**
     * 微信用户表Id
     */
    private Integer weixinUserId;

    /**
     * 用户的标识，对当前公众号唯一 
     */
    private String openId;

    /**
     * 是否删除
     */
    private Integer isDelete;

    /**
     * 是否可展示
     */
    private Integer isShow;

    /**
     * 消息
     */
    private String message;

    /**
     * 创建时间
     */
    private String createTime;

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
     * @return 微信用户表Id
     */
    public Integer getWeixinUserId() {
        return weixinUserId;
    }

    /**
     * @param weixinUserId 
	 *            微信用户表Id
     */
    public void setWeixinUserId(Integer weixinUserId) {
        this.weixinUserId = weixinUserId;
    }

    /**
     * @return 用户的标识，对当前公众号唯一 
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * @param openId 
	 *            用户的标识，对当前公众号唯一 
     */
    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    /**
     * @return 是否删除
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    /**
     * @param isDelete 
	 *            是否删除
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * @return 是否可展示
     */
    public Integer getIsShow() {
        return isShow;
    }

    /**
     * @param isShow 
	 *            是否可展示
     */
    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    /**
     * @return 消息
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message 
	 *            消息
     */
    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TbTalk other = (TbTalk) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getWeixinUserId() == null ? other.getWeixinUserId() == null : this.getWeixinUserId().equals(other.getWeixinUserId()))
            && (this.getOpenId() == null ? other.getOpenId() == null : this.getOpenId().equals(other.getOpenId()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()))
            && (this.getIsShow() == null ? other.getIsShow() == null : this.getIsShow().equals(other.getIsShow()))
            && (this.getMessage() == null ? other.getMessage() == null : this.getMessage().equals(other.getMessage()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getWeixinUserId() == null) ? 0 : getWeixinUserId().hashCode());
        result = prime * result + ((getOpenId() == null) ? 0 : getOpenId().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        result = prime * result + ((getIsShow() == null) ? 0 : getIsShow().hashCode());
        result = prime * result + ((getMessage() == null) ? 0 : getMessage().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        return result;
    }
}