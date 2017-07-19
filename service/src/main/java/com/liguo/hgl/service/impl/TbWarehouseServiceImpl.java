package com.liguo.hgl.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liguo.hgl.proxydao.dao.TbWarehouseMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbWarehouse;
import com.liguo.hgl.proxydao.model.WarehouseDto;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbWarehouseService;

@Service
@Scope(value="prototype")
public class TbWarehouseServiceImpl implements TbWarehouseService {
    @Autowired
    private TbWarehouseMapper tbWarehouseMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbWarehouseServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbWarehouseMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbWarehouse selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbWarehouseMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

  public List<TbWarehouse> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbWarehouseMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<WarehouseDto> selectByObject(Criteria example,PageDto page) throws RuntimeException {
        try {
            return this.tbWarehouseMapper.selectByObject(example,page);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
 
    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbWarehouseMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbWarehouse record) throws RuntimeException {
        try {
            return this.tbWarehouseMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbWarehouse record) throws RuntimeException {
        try {
            return this.tbWarehouseMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbWarehouse record) throws RuntimeException {
        try {
            return this.tbWarehouseMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}