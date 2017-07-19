package com.liguo.hgl.service.impl;

import com.liguo.hgl.proxydao.dao.TbWapOrderTrackingServiceMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbWapOrderTrackingService;
import com.liguo.hgl.service.TbWapOrderTrackingServiceService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value="prototype")
public class TbWapOrderTrackingServiceServiceImpl implements TbWapOrderTrackingServiceService {
    @Autowired
    private TbWapOrderTrackingServiceMapper tbWapOrderTrackingServiceMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbWapOrderTrackingServiceServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbWapOrderTrackingServiceMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbWapOrderTrackingService selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbWapOrderTrackingServiceMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbWapOrderTrackingService> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbWapOrderTrackingServiceMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbWapOrderTrackingServiceMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbWapOrderTrackingService record) throws RuntimeException {
        try {
            return this.tbWapOrderTrackingServiceMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbWapOrderTrackingService record) throws RuntimeException {
        try {
            return this.tbWapOrderTrackingServiceMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbWapOrderTrackingService record) throws RuntimeException {
        try {
            return this.tbWapOrderTrackingServiceMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}