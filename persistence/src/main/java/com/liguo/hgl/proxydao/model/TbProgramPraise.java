package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbProgramPraise implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;

    /**
     * 节目id
     */
    private Integer programId;

    /**
     * 微信id
     */
    private String openId;

    /**
     * 微信号
     */
    private String weixinNum;

    /**
     * 微信名称
     */
    private String weixinName;

    /**
     * 微信头像
     */
    private String weixinHeadImage;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 是否删除
     */
    private Integer isDelete;

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
     * @return 节目id
     */
    public Integer getProgramId() {
        return programId;
    }

    /**
     * @param programId 
	 *            节目id
     */
    public void setProgramId(Integer programId) {
        this.programId = programId;
    }

    /**
     * @return 微信id
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * @param openId 
	 *            微信id
     */
    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    /**
     * @return 微信号
     */
    public String getWeixinNum() {
        return weixinNum;
    }

    /**
     * @param weixinNum 
	 *            微信号
     */
    public void setWeixinNum(String weixinNum) {
        this.weixinNum = weixinNum == null ? null : weixinNum.trim();
    }

    /**
     * @return 微信名称
     */
    public String getWeixinName() {
        return weixinName;
    }

    /**
     * @param weixinName 
	 *            微信名称
     */
    public void setWeixinName(String weixinName) {
        this.weixinName = weixinName == null ? null : weixinName.trim();
    }

    /**
     * @return 微信头像
     */
    public String getWeixinHeadImage() {
        return weixinHeadImage;
    }

    /**
     * @param weixinHeadImage 
	 *            微信头像
     */
    public void setWeixinHeadImage(String weixinHeadImage) {
        this.weixinHeadImage = weixinHeadImage == null ? null : weixinHeadImage.trim();
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
        TbProgramPraise other = (TbProgramPraise) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getProgramId() == null ? other.getProgramId() == null : this.getProgramId().equals(other.getProgramId()))
            && (this.getOpenId() == null ? other.getOpenId() == null : this.getOpenId().equals(other.getOpenId()))
            && (this.getWeixinNum() == null ? other.getWeixinNum() == null : this.getWeixinNum().equals(other.getWeixinNum()))
            && (this.getWeixinName() == null ? other.getWeixinName() == null : this.getWeixinName().equals(other.getWeixinName()))
            && (this.getWeixinHeadImage() == null ? other.getWeixinHeadImage() == null : this.getWeixinHeadImage().equals(other.getWeixinHeadImage()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getProgramId() == null) ? 0 : getProgramId().hashCode());
        result = prime * result + ((getOpenId() == null) ? 0 : getOpenId().hashCode());
        result = prime * result + ((getWeixinNum() == null) ? 0 : getWeixinNum().hashCode());
        result = prime * result + ((getWeixinName() == null) ? 0 : getWeixinName().hashCode());
        result = prime * result + ((getWeixinHeadImage() == null) ? 0 : getWeixinHeadImage().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        return result;
    }
}