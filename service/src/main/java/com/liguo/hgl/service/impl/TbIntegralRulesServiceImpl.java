package com.liguo.hgl.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liguo.hgl.proxydao.dao.TbIntegralRulesMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbIntegralRules;
import com.liguo.hgl.service.TbIntegralRulesService;

@Service
@Scope(value="prototype")
public class TbIntegralRulesServiceImpl implements TbIntegralRulesService {
    @Autowired
    private TbIntegralRulesMapper tbIntegralRulesMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbIntegralRulesServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbIntegralRulesMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbIntegralRules selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbIntegralRulesMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbIntegralRules> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbIntegralRulesMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbIntegralRulesMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbIntegralRules record) throws RuntimeException {
        try {
            return this.tbIntegralRulesMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbIntegralRules record) throws RuntimeException {
        try {
            return this.tbIntegralRulesMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbIntegralRules record) throws RuntimeException {
        try {
            return this.tbIntegralRulesMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public int batchInsert(List<TbIntegralRules> record){
    	return this.tbIntegralRulesMapper.batchInsert(record);
    }
    
    @Override
    public int batchUpdate(List<TbIntegralRules> record){
    	return this.tbIntegralRulesMapper.batchUpdate(record);
    }
    @Override
    public List<TbIntegralRules> selectUsedRule(Integer shopId){
    	Criteria example = new Criteria();
    	example.put("shopId", shopId);
    	return this.tbIntegralRulesMapper.selectUsedRule(example);
    }
}