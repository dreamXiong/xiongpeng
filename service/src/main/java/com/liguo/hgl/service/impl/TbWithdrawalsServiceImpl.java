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
import com.liguo.hgl.proxydao.dao.TbAccountMapper;
import com.liguo.hgl.proxydao.dao.TbCashAccountMapper;
import com.liguo.hgl.proxydao.dao.TbFreezingMapper;
import com.liguo.hgl.proxydao.dao.TbWebUserMapper;
import com.liguo.hgl.proxydao.dao.TbWithdrawalsMapper;
import com.liguo.hgl.proxydao.dto.WithdrawalsDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbAccount;
import com.liguo.hgl.proxydao.model.TbCashAccount;
import com.liguo.hgl.proxydao.model.TbFreezing;
import com.liguo.hgl.proxydao.model.TbWithdrawals;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbWithdrawalsService;
import com.liguo.hgl.util.SmsSend;

@Service
@Scope(value="prototype")
public class TbWithdrawalsServiceImpl implements TbWithdrawalsService {
    @Autowired
    private TbWithdrawalsMapper tbWithdrawalsMapper;
    
    @Autowired
    private TbFreezingMapper tbFreezingMapper;
    
    @Autowired
    private TbAccountMapper tbAccountMapper;
    
    @Autowired
    private TbCashAccountMapper tbCashAccountMapper;
    
    @Autowired
    private TbWebUserMapper tbWebUserMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbWithdrawalsServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbWithdrawalsMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbWithdrawals selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbWithdrawalsMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbWithdrawals> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbWithdrawalsMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbWithdrawalsMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbWithdrawals record) throws RuntimeException {
        try {
            return this.tbWithdrawalsMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbWithdrawals record) throws RuntimeException {
        try {
            return this.tbWithdrawalsMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbWithdrawals record) throws RuntimeException {
        try {
            return this.tbWithdrawalsMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

	@Override
	public List<WithdrawalsDto> selectWithdrawalsPage(Criteria criteria,
			PageDto page) {
		List<WithdrawalsDto> withdrawalsDtos = tbWithdrawalsMapper.selectWithdrawalsPage(criteria,page);
		return withdrawalsDtos;
	}

	@Override
	public Map<String, Object> auditWithrawals(Integer id, Integer loginUserId) {
		Map<String, Object> map = new HashMap<String, Object>();
		int count =0;
		TbWithdrawals withdrawals = tbWithdrawalsMapper.selectByPrimaryKey(id);
		TbFreezing tbFreezing = tbFreezingMapper.selectByPrimaryKey(withdrawals.getFreezing());
		if(withdrawals!=null){
			//修改冻结记录表
			tbFreezing.setFinish(HglContants.FREEZING_FINISH_PASS);
			tbFreezingMapper.updateByPrimaryKeySelective(tbFreezing);
			//修改用户资金表
			TbAccount tbAccount = tbAccountMapper.selectByPrimaryKey(withdrawals.getAccountId());
			
			BigDecimal freeze = new BigDecimal(tbAccount.getFreeze());
	    	BigDecimal freezeMoney = new BigDecimal(tbFreezing.getFreezeMoney());
	    	BigDecimal balance = new BigDecimal(tbAccount.getBalance());
			
			tbAccount.setFreeze(freeze.subtract(freezeMoney).doubleValue());
			tbAccount.setBalance(balance.subtract(freezeMoney).doubleValue());
			tbAccountMapper.updateByPrimaryKeySelective(tbAccount);
			
			//修改提现表
			withdrawals.setState(HglContants.WITHDRAWALS_STATE_PASS);
			withdrawals.setAuditId(loginUserId);
			withdrawals.setAuditDt(System.currentTimeMillis());
			count = tbWithdrawalsMapper.updateByPrimaryKeySelective(withdrawals);
			
			//插入资金流水表
			TbCashAccount cashAccount = new TbCashAccount();
			cashAccount.setCashOut(tbFreezing.getFreezeMoney());
			/*cashAccount.setBalance(balance.subtract(freezeMoney).doubleValue());*/
			cashAccount.setBalance(tbAccount.getBalance());
			cashAccount.setOperationDt(System.currentTimeMillis());
			cashAccount.setAccountId(withdrawals.getAccountId());
			cashAccount.setPlatform(HglContants.CASH_WITHDRAWALS);
			cashAccount.setOrderSerialNo("提现审核记录");
			tbCashAccountMapper.insertSelective(cashAccount);
			
		}
		if(count >0){
			map.put("errcode", 0);
			map.put("msg", "审核通过");
			/*b.substring(b.length()-4,b.length());*/
			String bankAccount = withdrawals.getBankAccount();
			SmsSend.sendCashPass(
					tbWebUserMapper.selectByPrimaryKey(withdrawals.getUserId()).getMobile(),
					withdrawals.getCardHolder(),
					tbFreezing.getFreezeMoney(),
					bankAccount.substring(bankAccount.length()-4,bankAccount.length()));
		}else{
			map.put("errcode", 0);
			map.put("msg", "审核失败");
		}
		return map;
	}
	@Override
	public Map<String, Object> refuseAutomaticShow(Integer id, Integer loginUserId) {
		Map<String, Object> map = new HashMap<String, Object>();
		int count =0;
		TbWithdrawals withdrawals = tbWithdrawalsMapper.selectByPrimaryKey(id);
		if(withdrawals!=null){
			//修改冻结记录表
			TbFreezing tbFreezing = tbFreezingMapper.selectByPrimaryKey(withdrawals.getFreezing());
			tbFreezing.setFinish(HglContants.FREEZING_FINISH_PASS);
			tbFreezingMapper.updateByPrimaryKeySelective(tbFreezing);
			//修改用户资金表
			TbAccount tbAccount = tbAccountMapper.selectByPrimaryKey(withdrawals.getAccountId());
			BigDecimal freeze = new BigDecimal(tbAccount.getFreeze());
	    	BigDecimal freezeMoney = new BigDecimal(tbFreezing.getFreezeMoney());
			
			tbAccount.setFreeze(freeze.subtract(freezeMoney).doubleValue());
			tbAccountMapper.updateByPrimaryKeySelective(tbAccount);
			
			//修改提现表
			withdrawals.setState(HglContants.WITHDRAWALS_STATE_REFUSE);
			withdrawals.setAuditId(loginUserId);
			withdrawals.setAuditDt(System.currentTimeMillis());
			count = tbWithdrawalsMapper.updateByPrimaryKeySelective(withdrawals);
			
			//插入解冻明细
			TbFreezing f = new TbFreezing();
			f.setOrderId(0);
			f.setAccountId(tbAccount.getId());
			f.setOrderSerialNo("");
			f.setFreezeMoney(tbAccount.getFreeze());
			f.setUnfreezeMoney(tbFreezing.getFreezeMoney());
			f.setBalance(tbAccount.getBalance());
			f.setFreeze(0.0);
			f.setOpearDt(System.currentTimeMillis());
			f.setFinish(1);
			tbFreezingMapper.insert(f);
			SmsSend.sendCashRefuse(tbWebUserMapper.selectByPrimaryKey(withdrawals.getUserId()).getMobile(),
										withdrawals.getCardHolder(),
											tbFreezing.getFreezeMoney());
		}
		return map;
	}
}