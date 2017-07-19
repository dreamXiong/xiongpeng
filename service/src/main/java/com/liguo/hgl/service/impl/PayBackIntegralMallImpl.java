package com.liguo.hgl.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.common.MessageEnum;
import com.liguo.hgl.exceptions.TransactionException;
import com.liguo.hgl.proxydao.dao.TbAccountMapper;
import com.liguo.hgl.proxydao.dao.TbCashAccountMapper;
import com.liguo.hgl.proxydao.dao.TbIntegralDetailedMapper;
import com.liguo.hgl.proxydao.dao.TbIntegralMallRecordMapper;
import com.liguo.hgl.proxydao.dao.TbIntegralMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbAccount;
import com.liguo.hgl.proxydao.model.TbCashAccount;
import com.liguo.hgl.proxydao.model.TbIntegral;
import com.liguo.hgl.proxydao.model.TbIntegralDetailed;
import com.liguo.hgl.proxydao.model.TbIntegralMallRecord;
import com.liguo.hgl.service.PayClallBackService;
import com.liguo.hgl.service.TbIntegralMallRecordService;


/**
 * * 
*Company:  hgl
* Description:积分支付完成后回调业务层
* @author:zk
* @date 2016-7-29 下午3:35:44
 */
@Service("payBackIntegralMall")
public class PayBackIntegralMallImpl implements PayClallBackService {
	
	private static final Logger logger = LoggerFactory.getLogger(TbAccountServiceImpl.class);
	 
	@Autowired
	private TbCashAccountMapper tbCashAccountMapper;
	
	@Autowired
	private TbIntegralMallRecordMapper tbIntegralMallRecordMapper;
	
	@Autowired
	private TbAccountMapper tbAccountMapper;
	
	@Autowired
	private TbIntegralMapper tbIntegralMapper;
	
	@Autowired
	private TbIntegralDetailedMapper tbIntegralDetailedMapper;
	 
	@Override
	public String doSomething(String... args) throws TransactionException {
	    Criteria criteria = new Criteria();
	    criteria.put("integralSerialNo", args[0]);
	    TbIntegralMallRecord t = tbIntegralMallRecordMapper.selectBySerialNo(criteria);
	    if(t == null){
    		logger.debug("积分商城支付回调时，订单号不存在");
        	throw new TransactionException(MessageEnum.Z2005);
        }
		//根据用户ID查询出账号
		TbAccount tbAccount = tbAccountMapper.selectAccountByUserId(t.getUserId());
		//插入资金记录表
		insertCashAccount(t,tbAccount);
		//更改状态和扣除积分
		updateIntegralMallRecordStatus(t,t.getUserId());
	    
	    /*tbIntegralMallRecordService.payCallUpdate(t);*/
		return "integralMallRecord/index";
    }
	
	/**
	 * 插入资金明细记录
	 * @param t
	 * @param tbAccount
	 */
	private void insertCashAccount(TbIntegralMallRecord t,TbAccount tbAccount) {
		//添加资金明细记录
		logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>增加资金明细记录："+t.getIntegralSerialNo());
		TbCashAccount sellerAccount = new TbCashAccount();
		sellerAccount.setCashOut(t.getPayMoney());
		sellerAccount.setBalance(tbAccount.getBalance());
		sellerAccount.setOperationDt(System.currentTimeMillis());
		sellerAccount.setPlatform(1);
		sellerAccount.setAccountId(tbAccount.getId());
		sellerAccount.setOrderSerialNo(t.getIntegralSerialNo());
		int count = tbCashAccountMapper.insertSelective(sellerAccount);
		logger.debug("增加资金明细记录条数: " + count); 
	}
	
	/**
	 * 更改积分记录表中的状态和扣掉积分
	 */
	public void updateIntegralMallRecordStatus(TbIntegralMallRecord t,Integer userId) {
		//更新记录表支付状态
		TbIntegralMallRecord record = new TbIntegralMallRecord();
		record.setPayStatus(HglContants.integrall_mall_pay_1106);
		record.setVersion(t.getVersion());
		record.setId(t.getId());
		int updCount = tbIntegralMallRecordMapper.updateByPrimaryKeySelective(record);
		logger.debug("更改积分商城记录表支付状态条数: " + updCount);
    	//查询出该用户的积分
    	Criteria criteria = new Criteria();
 		criteria.put("userId", userId);
 		TbIntegral integral = tbIntegralMapper.selectByUserIdObject(criteria);
 		TbIntegral integralRecord = new TbIntegral();
 		integralRecord.setIntegral(integral.getIntegral()-t.getRemainingIntegral());
 		integralRecord.setId(integral.getId());
 		integralRecord.setVersion(integral.getVersion());
 		int intUpdCount = tbIntegralMapper.updateByPrimaryKeySelective(integralRecord);
 		logger.debug("扣除我的积分中的积分更新条数: " + intUpdCount);
 		//插入积分明细记录
		TbIntegralDetailed tbIntegralDetailed = new TbIntegralDetailed();
		tbIntegralDetailed.setIntegralId(integral.getId());
		tbIntegralDetailed.setOrderId(t.getId());
		tbIntegralDetailed.setIntegral(t.getRemainingIntegral());
		tbIntegralDetailed.setOrderSerialNo(t.getIntegralSerialNo());
		tbIntegralDetailed.setOpearDt(System.currentTimeMillis());
		tbIntegralDetailed.setType(4);
		tbIntegralDetailed.setVersion(0);
		int insCount = tbIntegralDetailedMapper.insertSelective(tbIntegralDetailed);
		logger.debug("插入积分明细条数: " + insCount);
	}
}
