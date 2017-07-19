package com.liguo.hgl.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liguo.hgl.proxydao.dao.TbIntegralDetailedMapper;
import com.liguo.hgl.proxydao.dao.TbIntegralMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbIntegral;
import com.liguo.hgl.proxydao.model.TbIntegralDetailed;
import com.liguo.hgl.service.TbIntegralService;

@Service
@Scope(value="prototype")
public class TbIntegralServiceImpl implements TbIntegralService {
    @Autowired
    private TbIntegralMapper tbIntegralMapper;
    
    @Autowired
    private TbIntegralDetailedMapper tbIntegralDetailedMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbIntegralServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbIntegralMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbIntegral selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbIntegralMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbIntegral> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbIntegralMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbIntegralMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbIntegral record) throws RuntimeException {
        try {
            return this.tbIntegralMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbIntegral record) throws RuntimeException {
        try {
            return this.tbIntegralMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbIntegral record) throws RuntimeException {
        try {
            return this.tbIntegralMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public int insert(TbIntegral record) throws RuntimeException {
        try {
            return this.tbIntegralMapper.insert(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

	@Override
	public void addIntegral(Integer userId,Integer integral,Integer orderId,String orderSerialNo,Integer type) {
		try {
			Criteria e = new Criteria();
			e.put("userId", userId);
			List<TbIntegral> tbIntegralList = tbIntegralMapper.selectByObject(e);
			if (tbIntegralList.size() > 0) {
				TbIntegral tbIntegral = tbIntegralList.get(0);
				tbIntegral.setIntegral(tbIntegral.getIntegral()	+ integral);
				tbIntegralMapper.updateByPrimaryKeySelective(tbIntegral);
				TbIntegralDetailed tbIntegralDetailed = new TbIntegralDetailed();
				tbIntegralDetailed.setIntegralId(tbIntegral.getId());
				tbIntegralDetailed.setOpearDt(System.currentTimeMillis());
				tbIntegralDetailed.setOrderId(orderId);
				tbIntegralDetailed.setOrderSerialNo(orderSerialNo);
				tbIntegralDetailed.setIntegral(integral);
				tbIntegralDetailed.setVersion(0);
				tbIntegralDetailed.setType(type);
				tbIntegralDetailedMapper.insert(tbIntegralDetailed);
			}
		 } catch (Exception e) {
	         throw new RuntimeException(e);
	     }
	}
	
	@Override
	public TbIntegral selectByUserIdObject(Criteria example) throws RuntimeException {
		try {
            return this.tbIntegralMapper.selectByUserIdObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}
}