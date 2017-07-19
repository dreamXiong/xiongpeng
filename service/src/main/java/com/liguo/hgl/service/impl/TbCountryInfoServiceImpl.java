package com.liguo.hgl.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liguo.hgl.proxydao.dao.TbCountryInfoMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbCityInfo;
import com.liguo.hgl.proxydao.model.TbCountryInfo;
import com.liguo.hgl.service.TbCountryInfoService;

@Service
@Scope(value="prototype")
public class TbCountryInfoServiceImpl implements TbCountryInfoService {
    @Autowired
    private TbCountryInfoMapper tbCountryInfoMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbCountryInfoServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbCountryInfoMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbCountryInfo selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbCountryInfoMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbCountryInfo> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbCountryInfoMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

	@Override
	public List<TbCountryInfo> getCountrys(Integer cid) {
		Criteria example = new Criteria();
		example.put("parentid", cid);
		List<TbCountryInfo> TbCountryInfos = tbCountryInfoMapper.selectByObject(example);
		return TbCountryInfos;
	}

	@Override
	public int insert(TbCountryInfo record) {
		
		return this.tbCountryInfoMapper.insert(record);
	}

	@Override
	public int updateByPrimaryKeySelective(TbCountryInfo record) {
				
		return this.tbCountryInfoMapper.updateByPrimaryKeySelective(record);
	}

    @Override
    public List<TbCityInfo> findBatchCountryById(Criteria example) throws RuntimeException {
        
        return this.tbCountryInfoMapper.findBatchCountryById(example);
    }

	

}