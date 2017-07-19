package com.liguo.hgl.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liguo.hgl.proxydao.dao.TbAccountBankMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbAccountBank;
import com.liguo.hgl.service.TbAccountBankService;

@Service
@Scope(value="prototype")
public class TbAccountBankServiceImpl implements TbAccountBankService {
    @Autowired
    private TbAccountBankMapper tbAccountBankMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbAccountBankServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbAccountBankMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbAccountBank selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbAccountBankMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbAccountBank> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbAccountBankMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbAccountBankMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbAccountBank record) throws RuntimeException {
        try {
            return this.tbAccountBankMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbAccountBank record) throws RuntimeException {
        try {
            return this.tbAccountBankMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbAccountBank record) throws RuntimeException {
        try {
            return this.tbAccountBankMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}