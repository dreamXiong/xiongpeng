package com.liguo.hgl.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSONObject;
import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbPayContextParam;
import com.liguo.hgl.proxydao.model.TbPayParamMessage;
import com.liguo.hgl.proxydao.model.TbPayWater;
import com.liguo.hgl.proxydao.util.BeanUtil;
import com.liguo.hgl.proxydao.util.HttpMethod;
import com.liguo.hgl.proxydao.util.StringUtil;
import com.liguo.hgl.service.PaymentHandler;
import com.liguo.hgl.service.TbPayContextParamService;
import com.liguo.hgl.service.TbPayParamMessageService;
import com.liguo.hgl.service.TbPayWaterService;
import com.liguo.hgl.util.Des3;

@Service
public class PaymentHttpHandlerImpl implements PaymentHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // 支付参数信息
    @Autowired
    private TbPayParamMessageService tbPayParamMessageService;
    
    @Autowired
    private TbPayWaterService tbPayWaterService;
    
    @Autowired
    private TbPayContextParamService tbPayContextParamService;

    @Override
    public Map<String, Object> goToPayment(Map<String, Object> param, String payType) throws RuntimeException {
        // 这里不允许为空
        Assert.notNull(payType);
        
        // 根据支付类型去分发至不同的支付方法
        if ("1".equals(payType)) {//1微信公众号支付
            return  goTo_wx_http(param,payType);
        }
        // 根据支付类型去分发至不同的支付方法
        else if ("2".equals(payType)) {//2微信扫码支付
            return  goTo_wx_http(param,payType);
        }else{
            logger.info(">>>>>>>>>>>>>>>payType is not found!!"+payType);
        }
        
        return null;

    }

    /***
     * 
     * <支付宝http协议支付方法,暂时假银行不支持回调,所以采用了返回result结果>
     * 
     * @param param
     *            订单信息
     * @author wzt
     * @throws IllegalAccessException 
     * @throws IllegalArgumentException 
     * @since 2016年5月20日
     */
    public Map<String, Object> goTo_wx_http(Map<String, Object> param,String type){
        logger.info(">>>>>>>>>>>>>>start  goTo_wx_http...");   
        /** 协议定义:http:***/
//        String header = "http://";
        // 获得订单号
        String orderNo = String.valueOf(param.get("orderSerialNo"));
        
        String shopId = (String)param.get("shopId");
        
        String payment = String.valueOf(param.get("payment"));
        
        String accountId = String.valueOf(param.get("accountId"));
        
        String openId = String.valueOf(param.get("open_id"));
        
        // 获得支付金额
        BigDecimal pay_amt = new BigDecimal(String.valueOf(param.get("payMoney")));
        logger.info(">>>>>>>>>>>>>>>>>openId  : "+openId);
        logger.info(">>>>>>>>>>>>>>>>>orderNo  deCode old "+orderNo);
        logger.info(">>>>>>>>>>>>>>>>>pay_amt  deCode old "+pay_amt);
        logger.info(">>>>>>>>>>>>>>>>>shopId  : "+shopId);
        logger.info(">>>>>>>>>>>>>>>>>payment  : "+payment);
        logger.info(">>>>>>>>>>>>>>>>>accountId  : "+accountId);
        
        //加密后的金额
        String payAmt = "";
        //加密后的订单号
        String orderNoDecode = "";
        try {
            orderNoDecode =Des3.encode(orderNo);
            payAmt=Des3.encode(pay_amt.toString());
            logger.info(">>>>>>>>>>>>>>>>>orderNo  deCode  now"+orderNo);
            logger.info(">>>>>>>>>>>>>>>>>pay_amt  deCode  now"+payAmt);
        }
        catch (Exception e) {
            logger.error("3DES enCode is error,error message:"+e.getMessage());
        }

        /************* 在此组装支付宝接口中的数据start *****/
        // 查询支付信息表
        List<TbPayParamMessage> tbPayParamMessageList = getPayMsg(type);
        TbPayParamMessage tbPayParamMessage = tbPayParamMessageList.get(0);
        // addr
        String addr = tbPayParamMessage.getPayAddr();
        logger.info(">>>>>>>>>>>>>>http go to addr value is "+addr);

        //添加报文参数
        Map<String, String> payMap = new HashMap<String, String>();
        payMap.put("out_trade_no", orderNoDecode);
        payMap.put("total_fee", payAmt);
        payMap.put("shop_id", shopId);
        payMap.put("open_id", openId);
//        try {
//            url=URLDecoder.decode(url, "utf-8");
//            payMap.put("return_url", url);
//        }
//        catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
        /************* 在此组装支付宝接口中的数据end *******/
        //第三方特殊字段初始化
        TbPayWater tbPayWater = new TbPayWater();
        tbPayWater.setOrderGroupNo(orderNo);//订单号
        tbPayWater.setPayAmt(pay_amt);  //金额
        tbPayWater.setPayType(tbPayParamMessage.getPayTypeCode());//支付方式
        pay_initialize(tbPayWater, tbPayParamMessage);
        
        //生成支付单
        TbPayContextParam tbPayContextParam = new TbPayContextParam();
        tbPayContextParam.setAccountId(accountId);
        tbPayContextParam.setPayment(payment);
        tbPayContextParam.setOrderGroubNo(orderNo);
        single_payment_initialize(tbPayContextParam);
        
//        HttpMethod.URLGet(addr, payMap, "utf-8");
        param=HttpMethod.getResult(addr, payMap, "utf-8");
        logger.info(">>>>>>>>>>>>>>>>> http result map value :" + param);
        return param;
    }

    private List<TbPayParamMessage> getPayMsg(String type) {
        Criteria criteria = new Criteria();
        criteria.put("payTypeCode", type);// 微信支付
        List<TbPayParamMessage> tbPayParamMessageList = tbPayParamMessageService.selectByObject(criteria);
        return tbPayParamMessageList;
    }

    /***
     * 
     * <支付单初始化操作>
     * @param orderNo
     * @author wzt
     * @since   2016年6月20日
     */
    private void single_payment_initialize(TbPayContextParam tbPay) {
        //此处不能为空
        Assert.isTrue(tbPay!=null);
        Criteria criteria = new Criteria();
        criteria.put("orderGroubNo", tbPay.getOrderGroubNo());
        List<TbPayContextParam>  tbPayContextParamList =tbPayContextParamService.selectByObject(criteria);
        
        TbPayContextParam tbPayContextParam = new TbPayContextParam();
        tbPayContextParam.setOrderGroubNo(tbPay.getOrderGroubNo());
        tbPayContextParam.setTradeStatus(HglContants.PAY_RESULT_UNKNOWN);
        tbPayContextParam.setAccountId(tbPay.getAccountId());
        tbPayContextParam.setPayment(tbPay.getPayment());
        //如果支付单存在,则更新支付单，不存在就新增记录
        if(CollectionUtils.isEmpty(tbPayContextParamList)){
            tbPayContextParamService.insertSelective(tbPayContextParam);
        }else{
            tbPayContextParamService.updateByPrimaryKeySelective(tbPayContextParam);
        }
        
    }
    
    /**
     * 支付状态码设置成，开始-结果状态码设置成-未知，第三方交易状态设置成-未知，启用状态设置成-启用
     * <支付初始化参数>
     * @author wzt
     * @since   2016年5月23日
     */
    private int pay_initialize(TbPayWater tbPayWater,TbPayParamMessage TbPayParamMessage){
        //支付时间开始时间
        tbPayWater.setPayDateStart(new Date());
        //支付状态码
        tbPayWater.setPayStatusCode(HglContants.PAY_STATUS_BEGIN);
        //结果状态码
        tbPayWater.setPayResultCode(HglContants.PAY_RESULT_UNKNOWN.toString());
        //第三方交易状态
        tbPayWater.setThdTradeStatus(HglContants.THD_TRADE_STATUS_UNKNOWN);
        //启用状态
        tbPayWater.setPayStatus(String.valueOf(HglContants.USER_STATUS_Valid));
        return tbPayWaterService.insertSelective(tbPayWater);
        
    }
    
    /**
     * 
     * <支付结束>
     * @author wzt
     * @since   2016年5月23日
     */
    private void updatePayWater(TbPayWater  tbPayWater){
        logger.info(">>>>>>>>>>> start pay_end ....");
        String orderGroupNo =tbPayWater.getOrderGroupNo();
        Criteria criteria = new Criteria();
        criteria.put("orderGroupNo", orderGroupNo);
        criteria.setMysqlOffset(0);
        criteria.setMysqlLength(1);
        criteria.setOrderByClauseDesc("pay_date_start");
        //根据订单号查询最新的一笔流水
        List<TbPayWater> TbPayWaterList =tbPayWaterService.selectByObject(criteria);
        //这里不允许为空
        Assert.isTrue(!CollectionUtils.isEmpty(TbPayWaterList));
        TbPayWater tbPayWaterTemp =TbPayWaterList.get(0);
        
        tbPayWater.setId(tbPayWaterTemp.getId());
        //支付结束时间
        tbPayWater.setPayDateEnd(new Date());
        
        //支付状态码
        tbPayWater.setPayStatus(HglContants.PAY_STATUS_END);
        //第三方交易状态
        tbPayWaterService.updateByPrimaryKeySelective(tbPayWater);
    }
    
    
    /**
     * 
     * <支付结束>
     * @author wzt
     * @since   2016年5月23日
     */
    private void updatePayConParam(TbPayWater  tbPayWater){
        logger.info(">>>>>>>>>>> start pay_end ....");
        String orderGroupNo =tbPayWater.getOrderGroupNo();
        Criteria criteria = new Criteria();
        criteria.put("orderGroupNo", orderGroupNo);
        //根据订单号查询最新的一笔流水
        List<TbPayContextParam> tbPayContextParamList =tbPayContextParamService.selectByObject(criteria);
        //这里不允许为空
        Assert.isTrue(!CollectionUtils.isEmpty(tbPayContextParamList));
        TbPayContextParam tbPayContextParam =tbPayContextParamList.get(0);
        
        tbPayContextParam.setId(tbPayContextParam.getId());
        String payStatusCode =tbPayWater.getPayStatusCode();
        //更新支付结果状态
        tbPayContextParam.setTradeStatus(Integer.valueOf(payStatusCode));
        
        //第三方交易状态
        tbPayContextParamService.updateByPrimaryKeySelective(tbPayContextParam);
    }
    
    
    
    /***
     * 
     * <支付返回适配器>
     * @param tbPayWater
     * @param payType
     * @author wzt
     * @since   2016年7月3日
     */
    public void result_payment(TbPayWater  tbPayWater){
        String payType =tbPayWater.getPayType();
     // 根据支付类型去分发至不同的支付方法
        if ("1".equals(payType)) {//1微信公众号支付
            result_wx_http(tbPayWater);
        }
        else if ("2".equals(payType)) {//2扫码支付
            result_wx_http(tbPayWater);
        }
        else{
            logger.info(">>>>>>>>>>>>>>>payType is not found!!" +payType );
        }
        
    }
    /***
     * 
     * <微信支付返回适配器>
     * @param tbPayWater
     * @author wzt
     * @since   2016年7月3日
     */
    public void result_wx_http(TbPayWater  tbPayWater){
        logger.info(">>>>>>>>>>start result_wx_http ...");
        updatePayWater(tbPayWater);
        updatePayConParam(tbPayWater);
        Map<String,Object> map = new HashMap<String,Object>();
        String orderGroupNo =tbPayWater.getOrderGroupNo();
        try {
            BeanUtil.CopyBeanToMap(tbPayWater, map);
            //加密订单号
            orderGroupNo =  Des3.encode(orderGroupNo);
            map.put("orderGroupNo", orderGroupNo);            
        }
        catch (Exception e) {
            logger.info("copy bean is error ,error messsage:"+e.getMessage());
        }
        String type= tbPayWater.getPayType();
        List<TbPayParamMessage>  mesList =getPayMsg(type);
        String addr =mesList.get(0).getPayReturnUrl();
        HttpMethod.URLGetObjectMap(addr, map, "utf-8");
        logger.info(">>>>>>>>>>>>>>>>>>>>end result_wx_http...");
        
    }
    
    public static void main(String[] args) {
        try {
            Map<String,Object> m = new HashMap<String,Object>();
            System.out.println((String)m.get("key") == null);
            System.out.println(StringUtil.isBlank(String.valueOf(m.get("key"))));
//            String shopId = String.valueOf();null
//            System.out.println(shopId);
//            Map<String,String> s = new HashMap<String,String>();
//            s.put("1", "1");
//            s.put("2", "1");
//            s.put("3", "1");
//            s.put("4", "1");
//            String str =JSONObject.toJSONString(s);
            String ss =Des3.encode("11111");
            System.out.println(Des3.encode("11111"));
            System.out.println(Des3.decode(ss));
//            String str = 
//                    "{\"user\":\"appuser1\",\"mobile\":\"15612345698\",\"qq\":\"1111113250\",\"os\":1,\"ver\":\"1.1\",\"uuid\":\"33333333\",\"content\":\"content\"}";
//            System.out.println(Des3.encode(str));
        }
        catch (Exception e) {
            
            // TODO Auto-generated catch block
            e.printStackTrace();
            
        }
    }
}
