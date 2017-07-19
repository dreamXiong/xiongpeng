package com.liguo.hgl.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liguo.hgl.proxydao.dao.TbDealersWeixinConfigMapper;
import com.liguo.hgl.proxydao.dto.DealersWeixinConfigDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbDealersWeixinConfig;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbDealersWeixinConfigService;

@Service
@Scope(value="prototype")
public class TbDealersWeixinConfigServiceImpl implements TbDealersWeixinConfigService {
    @Autowired
    private TbDealersWeixinConfigMapper tbDealersWeixinConfigMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbDealersWeixinConfigServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbDealersWeixinConfigMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbDealersWeixinConfig selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbDealersWeixinConfigMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<DealersWeixinConfigDto> selectByObject(Criteria example,PageDto pgo) throws RuntimeException {
        try {
            return this.tbDealersWeixinConfigMapper.selectByObject(example,pgo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbDealersWeixinConfigMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbDealersWeixinConfig record) throws RuntimeException {
        try {
            return this.tbDealersWeixinConfigMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbDealersWeixinConfig record) throws RuntimeException {
        try {
            return this.tbDealersWeixinConfigMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbDealersWeixinConfig record) throws RuntimeException {
        try {
            return this.tbDealersWeixinConfigMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

	@Override
	public int insert(TbDealersWeixinConfig record) {
		try {
            return this.tbDealersWeixinConfigMapper.insert(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}

	@Override
	public TbDealersWeixinConfig selectByShopId(Criteria example) {
		try {
            return this.tbDealersWeixinConfigMapper.selectByShopId(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}
}