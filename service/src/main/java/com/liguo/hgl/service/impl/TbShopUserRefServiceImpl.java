package com.liguo.hgl.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liguo.hgl.proxydao.dao.TbShopUserRefMapper;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbShopUserRef;
import com.liguo.hgl.service.TbShopUserRefService;

@Service
@Scope(value="prototype")
public class TbShopUserRefServiceImpl implements TbShopUserRefService {
    @Autowired
    private TbShopUserRefMapper tbShopUserRefMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbShopUserRefServiceImpl.class);

    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbShopUserRefMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TbShopUserRef selectByPrimaryKey(TbShopUserRef key) throws RuntimeException {
        try {
            return this.tbShopUserRefMapper.selectByPrimaryKey(key);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<TbShopUserRef> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbShopUserRefMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public int deleteByPrimaryKey(TbShopUserRef key) throws RuntimeException {
        try {
            return this.tbShopUserRefMapper.deleteByPrimaryKey(key);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKeySelective(TbShopUserRef record) throws RuntimeException {
        try {
            return this.tbShopUserRefMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int updateByPrimaryKey(TbShopUserRef record) throws RuntimeException {
        try {
            return this.tbShopUserRefMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int insertSelective(TbShopUserRef record) throws RuntimeException {
        try {
            return this.tbShopUserRefMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

	@Override
	public List<Map<String, Object>> findNotUserList(TbShopUserRef record)
			throws RuntimeException {
		return this.tbShopUserRefMapper.findNotUserList(record);
	}
    @Override
    public List<Integer> selectShopListByUserID(Integer userId) throws RuntimeException {
        try {
            return this.tbShopUserRefMapper.selectShopListByUser(userId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}