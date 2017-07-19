package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbAccountBank implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 银行账户
     */
    private String bankAccount;

    /**
     * 账户ID
     */
    private Integer accountId;

    /**
     * 银行
     */
    private String bank;

    /**
     * 支行
     */
    private String branch;

    /**
     * 持卡人姓名
     */
    private String name;

    /**
     * 版本
     */
    private Integer version;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return 银行账户
     */
    public String getBankAccount() {
        return bankAccount;
    }

    /**
     * @param bankAccount 
	 *            银行账户
     */
    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount == null ? null : bankAccount.trim();
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

    /**
     * @return 支行
     */
    public String getBranch() {
        return branch;
    }

    /**
     * @param branch 
	 *            支行
     */
    public void setBranch(String branch) {
        this.branch = branch == null ? null : branch.trim();
    }

    /**
     * @return 持卡人姓名
     */
    public String getName() {
        return name;
    }

    /**
     * @param name 
	 *            持卡人姓名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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