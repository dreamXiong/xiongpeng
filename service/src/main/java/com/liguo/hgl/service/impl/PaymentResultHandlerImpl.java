package com.liguo.hgl.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbPayContextParam;
import com.liguo.hgl.proxydao.model.TbPayWater;
import com.liguo.hgl.proxydao.util.StringUtil;
import com.liguo.hgl.service.PaymentResultHandler;
import com.liguo.hgl.service.TbPayContextParamService;
import com.liguo.hgl.service.TbPayWaterService;

@Service
public class PaymentResultHandlerImpl implements PaymentResultHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TbPayWaterService tbPayWaterService;
    
    @Autowired
    private TbPayContextParamService tbPayContextParamService;
    
    
    @Override
    public void savePaymentResult(TbPayWater tbPayWater) throws RuntimeException {
        String orderNo =tbPayWater.getOrderGroupNo();
        Assert.isNull(orderNo);
        
        //取当前流水中最新的一条记录，通过订单号和第三方状态查询
        Criteria criteria = new Criteria();
        criteria.put("orderGroupNo", orderNo);
        criteria.put("thdTradeNo", HglContants.THD_TRADE_STATUS_UNKNOWN);
        criteria.setMysqlOffset(0);
        criteria.setMysqlLength(1);
        criteria.setOrderByClauseDesc("pay_date_start");
        
        List<TbPayWater> tbPayWaterList =tbPayWaterService.selectByObject(criteria);
        
        //如果查询为空，是异常行为
        Assert.isTrue(CollectionUtils.isEmpty(tbPayWaterList));
        Integer versiion =tbPayWaterList.get(0).getVersion();
        tbPayWater.setVersion(versiion);
        
        //更新流水表
        tbPayWaterService.updateByPrimaryKey(tbPayWater);
        
        criteria = new Criteria();
        criteria.put("orderGroupNo", orderNo);
        List<TbPayContextParam> tbPayContextParamList =tbPayContextParamService.selectByObject(criteria);
        
        //如果查询为空，是异常行为
        Assert.isTrue(CollectionUtils.isEmpty(tbPayContextParamList));
        TbPayContextParam tbPayContextParam =tbPayContextParamList.get(0);
        String payStatusCode =tbPayWater.getPayStatusCode();
        tbPayContextParam.setTradeStatus(Integer.parseInt(payStatusCode));
        
        //更新支付单表
        tbPayContextParamService.updateByPrimaryKeySelective(tbPayContextParam);
        
        
        /**************以下是修改订单逻辑调用 start**********************/
        
        
        
        /**************修改订单逻辑调用 end**********************/
        
        /*****************判断支付结果start******************************/
        if(HglContants.PAY_RESULT_SUCCESS.equals(payStatusCode)){
            //余额接口调用
        }
        
        
        /*****************判断支付结果end******************************/
        
        
    }

}
