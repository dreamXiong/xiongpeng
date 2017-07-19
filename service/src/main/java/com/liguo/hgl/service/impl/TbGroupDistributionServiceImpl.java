package com.liguo.hgl.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liguo.hgl.proxydao.dao.TbGroupDistributionMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbGroupDistribution;
import com.liguo.hgl.service.TbGroupDistributionService;

@Service
@Scope(value="prototype")
public class TbGroupDistributionServiceImpl implements TbGroupDistributionService {
    @Autowired
    private TbGroupDistributionMapper tbGroupDistributionMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbGroupDistributionServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbGroupDistributionMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbGroupDistribution selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbGroupDistributionMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbGroupDistribution> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbGroupDistributionMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbGroupDistributionMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbGroupDistribution record) throws RuntimeException {
        try {
            return this.tbGroupDistributionMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbGroupDistribution record) throws RuntimeException {
        try {
            return this.tbGroupDistributionMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbGroupDistribution record) throws RuntimeException {
        try {
            return this.tbGroupDistributionMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

	@Override
	public List<TbGroupDistribution> selectByShopIds(Criteria example) {
		try {
            return this.tbGroupDistributionMapper.selectByShopIds(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}
}