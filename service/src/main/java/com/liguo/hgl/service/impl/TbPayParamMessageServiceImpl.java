package com.liguo.hgl.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liguo.hgl.proxydao.dao.TbPayParamMessageMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbPayParamMessage;
import com.liguo.hgl.service.TbPayParamMessageService;

@Service
@Scope(value="prototype")
public class TbPayParamMessageServiceImpl implements TbPayParamMessageService {
    @Autowired
    private TbPayParamMessageMapper tbPayParamMessageMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbPayParamMessageServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbPayParamMessageMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbPayParamMessage selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbPayParamMessageMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbPayParamMessage> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbPayParamMessageMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbPayParamMessageMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbPayParamMessage record) throws RuntimeException {
        try {
            return this.tbPayParamMessageMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbPayParamMessage record) throws RuntimeException {
        try {
            return this.tbPayParamMessageMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbPayParamMessage record) throws RuntimeException {
        try {
            return this.tbPayParamMessageMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}