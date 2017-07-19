package com.liguo.hgl.service.impl;

import com.liguo.hgl.proxydao.dao.TbCouponInfoMapper;
import com.liguo.hgl.proxydao.dto.CouponInfoDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbCouponInfo;
import com.liguo.hgl.proxydao.page.PageDto;
import com.liguo.hgl.service.TbCouponInfoService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value="prototype")
public class TbCouponInfoServiceImpl implements TbCouponInfoService {
    @Autowired
    private TbCouponInfoMapper tbCouponInfoMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbCouponInfoServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbCouponInfoMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbCouponInfo selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbCouponInfoMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbCouponInfo> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbCouponInfoMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbCouponInfoMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbCouponInfo record) throws RuntimeException {
        try {
            return this.tbCouponInfoMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbCouponInfo record) throws RuntimeException {
        try {
            return this.tbCouponInfoMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbCouponInfo record) throws RuntimeException {
        try {
            return this.tbCouponInfoMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

	@Override
	public List<CouponInfoDto> selectByObjectPage(Criteria criteria, PageDto page) {
		
		return this.tbCouponInfoMapper.selectByObjectPage(criteria, page);
	}

	@Override
	public List<CouponInfoDto> selectAmount(Criteria criteria) {
		
		return this.tbCouponInfoMapper.selectAmount(criteria);
	}
}