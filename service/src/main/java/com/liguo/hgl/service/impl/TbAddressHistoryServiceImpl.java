package com.liguo.hgl.service.impl;

import com.liguo.hgl.proxydao.dao.TbAddressHistoryMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbAddressHistory;
import com.liguo.hgl.service.TbAddressHistoryService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value="prototype")
public class TbAddressHistoryServiceImpl implements TbAddressHistoryService {
    @Autowired
    private TbAddressHistoryMapper tbAddressHistoryMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbAddressHistoryServiceImpl.class);

    /**
     * 根据条件查询出count数
     */
    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbAddressHistoryMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据ID查询出地址对象
     */
    public TbAddressHistory selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbAddressHistoryMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据条件查询出地址集合
     */
    public List<TbAddressHistory> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbAddressHistoryMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据ID删除
     */
    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbAddressHistoryMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据地址对象选择更新
     */
    public int updateByPrimaryKeySelective(TbAddressHistory record) throws RuntimeException {
        try {
            return this.tbAddressHistoryMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据地址对象更新
     */
    public int updateByPrimaryKey(TbAddressHistory record) throws RuntimeException {
        try {
            return this.tbAddressHistoryMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 插入
     */
    public int insertSelective(TbAddressHistory record) throws RuntimeException {
        try {
            return this.tbAddressHistoryMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}