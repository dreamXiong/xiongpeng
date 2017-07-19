package com.liguo.hgl.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liguo.hgl.proxydao.dao.TbWarehouseUserMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbWarehouseUser;
import com.liguo.hgl.service.TbWarehouseUserService;

@Service
@Scope(value="prototype")
public class TbWarehouseUserServiceImpl implements TbWarehouseUserService {
    @Autowired
    private TbWarehouseUserMapper tbWarehouseUserMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbWarehouseUserServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbWarehouseUserMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbWarehouseUser selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbWarehouseUserMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbWarehouseUser> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbWarehouseUserMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbWarehouseUserMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbWarehouseUser record) throws RuntimeException {
        try {
            return this.tbWarehouseUserMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbWarehouseUser record) throws RuntimeException {
        try {
            return this.tbWarehouseUserMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbWarehouseUser record) throws RuntimeException {
        try {
            return this.tbWarehouseUserMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Override
	public List<Integer> selectOrderByWarehouseIds(Integer userId) {
		Criteria example = new Criteria();
		example.put("userId", userId);
		List<TbWarehouseUser> wList = this.selectByObject(example);
		List<Integer> wIds = new ArrayList<Integer>();
		for(TbWarehouseUser w :wList){
			wIds.add(w.getWarehouseId());
		}
		return wIds;
	}
}