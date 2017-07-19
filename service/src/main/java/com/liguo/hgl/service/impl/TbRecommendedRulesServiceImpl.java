package com.liguo.hgl.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liguo.hgl.proxydao.dao.TbRecommendedRulesMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbRecommendedRules;
import com.liguo.hgl.service.TbRecommendedRulesService;

@Service
@Scope(value="prototype")
public class TbRecommendedRulesServiceImpl implements TbRecommendedRulesService {
    @Autowired
    private TbRecommendedRulesMapper tbRecommendedRulesMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbRecommendedRulesServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbRecommendedRulesMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbRecommendedRules selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbRecommendedRulesMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbRecommendedRules> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbRecommendedRulesMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbRecommendedRulesMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbRecommendedRules record) throws RuntimeException {
        try {
            return this.tbRecommendedRulesMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbRecommendedRules record) throws RuntimeException {
        try {
            return this.tbRecommendedRulesMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbRecommendedRules record) throws RuntimeException {
        try {
            return this.tbRecommendedRulesMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}