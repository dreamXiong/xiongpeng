package com.liguo.hgl.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liguo.hgl.proxydao.dao.TbPayWaterMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbPayWater;
import com.liguo.hgl.service.TbPayWaterService;

@Service
@Scope(value="prototype")
public class TbPayWaterServiceImpl implements TbPayWaterService {
    @Autowired
    private TbPayWaterMapper tbPayWaterMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbPayWaterServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbPayWaterMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbPayWater selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbPayWaterMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbPayWater> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbPayWaterMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbPayWaterMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbPayWater record) throws RuntimeException {
        try {
            return this.tbPayWaterMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbPayWater record) throws RuntimeException {
        try {
            return this.tbPayWaterMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbPayWater record) throws RuntimeException {
        try {
            return this.tbPayWaterMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}