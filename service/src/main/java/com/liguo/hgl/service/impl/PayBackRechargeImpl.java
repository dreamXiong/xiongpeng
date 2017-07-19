package com.liguo.hgl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liguo.hgl.exceptions.TransactionException;
import com.liguo.hgl.proxydao.dao.TbAccountMapper;
import com.liguo.hgl.proxydao.dao.TbCashAccountMapper;
import com.liguo.hgl.proxydao.dao.TbPayWaterMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbAccount;
import com.liguo.hgl.proxydao.model.TbCashAccount;
import com.liguo.hgl.proxydao.model.TbPayWater;
import com.liguo.hgl.service.PayClallBackService;
import com.liguo.hgl.service.TbWapOrderGroupService;


/**
 * * 
*Company:  hgl
* Description:微信充值回调业务层
* @author:zk
* @date 2016-7-29 下午3:35:44
 */
@Service("payBackRechargeImpl")
public class PayBackRechargeImpl implements PayClallBackService {
	
	private static final Logger logger = LoggerFactory.getLogger(TbAccountServiceImpl.class);
	 
	@Autowired
	private TbAccountMapper tbAccountMapper;
	
	@Autowired
	private TbPayWaterMapper tbPayWaterMapper;
	
	@Autowired
	private TbCashAccountMapper tbCashAccountMapper;
	
	 
	@Override
	public String doSomething(String... args) throws TransactionException {
		logger.debug("微信充值recharge >>>>>>>>>>>>>>>>>>>>>>>>>.orderGroupNo:"+args[0]+";>>>>>>>>>accountId:"+args[1]);
    	Criteria pCriteria = new Criteria();
		pCriteria.put("orderGroupNo", args[0]);
		List<TbPayWater> tbPayWaterLsit = tbPayWaterMapper.selectByObject(pCriteria);
		
		TbPayWater tbPayWater = tbPayWaterLsit.get(0);
		BigDecimal payAmt = tbPayWater.getPayAmt();
		logger.debug("recharge >>>>>>>>>>>>>>>>>>>>>>>>>.payAmt:"+payAmt.doubleValue());
		TbAccount tbAccount = tbAccountMapper.selectByPrimaryKey(Integer.parseInt(args[1]));
		BigDecimal aBalance = new BigDecimal(tbAccount.getBalance());
		tbAccount.setBalance(aBalance.add(payAmt).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
		tbAccountMapper.updateByPrimaryKey(tbAccount);
		
		//插入资金明细记录表
		TbCashAccount tbCashAccount = new TbCashAccount();
		tbCashAccount.setCashOut(0.0);
		tbCashAccount.setCashIn(payAmt.doubleValue());
		tbCashAccount.setBalance(tbAccount.getBalance());
		tbCashAccount.setOperationDt(System.currentTimeMillis());
		tbCashAccount.setShopId(0);
		tbCashAccount.setAccountId(Integer.parseInt(args[1]));
		tbCashAccount.setPlatform(0);
		tbCashAccount.setOrderSerialNo(tbPayWater.getOrderGroupNo());
		tbCashAccountMapper.insertSelective(tbCashAccount);
		return "myWallet/index";
    }
}
