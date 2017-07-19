package com.liguo.hgl.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liguo.hgl.proxydao.dao.TbIntegralMallMapper;
import com.liguo.hgl.proxydao.dto.IntegralMallDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbIntegralMall;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbIntegralMallService;

@Service
@Scope(value="prototype")
public class TbIntegralMallServiceImpl implements TbIntegralMallService {
    @Autowired
    private TbIntegralMallMapper tbIntegralMallMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbIntegralMallServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbIntegralMallMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public IntegralMallDto selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbIntegralMallMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbIntegralMall> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbIntegralMallMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbIntegralMallMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbIntegralMall record) throws RuntimeException {
        try {
            return this.tbIntegralMallMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbIntegralMall record) throws RuntimeException {
        try {
            return this.tbIntegralMallMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbIntegralMall record) throws RuntimeException {
        try {
            return this.tbIntegralMallMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

	@Override
	public List<IntegralMallDto> selectByObject(Criteria example, PageDto pgo)
			throws RuntimeException {
		 try {
            return this.tbIntegralMallMapper.selectByObject(example,pgo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}

	@Override
	public int insert(TbIntegralMall record) {
		 try {
            return this.tbIntegralMallMapper.insert(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}
}