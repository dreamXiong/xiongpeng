package com.liguo.hgl.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liguo.hgl.proxydao.dao.TbShopLevelMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbShopLevel;
import com.liguo.hgl.service.TbShopLevelService;

@Service
@Scope(value="prototype")
public class TbShopLevelServiceImpl implements TbShopLevelService {
    @Autowired
    private TbShopLevelMapper tbShopLevelMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbShopLevelServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbShopLevelMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbShopLevel selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbShopLevelMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbShopLevel> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbShopLevelMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbShopLevelMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbShopLevel record) throws RuntimeException {
        try {
            return this.tbShopLevelMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbShopLevel record) throws RuntimeException {
        try {
            return this.tbShopLevelMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbShopLevel record) throws RuntimeException {
        try {
            return this.tbShopLevelMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}