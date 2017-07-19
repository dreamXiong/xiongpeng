package com.liguo.hgl.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.common.MessageEnum;
import com.liguo.hgl.exceptions.TransactionException;
import com.liguo.hgl.proxydao.dao.TbAccountMapper;
import com.liguo.hgl.proxydao.dao.TbCashAccountMapper;
import com.liguo.hgl.proxydao.dao.TbFreezingMapper;
import com.liguo.hgl.proxydao.dao.TbIntegralDetailedMapper;
import com.liguo.hgl.proxydao.dao.TbIntegralMallMapper;
import com.liguo.hgl.proxydao.dao.TbIntegralMallRecordMapper;
import com.liguo.hgl.proxydao.dao.TbIntegralMapper;
import com.liguo.hgl.proxydao.dto.IntegralExchangeForm;
import com.liguo.hgl.proxydao.dto.IntegralMallRecordDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbAccount;
import com.liguo.hgl.proxydao.model.TbCashAccount;
import com.liguo.hgl.proxydao.model.TbFreezing;
import com.liguo.hgl.proxydao.model.TbIntegral;
import com.liguo.hgl.proxydao.model.TbIntegralDetailed;
import com.liguo.hgl.proxydao.model.TbIntegralMall;
import com.liguo.hgl.proxydao.model.TbIntegralMallRecord;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbIntegralMallRecordService;
import com.liguo.hgl.service.TbWapOrderGroupService;

@Service
@Scope(value="prototype")
public class TbIntegralMallRecordServiceImpl implements TbIntegralMallRecordService {
    @Autowired
    private TbIntegralMallRecordMapper tbIntegralMallRecordMapper;
    @Autowired
    private TbIntegralMallMapper tbIntegralMallMapper;
    @Autowired
    private TbIntegralMapper tbIntegralMapper;
	@Autowired
	protected TbWapOrderGroupService tbWapOrderGroupService;
	@Autowired
	private TbAccountMapper tbAccountMapper;
	@Autowired
	private TbFreezingMapper tbFreezingMapper;
	@Autowired
	private TbIntegralDetailedMapper tbIntegralDetailedMapper;
	@Autowired
	private TbCashAccountMapper tbCashAccountMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbIntegralMallRecordServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbIntegralMallRecordMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbIntegralMallRecord selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbIntegralMallRecordMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<IntegralMallRecordDto> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbIntegralMallRecordMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbIntegralMallRecordMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbIntegralMallRecord record) throws RuntimeException {
        try {
            return this.tbIntegralMallRecordMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbIntegralMallRecord record) throws RuntimeException {
        try {
            return this.tbIntegralMallRecordMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbIntegralMallRecord record) throws RuntimeException {
        try {
            return this.tbIntegralMallRecordMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

	@Override
	public List<IntegralMallRecordDto> selectByObject(Criteria example,PageDto pgo) throws RuntimeException {
		 try {
            return this.tbIntegralMallRecordMapper.selectByObject(example,pgo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}

	@Override
	public Map<String,Object> wantPay(IntegralExchangeForm form,Integer userId) throws RuntimeException {
		try {
		   	//根据积分商城ID查询
		   	TbIntegralMall integralMall = tbIntegralMallMapper.selectByPrimaryKey(form.getId());
		   	//查询出该用户的积分
		   	Criteria criteria = new Criteria();
			criteria.put("userId", userId);
			TbIntegral integral = tbIntegralMapper.selectByUserIdObject(criteria);
			//判断积分是否大于当前用户的积分
			if(integralMall.getIntegral() > integral.getIntegral()){
				return null;
			}
			//根据选择的收货地址ID查询
			Integer addressHistoryId = tbWapOrderGroupService.modifyAddressHistory(form.getAddressId(),form.getPhone(),form.getRecipient(),userId);
			//执行插入方法
			return insertIntegralMallRecord(addressHistoryId,userId,form,integralMall,integral);
        } catch (Exception e) {
        	logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
	}
	
    public Map<String,Object> insertIntegralMallRecord(Integer addressHistoryId,Integer userId,IntegralExchangeForm form,TbIntegralMall integralMall,TbIntegral integral) throws RuntimeException {
    	Map<String,Object> param = new HashMap<String, Object>();
    	TbIntegralMallRecord record = new TbIntegralMallRecord();
		record.setAddressId(addressHistoryId);
		record.setCreateBy(userId);
		record.setCreateDt(System.currentTimeMillis());
		record.setExchangeNum(form.getExchangeNum());
		record.setRemark(form.getRemark());     
		record.setIntegralMallId(form.getId());
		String orderSerialNo = tbWapOrderGroupService.makeOrderNum("J");
		record.setIntegralSerialNo(orderSerialNo);
		//如果支付金额大于0,支付金额乘于数量=支付金额
		double payMoney = 0;
		if(integralMall.getPayAmount() >0){
			BigDecimal payAmount = new BigDecimal(integralMall.getPayAmount());
			payMoney = payAmount.multiply(new BigDecimal(form.getExchangeNum())).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		}
		record.setPayMoney(payMoney);
		record.setPayStatus(HglContants.integrall_mall_pay_1104);
		record.setPlatStatus(HglContants.integrall_mall_plat_1108);
		record.setUserId(userId);
		record.setRemainingIntegral((integralMall.getIntegral()*form.getExchangeNum()));
		int count = tbIntegralMallRecordMapper.insert(record);
		logger.debug("插入积分商城记录表受影响的行数: " + count);
		param.put("integralMallRecordId", record.getId());
		//如果等于0说明不需要支付,修改状态和添加积分明细
		if(payMoney==0){
			TbIntegralMallRecord t = tbIntegralMallRecordMapper.selectByPrimaryKey(record.getId());
			updateIntegralMallRecordStatus(t,t.getUserId());
		}
		return param;
    }
	
	/**
	 * 支付操作
	 * */
	public void payMyOrderGroup(Integer orderId, Integer accountId) throws TransactionException {
		//根据账户查询
		TbAccount tbAccount = tbAccountMapper.selectByPrimaryKey(accountId);
		//根据积分商城订单查询
		TbIntegralMallRecord t = tbIntegralMallRecordMapper.selectByPrimaryKey(orderId);
		
		BigDecimal balance = new BigDecimal(tbAccount.getBalance());
		BigDecimal freeze = new BigDecimal(tbAccount.getFreeze());
		BigDecimal payMoney = new BigDecimal(t.getPayMoney());

		logger.debug("money:" + balance.subtract(freeze).doubleValue() + ",payMoney" + t.getPayMoney() + ",accountId" + tbAccount.getId());
		
		// 支付前先判断可用余额是否大于订单总额
		if ((balance.subtract(freeze).doubleValue()) - t.getPayMoney() >= 0) {
			// 减去用户的账户金额和冻结金额
			tbAccount.setBalance(balance.subtract(payMoney).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			tbAccountMapper.updateByPrimaryKeySelective(tbAccount);

			//插入资金记录表
			insertCashAccount(t,tbAccount);
			
			//更改状态和扣除积分
			updateIntegralMallRecordStatus(t,t.getUserId());
		} else {
			logger.debug("金额不足，支付失败");
			throw new TransactionException(MessageEnum.Z2001);
		}
	}
	
	/**
	 * 插入资金明细记录
	 * @param t
	 * @param tbAccount
	 */
	public void insertCashAccount(TbIntegralMallRecord t,TbAccount tbAccount) {
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
	
	/**
	 * 支付回调处理,增加资金记录表记录,修改积分状态,增加积分明细表记录
	 * @param t
	 */
	public void payCallUpdate(TbIntegralMallRecord t) {
		//根据用户ID查询出账号
		TbAccount tbAccount = tbAccountMapper.selectAccountByUserId(t.getUserId());
		//插入资金记录表
		insertCashAccount(t,tbAccount);
		//更改状态和扣除积分
		updateIntegralMallRecordStatus(t,t.getUserId());
	 }

	/**
	 * 根据订单的序列号查询
	 */
	@Override
	public TbIntegralMallRecord selectBySerialNo(Criteria example) throws RuntimeException {
		try {
            return this.tbIntegralMallRecordMapper.selectBySerialNo(example);
        } catch (Exception e) {
        	logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
	}
	
}