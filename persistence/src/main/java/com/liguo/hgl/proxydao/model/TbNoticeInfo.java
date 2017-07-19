package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbNoticeInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 自动增长的id
     */
    private Integer id;

    /**
     * 公告名称
     */
    private String noticeName;

    /**
     * 公告类型(1升级,2公告,3招商公告)
     */
    private Integer noticeType;

    /**
     * 公告添加时间，系统在添加数据时自动产生
     */
    private Long createDt;

    /**
     * 告公创建人
     */
    private String createBy;

    /**
     * 统系公告内容
     */
    private String noticeContent;

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
     * @return 公告名称
     */
    public String getNoticeName() {
        return noticeName;
    }

    /**
     * @param noticeName 
	 *            公告名称
     */
    public void setNoticeName(String noticeName) {
        this.noticeName = noticeName == null ? null : noticeName.trim();
    }

    /**
     * @return 公告类型(1升级,2公告,3招商公告)
     */
    public Integer getNoticeType() {
        return noticeType;
    }

    /**
     * @param noticeType 
	 *            公告类型(1升级,2公告,3招商公告)
     */
    public void setNoticeType(Integer noticeType) {
        this.noticeType = noticeType;
    }

    /**
     * @return 公告添加时间，系统在添加数据时自动产生
     */
    public Long getCreateDt() {
        return createDt;
    }

    /**
     * @param createDt 
	 *            公告添加时间，系统在添加数据时自动产生
     */
    public void setCreateDt(Long createDt) {
        this.createDt = createDt;
    }

    /**
     * @return 告公创建人
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * @param createBy 
	 *            告公创建人
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    /**
     * @return 统系公告内容
     */
    public String getNoticeContent() {
        return noticeContent;
    }

    /**
     * @param noticeContent 
	 *            统系公告内容
     */
    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent == null ? null : noticeContent.trim();
    }
}