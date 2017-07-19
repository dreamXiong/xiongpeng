package com.liguo.hgl.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liguo.hgl.proxydao.dao.TbProvinceInfoMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbProvinceInfo;
import com.liguo.hgl.service.TbProvinceInfoService;

@Service
@Scope(value="prototype")
public class TbProvinceInfoServiceImpl implements TbProvinceInfoService {
    @Autowired
    private TbProvinceInfoMapper tbProvinceInfoMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbProvinceInfoServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbProvinceInfoMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbProvinceInfo selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbProvinceInfoMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbProvinceInfo> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbProvinceInfoMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

  

    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbProvinceInfoMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbProvinceInfo record) throws RuntimeException {
        try {
            return this.tbProvinceInfoMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbProvinceInfo record) throws RuntimeException {
        try {
            return this.tbProvinceInfoMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbProvinceInfo record) throws RuntimeException {
        try {
            return this.tbProvinceInfoMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}