package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbCompanyConsult implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 分类id
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 联系人
     */
    private String contract;

    /**
     * 用户电话号码
     */
    private String contractPhone;

    /**
     * 公司id
     */
    private Integer companyId;

    /**
     * 时间
     */
    private Long createDt;

    /**
     * 描述
     */
    private String describes;

    private Integer version;

    /**
     * @return 分类id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id 
	 *            分类id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId 
	 *            用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return 联系人
     */
    public String getContract() {
        return contract;
    }

    /**
     * @param contract 
	 *            联系人
     */
    public void setContract(String contract) {
        this.contract = contract == null ? null : contract.trim();
    }

    /**
     * @return 用户电话号码
     */
    public String getContractPhone() {
        return contractPhone;
    }

    /**
     * @param contractPhone 
	 *            用户电话号码
     */
    public void setContractPhone(String contractPhone) {
        this.contractPhone = contractPhone == null ? null : contractPhone.trim();
    }

    /**
     * @return 公司id
     */
    public Integer getCompanyId() {
        return companyId;
    }

    /**
     * @param companyId 
	 *            公司id
     */
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    /**
     * @return 时间
     */
    public Long getCreateDt() {
        return createDt;
    }

    /**
     * @param createDt 
	 *            时间
     */
    public void setCreateDt(Long createDt) {
        this.createDt = createDt;
    }

    /**
     * @return 描述
     */
    public String getDescribes() {
        return describes;
    }

    /**
     * @param describes 
	 *            描述
     */
    public void setDescribes(String describes) {
        this.describes = describes == null ? null : describes.trim();
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}