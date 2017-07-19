package com.liguo.hgl.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liguo.hgl.proxydao.dao.TbPayContextParamMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbPayContextParam;
import com.liguo.hgl.service.TbPayContextParamService;

@Service
@Scope(value="prototype")
public class TbPayContextParamServiceImpl implements TbPayContextParamService {
    @Autowired
    private TbPayContextParamMapper tbPayContextParamMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbPayContextParamServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbPayContextParamMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbPayContextParam selectByPrimaryKey(String orderGroubNo) throws RuntimeException {
        try {
            return this.tbPayContextParamMapper.selectByPrimaryKey(orderGroubNo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbPayContextParam> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbPayContextParamMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public int deleteByPrimaryKey(String orderGroubNo) throws RuntimeException {
        try {
            return this.tbPayContextParamMapper.deleteByPrimaryKey(orderGroubNo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbPayContextParam record) throws RuntimeException {
        try {
            return this.tbPayContextParamMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbPayContextParam record) throws RuntimeException {
        try {
            return this.tbPayContextParamMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbPayContextParam record) throws RuntimeException {
        try {
            return this.tbPayContextParamMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}