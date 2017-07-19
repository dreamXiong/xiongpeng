package com.liguo.hgl.service.impl;

import com.liguo.hgl.proxydao.dao.TbPayParamMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbPayParam;
import com.liguo.hgl.service.TbPayParamService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value="prototype")
public class TbPayParamServiceImpl implements TbPayParamService {
    @Autowired
    private TbPayParamMapper tbPayParamMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbPayParamServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbPayParamMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbPayParam selectByPrimaryKey(String orderGroubNo) throws RuntimeException {
        try {
            return this.tbPayParamMapper.selectByPrimaryKey(orderGroubNo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbPayParam> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbPayParamMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteByPrimaryKey(String orderGroubNo) throws RuntimeException {
        try {
            return this.tbPayParamMapper.deleteByPrimaryKey(orderGroubNo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbPayParam record) throws RuntimeException {
        try {
            return this.tbPayParamMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbPayParam record) throws RuntimeException {
        try {
            return this.tbPayParamMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbPayParam record) throws RuntimeException {
        try {
            return this.tbPayParamMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}