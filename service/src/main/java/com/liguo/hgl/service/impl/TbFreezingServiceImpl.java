package com.liguo.hgl.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liguo.hgl.proxydao.dao.TbFreezingMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbFreezing;
import com.liguo.hgl.service.TbFreezingService;

@Service
@Scope(value="prototype")
public class TbFreezingServiceImpl implements TbFreezingService {
    @Autowired
    private TbFreezingMapper tbFreezingMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbFreezingServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbFreezingMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbFreezing selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbFreezingMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbFreezing> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbFreezingMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbFreezingMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbFreezing record) throws RuntimeException {
        try {
            return this.tbFreezingMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbFreezing record) throws RuntimeException {
        try {
            return this.tbFreezingMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbFreezing record) throws RuntimeException {
        try {
            return this.tbFreezingMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}