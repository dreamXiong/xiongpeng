package com.liguo.hgl.proxydao.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.liguo.hgl.proxydao.base.BaseMapperImpl;
import com.liguo.hgl.proxydao.dao.TbCustomerInterestsMapper;
import com.liguo.hgl.proxydao.dto.CustomerInterestsDto;
import com.liguo.hgl.proxydao.model.Criteria;
import com.liguo.hgl.proxydao.model.TbCustomerInterests;

@Repository
public class TbCustomerInterestsMapperImpl extends BaseMapperImpl<TbCustomerInterests> implements TbCustomerInterestsMapper {

    public TbCustomerInterestsMapperImpl() {
        super();
    }

    public int deleteByPrimaryKey(Integer id) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbCustomerInterestsMapper.deleteByPrimaryKey", id);
    }

    public int updateByPrimaryKeySelective(TbCustomerInterests record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbCustomerInterestsMapper.updateByPrimaryKeySelective", record);
    }

    public int updateByPrimaryKey(TbCustomerInterests record) {
        return this.sqlSessionTemplate.update("com.liguo.hgl.proxydao.dao.TbCustomerInterestsMapper.updateByPrimaryKey", record);
    }

    public int deleteByObject(Criteria parameter) {
        return this.sqlSessionTemplate.delete("com.liguo.hgl.proxydao.dao.TbCustomerInterestsMapper.deleteByObject", parameter);
    }

    public int insert(TbCustomerInterests record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbCustomerInterestsMapper.insert", record);
    }

    public int insertSelective(TbCustomerInterests record) {
        return this.sqlSessionTemplate.insert("com.liguo.hgl.proxydao.dao.TbCustomerInterestsMapper.insertSelective", record);
    }

    public int countByObject(Criteria parameter) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbCustomerInterestsMapper.countByObject", parameter);
        return obj != null ? Integer.parseInt(String.valueOf(obj)) : 0;
    }

    @Override
    public List<TbCustomerInterests> selectByObject(Criteria parameter) {
        return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbCustomerInterestsMapper.selectByObject", parameter);
    }

    public TbCustomerInterests selectByPrimaryKey(Integer id) {
        Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbCustomerInterestsMapper.selectByPrimaryKey", id);
        return obj != null ? (TbCustomerInterests)obj : null;
    }

	@Override
	public void callCustomerInterests(Map<String,Integer> rowCountMap) {
		this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbCustomerInterestsMapper.callCustomerInterests", rowCountMap);
	}

	@Override
	public List<CustomerInterestsDto> selectByDataInfo(Criteria example) {
		return this.sqlSessionTemplate.selectList("com.liguo.hgl.proxydao.dao.TbCustomerInterestsMapper.selectByDataInfo", example);
	}

	@Override
	public TbCustomerInterests selectByDataInfoSum(Criteria example) {
		Object obj = this.sqlSessionTemplate.selectOne("com.liguo.hgl.proxydao.dao.TbCustomerInterestsMapper.selectByDataInfoSum", example);
		return obj != null ? (TbCustomerInterests)obj : null;
	}
}