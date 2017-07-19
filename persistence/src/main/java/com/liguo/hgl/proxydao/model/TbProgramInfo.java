package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbProgramInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 节目id
     */
    private Integer id;

    /**
     * 节目名称
     */
    private String name;

    /**
     * 节目标题
     */
    private String title;

    /**
     * 节目说明
     */
    private String remark;

    /**
     * 节目图片
     */
    private String image;

    /**
     * 关键字
     */
    private String keyWord;

    /**
     * 节目是否可以点赞标志
     */
    private Integer praiseFlag;

    
    /**
     * 节目获得的赞数
     */
    private Integer praise;
    
    private Integer version;

    /**
     * @return 节目id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id 
	 *            节目id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return 节目名称
     */
    public String getName() {
        return name;
    }

    /**
     * @param name 
	 *            节目名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return 节目标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title 
	 *            节目标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * @return 节目说明
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark 
	 *            节目说明
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * @return 节目图片
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image 
	 *            节目图片
     */
    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    /**
     * @return 关键字
     */
    public String getKeyWord() {
        return keyWord;
    }

    /**
     * @param keyWord 
	 *            关键字
     */
    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord == null ? null : keyWord.trim();
    }

    /**
     * @return 节目获得的赞数
     */
    public Integer getPraise() {
        return praise;
    }

    /**
     * @param praise 
	 *            节目获得的赞数
     */
    public void setPraise(Integer praise) {
        this.praise = praise;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getPraiseFlag() {
		return praiseFlag;
	}

	public void setPraiseFlag(Integer praiseFlag) {
		this.praiseFlag = praiseFlag;
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
        TbProgramInfo other = (TbProgramInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getImage() == null ? other.getImage() == null : this.getImage().equals(other.getImage()))
            && (this.getKeyWord() == null ? other.getKeyWord() == null : this.getKeyWord().equals(other.getKeyWord()))
            && (this.getPraise() == null ? other.getPraise() == null : this.getPraise().equals(other.getPraise()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getImage() == null) ? 0 : getImage().hashCode());
        result = prime * result + ((getKeyWord() == null) ? 0 : getKeyWord().hashCode());
        result = prime * result + ((getPraise() == null) ? 0 : getPraise().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        return result;
    }
}