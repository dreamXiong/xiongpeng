package com.liguo.hgl.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liguo.hgl.common.HglContants;
import com.liguo.hgl.proxydao.dao.TbCityInfoMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbCityInfo;
import com.liguo.hgl.proxydao.model.TbMerchants;
import com.liguo.hgl.proxydao.model.TbOpenedRegional;
import com.liguo.hgl.service.TbCityInfoService;

@Service
@Scope(value="prototype")
public class TbCityInfoServiceImpl implements TbCityInfoService {
    @Autowired
    private TbCityInfoMapper tbCityInfoMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbCityInfoServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbCityInfoMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbCityInfo selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbCityInfoMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbCityInfo> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbCityInfoMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

	@Override
	public List<TbCityInfo> getCityInfos(Integer pid) {
		Criteria example = new Criteria();
		example.put("parentid", pid);
		List<TbCityInfo> tbCityInfos = tbCityInfoMapper.selectByObject(example);
		return tbCityInfos;
	}

	@Override
	public int updateByPrimaryKeySelective(TbCityInfo cityInfo) {
		
		return tbCityInfoMapper.updateByPrimaryKeySelective(cityInfo);
	}

    @Override
    public List<Map<String, Object>> findCityByProvice(Integer id) throws RuntimeException {
        
        return tbCityInfoMapper.findCityByProvice(id);
    }

    @Override
    public List<TbCityInfo> findBatchCityById(List<String> tbOpenedRegionalList) throws RuntimeException {
        
        return tbCityInfoMapper.findBatchCityById(tbOpenedRegionalList);
    }

}