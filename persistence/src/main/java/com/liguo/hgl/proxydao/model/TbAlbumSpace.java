package com.liguo.hgl.proxydao.model;

import java.io.Serializable;
import java.util.Date;

public class TbAlbumSpace implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Integer id;

    /**
     * 图片名称
     */
    private String imageNameOld;


    /**
     * 操作人Id
     */
    private String imageUrl;

    /**
     * 图片压缩包名称
     */
    private String imageTagName;

    /**
     * 上传时间
     */
    private Date updateTime;

    /**
     * 操作人
     */
    private String updateName;

    /**
     * 下载次数
     */
    private String downloadCount;

    /**
     * 版本号
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
     * @return 图片原名称
     */
    public String getImageNameOld() {
        return imageNameOld;
    }

    /**
     * @param imageNameOld 
	 *            图片原名称
     */
    public void setImageNameOld(String imageNameOld) {
        this.imageNameOld = imageNameOld == null ? null : imageNameOld.trim();
    }
    
    /**
     * @return 图片url
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * @param imageUrl 
	 *            图片url
     */
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl == null ? null : imageUrl.trim();
    }

    /**
     * @return 图片压缩包名称
     */
    public String getImageTagName() {
        return imageTagName;
    }

    /**
     * @param imageTagName 
	 *            图片压缩包名称
     */
    public void setImageTagName(String imageTagName) {
        this.imageTagName = imageTagName == null ? null : imageTagName.trim();
    }

    /**
     * @return 上传时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime 
	 *            上传时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return 操作人
     */
    public String getUpdateName() {
        return updateName;
    }

    /**
     * @param updateName 
	 *            操作人
     */
    public void setUpdateName(String updateName) {
        this.updateName = updateName == null ? null : updateName.trim();
    }

    /**
     * @return 下载次数
     */
    public String getDownloadCount() {
        return downloadCount;
    }

    /**
     * @param downloadCount 
	 *            下载次数
     */
    public void setDownloadCount(String downloadCount) {
        this.downloadCount = downloadCount == null ? null : downloadCount.trim();
    }

    /**
     * @return 版本号
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * @param version 
	 *            版本号
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object obj) {
        boolean equals = false;
        // TODO Auto-generated method stub
        if(!(obj instanceof TbAlbumSpace)){
            return false;
        }
        
        if(super.equals(obj)){
            return true;
        }
        TbAlbumSpace tas = (TbAlbumSpace) obj;
        
        if(null!=this.imageNameOld&&(this.imageNameOld.equals(tas.getImageNameOld()))){
            equals = true;
        }
        return equals;
    }
    
    
}