package com.liguo.hgl.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.liguo.hgl.proxydao.dao.TbOpenedRegionalMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbCityInfo;
import com.liguo.hgl.proxydao.model.TbOpenedRegional;
import com.liguo.hgl.service.TbOpenedRegionalService;

@Service
@Scope(value="prototype")
public class TbOpenedRegionalServiceImpl implements TbOpenedRegionalService {
    @Autowired
    private TbOpenedRegionalMapper tbOpenedRegionalMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbOpenedRegionalServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbOpenedRegionalMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbOpenedRegional selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbOpenedRegionalMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbOpenedRegional> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbOpenedRegionalMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbOpenedRegionalMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbOpenedRegional record) throws RuntimeException {
        try {
            Criteria criteria = new Criteria();
            criteria.put("proviceId", record.getProviceId());
            List<TbOpenedRegional>  tbOpenedRegionalList =tbOpenedRegionalMapper.selectByObject(criteria);
            if(CollectionUtils.isEmpty(tbOpenedRegionalList)){
                return this.tbOpenedRegionalMapper.insertSelective(record);
            }
            else {
                return this.tbOpenedRegionalMapper.updateByPrimaryKeySelective(record);
                
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbOpenedRegional record) throws RuntimeException {
        try {
            return this.tbOpenedRegionalMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbOpenedRegional record) throws RuntimeException {
        try {
            return this.tbOpenedRegionalMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<TbOpenedRegional> findOpenedRegByproviceId(Criteria example) throws RuntimeException {
        
        return this.tbOpenedRegionalMapper.findOpenedRegByproviceId(example);
    }

	@Override
	public TbOpenedRegional selectByNameOpenCity(Criteria example) {
		return this.tbOpenedRegionalMapper.selectByNameOpenCity(example);
	}
}