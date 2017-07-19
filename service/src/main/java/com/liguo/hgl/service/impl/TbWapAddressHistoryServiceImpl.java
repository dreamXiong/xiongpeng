package com.liguo.hgl.service.impl;

import com.liguo.hgl.proxydao.dao.TbWapAddressHistoryMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbWapAddressHistory;
import com.liguo.hgl.service.TbWapAddressHistoryService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value="prototype")
public class TbWapAddressHistoryServiceImpl implements TbWapAddressHistoryService {
    @Autowired
    private TbWapAddressHistoryMapper tbWapAddressHistoryMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbWapAddressHistoryServiceImpl.class);

    /**
     * 根据条件获取总行数
     */
    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbWapAddressHistoryMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据主键查询记录
     */
    public TbWapAddressHistory selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbWapAddressHistoryMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据指定条件查询记录
     */
    public List<TbWapAddressHistory> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbWapAddressHistoryMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据主键删除记录
     */
    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbWapAddressHistoryMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据主键更新属性不为空的记录
     */
    public int updateByPrimaryKeySelective(TbWapAddressHistory record) throws RuntimeException {
        try {
            return this.tbWapAddressHistoryMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据主键更新记录
     */
    public int updateByPrimaryKey(TbWapAddressHistory record) throws RuntimeException {
        try {
            return this.tbWapAddressHistoryMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 保存属性不为空的记录
     */
    public int insertSelective(TbWapAddressHistory record) throws RuntimeException {
        try {
            return this.tbWapAddressHistoryMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}