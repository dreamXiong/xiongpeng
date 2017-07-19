package com.liguo.hgl.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liguo.hgl.common.MessageEnum;
import com.liguo.hgl.exceptions.TransactionException;
import com.liguo.hgl.proxydao.dao.TbAccountMapper;
import com.liguo.hgl.proxydao.dao.TbFreezingMapper;
import com.liguo.hgl.proxydao.dao.TbWithdrawalsMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbAccount;
import com.liguo.hgl.proxydao.model.TbAccountBank;
import com.liguo.hgl.proxydao.model.TbFreezing;
import com.liguo.hgl.proxydao.model.TbWithdrawals;
import com.liguo.hgl.service.TbAccountBankService;
import com.liguo.hgl.service.TbAccountService;

@Service
@Scope(value="prototype")
public class TbAccountServiceImpl implements TbAccountService {
    @Autowired
    private TbAccountMapper tbAccountMapper;
    
    @Autowired
    private TbFreezingMapper tbFreezingMapper;
    
    @Autowired
    private TbWithdrawalsMapper tbWithdrawalsMapper;
    
    @Autowired
    private TbAccountBankService tbAccountBankService;
    
    private static final Logger logger = LoggerFactory.getLogger(TbAccountServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbAccountMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbAccount selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbAccountMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbAccount> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbAccountMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
   
    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbAccountMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbAccount record) throws RuntimeException {
        try {
            return this.tbAccountMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbAccount record) throws RuntimeException {
        try {
            return this.tbAccountMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbAccount record) throws RuntimeException {
        try {
            return this.tbAccountMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public int insert(TbAccount record) throws RuntimeException {
        try {
            return this.tbAccountMapper.insert(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public int withdrawalsSave(Integer accountId,Integer accountBankId,Double money,Integer userId,Integer shopId) throws TransactionException{
    	TbAccount tbAccount = tbAccountMapper.selectByPrimaryKey(accountId);
    	
    	BigDecimal balance = new BigDecimal(tbAccount.getBalance());
    	BigDecimal freezed = new BigDecimal(tbAccount.getFreeze());
    	
    	double withdrawalsMoney = balance.subtract(freezed).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		if(withdrawalsMoney < money){
			logger.debug("金额不足");
        	throw new TransactionException(MessageEnum.Z2004);
		}
		//冻结账户提现金额
		BigDecimal aBalance = new BigDecimal(tbAccount.getFreeze());
    	BigDecimal freeze = new BigDecimal(money);
    	tbAccount.setFreeze(aBalance.add(freeze).doubleValue());
    	if(StringUtils.isBlank(tbAccount.getAccountbankId()+"")){
    		tbAccount.setAccountbankId(accountBankId);
    	}
    	tbAccountMapper.updateByPrimaryKey(tbAccount);
    	
    	//生成冻结明细
    	TbFreezing tbFreezing = new TbFreezing();
    	tbFreezing.setOrderId(0);
    	tbFreezing.setAccountId(accountId);
    	tbFreezing.setOrderSerialNo("");
    	tbFreezing.setFreezeMoney(money);
    	tbFreezing.setUnfreezeMoney(0.0);
    	tbFreezing.setBalance(tbAccount.getBalance());
    	tbFreezing.setFreeze(tbAccount.getFreeze());
    	tbFreezing.setOpearDt(System.currentTimeMillis());
    	tbFreezing.setFinish(0);
    	tbFreezingMapper.insertSelective(tbFreezing);
    	
    	TbWithdrawals tbWithdrawals = new TbWithdrawals();
    	tbWithdrawals.setUserId(userId);
    	tbWithdrawals.setAccountId(accountId);
    	tbWithdrawals.setState(0);
    	tbWithdrawals.setMoney(money);
    	tbWithdrawals.setAccountbankId(accountBankId);
    	tbWithdrawals.setOperationDt(System.currentTimeMillis());
    	tbWithdrawals.setVersion(0);
    	TbAccountBank tbAccountBank = tbAccountBankService.selectByPrimaryKey(accountBankId);
		tbWithdrawals.setAuditPerson(1);
		tbWithdrawals.setShopId(0);
    	tbWithdrawals.setBankAccount(tbAccountBank.getBankAccount());
    	tbWithdrawals.setBank(tbAccountBank.getBank());
    	tbWithdrawals.setCardHolder(tbAccountBank.getName());
    	tbWithdrawals.setShopId(shopId);
    	tbWithdrawals.setFreezing(tbFreezing.getId());
    	tbWithdrawalsMapper.insert(tbWithdrawals);
    	return 0;
    }

	@Override
	public TbAccount selectAccountByUserId(Integer id){
	    return this.tbAccountMapper.selectAccountByUserId(id);
	    
	}
}