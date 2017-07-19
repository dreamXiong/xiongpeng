package com.liguo.hgl.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.liguo.hgl.proxydao.dao.TbGoodsProfitMapper;
import com.liguo.hgl.proxydao.dto.GoodsProfitDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbGoodsProfit;
import com.liguo.hgl.service.TbGoodsProfitService;

@Service
@Scope(value="prototype")
public class TbGoodsProfitServiceImpl implements TbGoodsProfitService {
    @Autowired
    private TbGoodsProfitMapper tbGoodsProfitMapper;

    private static final Logger logger = LoggerFactory.getLogger(TbGoodsProfitServiceImpl.class);

    /**
     * 根据条件获取总行数
     */
    public int countByObject(Criteria example) throws RuntimeException {
        try {
            int count = this.tbGoodsProfitMapper.countByObject(example);
            logger.debug("count: {}", count);
            return count;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据主键查询记录
     */
    public TbGoodsProfit selectByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbGoodsProfitMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据指定条件查询记录
     */
    public List<TbGoodsProfit> selectByObject(Criteria example) throws RuntimeException {
        try {
            return this.tbGoodsProfitMapper.selectByObject(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据主键删除记录
     */
    public int deleteByPrimaryKey(Integer id) throws RuntimeException {
        try {
            return this.tbGoodsProfitMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据主键更新属性不为空的记录
     */
    public int updateByPrimaryKeySelective(TbGoodsProfit record) throws RuntimeException {
        try {
            return this.tbGoodsProfitMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据主键更新记录
     */
    public int updateByPrimaryKey(TbGoodsProfit record) throws RuntimeException {
        try {
            return this.tbGoodsProfitMapper.updateByPrimaryKey(record);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 保存属性不为空的记录
     */
    public int insertSelective(TbGoodsProfit record) throws RuntimeException {
        try {
            return this.tbGoodsProfitMapper.insertSelective(record);
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
	public int callGoodsProfit(Map<String,Integer> rowCountMap) throws RuntimeException {
		 try {
            this.tbGoodsProfitMapper.callGoodsProfit(rowCountMap);
            return rowCountMap.get("rowCount");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}

    /**
     * 查询出数据分析列表
     * @param example
     * @return
     * @throws RuntimeException
     */
	@Override
	public List<GoodsProfitDto> selectByDataInfo(Criteria example) throws RuntimeException {
		try {
            return tbGoodsProfitMapper.selectByDataInfo(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}
    
    /**
     * 查询出数据分析列表求和数据
     * @param example
     * @return
     * @throws RuntimeException
     */
	@Override
	public TbGoodsProfit selectByDataInfoSum(Criteria example) throws RuntimeException {
		try {
            return tbGoodsProfitMapper.selectByDataInfoSum(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}
	
    /**
     * 查询出数据分析利润变化数据
     * @param example
     * @return
     * @throws RuntimeException
     */
	@Override
	public List<GoodsProfitDto> selectByProfitsChange(Criteria example) throws RuntimeException {
		try {
            return tbGoodsProfitMapper.selectByProfitsChange(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}

    /**
     * 查询出货品利润的总和
     * @param example
     * @return
     */
	@Override
	public TbGoodsProfit selectByDataInfoProfitsRate(Criteria example) {
		try {
            return tbGoodsProfitMapper.selectByDataInfoProfitsRate(example);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}
}