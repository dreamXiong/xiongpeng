package com.liguo.hgl.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liguo.hgl.dto.MyOrderForm;
import com.liguo.hgl.proxydao.dao.TbCashAccountMapper;
import com.liguo.hgl.proxydao.dto.CashAccountDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbCashAccount;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbCashAccountService;

@Service
@Scope(value="prototype")
public class TbCashAccountServiceImpl implements TbCashAccountService {
    @Autowired
    private TbCashAccountMapper tbCashAccountMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbCashAccountServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbCashAccountMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbCashAccount selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbCashAccountMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbCashAccount> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbCashAccountMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<CashAccountDto> selectCashAccount(Criteria example,PageDto page,Integer indexPage) throws RuntimeException {
        try {
        	example.put("pageFrom", page.getPageSize()*indexPage);
       	    example.put("pageSize", page.getPageSize());
            return this.tbCashAccountMapper.selectCashAccount(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbCashAccountMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbCashAccount record) throws RuntimeException {
        try {
            return this.tbCashAccountMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbCashAccount record) throws RuntimeException {
        try {
            return this.tbCashAccountMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbCashAccount record) throws RuntimeException {
        try {
            return this.tbCashAccountMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

	@Override
	public List<TbCashAccount> selectCashAccountList(MyOrderForm myOrderForm,PageDto page) {
		Criteria example = new Criteria();
		example.put("startTime", myOrderForm.getStartTime());
		example.put("endTime", myOrderForm.getEndTime());
		example.put("accountId", myOrderForm.getUserId());
		example.put("searchText", myOrderForm.getSearchText());
		return this.tbCashAccountMapper.selectCashAccountList(example,page);
	}
	
	@Override
    public int insertBatchCashAccount(List<TbCashAccount> List){
    	 return tbCashAccountMapper.insertBatchCashAccount(List);
    }
}