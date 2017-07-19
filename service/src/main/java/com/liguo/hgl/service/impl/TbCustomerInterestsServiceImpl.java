package com.liguo.hgl.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liguo.hgl.proxydao.dao.TbCustomerInterestsMapper;
import com.liguo.hgl.proxydao.dto.CustomerInterestsDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbCustomerInterests;
import com.liguo.hgl.service.TbCustomerInterestsService;

@Service
@Scope(value="prototype")
public class TbCustomerInterestsServiceImpl implements TbCustomerInterestsService {
    @Autowired
    private TbCustomerInterestsMapper tbCustomerInterestsMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbCustomerInterestsServiceImpl.class);

    /**
     * 根据条件获取总行数
     */
    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbCustomerInterestsMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据主键查询记录
     */
    public TbCustomerInterests selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbCustomerInterestsMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 根据指定条件查询记录
     */
    public List<TbCustomerInterests> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbCustomerInterestsMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据主键删除记录
     */
    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbCustomerInterestsMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据主键更新属性不为空的记录
     */
    public int updateByPrimaryKeySelective(TbCustomerInterests record) throws RuntimeException {
        try {
            return this.tbCustomerInterestsMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据主键更新记录
     */
    public int updateByPrimaryKey(TbCustomerInterests record) throws RuntimeException {
        try {
            return this.tbCustomerInterestsMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 保存属性不为空的记录
     */
    public int insertSelective(TbCustomerInterests record) throws RuntimeException {
        try {
            return this.tbCustomerInterestsMapper.insertSelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 调用存储过程
     * @param example
     * @return
     */
	@Override
	public int callCustomerInterests(Map<String,Integer> rowCountMap) throws RuntimeException {
		try {
            this.tbCustomerInterestsMapper.callCustomerInterests(rowCountMap);
            return rowCountMap.get("rowCount");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}

    /**
     * 查询出数据分析报表
     * @param example
     * @return
     * @throws RuntimeException
     */
	@Override
	public List<CustomerInterestsDto> selectByDataInfo(Criteria example) throws RuntimeException {
		try {
            return this.tbCustomerInterestsMapper.selectByDataInfo(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}

    /**
     * 查询出数据分析报表求和的数据
     * @param example
     * @return
     * @throws RuntimeException
     */
	@Override
	public TbCustomerInterests selectByDataInfoSum(Criteria example) throws RuntimeException {
		try {
            return this.tbCustomerInterestsMapper.selectByDataInfoSum(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}
}