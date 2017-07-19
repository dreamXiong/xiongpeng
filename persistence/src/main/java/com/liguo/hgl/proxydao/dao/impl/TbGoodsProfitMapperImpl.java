package com.liguo.hgl.proxydao.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbGoodsProfitMapper;
import com.liguo.hgl.proxydao.dto.GoodsProfitDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbGoodsProfit;

@Repository
public class TbGoodsProfitMapperImpl extends BaseMapperImpl<TbGoodsProfit> implements TbGoodsProfitMapper {

    public TbGoodsProfitMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbGoodsProfitMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbGoodsProfit record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbGoodsProfitMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbGoodsProfit record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbGoodsProfitMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbGoodsProfitMapper.deleteByObject", parameter);
    }

    public int insert(TbGoodsProfit record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbGoodsProfitMapper.insert", record);
    }

    public int insertSelective(TbGoodsProfit record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbGoodsProfitMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbGoodsProfitMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbGoodsProfit> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbGoodsProfitMapper.selectByObject", parameter);
    }

    public TbGoodsProfit selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbGoodsProfitMapper.selectByPrimaryKey", id);
        return obj != null ? (TbGoodsProfit)obj : null;
    }

	@Override
	public void callGoodsProfit(Map<String,Integer> rowCountMap) {
		this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbGoodsProfitMapper.callGoodsProfit", rowCountMap);
	}

	@Override
	public List<GoodsProfitDto> selectByDataInfo(Criteria example) {
		return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbGoodsProfitMapper.selectByDataInfo", example);
	}

	@Override
	public TbGoodsProfit selectByDataInfoSum(Criteria example) {
		Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbGoodsProfitMapper.selectByDataInfoSum", example);
		return obj != null ? (TbGoodsProfit)obj : null;
	}
	
	@Override
	public List<GoodsProfitDto> selectByProfitsChange(Criteria example) {
		return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbGoodsProfitMapper.selectByProfitsChange", example);
	}

	@Override
	public TbGoodsProfit selectByDataInfoProfitsRate(Criteria example) {
		Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbGoodsProfitMapper.selectByDataInfoProfitsRate", example);
		return obj != null ? (TbGoodsProfit)obj : null;
	}
}