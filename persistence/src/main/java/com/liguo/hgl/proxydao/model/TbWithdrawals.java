package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbWithdrawals implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 账户ID
     */
    private Integer accountId;

    /**
     * 0：待审核，1：审核通过,2审核拒绝
     */
    private Integer state;

    /**
     * 提现时冻结记录ID
     */
    private Integer freezing;

    /**
     * 提现金额
     */
    private Double money;

    /**
     * 操作时间
     */
    private Long operationDt;

    /**
     * 版本
     */
    private Integer version;

    /**
     * 审核人Id
     */
    private Integer auditId;

    /**
     * 银行账户ID
     */
    private Integer accountbankId;

    /**
     * 审核时间
     */
    private Long auditDt;

    /**
     * 店铺ID
     */
    private Integer shopId;

    /**
     * 审核人。1平台，2店铺
     */
    private Integer auditPerson;

    /**
     * 预留确定时间
     */
    private Long determineDt;

    /**
     * 银行账号
     */
    private String bankAccount;
    
    /**
     * 持卡人姓名
     */
    private String cardHolder;

    /**
     * 银行
     */
    private String bank;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return 用户ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId 
	 *            用户ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return 账户ID
     */
    public Integer getAccountId() {
        return accountId;
    }

    /**
     * @param accountId 
	 *            账户ID
     */
    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    /**
     * @return 0：待审核，1：审核通过,2审核拒绝
     */
    public Integer getState() {
        return state;
    }

    /**
     * @param state 
	 *            0：待审核，1：审核通过,2审核拒绝
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * @return 提现时冻结记录ID
     */
    public Integer getFreezing() {
        return freezing;
    }

    /**
     * @param freezing 
	 *            提现时冻结记录ID
     */
    public void setFreezing(Integer freezing) {
        this.freezing = freezing;
    }

    /**
     * @return 提现金额
     */
    public Double getMoney() {
        return money;
    }

    /**
     * @param money 
	 *            提现金额
     */
    public void setMoney(Double money) {
        this.money = money;
    }

    /**
     * @return 操作时间
     */
    public Long getOperationDt() {
        return operationDt;
    }

    /**
     * @param operationDt 
	 *            操作时间
     */
    public void setOperationDt(Long operationDt) {
        this.operationDt = operationDt;
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

    /**
     * @return 审核人Id
     */
    public Integer getAuditId() {
        return auditId;
    }

    /**
     * @param auditId 
	 *            审核人Id
     */
    public void setAuditId(Integer auditId) {
        this.auditId = auditId;
    }

    /**
     * @return 银行账户ID
     */
    public Integer getAccountbankId() {
        return accountbankId;
    }

    /**
     * @param accountbankId 
	 *            银行账户ID
     */
    public void setAccountbankId(Integer accountbankId) {
        this.accountbankId = accountbankId;
    }

    /**
     * @return 审核时间
     */
    public Long getAuditDt() {
        return auditDt;
    }

    /**
     * @param auditDt 
	 *            审核时间
     */
    public void setAuditDt(Long auditDt) {
        this.auditDt = auditDt;
    }

    /**
     * @return 店铺ID
     */
    public Integer getShopId() {
        return shopId;
    }

    /**
     * @param shopId 
	 *            店铺ID
     */
    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }

    /**
     * @return 审核人。1平台，2店铺
     */
    public Integer getAuditPerson() {
        return auditPerson;
    }

    /**
     * @param auditPerson 
	 *            审核人。1平台，2店铺
     */
    public void setAuditPerson(Integer auditPerson) {
        this.auditPerson = auditPerson;
    }

    /**
     * @return 预留确定时间
     */
    public Long getDetermineDt() {
        return determineDt;
    }

    /**
     * @param determineDt 
	 *            预留确定时间
     */
    public void setDetermineDt(Long determineDt) {
        this.determineDt = determineDt;
    }

    /**
     * @return 银行账号
     */
    public String getBankAccount() {
        return bankAccount;
    }

    /**
     * @param bankAccount 
	 *            银行账号
     */
    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount == null ? null : bankAccount.trim();
    }

    /**
     * @return 银行
     */
    public String getBank() {
        return bank;
    }

    /**
     * @param bank 
	 *            银行
     */
    public void setBank(String bank) {
        this.bank = bank == null ? null : bank.trim();
    }

	public String getCardHolder() {
		return cardHolder;
	}

	public void setCardHolder(String cardHolder) {
		this.cardHolder = cardHolder;
	}
    
}