package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbWeixinPassUser implements Serializable {
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
     * 用户的昵称
     */
    private String nickname;

    /**
     *   用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效
     */
    private String headimgurl;

    /**
     * 是否删除
     */
    private Integer isDelete;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 获得的奖项
     */
    private Integer winGrade;

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
     * @return 用户的昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname 
	 *            用户的昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    /**
     * @return   用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效
     */
    public String getHeadimgurl() {
        return headimgurl;
    }

    /**
     * @param headimgurl 
	 *              用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效
     */
    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl == null ? null : headimgurl.trim();
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
     * @return 获得的奖项
     */
    public Integer getWinGrade() {
        return winGrade;
    }

    /**
     * @param winGrade 
	 *            获得的奖项
     */
    public void setWinGrade(Integer winGrade) {
        this.winGrade = winGrade;
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
        TbWeixinPassUser other = (TbWeixinPassUser) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getWeixinUserId() == null ? other.getWeixinUserId() == null : this.getWeixinUserId().equals(other.getWeixinUserId()))
            && (this.getOpenId() == null ? other.getOpenId() == null : this.getOpenId().equals(other.getOpenId()))
            && (this.getNickname() == null ? other.getNickname() == null : this.getNickname().equals(other.getNickname()))
            && (this.getHeadimgurl() == null ? other.getHeadimgurl() == null : this.getHeadimgurl().equals(other.getHeadimgurl()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getWinGrade() == null ? other.getWinGrade() == null : this.getWinGrade().equals(other.getWinGrade()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getWeixinUserId() == null) ? 0 : getWeixinUserId().hashCode());
        result = prime * result + ((getOpenId() == null) ? 0 : getOpenId().hashCode());
        result = prime * result + ((getNickname() == null) ? 0 : getNickname().hashCode());
        result = prime * result + ((getHeadimgurl() == null) ? 0 : getHeadimgurl().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getWinGrade() == null) ? 0 : getWinGrade().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        return result;
    }
}