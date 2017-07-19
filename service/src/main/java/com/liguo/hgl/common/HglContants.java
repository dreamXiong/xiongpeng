/*
 * Copyright (c)2014 快诺迅汇(长沙)网络技术股份有限公司。
 * 版权所有
 */

package com.liguo.hgl.common;

import java.io.File;

/**
 * 平台常量类型
 * @author 周琨
 *
 */
public class HglContants {
	//////////////////////////////////////////////////////以下的代码会被删除 新增的变量写最下面//////////////
    /** 一次随机获取的用户数 */
    public static final Integer GET_RAND_USER_COUNT = 12;
    public static final String PAGE_DTO_ID = "page";

    /** // appId
    public static final String APP_ID_FOR_KWINER = "wx5af353b3e0a40ede";
    // appSecret 
    public static final String APP_SECRET_FOR_KWINER = "079038d1c59c747d24aef2183a623eff";
    // token 
    public static final String TOKEN_FOR_KWINER = "kwiner_knxh";
    // aesKey 
    public static final String AES_KEY_FOR_KWINER = "JF8zzazGpbh5t24puMKDZssSUceKgRysX7GslDVeMrM";
    // grant_type 
    public static final String GRANT_TYPE_FOR_KWINER = "client_credential";*/

    // appId
    public static final String APP_ID_FOR_KWINER = AppConfig.getInstance().getProperty("weixin.app_id");
    // appSecret 
    public static final String APP_SECRET_FOR_KWINER = AppConfig.getInstance().getProperty("weixin.app_secret");
    // token 
    public static final String TOKEN_FOR_KWINER = AppConfig.getInstance().getProperty("weixin.token");
    // aesKey 
    public static final String AES_KEY_FOR_KWINER = AppConfig.getInstance().getProperty("weixin.aes_key");
    // grant_type 
    public static final String GRANT_TYPE_FOR_KWINER = AppConfig.getInstance().getProperty("weixin.grant_type");

    /** grant_type openID */
    public static final String GRANT_TYPE_QUERY_OPENID = "authorization_code";
    /** 信息配置文件 */
    public static final String CONFIG_FILE = "application.properties";
    /**session存储用户的Key*/
    public static final String SESSION_KEY = "session_key";
    
    /**session中的菜单Key***/
    public static final String SESSION_PARENT_LIST_KEY = "parentList";
    
 /*   public static final String SETTLEMENT_KEY = "settlement_key";*/
    
    public static final String SHOP_ID_LIST = "shop_list";

    public static final Integer DEFUAL_VERSION = 1;

    public static final String CHARSET_UTF = "UTF-8";

    public static final String LANG = "zh_CN";

    public static final String SIGN_IN_GROUP_ID = AppConfig.getInstance().getProperty("group.id.sign.in");

    public static final String SIGN_IN_MENU_ADDCONDITIONAL = AppConfig.getInstance().getProperty("addconditional.id.sign.in");

    public static final String WE_CHAT_BASE = "https://api.weixin.qq.com/cgi-bin";

    public static final String WE_CHAT_ACCESS_TOKEN_PARAM = "?access_token=%s";

    public static final String WE_CHAT_TOKEN = WE_CHAT_BASE + "/token";

    public static final String WE_CHAT_MENU_CREATE = WE_CHAT_BASE + "/menu/create";

    public static final String WE_CHAT_MENU_ADDCONDITIONAL = WE_CHAT_BASE + "/menu/addconditional";

    public static final String WE_CHAT_MENU_DELCONDITIONAL = WE_CHAT_BASE + "/menu/delconditional";

    public static final String WE_CHAT_QUERY_USER_INFO = WE_CHAT_BASE + "/user/info";

    public static final String WE_CHAT_CUSTOM_SEND = WE_CHAT_BASE + "/message/custom/send";

    public static final String WE_CHAT_GROUPS_CREATE = WE_CHAT_BASE + "/groups/create";

    public static final String WE_CHAT_GROUPS_MOVE = WE_CHAT_BASE + "/groups/members/update";

    public static final String WE_USER_OPENID = "https://api.weixin.qq.com/sns/oauth2/access_token";

    public static final String PRAISE_URL = AppConfig.getInstance().getProperty("praise.url");

    public static final String SHAKE_URL = AppConfig.getInstance().getProperty("shake.url");

    public static final String WEIXIN_PRAISE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + APP_ID_FOR_KWINER + "&redirect_uri=" + PRAISE_URL + "&response_type=code&scope=snsapi_base#wechat_redirect";

    public static final String WEIXIN_SHAKE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + APP_ID_FOR_KWINER + "&redirect_uri=" + SHAKE_URL + "&response_type=code&scope=snsapi_base#wechat_redirect";

    /**公众号关注页面url*/
    public static final String CSKNXH_WEIXIN_URL = AppConfig.getInstance().getProperty("weixin.url");

    public static final String SERVER_URL = AppConfig.getInstance().getProperty("server.url");
    
    public static final Integer MERCHANTS_NOT_RELEASE = 0; //未发布
    
    public static final Integer MERCHANTS_RELEASE = 1; //发布中
    
    public static final Integer MERCHANTS_FAILURE = 2;//失效
    
    //////////////////////////////////////////////////////以上的代码会被删除 新增的变量写下面//////////////
    
    ///////////////////////////////////begin 获取微信openID常量/////////////////////////////////////////
    // appId
    public static final String WEIXIN_APP_ID = "wx9dd0df5b1621ec44";//AppConfig.getInstance().getProperty("weixin.app_id");
    // appSecret 
    public static final String WEIXIN_APP_SECRET = "5ee53d8c4df5f2fa544f5fea5fc6fe61";//AppConfig.getInstance().getProperty("weixin.app_secret");
    // grant_type 
    public static final String WEIXIN_GRANT_TYPE = "authorization_code";//AppConfig.getInstance().getProperty("weixin.grant_type");
    // scope,不需要授权
    public static final String WEIXIN_SNSAPI_BASE = "snsapi_base";
    // scope,需要授权
    public static final String WEIXIN_SNSAPI_USERINFO = "snsapi_userinfo";
    // token在微信公众平台配置的token
    public static final String WEIXIN_TOKEN = "liguo";
    // aesKey在微信公众平台配置的sesKey
    public static final String WEIXIN_ENCODINGAESKEY = "VBkxP5xFjoj1V6xlaFsW63wZOZDkOpiJ9edZi2etD8R";
    // 一键登录call_method,调用openid回调方法
    public static final String WEIXIN_CALL_METHOD = "http://wap.hgeili.com/weixin/getWeixinOpenIdCall";
    // 自动登录call_method,调用openid回调方法
    public static final String WEIXIN_AUTOLOGIN_CALL_METHOD = "http://wap.hgeili.com/weixin/weixinOpenIdCallAutoLogin";
    // 登录后call_method,调用openid回调方法
    public static final String WEIXIN_LOGIN_CALL_METHOD = "http://wap.hgeili.com/weixin/weixinOpenIdCallLogin";
    // open_url,获取code的url
    public static final String WEIXIN_OPEN_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
    // api_url,获取openid的url
    public static final String WEIXIN_API_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=GRANT_TYPE";
    // userinfo_url,获取用户信息的url,无需授权
    public static final String WEIXIN_USERINFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
    // userinfo_url,获取用户信息的url,需授权
    public static final String WEIXIN_USERINFO_OAUTH_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";
    /////////////////////////////////end 获取微信openID常量///////////////////////////////////////////
    
    ///////////////////////////////////begin 微信支付常量/////////////////////////////////////////
    // MCH_ID
    public static final String WEIXIN_MCH_ID = "1355718802";
    // WEIXIN_NOTIFY_URL
    public static final String WEIXIN_NOTIFY_URL = "http://wap.hgeili.com/weixinPay/weixinPayCall";
    // WEIXIN_NOTIFY_NATIVE_URL
    public static final String WEIXIN_NOTIFY_NATIVE_URL = "http://wap.hgeili.com/weixinPay/nativePayCall";
    // WEIXIN_PAY_URL
    public static final String WEIXIN_PAY_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    // WEIXIN_APP_KEY
    public static final String WEIXIN_APP_KEY = "5ee53d8c4df5f2fa544f5fea5fc6fe62";
    /////////////////////////////////end 微信支付常量///////////////////////////////////////////
    
    /**
     * 统计类型-1围观
     */
    public static final Integer STATISTICAL_VIEW = 1;
    public static final Integer STATISTICAL_PARTICIPATE = 2;//参与
    public static final Integer STATISTICAL_WINNING =3;//中标
    /*无记录数*/
    public static final Integer NO_REPEAT = 00000;
    
    /*有记录数*/
    public static final Integer IS_REPEAT = 99999;
    
    /*操作成功：更，删等操作*/
    public static final Integer SUCCESS = 0;
    
    /*操作失败：更，删等操作*/
    public static final Integer FAIL = 1;
    
    /*品牌Logo路径*/
    public static final String LOGO_PATH = System.getProperty("user.home") + File.separator + "shopLogo" + File.separator;
    
    /*产品图片和产品描述图片 预览时所用的目录*/
    public static final String PRODUCT_TESTPATH = System.getProperty("user.home") + File.separator + "productTest" + File.separator;
    /*产品图片和产品描述图片 预览时所用的目录*/
    public static final String PRODUCT_PATH = System.getProperty("user.home") + File.separator + "product" + File.separator;
    
    /**用户二维码图片**/
    public static final String USER_LOGO_CODE_PATH = System.getProperty("user.home") + File.separator + "userLogoCode" + File.separator;
    public static final String USER_REGISTER_PATH = "http://wap.hgeili.com/shop/userShop?recommendCode=";
    /**店铺二维码图片**/
    public static final String SHOP_LOGO_CODE_PATH = System.getProperty("user.home") + File.separator + "shopLogoCode" + File.separator;
    public static final String SHOP_PATH = "http://wap.hgeili.com/shop/userShop?sId=";
    
    public static final String CUSTOMER_TESTPATH = System.getProperty("user.home") + File.separator + "customerServiceTest" + File.separator;
    
    public static final String COMPANY_TYPE = System.getProperty("user.home") + File.separator + "companyType" + File.separator;
    
    public static final String COMPANY_SERVICE = System.getProperty("user.home") + File.separator + "companyService" + File.separator;
    
    public static final String CUSTOMER_PATH = System.getProperty("user.home") + File.separator + "customerService" + File.separator;
    
    /*师傅照片、身份证照片*/
    public static final String USER_PATH = System.getProperty("user.home") + File.separator + "user" + File.separator;
    
    /*活动图片存储路径*/
    public static final String ACTIVITY_PATH = System.getProperty("user.home") + File.separator + "activityInfo" + File.separator;
    
    /*活动图片存放的临时目录*/
    public static final String ACTIVITY_TESTPATH = System.getProperty("user.home") + File.separator + "activityInfoTest" +File.separator;
    
    /*微信扫码支付二维码图标*/
    public static final String WECHATCODE_ICON = System.getProperty("user.home") + File.separator + "wechatcode" + File.separator + "hgl.jpg";
    
    /*微信扫码支付二维码目录*/
    public static final String WECHATCODE_PATH = System.getProperty("user.home") + File.separator + "wechatcode" + File.separator;
    
    /*
     * public static final String PRODUCT_TESTPATH = System.getProperty("user.home") + File.separator + "productTest" + File.separator;
     * */
    
    /***用户类型:厂家 ****/
    public static final Integer USER_TYPE_FACTOR = 102;
    /** 经销商**/
    public static final Integer USER_TYPE_DISTRIBUTOR = 104; 
    public static final Integer USER_TYPE_CUS = 106;// 终端客户 
    public static final Integer USER_TYPE_SALE = 108;// 地推人员 
    public static final Integer USER_TYPE_WAREHOUSE = 110;// 仓库管理员
    /***用户类型:店铺管理员***/
    public static final Integer USER_TYPE_SHOPUSE = 112;
    /**师傅**/
    public static final Integer USER_TYPE_MASTER = 114;
    
    /**启用**/
    public static final Integer USER_STATUS_Valid = 132; 
    
    /**停用**/
    public static final Integer USER_STATUS_Invalid = 134; 

    /**admin用户名称**/
    public static final String ADMIN_USER_NAME = "admin"; 
  
    /**
     * 注册厂家和经销商增加图片目录
     */
    public static final String SHOP_INFO_PATH = System.getProperty("user.home") + File.separator + "shopinfo" + File.separator;
    /**
     * 注册临时图片文件  （招商封面临时图片文件，订单漏发临时文件）
     */
    public static final String SHOP_INFO_TESTPATH = System.getProperty("user.home") + File.separator + "shopinfoTest" + File.separator;
    
    public static final String REISSUE_ODERGROUP_PATH = System.getProperty("user.home") + File.separator + "reissueOderGroup" + File.separator;
    
    /**
     * wap产品图片目录
     * */
    public static final String WAP_PRODUCT_TESTPATH = System.getProperty("user.home") + File.separator + "wapProductTest" + File.separator;
    public static final String WAP_PRODUCT = System.getProperty("user.home") + File.separator + "wapProduct"+ File.separator;
    
    /***
     * 个人相册图片目录。
     */
    public static final String WAP_ALBUM_SPACE = System.getProperty("user.home") + File.separator + "wapAlbumSpace"+ File.separator;
    
    /***
     * 个人相册图片缩略图保存目录。
     */
    public static final String WAP_ALBUM_SPACE_MIN_SAVE_PATH = System.getProperty("user.home") + File.separator + "wapAlbumSpaceMinSavePath"+ File.separator;
    /***
     * 产品批量导入模板下载地址。
     */
    public static final String PRODUCT_BATCH_DOWNLOAD_PATH = System.getProperty("user.home") + File.separator + "productBatchDownload"+ File.separator;
    public static final String PRODUCT_BATCH_DOWNLOAD_NAME = "productBatchDownload.zip";
    		
    public static final Integer IS_EXIST = 0; //存在
    public static final Integer IS_NOT_EXIST = 1; //不存在

    /*******************************订单状态*******************************************/
    
    /* 待确认*/
    public static final Integer ORDER_STATE_200 = 200; 
    /* 待付款*/
    public static final Integer ORDER_STATE_202 = 202; 
    /* 待补发货*/
    public static final Integer ORDER_STATE_204 = 204; 
    /* 已终止*/
    public static final Integer ORDER_STATE_206 = 206; 
    /* 待发货*/
    public static final Integer ORDER_STATE_208 = 208; 
    /* 待确认收货*/
    public static final Integer ORDER_STATE_210 = 210; 
    /* 待登记发票*/
    public static final Integer ORDER_STATE_212 = 212; 
    /* 待确认收票*/
    public static final Integer ORDER_STATE_214 = 214; 
    /* 待买确认终止*/
    public static final Integer ORDER_STATE_216 = 216; 
    /* 待卖确认终止*/
    public static final Integer ORDER_STATE_218 = 218; 
    /* 交易完成*/
    public static final Integer ORDER_STATE_220 = 220; 
    /* 交易关闭*/
    public static final Integer ORDER_STATE_222 = 222; 
    
    /*******************************wap订单状态*******************************************/
    /** 待确认*/
    public static final Integer WAP_ORDER_STATE_600 = 600; 
    /** 待付款*/
    public static final Integer WAP_ORDER_STATE_602 = 602; 
    /** 待发货*/
    public static final Integer WAP_ORDER_STATE_608 = 608; 
    /** 待发货*/
    public static final Integer WAP_ORDER_STATE_606 = 606; 
    /** 待确认收货*/
    public static final Integer WAP_ORDER_STATE_610 = 610; 
    /** 待评价*/
    public static final Integer WAP_ORDER_STATE_612 = 612; 
    /** 待买确认终止*/
    public static final Integer WAP_ORDER_STATE_616 = 616; 
    /** 待卖确认终止*/
    public static final Integer WAP_ORDER_STATE_618 = 618; 
    /** 交易完成*/
    public static final Integer WAP_ORDER_STATE_620 = 620; 
    /** 交易关闭*/
    public static final Integer WAP_ORDER_STATE_622 = 622; 
    
    /*******************************wap服务订单状态*******************************************/
    /** 待接单*/
    public static final Integer WAP_ORDER_SERVICE_STATE_800 = 800; 
    /** 待服务*/
    public static final Integer WAP_ORDER_SERVICE_STATE_802 = 802; 
    /** 服务中*/
    public static final Integer WAP_ORDER_SERVICE_STATE_804 = 804; 
    /** 挂起中*/
    public static final Integer WAP_ORDER_SERVICE_STATE_806 = 806; 
    /** 待支付*/
    public static final Integer WAP_ORDER_SERVICE_STATE_808 = 808; 
    /** 待评价*/
    public static final Integer WAP_ORDER_SERVICE_STATE_810 = 810; 
    /** 完成*/
    public static final Integer WAP_ORDER_SERVICE_STATE_812 = 812; 
    /** 关闭*/
    public static final Integer WAP_ORDER_SERVICE_STATE_814 = 814; 
    /**待确认（师傅）**/
    public static final Integer WAP_ORDER_SERVICE_STATE_816 = 816;
    
    /**********订单类型************/
    /* 招商订单*/
    public static final Integer MERCHANT_ORDER = 262; 
    
    /* 订货订单*/
    public static final Integer GOODS_ORDER = 264; 
    
    
    /**********支付类型************/
    /* 线上支付*/
    public static final Integer SETTLE_TYPE_240 = 240; 
    
    /* 线下支付*/
    public static final Integer SETTLE_TYPE_242 = 242; 
    
    /* wap-自提*/
    public static final Integer RECEIVING_TYPE_414 = 414; 
    
    /* wap-送货*/
    public static final Integer RECEIVING_TYPE_416 = 416; 
    
    /*店铺收藏和产品收藏、师傅收藏*/
    public static final Integer SHOP_SAVE = 270; //店铺收藏
    
    public static final Integer GOOD_SAVE = 272; //产品收藏
    
    public static final Integer WORKER_SAVE = 274; //师傅收藏
    
    public static final Integer SERVICE_SAVE = 276; //服务收藏
    
    
    public static final Integer SAVE_OPERATE_SAVE = 1; //收藏
    public static final Integer SAVE_OPERATE_CANCEL = 0;//表示取消收藏
    
    /*表示用户未登录，重新登录*/
    public static final Integer RE_LOGIN = 3;
    
    /*默认版本号*/
    public static final Integer VERSION = 0;
    
    public static final Integer INSERT_SUCCESS =1;
    
    public static final Integer INSERT_FAIL = -1;
    
    /**
     * 品牌状态：0是显示 1是隐藏',
     */
    public static final int BRAND_STATE_ISSHOW=0;
    public static final int BRAND_STATE_ISHIDDE=1;
    /**
     * 注册默认等级
     */
    public static final int SHOP_EXP_LEVEL = 1;
    public static final int SHOP_EXP = 0;
    /**
     * 默认销售量
     */
    public static final int SHOP_SALES = 0;
  
    /**
     * 用户状态，0待审核，1，审核通过,2	审核拒绝，3关闭
     */
    public static final int WEB_AUTH_STATUS = 0;
    public static final int WEB_AUTH_STATUS1 = 1;
    public static final int WEB_AUTH_STATUS2 = 2;
    public static final int WEB_AUTH_STATUS3 = 3;
    
    /**
     * wap端手机坐标的纵横坐标
     * */
    public static final String LON = "shop_lon";
    public static final String LAT = "shop_lat";
    
    /**
     * 
     * */
    public static final String NEAR_LON = "near_lon";
    public static final String NEAR_LAT = "near_lat";
    
    /*招商公告*/
    public static final Integer MERCH_NOTICE_VALIDATE=280; //激活
    public static final Integer MERCH_NOTICE_INVALIDATE=282; //关闭
    
    /*招商公告类型*/
    public static final Integer MERCH_NOTICE_ORDER=290; //招商公告
    public static final Integer MERCH_NOTICE_WIN=292; //中标公告 
    
    /*优惠券 --类型*/
    public static final Integer COUPON_TYPE_BUY=300;  //购买
    public static final Integer COUPON_TYPE_GIVE=302; //赠送
    
    /*优惠券 -- 状态*/
    public static final Integer COUPON_STATUS_GAIN = 400; //获得
    public static final Integer COUPON_STATUS_EMPLOY=402; //使用
    
    /*计算方式：410：按距离，412：按金额*/
    public static final Integer BY_DISTANCE = 410;
    public static final Integer BY_TOTALAMOUNT = 412;
    
    /*wap端： 订单类型   服务订单*/
    public static final Integer ORDINARY_ORDER = 510;
    public static final Integer ORDSERVICE_ORDER = 512;
    
    /*wap服务订单明细状态：待确认 待支付 */
    public static final Integer ORDERSERVICEDETAIL_820 = 820;/*师傅已推送材料*/
    public static final Integer ORDERSERVICEDETAIL_822 = 822;/*用户已确认材料*/
    
    /**
     * session的购物车数量
     */
    public static final String SESSION_CART = "session_cart";
    
    /**
     * session的运费
     */
    public static final String SESSION_FREIGHT = "calculateFreight";
    
    /**
     * session的记录actionURL对象
     */
    public static final String ACTION_URL_RECORD = "actionUrlRecord";
    
    /**
     * 师傅推送材料存放session
     */
    public static final String ORDER_SERVICE_ID = "orderServiceId";
    
    /**
     * 微信Token
     */
    public static final String SESSION_TOKEN = "token";
    
    /**
     * 手机验证码session
     */
    public static final String SESSION_PHONE_CODE = "phoneCode";

    
    /**
     * 用户名session
     */
    public static final String SESSION_USERNAME = "session_userName";
    
    /**
     * cookie存储账号名称
     */
    public static final String COOKIE_USERNAME = "userName"; 
    
    /**
     * 惠给力的推荐码
     */
    public static final String HGL_RECOMMENDCODE = "0000";  
    
    public static final Integer HGL_DEFAULT_SHOP=1;
    
    public enum CheckType{
		CheckUserName,
		CheckMobile,
		CheckEmail,
		CheckIdCard
	};   
    /**
     * 招商封面图片地址
     */
	 public static final String MERCHANT_PATH = System.getProperty("user.home") + File.separator + "merchant" + File.separator;
	 
	 /**
	  * 经验值类型 0购买，1服务，2活动
	  */
	 public static final Integer BUY_EXPERIENCE = 0;
	 public static final Integer SERVICE_EXPERIENCE = 1;
	 public static final Integer ACTIVITY_EXPERIENCE = 2;
	
	 /**
	  * 层级 1 省，2市3区县4街道
	  */
	 public static final Integer PROVINCE_LEVEL = 1;
	 public static final Integer CITY_LEVEL = 2;
	 public static final Integer COUNTRY_LEVEL = 3;
	 public static final Integer STREET_LEVEL = 4;
	 
	 /**
	  * 购买代理权状态，0 未生效 1暂时生效2生效3失效
	  */
	 public static final Integer MERCHANTS_AGENCY_STATE = 0;
	 public static final Integer MERCHANTS_AGENCY_STATE1 = 1;
	 public static final Integer MERCHANTS_AGENCY_STATE2 = 2;
	 public static final Integer MERCHANTS_AGENCY_STATE3 = 3;
	 /**
	  * 服务费状态0.未付费
	  */
	 public static final Integer MERCHANTS_AGENCY_COUPONSSTATE = 0;
	 public static final Integer MERCHANTS_AGENCY_COUPONSSTATE1 = 1;
	 
	 public static final String PRODUCTTYPES="productFstTypes";
	 /**
	  * 经销商，厂家注册默认角色
	  */
	 public static final Integer REGISTER_ROLE_ID =3;
	 /**
	  * 经销商，厂家注册审核通过默认角色
	  */
	 public static final Integer DEFAULT_ROLE_ID =2;
	 
	 /**
	  * 经销商购买发表货品权限的角色
	  */
	 public static final Integer GETED_ROLE_ID =1;
	 
   /******************支付状态start**2016年5月23日****************************/
     
     /**支付状态码-支付开始**/
     public static final String PAY_STATUS_BEGIN = "0";
     
     /**支付状态码-支付完结**/
     public static final String PAY_STATUS_END = "1";
     
     
     /**结果状态码-支付成功**/
     public static final Integer PAY_RESULT_SUCCESS = 0;
     
     /**结果状态码-支付未知**/
     public static final Integer PAY_RESULT_UNKNOWN = 1;
     
     /**结果状态码-支付失败**/
     public static final Integer PAY_RESULT_FAIL = 2;
     
     
     
     
     
     /**第三方交易状态-未知**/
     public static final String THD_TRADE_STATUS_UNKNOWN = "1";
     
     
     
     
     
     
     /******************支付状态end******************************/
     /**web端用户添加自有品牌默认厂商id**/
     public static final Integer BRAND_DEFAULT_MANUFACTURERID = 0;
     
     /**web端用户添加自有品牌默认类型**/
     public static final Integer BRAND_DEFAULT_TYPE = 1;
     
     /**admin添加自分类默认type**/
     public static final Integer PRODUCT_TYPE_DEFAULT_ADMIN = 0;
     
     /**web端用户添加自分类默认type**/
     public static final Integer PRODUCT_TYPE_DEFAULT_WEB = 1;
     
     public static final String APPLICATION_KEY = "application_key";
     /**wap服务订单默认显示**/
     public static final Integer SERVICE_REPAIRMANSHOW = 0;
     public static final Integer SERVICE_REPAIRMANSHOW_DELETE = 1;
     public static final Integer SERVICE_MASTERSHOW=0;
     public static final Integer SERVICE_MASTERSHOW_DELETE=1;
     
     /**定位的城市*/
     public static final String ADDRESS_CITY = "city";
     
     
     public static final String SHOP_BLANCE = "blance";
     
     
     
    /**店铺默认是否确认订单 否**/
     public static final Integer SHOP_ISAUTOMATICORDER_DEFAULT = 0;
     /**店铺开启自动确认订单**/
     public static final Integer SHOP_ISAUTOMATICORDER = 1;
     
     /**待审核**/
     public static final Integer WITHDRAWALS_STATE_WAIT = 0;
     /**提现审核通过**/
     public static final Integer WITHDRAWALS_STATE_PASS = 1;
     /**审核拒绝**/
     public static final Integer WITHDRAWALS_STATE_REFUSE = 2;
     
     /**冻结记录表状态完成**/
     public static final Integer FREEZING_FINISH_PASS = 1;
     /**推荐规则类型平台**/
     public static final Integer TYPE_RECOMMEND_PT = 0;
     /**推荐规则类型经销商**/
     public static final Integer TYPE_RECOMMEND_JX = 1;
     /**推荐种类**/
     public static final Integer RECOMMEND_TYPESIZE =4;
     
     /*活动管理:是否显示倒计时*/
     public static final Integer COUNTDOWNYES = 900;
     public static final Integer COUNTDOWNNO = 902;
     
     /*活动管理:是否显示剩余数量*/
     public static final Integer REMAINYES = 1000;
     public static final Integer REMAINNO = 1002;
     
     
     /*活动管理:活动状态*/
     public static final Integer STATUSYES = 1100;
     public static final Integer STATUSNO = 1102;
     
     /**站内信默认状态0未读，1已读**/
     public static final Integer LETTER_DEFAULT= 0;
     public static final Integer LETTER_IS_READ= 1;
     
     
     /**站内信默认状态0发送，1接受**/
     public static final Integer LETTER_IS_SEND= 0;
     public static final Integer LETTER_IS_ACCEPT= 1;
     
     /**站内信类型0文字，1活动**/
     public static final Integer STAND_LETTER_CONTENT= 0;
     public static final Integer STAND_LETTER_ACTIVITY= 1;
     public static final Integer STAND_LETTER_NUMBER= 20;
     
     /**资金明细类型：0，微信充值，1，支付，2，返利，3，提现*/
     public static final Integer CASH_RECHARGE= 0;
     public static final Integer CASH_PAY= 1;
     public static final Integer CASH_REBATE= 2;
     public static final Integer CASH_WITHDRAWALS= 3;
     
     /** 积分类型 订单积分  */
     public static final Integer ORDER_INTEGRAL= 1;
     /** 积分类型 返利积分  */
     public static final Integer REBATE_INTEGRAL= 2;
     /** 积分类型 签到积分  */
     public static final Integer SIGN_INTEGRAL= 3;
     
     public static final String RECOMMONDCODE_DEFULT="0000";
     public static final Integer SAVE_SHOP_NOT_BIND = 0;
     /**推荐规则方式 金额**/
     public static final Integer RECOMMENDED_RULES_REWARD_MONEY =1;
     
     /**
      * 积分商城支付状态
      */
     public static final Integer integrall_mall_pay_1104 = 1104;
     public static final Integer integrall_mall_pay_1106 = 1106;
     
     /**
      *积分规则类型
      *0:百分比赠送
      *1：满额送
      */
     public static final Integer INTEGRALL_TYPE_0 = 0;
     
     /**
      *积分规则类型
      *0:百分比赠送
      *1：满额送
      */
     public static final Integer INTEGRALL_TYPE_1 = 1;
     
     /**
      * 积分商城发货状态
      */
     public static final Integer integrall_mall_plat_1108 = 1108;
     public static final Integer integrall_mall_plat_1110 = 1110;
     
     /**
      * 店铺权限开通类型
      */
     public static final String SHOP_SETTLEMENT = "settlement"; //上架货品平台结算权限
     public static final String SHOP_MEDAL_AGENT = "medalAgent";//金牌代理商权限
     
     /**
      * 店铺权限开通返利的配置值
      */
     public static final String SETTLEMENT_TO_SETTLEMENT = "settlementToSettlement"; //6800推荐6800
     public static final String SETTLEMENT_TO_MEDALAGENT = "settlementToMedalAgent"; //6800推荐20000
     public static final String MEDALAGENT_TO_SETTLEMENT = "medalAgentToSettlement"; //20000推荐6800
     public static final String MEDALAGENT_TO_MEDALAGENT = "medalAgentToMedalAgent"; //20000推荐20000
     
     /**
      * 用于信息数量提示
      * */
     public static final String BIND_WEIXIN_FLAG = "bindingWeixinFlag";
     
     public static final String INFO_COUNT_SUM = "infoCountSum"; 
}
