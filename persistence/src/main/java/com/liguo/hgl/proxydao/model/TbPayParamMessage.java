package com.liguo.hgl.proxydao.model;

public class TbPayParamMessage {
    /**
     * id
     */
    private Integer id;

    /**
     * 支付类型编码
     */
    private String payTypeCode;

    /**
     * 支付类型名称
     */
    private String payTypeName;

    /**
     * 收款唯一标识,收款人账号
     */
    private String payGetCode;

    /**
     * 通知结果的url
     */
    private String payNotifyUrl;

    /**
     * 支付完成后跳转的url
     */
    private String payReturnUrl;

    /**
     * 支付接口ip
     */
    private String payIp;

    /**
     * 支付接口端口
     */
    private String payPort;

    /**
     * 编码格式
     */
    private String payEncode;

    /**
     * 支付接口地址
     */
    private String payAddr;

    /**
     * 支付秘钥
     */
    private String payKey;

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
     * @return 支付类型编码
     */
    public String getPayTypeCode() {
        return payTypeCode;
    }

    /**
     * @param payTypeCode 
	 *            支付类型编码
     */
    public void setPayTypeCode(String payTypeCode) {
        this.payTypeCode = payTypeCode == null ? null : payTypeCode.trim();
    }

    /**
     * @return 支付类型名称
     */
    public String getPayTypeName() {
        return payTypeName;
    }

    /**
     * @param payTypeName 
	 *            支付类型名称
     */
    public void setPayTypeName(String payTypeName) {
        this.payTypeName = payTypeName == null ? null : payTypeName.trim();
    }

    /**
     * @return 收款唯一标识,收款人账号
     */
    public String getPayGetCode() {
        return payGetCode;
    }

    /**
     * @param payGetCode 
	 *            收款唯一标识,收款人账号
     */
    public void setPayGetCode(String payGetCode) {
        this.payGetCode = payGetCode == null ? null : payGetCode.trim();
    }

    /**
     * @return 通知结果的url
     */
    public String getPayNotifyUrl() {
        return payNotifyUrl;
    }

    /**
     * @param payNotifyUrl 
	 *            通知结果的url
     */
    public void setPayNotifyUrl(String payNotifyUrl) {
        this.payNotifyUrl = payNotifyUrl == null ? null : payNotifyUrl.trim();
    }

    /**
     * @return 支付完成后跳转的url
     */
    public String getPayReturnUrl() {
        return payReturnUrl;
    }

    /**
     * @param payReturnUrl 
	 *            支付完成后跳转的url
     */
    public void setPayReturnUrl(String payReturnUrl) {
        this.payReturnUrl = payReturnUrl == null ? null : payReturnUrl.trim();
    }

    /**
     * @return 支付接口ip
     */
    public String getPayIp() {
        return payIp;
    }

    /**
     * @param payIp 
	 *            支付接口ip
     */
    public void setPayIp(String payIp) {
        this.payIp = payIp == null ? null : payIp.trim();
    }

    /**
     * @return 支付接口端口
     */
    public String getPayPort() {
        return payPort;
    }

    /**
     * @param payPort 
	 *            支付接口端口
     */
    public void setPayPort(String payPort) {
        this.payPort = payPort == null ? null : payPort.trim();
    }

    /**
     * @return 编码格式
     */
    public String getPayEncode() {
        return payEncode;
    }

    /**
     * @param payEncode 
	 *            编码格式
     */
    public void setPayEncode(String payEncode) {
        this.payEncode = payEncode == null ? null : payEncode.trim();
    }

    /**
     * @return 支付接口地址
     */
    public String getPayAddr() {
        return payAddr;
    }

    /**
     * @param payAddr 
	 *            支付接口地址
     */
    public void setPayAddr(String payAddr) {
        this.payAddr = payAddr == null ? null : payAddr.trim();
    }

    /**
     * @return 支付秘钥
     */
    public String getPayKey() {
        return payKey;
    }

    /**
     * @param payKey 
	 *            支付秘钥
     */
    public void setPayKey(String payKey) {
        this.payKey = payKey == null ? null : payKey.trim();
    }
}