package com.liguo.hgl.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liguo.hgl.proxydao.dao.TbStreetInfoMapper;
import com.liguo.hgl.proxydao.dto.AddressDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbCountryInfo;
import com.liguo.hgl.proxydao.model.TbStreetInfo;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbStreetInfoService;

@Service
@Scope(value="prototype")
public class TbStreetInfoServiceImpl implements TbStreetInfoService {
    @Autowired
    private TbStreetInfoMapper tbStreetInfoMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbStreetInfoServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbStreetInfoMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbStreetInfo selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbStreetInfoMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbStreetInfo> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbStreetInfoMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

	@Override
	public List<TbStreetInfo> getStreetInfos(Integer cid) {
		Criteria example = new Criteria();
		example.put("parentid", cid);
		List<TbStreetInfo> TbCountryInfos = tbStreetInfoMapper.selectByObject(example);
		return TbCountryInfos;
	}

	@Override
	public List<AddressDto> selectObjectByPage(Criteria criteria, PageDto page) {
		
		return this.tbStreetInfoMapper.selectObjectByPage(criteria, page);
	}

	@Override
	public int insert(TbStreetInfo record) {
		
		return this.tbStreetInfoMapper.insert(record);
	}

	@Override
	public int updateByPrimaryKeySelective(TbStreetInfo record) {
		
		return this.tbStreetInfoMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		
		return this.tbStreetInfoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<AddressDto> selectAddressInfo(Criteria example) {
		
		return this.tbStreetInfoMapper.selectAddressInfo(example);
	}


}