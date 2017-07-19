package com.liguo.hgl.proxydao.model;

import java.io.Serializable;

public class TbAccount implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 余额
     */
    private Double balance;

    /**
     * 冻结金额
     */
    private Double freeze;

    /**
     * 银行账户
     */
    private String bankAccount;

    /**
     * 银行
     */
    private String bank;

    /**
     * 支行
     */
    private String branch;

    /**
     * 银行账户ID
     */
    private Integer accountbankId;

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
     * @return 余额
     */
    public Double getBalance() {
        return balance;
    }

    /**
     * @param balance 
	 *            余额
     */
    public void setBalance(Double balance) {
        this.balance = balance;
    }

    /**
     * @return 冻结金额
     */
    public Double getFreeze() {
        return freeze;
    }

    /**
     * @param freeze 
	 *            冻结金额
     */
    public void setFreeze(Double freeze) {
        this.freeze = freeze;
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