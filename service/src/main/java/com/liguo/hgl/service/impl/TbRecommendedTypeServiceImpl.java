package com.liguo.hgl.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liguo.hgl.proxydao.dao.TbRecommendedTypeMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbRecommendedType;
import com.liguo.hgl.service.TbRecommendedTypeService;

@Service
@Scope(value="prototype")
public class TbRecommendedTypeServiceImpl implements TbRecommendedTypeService {
    @Autowired
    private TbRecommendedTypeMapper tbRecommendedTypeMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbRecommendedTypeServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbRecommendedTypeMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbRecommendedType selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbRecommendedTypeMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbRecommendedType> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbRecommendedTypeMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

   

    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbRecommendedTypeMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbRecommendedType record) throws RuntimeException {
        try {
            return this.tbRecommendedTypeMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbRecommendedType record) throws RuntimeException {
        try {
            return this.tbRecommendedTypeMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbRecommendedType record) throws RuntimeException {
        try {
            return this.tbRecommendedTypeMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}