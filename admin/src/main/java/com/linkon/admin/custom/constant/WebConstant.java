package com.linkon.admin.custom.constant;

/**
 * WEB应用常量定义
 */
public class WebConstant {

    public static final String BANK_RESP_CODE_SUCCESS = "000000"; //无法识别

    //协议类：1xxx
    //转账类：2xxx
    //查询类：3xxx
    //管理类：4xxx
    //系统类：5xxx
    //对账类：6xxx
    //现货交易类：7xxx
    //    public static final String TRNX_CODE_HYQY = "1001"; //会员签约
    //    public static final String TRNX_CODE_HYQY = "1002"; //会员解约
    //    public static final String TRNX_CODE_HYQY = "1003"; //会员信息同步
    //
    //    public static final String TRNX_CODE_HYQY = "2001"; //入金
    //    public static final String TRNX_CODE_HYQY = "2002"; //出金
    //    public static final String TRNX_CODE_HYQY = "2003"; //出金审核结果通知
    //
    //    public static final String TRNX_CODE_HYQY = "3001"; //查询银行账户余额

    public static final String TRNX_CODE_QD = "4001"; //签到
    public static final String TRNX_CODE_QT = "4002"; //签退

    public static final String TRNX_CODE_MYTB_ZS = "5001"; //密钥同步
    public static final String TRNX_CODE_MYTB_XY = "5002"; //兴业银行密钥同步
    public static final String TRNX_CODE_MYXZ_JS = "5001";
    public static final String TRNX_CODE_ZSXZ_JS = "5003";
    public static final String TRNX_CODE_DZWJ = "6001"; //请求对账文件生成
    public static final String TRNX_CODE_CRJDZ = "6002"; //出入金明细核对
    public static final String TRNX_CODE_QS = "6003"; //资金清算
    public static final String TRNX_CODE_DZ = "6004"; //余额对账

    //    public static final String TRNX_CODE_HYQY = "7001"; //资金冻结/解冻
    //    public static final String TRNX_CODE_HYQY = "7002"; //付款状态查询
    //    public static final String TRNX_CODE_HYQY = "7003"; //终止交易
    //    public static final String TRNX_CODE_HYQY = "7004"; //冻结解冻明细查询
    //    public static final String TRNX_CODE_HYQY = "7005"; // 强制付款通知/撤销
    public static final String TRNX_CODE_HYQY = "7006"; //强制付款
    //    public static final String TRNX_CODE_HYQY = "7007"; //强制转账，仅限于建行会员之间，由两个交易合成

    public static final String TRNX_CEODE_HYQY = "XXXX"; //无法识别

    /** 页面显示方式 1按钮 2链接 */
    public static final String SHOW_TYPE_1 = "1";
    /** 页面显示方式 1按钮 2链接 */
    public static final String SHOW_TYPE_2 = "2";
    /*************************************银行ID常量********************************/
    /** 华夏银行ID-HX */
    public static final String BANK_ID_HX = "HX";
    /** 兴业银行ID-XY */
    public static final String BANK_ID_XY = "XY";
    /** 招商银行ID-ZS */
    public static final String BANK_ID_ZS = "ZS";
    /** 建设银行ID-JS */
    public static final String BANK_ID_JS = "JS";
    /** 交通银行ID-JT */
    public static final String BANK_ID_JT = "JT";

    /** 功能编码-申请会话密钥-SQHHMY */
    public static final String FUNC_CODE_SQHHMY = "SQHHMY";
    /** 功能编码-签到-QD */
    public static final String FUNC_CODE_QD = "QD";
    /** 功能编码-签退-QT */
    public static final String FUNC_CODE_QT = "QT";
    /** 功能编码-出入金对账-CRJDZ */
    public static final String FUNC_CODE_CRJDZ = "CRJDZ";
    /** 功能编码-查看出入金对账结果-CRJDZJG */
    public static final String FUNC_CODE_CRJDZJG = "CRJDZJG";
    /** 功能编码-出入金调账-CRJTZ */
    public static final String FUNC_CODE_CRJTZ = "CRJTZ";
    /** 功能编码-建行余额对账-JSYEDZ */
    public static final String FUNC_CODE_JSYEDZ = "JSYEDZ";
    /** 功能编码-生成清算、对账文件-QSDZWJ */
    public static final String FUNC_CODE_QSDZWJ = "QSDZWJ";
    /** 功能编码-查看分账户余额不平记录-FZHYEBPJL */
    public static final String FUNC_CODE_FZHYEBPJL = "FZHYEBPJL";
    /** 功能编码-查看总账户余额对账记录-ZZHYEBPJL */
    public static final String FUNC_CODE_ZZHYEBPJL = "ZZHYEBPJL";
    /** 功能编码-清算-QS */
    public static final String FUNC_CODE_QS = "QS";
    /** 功能编码-查看清算失败记录-QSSBJL */
    public static final String FUNC_CODE_QSSBJL = "QSSBJL";
    /** 功能编码-对账-DZ */
    public static final String FUNC_CODE_DZ = "DZ";
    /** 功能编码-查看对账失败记录-DZSBJL */
    public static final String FUNC_CODE_DZSBJL = "DZSBJL";
    /** 功能编码-建行密钥下载 -MYXZ */
    public static final String FUNC_CODE_MYXZ = "MYXZ";
    /** 功能编码-建行证书下载 -ZSXZ */
    public static final String FUNC_CODE_ZSXZ = "ZSXZ";
    /** 功能编码-建行发送强制付款通知-JSQFTZ */
    public static final String FUNC_CODE_JSQFTZ = "JSQFTZ";
}
