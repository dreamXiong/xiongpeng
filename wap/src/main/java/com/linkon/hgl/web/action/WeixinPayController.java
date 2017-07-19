package com.linkon.hgl.web.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONObject;
import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.common.QRCodeUtil;
import com.liguo.hgl.dto.MyOrderForm;
import com.liguo.hgl.proxydao.model.TbIntegralMallRecord;
import com.liguo.hgl.proxydao.model.TbPayWater;
import com.liguo.hgl.proxydao.model.TbWapOrderGroup;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.proxydao.util.CommonUtil;
import com.liguo.hgl.service.PaymentHandler;
import com.liguo.hgl.service.TbDealersWeixinConfigService;
import com.liguo.hgl.service.TbIntegralMallRecordService;
import com.liguo.hgl.service.TbWapOrderGroupService;
import com.liguo.hgl.util.Des3;
import com.liguo.hgl.util.MD5Utils;

@Controller
@RequestMapping("/weixinPay")
public class WeixinPayController extends IBaseController {
	private static final Object LOCK_OBJ = "lockerOrder";
	@Autowired
	protected PaymentHandler paymentHandler;
	@Autowired
	protected TbWapOrderGroupService tbWapOrderGroupService;
	@Autowired
	protected TbDealersWeixinConfigService tbDealersWeixinConfigService;
	@Autowired
	protected TbIntegralMallRecordService tbIntegralMallRecordService;
	
	/**
	 * 公众号支付
	 * @param request
	 * @param response
	 * @param model
	 * @throws Exception
	 */
    @RequestMapping(value = "/goPay")
    public void goPay(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception{
    	SortedMap<Object,Object> resultSortedMap = new TreeMap<Object,Object>();
    	try {
			//获取订单号,金额,openId
			String outTradeNo = Des3.decode(request.getParameter("out_trade_no"));
			logger.info("-----------------------outTradeNo: " + outTradeNo);
			String totalFee = Des3.decode(request.getParameter("total_fee"));
			logger.info("-----------------------totalFee: " + totalFee);
			String openId = request.getParameter("open_id");
			logger.info("-----------------------openId: " + openId);
	
			//封装微信预支付需要参数
			SortedMap<Object,Object> parameters = new TreeMap<Object,Object>();
			parameters.put("appid", HglContants.WEIXIN_APP_ID);
			parameters.put("mch_id", HglContants.WEIXIN_MCH_ID);
			parameters.put("device_info", "WEB");
			parameters.put("nonce_str", RandomStringUtils.random(8, "123456789"));
			parameters.put("body", "惠给利微信支付");
			parameters.put("out_trade_no", outTradeNo);
			parameters.put("total_fee", String.valueOf((int)(Double.parseDouble(totalFee)*100))); //单位：分
			parameters.put("spbill_create_ip", getIp(request));
			parameters.put("notify_url", HglContants.WEIXIN_NOTIFY_URL);
			parameters.put("trade_type", "JSAPI");
			parameters.put("openid", openId);  
			parameters.put("sign", createSign(HglContants.WEIXIN_APP_KEY,"UTF-8", parameters));  //创建签名
			
			//调用微信支付接口
			Map<String,String> resultMap = sendWeixinPayParam(parameters);
			
			//封装参数到前台支付
			resultSortedMap.put("appId", HglContants.WEIXIN_APP_ID);
			resultSortedMap.put("timeStamp", Long.toString(new Date().getTime()));
			resultSortedMap.put("nonceStr", resultMap.get("nonce_str"));
			resultSortedMap.put("package", "prepay_id="+resultMap.get("prepay_id"));
			resultSortedMap.put("signType", "MD5");
			resultSortedMap.put("paySign", createSign(HglContants.WEIXIN_APP_KEY,"UTF-8", resultSortedMap));    
			resultSortedMap.put("packageValue", "prepay_id="+resultMap.get("prepay_id"));
			logger.info("-----------------------resultSortedMap: " + resultSortedMap.toString());
		} catch (Exception e) {
			logger.error("-----------------------微信支付异常：" + e.getMessage());
			e.printStackTrace();
		}
    	//响应json
		String json = JSONObject.toJSONString(JSONObject.toJSON(resultSortedMap));
		response.getWriter().write(json);
    }
    
    /**
     * 扫码支付
     * @param request
     * @param response
     * @param model
     * @throws Exception
     */
    @RequestMapping(value = "/nativePay")
    public void nativePay(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception{
    	//获取订单号,金额
		String outTradeNo = Des3.decode(request.getParameter("out_trade_no"));
		logger.info("-----------------------outTradeNo: " + outTradeNo);
		String totalFee = Des3.decode(request.getParameter("total_fee"));
		logger.info("-----------------------totalFee: " + totalFee);

		//封装微信预支付需要参数
		SortedMap<Object,Object> parameters = new TreeMap<Object,Object>();
		parameters.put("trade_type", "NATIVE"); 
		parameters.put("spbill_create_ip", getIp(request)); 
		parameters.put("product_id", outTradeNo); 
		parameters.put("body", "惠给利微信支付");         
		parameters.put("out_trade_no", outTradeNo); 
		parameters.put("total_fee", String.valueOf((int)(Double.parseDouble(totalFee)*100))); //单位：分
		parameters.put("notify_url", HglContants.WEIXIN_NOTIFY_NATIVE_URL); 
		parameters.put("appid", HglContants.WEIXIN_APP_ID);
		parameters.put("mch_id", HglContants.WEIXIN_MCH_ID); 
		parameters.put("nonce_str", RandomStringUtils.random(8, "123456789")); 
		parameters.put("sign",createSign(HglContants.WEIXIN_APP_KEY,"UTF-8", parameters));
		
		//调用微信支付接口
		Map<String,String> resultMap = sendWeixinPayParam(parameters);
		
		//获取扫码支付的二维码连接
		String codeUrl = resultMap.get("code_url");
		logger.info("--------------------------二维码连接："+codeUrl);

		//生成二维码
		QRCodeUtil.encode(codeUrl, HglContants.WECHATCODE_ICON,HglContants.WECHATCODE_PATH, true,outTradeNo);
    }
    
    /**
     * 发送微信支付接口
     * @param parameters
     * @return
     */
    public Map<String,String> sendWeixinPayParam(SortedMap<Object,Object> parameters){
    	//把参数对象转换为xml格式发给微信支付
		String requestXML = getRequestXml(parameters);
		logger.info("-------------------requestXML: " + requestXML);
		
		//发送微信预支付请求
		String weixinResult = CommonUtil.httpsRequestStr(HglContants.WEIXIN_PAY_URL, "POST", requestXML);
		logger.info("-------------------weixinResultStr: " + weixinResult);
		
		//把微信支付返回的值解析为对象
		Map<String,String> resultMap = readStringXmlOut(weixinResult);
		logger.info("-------------------resultMap: " + resultMap);
		return resultMap;
    }
    
	/**
	 * 
	 * 微信支付
	 * 
	 * */
	@RequestMapping(value = "/weixinPay")
	public String weixinPay(MyOrderForm myOrderForm ,Model model,PageDto page,HttpServletRequest request,RedirectAttributes attr){
		boolean isweixin = CommonUtil.isWeiXin(request);
		if (!isweixin) {
			logger.debug("微信5.0 版本之后的才支持微信支付");
			attr.addAttribute("location", "weixinPay/notWeixinBrowser");  
	    	return "redirect:/weixinPay/forwardPayAfterPage";
		}
		if(StringUtils.isBlank(getLoginUser().getOpenId())){
			logger.debug("没有openId,支付失败");
			attr.addAttribute("location", "weixinPay/payFail");  
	    	return "redirect:/weixinPay/forwardPayAfterPage";
		}

		TbWapOrderGroup tbWapOrderGroup = tbWapOrderGroupService.selectByPrimaryKey(myOrderForm.getOrderGroupId());
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("shopId", tbWapOrderGroup.getShopId().toString());
		param.put("orderSerialNo", tbWapOrderGroup.getOrderSerialNo());
		param.put("payMoney",tbWapOrderGroup.getPayMoney());
		param.put("payment","payBackWinxinPayImpl");
		param.put("accountId",getAccountId());
		param.put("open_id",getLoginOpenId());
		//1：微信支付
		Map<String,Object> m = paymentHandler.goToPayment(param, "1");
		logger.debug("微信支付请求发送！..................."+m.toString());
		model.addAttribute("resultSortedMap",m);
		return "weixinPay/pay";
	}
	
	/**
	 * 
	 * 微信充值
	 * 
	 * */
	@RequestMapping(value = "/recharge")
	public String recharge(Model model,HttpServletRequest request,String money,RedirectAttributes attr){
		boolean isweixin = CommonUtil.isWeiXin(request);
		if (!isweixin) {
			logger.debug("微信5.0 版本之后的才支持微信支付");
			attr.addAttribute("location", "weixinPay/notWeixinBrowser");  
	    	return "redirect:/weixinPay/forwardPayAfterPage";
		}
		if(StringUtils.isBlank(getLoginUser().getOpenId())){
			logger.debug("没有openId,支付失败");
			attr.addAttribute("location", "weixinPay/payFail");  
	    	return "redirect:/weixinPay/forwardPayAfterPage";
		}

		Map<String,Object> param = new HashMap<String, Object>();
		param.put("orderSerialNo", makeOrderNum("C"));
		param.put("payMoney",money);
		param.put("accountId",getAccountId());
		param.put("payment","payBackRechargeImpl");
		param.put("open_id",getLoginOpenId());
		logger.debug("微信充值，发送请求..................."+param.toString());
		//1：微信充值
		Map<String,Object> m = paymentHandler.goToPayment(param, "1");
		model.addAttribute("resultSortedMap",m);
		return "weixinPay/pay";
	}

	/**
	 * 
	 * 积分兑换商品金额支付
	 * 
	 * */
	@RequestMapping(value = "/integralMallPay")
	public String integralMallPay(Integer orderGroupId ,Model model,HttpServletRequest request,RedirectAttributes attr){
		boolean isweixin = CommonUtil.isWeiXin(request);
		if (!isweixin) {
			logger.debug("微信5.0 版本之后的才支持微信支付");
			attr.addAttribute("location", "weixinPay/notWeixinBrowser");  
	    	return "redirect:/weixinPay/forwardPayAfterPage";
		}
		if(StringUtils.isBlank(getLoginUser().getOpenId())){
			logger.debug("没有openId,支付失败");
			attr.addAttribute("location", "weixinPay/payFail");  
	    	return "redirect:/weixinPay/forwardPayAfterPage";
		}

		TbIntegralMallRecord integralMallRecord = tbIntegralMallRecordService.selectByPrimaryKey(orderGroupId);
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("orderSerialNo", integralMallRecord.getIntegralSerialNo());
		param.put("payMoney",integralMallRecord.getPayMoney());
		param.put("payment","payBackIntegralMall");
		param.put("open_id",getLoginOpenId());
		//1：微信支付
		Map<String,Object> m = paymentHandler.goToPayment(param, "1");
		logger.debug("微信支付请求发送！..................."+m.toString());
		model.addAttribute("resultSortedMap",m);
		return "weixinPay/pay";
	}
	
    /**
     * 微信支付成功后回调
     * @param request
     * @param response
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/weixinPayCall")
	public void weixinPayCall(HttpServletRequest request,HttpServletResponse response) throws Exception {
		//获取微信支付返回结果 
		Map<String, String> map = callResultParsing(request,response);
		
		//定义支付时候成功的状态码,1代表失败,0代表成功,默认为失败
		String payResultCode = "1";
		if(map != null && map.size()>0){
			//调用本系统回调分发逻辑
			TbPayWater tbPayWater = new TbPayWater();
			tbPayWater.setThdTradeStatus(map.get("result_code"));
			tbPayWater.setOrderGroupNo(map.get("out_trade_no"));
			tbPayWater.setPayType("1");
			payResultCode = map.get("result_code").equalsIgnoreCase("SUCCESS") ? "0" : "1";
			tbPayWater.setPayStatusCode(payResultCode);
			paymentHandler.result_payment(tbPayWater);
		}
		
		//通知微信支付成功或失败
		if("0".equals(payResultCode)){
			logger.info("通知微信公众号支付成功：" + setXML("SUCCESS", "OK"));
			response.getWriter().write(setXML("SUCCESS", "OK"));
		}else{
			logger.info("通知微信公众号支付失败：" + setXML("FAIL", "报文为空"));
			response.getWriter().write(setXML("FAIL", "报文为空"));
		}
	}
    
    /**
     * 微信扫码支付成功后回调
     * @param request
     * @param response
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/nativePayCall")
	public void nativePayCall(HttpServletRequest request,HttpServletResponse response) throws Exception {
		//获取微信支付返回结果 
		Map<String, String> map = callResultParsing(request,response);
		
		//定义支付时候成功的状态码,1代表失败,0代表成功,默认为失败
		String payResultCode = "1";
		if(map != null && map.size()>0){
			//调用本系统回调分发逻辑
			TbPayWater tbPayWater = new TbPayWater();
			tbPayWater.setThdTradeStatus(map.get("result_code"));
			tbPayWater.setOrderGroupNo(map.get("out_trade_no"));
			tbPayWater.setPayType("2");
			payResultCode = map.get("result_code").equalsIgnoreCase("SUCCESS") ? "0" : "1";
			tbPayWater.setPayStatusCode(payResultCode);
			paymentHandler.result_payment(tbPayWater);
		}
		
		//通知微信支付成功或失败
		if("0".equals(payResultCode)){
			logger.info("通知微信扫码支付成功：" + setXML("SUCCESS", "OK"));
			response.getWriter().write(setXML("SUCCESS", "OK"));
		}else{
			logger.info("通知微信扫码支付失败：" + setXML("FAIL", "报文为空"));
			response.getWriter().write(setXML("FAIL", "报文为空"));
		}
	}
    
    /**
     * 解析微信回调返回结果的xml
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public Map<String, String> callResultParsing(HttpServletRequest request,HttpServletResponse response) throws Exception {
    	//解析微信回调返回的xml
    	String inputLine;
		String notityXml = "";
		try {
			while ((inputLine = request.getReader().readLine()) != null) {
				notityXml += inputLine;
			}
			request.getReader().close();
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		logger.info("微信支付回调返回xml：" + notityXml);
		
		//解析微信支付返回结果 
		return readStringXmlOut(notityXml);
    }
    
    /**
     * 设置通知微信支付消息
     * @param return_code
     * @param return_msg
     * @return
     */
    public static String setXML(String return_code, String return_msg) {
        return "<xml><return_code><![CDATA[" + return_code
                + "]]></return_code><return_msg><![CDATA[" + return_msg
                + "]]></return_msg></xml>";
    }
    
    @RequestMapping(value = "/weixinIndex")
    public String weixinIndex(HttpServletRequest request, HttpServletResponse response,Model model) throws Exception{
    	return "weixinPay/open";
    }
    
    /**
     * 发送微信支付的对象转换为xml方式
     * @param parameters
     * @return
     */
    public static String getRequestXml(SortedMap<Object,Object> parameters){
        StringBuffer sb = new StringBuffer();
        sb.append("<xml>");
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while(it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            String k = (String)entry.getKey();
            String v = (String)entry.getValue();
            if ("attach".equalsIgnoreCase(k)||"body".equalsIgnoreCase(k)||"sign".equalsIgnoreCase(k)) {
                sb.append("<"+k+">"+"<![CDATA["+v+"]]></"+k+">");
            }else {
                sb.append("<"+k+">"+v+"</"+k+">");
            }
        }
        sb.append("</xml>");
        return sb.toString();
    }
    
    /**
     * 创建支付签名
     * @param characterEncoding
     * @param parameters
     * @return
     */
    public static String createSign(String key,String characterEncoding,SortedMap<Object,Object> parameters){
        StringBuffer sb = new StringBuffer();
        Set es = parameters.entrySet();
        Iterator it = es.iterator();
        while(it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            String k = (String)entry.getKey();
            Object v = entry.getValue();
            if(null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + key);
        String sign = MD5Utils.MD5Encode(sb.toString(), characterEncoding).toUpperCase();
        return sign;
    }

	/**
	 * 获取ip
	 * @param request
	 * @return
	 */
    @RequestMapping(value = "/getIp")
	public static String getIp(HttpServletRequest request) {
		if (request == null)
			return "";
		String ip = request.getHeader("X-Requested-For");
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Forwarded-For");
		}
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if(ip.indexOf(",") != -1){
			ip = ip.substring(0, ip.indexOf(","));
		}
		return ip;
	}
    
    /**
	 * 
	 * @description 将xml字符串转换成map
	 * @param xml
	 * @return Map
	 */
	public static Map<String,String> readStringXmlOut(String xml) {
		Map<String,String> map = new HashMap<String,String>();
		try {
			// 将字符串转为XML
			Document doc = DocumentHelper.parseText(xml); 
			// 获取根节点
			Element rootElt = doc.getRootElement(); 
			// 获取根节点下的所有子节点
			Iterator it = rootElt.elementIterator();
		    while(it.hasNext()) {
		        Element entry = (Element)it.next();
		        map.put(entry.getName(), entry.getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	@Override
	protected void init(PageDto page, HttpServletRequest request,
			HttpServletResponse response, Model model) {
	}

	@Override
	public String doSearchResult() {
		return null;
	}
	
	/**
	 * 支付后重定向跳转的页面
	 * @param location
	 * @return
	 */
    @RequestMapping(value = "/payAfterPage")
	public String payAfterPage(String location,RedirectAttributes attr) {
    	attr.addAttribute("location", location);  
    	return "redirect:/weixinPay/forwardPayAfterPage";
    }
    
	/**
	 * 支付后转发跳转的页面
	 * @param location
	 * @return
	 */
    @RequestMapping(value = "/forwardPayAfterPage")
	public String forwardPayAfterPage(String location) {
    	return location;
    }
    
    /**
     * 生成订单号 
     * @param prefix
     * @return
     */
    private String makeOrderNum(String prefix) {
		String finOrderNum = "";
		try {
			// 最终生成的订单号
			synchronized (LOCK_OBJ) {
				long nowLong = Long.parseLong(new SimpleDateFormat(
						"yyMMddHHmmssSSS").format(new Date()));
				for (int i = 0; i < 3; i++) {
					finOrderNum = finOrderNum + (int) (Math.random() * 10) + "";
				}
					return prefix + nowLong + finOrderNum;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return finOrderNum;
	}
}
